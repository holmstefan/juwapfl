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
package ch.wsl.fps.juwapfl.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

import ch.wsl.fps.juwapfl.Messages;

/**
 * 
 * @author Stefan Holm
 *
 */
public class DocumentionHelper {
	
	public static final String DOKU_DIR = "Dokumentation/"; //$NON-NLS-1$

	
	
	/**
	 * Opens a documentation pdf file that is stored inside the jar.
	 * Since files inside the jar file can not be read directly by the system,
	 * the pdf is first copied from the jar to the filesystem and then opened.
	 */
	public static void openDocumentationPdfFile(String path) {
		if (path.startsWith(DOKU_DIR) == false) {
			path = DOKU_DIR + path;
		}
		//copy files
		copyFileFromJarToFilesystemIfNecessary(path);
		generateReadMeFile();
		
		//try to open file
		File file = new File(path);
		try {
			Desktop.getDesktop().open(file);
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), Messages.getString("DocumentionHelper.FehlerBeimOeffnenDerDatei"), JOptionPane.ERROR_MESSAGE);			 //$NON-NLS-1$
		}
	}
	
	
	private static void copyFileFromJarToFilesystemIfNecessary(String path) {
		if (isStartedFromJar() == false) {
			return;
		}
		
		File fileInFilesystem = new File(path);
		
		//check if file already exists in file system
		if (fileInFilesystem.exists() == false) {
			System.out.println("File in file system does not exist. File will be copied from jar."); //$NON-NLS-1$
			copyFileFromJarToFilesystem(path);
		}
		else if (hasFilesystemFileDifferentDateThanFileInJar(path)) {
			System.out.println("File in file system has different last-modified date than file in jar. File in file system will be replaced."); //$NON-NLS-1$
			copyFileFromJarToFilesystem(path);
		}
		else {
			return; //no copy needed
		}
	}
	
	
	private static boolean isStartedFromJar() {
		URL url = DocumentionHelper.class.getResource("DocumentionHelper.class"); //$NON-NLS-1$
		boolean isStartedFromJar = url.toString().startsWith("jar") || url.toString().startsWith("rsrc"); //$NON-NLS-1$ //$NON-NLS-2$
		return isStartedFromJar;
	}
	
	
	private static void copyFileFromJarToFilesystem(String path) {
		File fileInFilesystem = new File(path);
		
		//try to open file in jar
		InputStream in = DocumentionHelper.class.getClassLoader().getResourceAsStream(path);
	    if (in == null) {
	        System.err.println("file '" + path + "' not found in jar"); //$NON-NLS-1$ //$NON-NLS-2$
	        return;
	    }
	    
	    
		//create target directory if it does not exist
		String[] dirs = path.split("\\\\|/"); //$NON-NLS-1$
		String dir = ""; //$NON-NLS-1$
		for (int i=0; i<dirs.length-1; i++) {
			dir += dirs[i] + "/"; //$NON-NLS-1$
		}
		File f = new File(dir);
		f.mkdirs();
		
	    
	    //copy file to filesystem
	    FileOutputStream out = null;
	    int readBytes;
	    byte[] buffer = new byte[4096];
	    try {
	    	out = new FileOutputStream(fileInFilesystem);
	        while ((readBytes = in.read(buffer)) > 0) {
	        	out.write(buffer, 0, readBytes);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				in.close();
				if (out != null) {
			        out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    //set lastModified
	    long lastModified = getJarFileLastModified(path);
	    if (lastModified >= 0) {
	    	fileInFilesystem.setLastModified(lastModified);
	    }
	}

	
	private static long getJarFileLastModified(String filePath) {
		filePath = "/" + filePath; //classFilePath muss mit einem slash starten //$NON-NLS-1$
		JarFile jar = null;
		try {
			jar = getJarFile();
			if (jar != null) {
				Enumeration<JarEntry> enumEntries = jar.entries();
				while (enumEntries.hasMoreElements()) {
					JarEntry file = (JarEntry) enumEntries.nextElement();
					if (file.getName().equals(filePath.substring(1))) {
						long time=file.getTime();
						return time;
					}
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			if (jar != null) {
				try {
					jar.close();
				} catch (IOException e) {
//					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	
	
	private static JarFile getJarFile() {
		JarFile jar = null;
		
		// Versuch 1: funktioniert, falls sich das jar im working-directory befindet
		try {
			jar = new JarFile("JuWaPfl.jar"); //$NON-NLS-1$
			return jar;
		} catch (Exception e) {
//			e.printStackTrace();
			try {
				// Filename erraten falls zB in Form "JuWaPfl-ZBaumDurchforstung.jar"
				String fileName = Files
						.list(Paths.get(".")) //$NON-NLS-1$
						.filter(p -> p.getFileName().toString().startsWith("JuWaPfl") && p.getFileName().toString().endsWith(".jar")) //$NON-NLS-1$ //$NON-NLS-2$
						.findAny()
						.get()
						.getFileName()
						.toString();
				jar = new JarFile(fileName);
				return jar;
			} catch (Exception e1) {
				//e1.printStackTrace();
			}
		}
		
		// Versuch 2: funktioniert, falls sich das jar ausserhalb working-directory befindet,
		// und das jar-File mit der Option "Package required libraries into generated JAR" erstellt wurde.
		try {
			String jarFilePath = ClassLoader.getSystemClassLoader().getResource(".") //$NON-NLS-1$
					.toURI()
					.getPath()
					.replaceFirst("/", ""); //$NON-NLS-1$ //$NON-NLS-2$
			jar = new JarFile(jarFilePath + File.separator + "JuWaPfl.jar"); //$NON-NLS-1$
			return jar;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private static boolean hasFilesystemFileDifferentDateThanFileInJar(String path) {
		File fileInFilesystem = new File(path);	

		long fileInFilesystemLastModified = -2;
		long fileInJarLastModified = -2;
		try {
			fileInFilesystemLastModified = fileInFilesystem.lastModified();
			fileInJarLastModified = getJarFileLastModified(path);	
//			System.out.println("filesystem: " + new Date(fileInFilesystemLastModified) + " / jar: " + new Date(fileInJarLastModified) );
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		if (fileInFilesystemLastModified <= 0 || fileInJarLastModified <=0) {
			return true;
		}
		
		return fileInFilesystemLastModified != fileInJarLastModified;
	}
	
	
	private static void generateReadMeFile() {
		File file = new File(DOKU_DIR + "README.txt"); //$NON-NLS-1$	
		if (file.exists() == false) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(file);
				writer.println(Messages.getString("DocumentionHelper.ReadmeTxtContent")); //$NON-NLS-1$
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
