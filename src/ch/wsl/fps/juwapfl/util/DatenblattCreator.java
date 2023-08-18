/*******************************************************************************
 * Copyright 2023 Stefan Holm
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ch.wsl.fps.juwapfl.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.JuWaPflExceptionHandler;

/**
 * 
 * @author Stefan Holm
 *
 */
public class DatenblattCreator {
	
	private static final String DEFAULT_XSLT_FILE_PATH = "data/kalkulation2fo-templates.xsl"; //$NON-NLS-1$
	private static final Object classLevelLock = new Object();
	private static volatile boolean isAlreadyPreinitialized = false;
	private static final String CHARSET = "UTF-8"; //$NON-NLS-1$
	
	public static void preInitializeInSeparateThread() {
		synchronized(classLevelLock) {
			if (isAlreadyPreinitialized == false) {
				new Thread(() -> {
					DatenblattCreator instance = new DatenblattCreator();
					String emptyXMLString = "<?xml version=\"1.0\" encoding=\"" + CHARSET + "\" standalone=\"yes\"?><kalkulation></kalkulation>"; //$NON-NLS-1$ //$NON-NLS-2$
					instance.generatePdf(emptyXMLString, null, DEFAULT_XSLT_FILE_PATH);
					isAlreadyPreinitialized = true;
				}).start();
			}
		}
	}
	
	public void create(String xmlString, File pdfFile, String xsltFilePath) {
		//Setze XML-String zusammen
		String finalXMLString = 
				"<?xml version=\"1.0\" encoding=\"" + CHARSET + "\" standalone=\"yes\"?>\n" + //$NON-NLS-1$ //$NON-NLS-2$
				"<kalkulation>\n" + //$NON-NLS-1$
				xmlString +
				"</kalkulation>"; //$NON-NLS-1$
		
		//Generiere das Pdf
		generatePdf(finalXMLString, pdfFile, xsltFilePath);
	}
	
	
	private void generatePdf(String xmlString, File pdfFile, String xsltFilePath) {
		synchronized(classLevelLock) {
			Logger fopLogger = Logger.getLogger("org.apache.fop"); //$NON-NLS-1$
			fopLogger.setLevel(Level.OFF);

			try {
				// Step 1: Construct a FopFactory
				// (reuse if you plan to render multiple documents!)
				FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI()); //$NON-NLS-1$

				// Step 2: Set up output stream.
				// Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
				OutputStream out = getOutputStream(pdfFile);

				try {
					// Step 3: Construct fop with desired output format
					Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

					// Step 4: Setup XSLT
					TransformerFactory factory = TransformerFactory.newInstance();
					factory.setURIResolver((href, base) -> {
//						System.out.println("resolve: " + href + " " + base);
						
						//try to open from jar
						InputStream is = this.getClass().getClassLoader().getResourceAsStream(href);	

						//otherwise, try to open from file system
						if (is == null) {
							try {
								is = new FileInputStream(href);
//								System.out.println("opened xsl-file from file system.");
							} catch (FileNotFoundException e) {
								JuWaPflExceptionHandler.handle(e);
							}
						}
//						else {
//							System.out.println("opened xsl-file from jar.");
//						}
					    
					    return new StreamSource(is);
					});
					Transformer transformer = factory.newTransformer(new StreamSource(openFile(xsltFilePath)));
			        transformer.setOutputProperty("encoding", CHARSET); //$NON-NLS-1$

					// Step 5: Setup input for XSLT transformation
					Source src = new StreamSource(new ByteArrayInputStream(xmlString.getBytes(CHARSET))); 

					// Resulting SAX events (the generated FO) must be piped through to FOP
					Result res = new SAXResult(fop.getDefaultHandler());

					// Step 6: Start XSLT transformation and FOP processing
					transformer.transform(src, res);
					
					if (pdfFile != null) {
						System.out.println("Datenblatt erfolgreich erstellt."); //$NON-NLS-1$
					}
				} catch (FOPException e) {
					JuWaPflExceptionHandler.handle(e);

				} catch (TransformerException e) {
					JuWaPflExceptionHandler.handle(e);

				} catch (TransformerFactoryConfigurationError e) {
					JuWaPflExceptionHandler.handle(e);

				} finally {
					//Clean-up
					out.close();
				}
			}
			catch (FileNotFoundException e) {
				String msg = Messages.getString("DatenblattCreator.FehlerBeimErstellenDerDatei") + "\n" + e.getLocalizedMessage(); //$NON-NLS-1$ //$NON-NLS-2$
				JOptionPane.showMessageDialog(null, msg, Messages.getString("Common.TitelErrorMessage"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			}
			catch (IOException e) {
				JuWaPflExceptionHandler.handle(e);
			}
		}
	}
	
	
	/**
	 * @param pdfFile if null, a NullOutputStream is returned.
	 * @throws FileNotFoundException 
	 */
	private OutputStream getOutputStream(File pdfFile) throws FileNotFoundException {
		if (pdfFile == null) {
			return new NullOutputStream();
		}
		else {
			// Set up output stream.
			// Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
			OutputStream out = new BufferedOutputStream(new FileOutputStream( pdfFile ));
			return out;
		}
	}
	
	
	private class NullOutputStream extends OutputStream {
		@Override
		public void write(int b) throws IOException {
			//do nothing
		}
	}
	
	
	private BufferedReader openFile(String filePath) {
		//try to open from jar
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);	
		if (is != null) {
			try {
				return new BufferedReader(new InputStreamReader(is, CHARSET));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return new BufferedReader(new InputStreamReader(is));
			}
		}
		
		
		//otherwise, try to open from file system
		File file = new File(filePath);
		try {
			//open file
			try {
				return new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			}
			
		} catch (FileNotFoundException e) {
			JuWaPflExceptionHandler.handle(e);
		}
		
		return null;
	}

}
