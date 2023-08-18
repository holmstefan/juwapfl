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
package ch.wsl.fps.juwapfl.gui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.CurrencySensitive;
import ch.wsl.fps.juwapfl.gui.DocumentionHelper;
import ch.wsl.fps.juwapfl.gui.JuWaPflExceptionHandler;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.controls.TitledBorderWithInfoButton;
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.AbstractErgebnisPanel;
import ch.wsl.fps.juwapfl.util.DatenblattCreator;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractMainWindow extends JFrame {
	
	private static boolean LOG_TO_FILE = false;

	protected static float SIZE = 1;
	protected static float WIDTH_FACTOR = 1;
	
	private final JTextField txtArbeitsort = new JTextField();
	
	private boolean reactOnInputChange = true;
	private final ActionListener defaultActionListener = e -> inputChanged();
	private final ChangeListener defaultChangeListener = e -> inputChanged();
	private final ItemListener defaultItemListener = e -> {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// we only listen for the SELECTED event, otherwise we would get two 
			// events for every change of a RadioButton, first a DELECTED, then
			// a SELECTED (and between these two events no radio button might be selected).
			inputChanged();	
		}
	};
	
	private final String defaultDir = isStartedFromJar() ? "." : "./data"; //muss für deployment "." sein, damit Standardordner für das Speichern der .hpm-Dateien wählbar ist ("Start in..") //$NON-NLS-1$ //$NON-NLS-2$
	
	private String currency;
	private final List<CurrencySensitive> currencyObservers = new ArrayList<>();
	
	public volatile boolean isInitializing = true;
	
	private static boolean isStartedFromJar() {
		return AbstractMainWindow.class.getResource("AbstractMainWindow.class").toString().startsWith("rsrc"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	
	protected static void main(String[] args, Runnable windowToStart) {
		//parse settings args		
		for (int i=0; i<args.length; i++) {
			String arg = args[i];			
			System.out.println("arg" + i + ": " + arg); //$NON-NLS-1$ //$NON-NLS-2$

			if (arg.equalsIgnoreCase("-logtofile") || arg.equalsIgnoreCase("logtofile") ) { //$NON-NLS-1$ //$NON-NLS-2$
				LOG_TO_FILE = true;
			}
			else if (arg.equalsIgnoreCase("-logdialog") || arg.equalsIgnoreCase("logdialog") ) { //$NON-NLS-1$ //$NON-NLS-2$
				JuWaPflExceptionHandler.setLogDialog(true);
			}
			else if (arg.equalsIgnoreCase("-stacktrace") || arg.equalsIgnoreCase("stacktrace") ) { //$NON-NLS-1$ //$NON-NLS-2$
				JuWaPflExceptionHandler.setLogStackTrace(true);
			}
		}
		
		if (LOG_TO_FILE) {
			try {
				OutputStream os = new FileOutputStream("juwapfl.log", true); //$NON-NLS-1$
				PrintStream ps = new PrintStream(os);
				System.setOut(ps);
				System.setErr(ps);
			} catch (FileNotFoundException e) {
				JuWaPflExceptionHandler.handle(e);
			}
		}
		
		Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
			JuWaPflExceptionHandler.handle(e, "Uncaught Exception in Thread " + t + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		});
		
		
		System.out.println("Default Locale: " + Locale.getDefault()); //$NON-NLS-1$
		if (Locale.getDefault().getLanguage().equals(Locale.FRENCH.getLanguage())) {
			JuWaPflMainWindow.setJuWaPflLocale(new Locale("fr")); //$NON-NLS-1$
		}
		if (Locale.getDefault().getLanguage().equals(Locale.ITALIAN.getLanguage())) {
			JuWaPflMainWindow.setJuWaPflLocale(new Locale("it")); //$NON-NLS-1$
		}
		
		//parse lang args		
		for (int i=0; i<args.length; i++) {
			String arg = args[i];			
			System.out.println("arg" + i + ": " + arg); //$NON-NLS-1$ //$NON-NLS-2$

			if (arg.equalsIgnoreCase("-fr") || arg.equalsIgnoreCase("fr") ) { //$NON-NLS-1$ //$NON-NLS-2$
				JuWaPflMainWindow.setJuWaPflLocale(new Locale("fr")); //$NON-NLS-1$
			}
			else if (arg.equalsIgnoreCase("-de") || arg.equalsIgnoreCase("de") ) { //$NON-NLS-1$ //$NON-NLS-2$
				JuWaPflMainWindow.setJuWaPflLocale(new Locale("de")); //$NON-NLS-1$
			}
			else if (arg.equalsIgnoreCase("-en") || arg.equalsIgnoreCase("en") ) { //$NON-NLS-1$ //$NON-NLS-2$
				JuWaPflMainWindow.setJuWaPflLocale(new Locale("en")); //$NON-NLS-1$
			}
			else if (arg.equalsIgnoreCase("-it") || arg.equalsIgnoreCase("it") ) { //$NON-NLS-1$ //$NON-NLS-2$
				JuWaPflMainWindow.setJuWaPflLocale(new Locale("it")); //$NON-NLS-1$
			}
		}
		
		
		SwingUtilities.invokeLater(() -> {
			loadLookAndFeel();
			
			// adapt GUIs based on default font
			Font defaultFont = new JLabel().getFont();	
			SIZE = (float)defaultFont.getSize() / 11; //Für Schriftgrösse 11 wurde die Applikation ursprünglich entwickelt.
			SIZE = Math.max(1, SIZE);

			// Sonderfall Apple
			if (System.getProperty("os.name") != null && System.getProperty("os.name").indexOf("Mac") >= 0) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				WIDTH_FACTOR = 1.15f;
			}

			//tooltip settings
			ToolTipManager.sharedInstance().setInitialDelay(0);
			ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
			
			windowToStart.run();
		});
	}
	
	
	private static void loadLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			JuWaPflExceptionHandler.handle(e);
		}
	}
	
	
	protected AbstractMainWindow() {
		this.setLocationByPlatform(true);
	}
	
	
	protected void init() {
		//window properties
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		//content
		initContent();

		setCurrency("CHF"); //$NON-NLS-1$
		onInputChangedBeforeCalculation();
		displayErgebnis();

		//taskbar icon
		this.setIconImage(Utilities.getWslLogo().getImage());

		//show window
//		this.pack();
		this.setVisible(true);
		
		isInitializing = false;
	}


	private void initContent() {
		createMenu();
		this.setLayout(new BorderLayout());
		this.add(createPanelArbeitsort(), BorderLayout.PAGE_START);
		this.add(createPanelInput(), BorderLayout.CENTER);
		this.add(createPanelOutput(), BorderLayout.PAGE_END);
	}


	private void createMenu() {
		final String dokuPdfFiles[] = getDokuPdfFileNames();
		
		this.setJMenuBar(new JMenuBar());
		
		JMenu menuFile = new JMenu(Messages.getString("Common.MenuDatei")); //$NON-NLS-1$
		this.getJMenuBar().add(menuFile);

		if (dokuPdfFiles[1] == null) { // only part A available
			menuFile.add(new AbstractAction(Messages.getString("Common.MenuModellDokuOeffnen")) { //$NON-NLS-1$
				@Override
				public void actionPerformed(ActionEvent e) {
					new Thread(() -> {
						DocumentionHelper.openDocumentationPdfFile(dokuPdfFiles[0]);
					}).start();
				}
			});
		}
		else { // part A and B available
			JMenu menuDocumentation = new JMenu(Messages.getString("Common.MenuModellDokuOeffnen")); //$NON-NLS-1$
			menuFile.add(menuDocumentation);
			
			menuDocumentation.add(new AbstractAction(Messages.getString("Common.MenuModellDokuTeilA")) { //$NON-NLS-1$
				@Override
				public void actionPerformed(ActionEvent e) {
					new Thread(() -> {
						DocumentionHelper.openDocumentationPdfFile(dokuPdfFiles[0]);
					}).start();
				}
			});
			
			menuDocumentation.add(new AbstractAction(Messages.getString("Common.MenuModellDokuTeilB")) { //$NON-NLS-1$
				@Override
				public void actionPerformed(ActionEvent e) {
					new Thread(() -> {
						DocumentionHelper.openDocumentationPdfFile(dokuPdfFiles[1]);
					}).start();
				}
			});	
		}


		menuFile.add(new AbstractAction(Messages.getString("Common.MenuDatenblattErstellen")) { //$NON-NLS-1$
			@Override
			public void actionPerformed(ActionEvent e) {
				DatenblattCreator.preInitializeInSeparateThread();
				
				//get filename
				final File pdfFile = getDatenblattFile();
				if (pdfFile == null) {
					return;
				}			
				
				new Thread(() -> createAndOpenDatenblattPdf(pdfFile)).start();
			}
		});

		menuFile.addSeparator();

		menuFile.add(new AbstractAction(Messages.getString("Common.MenuBeenden")) { //$NON-NLS-1$
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		JLabel lblPlaceholder = new JLabel("   "); //$NON-NLS-1$
		lblPlaceholder.setPreferredSize(new Dimension(5, 1));
		this.getJMenuBar().add(lblPlaceholder);

		
		JMenu menuCurrency = new JMenu(Messages.getString("Common.MenuWaehrungseinheit")); //$NON-NLS-1$
		this.getJMenuBar().add(menuCurrency);
		
		JRadioButtonMenuItem menuCHF = new JRadioButtonMenuItem("CHF", true); //$NON-NLS-1$
		menuCHF.addActionListener(e -> setCurrency("CHF")); //$NON-NLS-1$
		menuCurrency.add(menuCHF);
		
		JRadioButtonMenuItem menuEUR = new JRadioButtonMenuItem("EUR", false); //$NON-NLS-1$
		menuEUR.addActionListener(e -> setCurrency("EUR")); //$NON-NLS-1$
		menuCurrency.add(menuEUR);
		
		ButtonGroup group = new ButtonGroup();
		group.add(menuCHF);
		group.add(menuEUR);
	}
	
	/**
	 * Should be called only once.
	 */
	private JPanel createPanelArbeitsort() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2) );
		panel.setBorder(BorderFactory.createEmptyBorder(5,10,8,10));
		
		panel.add(new JLabel(Messages.getString("Common.Arbeitsort"))); //$NON-NLS-1$
		
		String textInfoButton = Messages.getString("Common.InfoButtonArbeitsort"); //$NON-NLS-1$
		panel.add(Utilities.getPanelWithInfoButton(txtArbeitsort, textInfoButton));
		
		return panel;
	}
	
	
	/**
	 * @return <code>String[2]</code>, where <code>result[0]</code> contains the file name of 
	 * part A of the documentation, <code>result[1]</code> the file name of part B of the 
	 * documentation. If part B of the documentation is not available, <code>result[1]</code>
	 * should be <code>null</code>. 
	 */
	protected abstract String[] getDokuPdfFileNames();


	protected abstract JPanel createPanelInput();
	
	
	protected abstract JPanel createPanelOutput();


	private File getDatenblattFile() {
		String defaultfileName = Messages.getString("Common.DatenblattDefaultFilename"); //$NON-NLS-1$
		JFileChooser fileChooser = new JFileChooser(defaultDir) {
			@Override
		    public void approveSelection(){
				//check file extension
				File file = getSelectedFile();
				if (file.getName().toLowerCase().endsWith(".pdf") == false) { //$NON-NLS-1$
					file = new File(file.getPath() + ".pdf"); //$NON-NLS-1$
					setSelectedFile(file);
				}
				
				//check if already exists
		        if(file.exists() && getDialogType() == SAVE_DIALOG){
		            int result = JOptionPane.showConfirmDialog(this, Messages.getString("Common.DateiExistiertBereitsUeberschreiben"), Messages.getString("Common.ButtonSpeichern"), JOptionPane.YES_NO_CANCEL_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
		            switch(result){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    return;
		                    
		                case JOptionPane.NO_OPTION:
		                case JOptionPane.CLOSED_OPTION:
		                    return;
		                    
		                case JOptionPane.CANCEL_OPTION:
		                    cancelSelection();
		                    return;
		            }
		        }
		        super.approveSelection();
		    }        
		};
		fileChooser.setSelectedFile(new File(defaultfileName));
		
		
		//check if 'save' pressed
		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		
		return fileChooser.getSelectedFile();
	}
	
	
	private void createAndOpenDatenblattPdf(File pdfFile) {
		//create pdf
		DatenblattCreator pdfCreator = new DatenblattCreator();
		pdfCreator.create(getModelAsXmlString(), pdfFile, getXsltFilePath());
		
		//try to open file
		try {
			Desktop.getDesktop().open(pdfFile);
		} catch (IOException e) {
			JuWaPflExceptionHandler.handle(e);
		}
	}
	
	protected abstract String getModelAsXmlString();
	
	protected static String getErgebnisAsXmlString(AbstractErgebnisPanel<?, ?> panelErgebnis) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<ergebnis>\n"); //$NON-NLS-1$
		
		String[][] lines = panelErgebnis.getErgebnisAsStringArray();
		doErgebnisXmlStringSanityCheck(lines);
		for (String[] line : lines) {
//			if (line == null) {
//				//do nothing
//			}
			/*else*/ if ( line.length == 0 ) {
				sb.append("\t<leerzeile/>\n"); //$NON-NLS-1$
			}
			else {
				sb.append("\t<ergebniszeile>\n"); //$NON-NLS-1$
				for (int i=0; i<line.length; i++) {
					sb.append("\t\t<spalte" + i + ">"); //$NON-NLS-1$ //$NON-NLS-2$
					sb.append(line[i]);
					sb.append("</spalte" + i + ">\n"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				sb.append("\t</ergebniszeile>\n"); //$NON-NLS-1$
			}
		}
		sb.append("</ergebnis>\n"); //$NON-NLS-1$
		
		return sb.toString();
	}
	
	/**
	 * Each line is either null, or has length 0, or has the same length
	 * as all other lines that are not null or have length 0.
	 */
	private static void doErgebnisXmlStringSanityCheck(String[][] lines) {
		int lineLength = -1;
		for (int i=0; i<lines.length; i++) {
			if (lines[i] != null && lines[i].length > 0) {
				if (lineLength < 0) {
					// lineLengh not yet assigned
					lineLength = lines[i].length;
				}
				else {
					if (lineLength != lines[i].length) {
						throw new IllegalStateException("ergebnisXmlString has wrong format."); //$NON-NLS-1$
					}
				}
			}
		}
	}
	
	protected abstract String getXsltFilePath();
	
	
	protected String getMetaInformationAsXmlString() {
		StringBuilder sb = new StringBuilder();
		String arbeitsort = txtArbeitsort.getText();
		
		sb.append("<info>\n"); //$NON-NLS-1$
		if (arbeitsort.length() > 0) {
			sb.append("\t<arbeitsort>"); //$NON-NLS-1$
			sb.append(arbeitsort);
			sb.append("\t</arbeitsort>\n"); //$NON-NLS-1$
		}
			sb.append("\t<creationdate>"); //$NON-NLS-1$
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"); //$NON-NLS-1$
				sb.append( formatter.format(LocalDateTime.now()) );
			sb.append("\t</creationdate>\n"); //$NON-NLS-1$
			sb.append("\t<currency>"); //$NON-NLS-1$
				sb.append( getCurrency() );
			sb.append("</currency>\n"); //$NON-NLS-1$
		sb.append("</info>\n"); //$NON-NLS-1$
		
		return sb.toString();
	}
	
	
	protected final String getPdfTitlesAsXmlString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<pdftitles>\n");
		
			sb.append("\t<haupttitel>");
				sb.append(Messages.getString("Pdf.Haupttitel")); //$NON-NLS-1$
			sb.append("</haupttitel>\n");
		
			sb.append("\t<arbeitsort>");
				sb.append(Messages.getString("Common.Arbeitsort")); //$NON-NLS-1$
			sb.append("</arbeitsort>\n");
		
			sb.append("\t<ergebnis>");
				sb.append(Messages.getString("Common.TitelErgebnis")); //$NON-NLS-1$
			sb.append("</ergebnis>\n");
		
			sb.append("\t<zeiten>");
				sb.append(Messages.getString("Common.TitelZeiten")); //$NON-NLS-1$
			sb.append("</zeiten>\n");
		
			sb.append("\t<kosten>");
				sb.append(Messages.getString("Common.ErgebnisKosten")); //$NON-NLS-1$
			sb.append("</kosten>\n");
			
			
			Map<String, String> additionalPdfTitles = getAdditionalPdfTitles();
			for (String key : additionalPdfTitles.keySet()) {
				sb.append("\t<" + key + ">"); //$NON-NLS-1$ $NON-NLS-2$
					sb.append(additionalPdfTitles.get(key));
				sb.append("</" + key + ">\n"); //$NON-NLS-1$ $NON-NLS-2$
			}
			

		sb.append("</pdftitles>\n");
		
		return sb.toString();
	}
	
	/**
	 * Can be overridden in subclasses. The default implementation returns an empty map.
	 */
	protected Map<String, String> getAdditionalPdfTitles() {
		return Collections.emptyMap();
	}
	

	private void inputChanged() {
		if (reactOnInputChange == true) {
			//Vorberechnungen
			reactOnInputChange = false;
			onInputChangedBeforeCalculation();
			reactOnInputChange = true;
			
			//Kalkulation
			displayErgebnis();
		}
	}
	
	
	/**
	 * Methode wird aufgerufen bei geänderten Input-Feldern, noch bevor die Ergebnisse berechnet 
	 * werden. Zweck der Methode: Aktualisierung von gegenseitig abhängigen Feldern vor 
	 * Neuberechnungen. Die Defaultimplementierung ist leer. Änderungen im Modell während der 
	 * Ausführung dieser Methode folgen nicht automatisch zu einer Neuberechnung des Ergebnisses.
	 */
	protected void onInputChangedBeforeCalculation() {
		// default implementation is empty
	}
	

	protected abstract void displayErgebnis();
	
	
	public static void initTitledBorder(JPanel panel, String title) {
		TitledBorderWithInfoButton border = new TitledBorderWithInfoButton(title);
		panel.setBorder(border);
	}
	

	public ActionListener getDefaultActionListener() {
		return defaultActionListener;
	}

	
	public ChangeListener getDefaultChangeListener() {
		return defaultChangeListener;
	}

	
	public ItemListener getDefaultItemListener() {
		return defaultItemListener;
	}
	
	
	/**
	 * JSpinner wird so angepasst dass bei ungültigen Eingaben gewarnt wird (farblich und mit Tooltip).
	 * Zusätzlich wird im Formatter der übergebenen JSpinner-Instanz die Methode setCommitsOnValidEdit(true) aufgerufen.
	 *  
	 * Achtung: nach Aufruf von JSpinner.setModel() muss diese Methode erneut aufgerufen werden.
	 */
	public static final void adjustJSpinnerFormatter(final JSpinner spinner, final boolean isPercentageField) {
		//Formatter-Methoden aufrufen
		final JFormattedTextField textField = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
		DefaultFormatter formatter = (DefaultFormatter) textField.getFormatter();
		formatter.setCommitsOnValidEdit(true);
//		formatter.setAllowsInvalid(false);
		
		//User-Warnung bei ungültigem Eingabewert
		textField.addPropertyChangeListener(e ->  {
			if ("editValid".equals(e.getPropertyName())) { //$NON-NLS-1$
				if (Boolean.FALSE.equals(e.getNewValue())) {
					DecimalFormat df = isPercentageField ? new DecimalFormat("#.##%") :  new DecimalFormat("#.##"); //$NON-NLS-1$ //$NON-NLS-2$
					SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();  
					textField.setBackground(Color.YELLOW);
					textField.setToolTipText(Messages.getString("Common.WertMussZwischenMinUndMaxLiegenA") + " " + df.format(model.getMinimum()) + " " +  Messages.getString("Common.WertMussZwischenMinUndMaxLiegenB") + " " + df.format(model.getMaximum()) + " " +  Messages.getString("Common.WertMussZwischenMinUndMaxLiegenC")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
				}
				else{
					textField.setBackground(Color.WHITE);
					textField.setToolTipText(null);
				}
			}
		});
	}
	
	
	public void registerForCurrencyUpdates(CurrencySensitive observer) {
		currencyObservers.add(observer);
	}
	
	protected void setCurrency(String currency) {
		this.currency = currency;
		currencyObservers.forEach(obs -> obs.updateCurrency());
	}
	
	public String getCurrency() {
		return currency;
	}
	
	protected int getWindowSize(int originalSize) {
		if (System.getProperty("os.name").toLowerCase().contains("mac")) { //$NON-NLS-1$ //$NON-NLS-2$
			return (int) (1.15 * originalSize);
		}
		return originalSize;
	}

}
