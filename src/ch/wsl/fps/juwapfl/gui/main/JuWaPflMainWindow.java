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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.JFreeChartPreloader;
import ch.wsl.fps.juwapfl.gui.JuWaPflExceptionHandler;


/**
 * 
 * @author Stefan Holm
 *
 */
public class JuWaPflMainWindow extends JFrame {
	
	private static final String betaSuffix = " <font size=4 color=red>BETA</font>"; //$NON-NLS-1$
	private static final boolean isBeta = false;
	private static final String versionNr = "1.3" + (isBeta ? betaSuffix : ""); //$NON-NLS-1$ //$NON-NLS-2$
	private static final LocalDate versionDate = LocalDate.of(2024, Month.AUGUST, 27);

	private static Locale locale = new Locale("de"); //$NON-NLS-1$
	static {
		setAllLocales(locale);
	}
	
	private JTree tree = initJTree();
	private JLabel lblWait;
	
	
	public static void main(String[] args) {
		JFreeChartPreloader.preload();
		AbstractMainWindow.main(args, () -> new JuWaPflMainWindow());
	}


	public JuWaPflMainWindow() {
		//window properties
		this.setSize((int) (380 * Math.pow(AbstractMainWindow.SIZE, 0.6) * AbstractMainWindow.WIDTH_FACTOR), (int) (340 * Math.pow(AbstractMainWindow.SIZE, 0.6)));
		this.setMinimumSize(new Dimension(350, 200));
		this.setLocationByPlatform(true);
		this.setTitle("JuWaPfl"); //$NON-NLS-1$
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//menu
		createMenu();

		//content
		initContent();
		
		//taskbar icon
		this.setIconImage(getWslLogo().getImage());

		//show window
		this.setVisible(true);
	}

	
	private void createMenu() {
		//MenuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		this.setJMenuBar(menuBar);
		
		//Top-level entries
		JMenu menuFile = new JMenu(Messages.getString("Common.MenuDatei")); //$NON-NLS-1$
		menuBar.add(menuFile);
		
		JMenu menuLanguage = new JMenu(Messages.getString("Common.MenuSprache")); //$NON-NLS-1$
		menuBar.add(menuLanguage);
		
		JMenu menuKontakt = new JMenu(Messages.getString("Common.MenuKontakt")); //$NON-NLS-1$
		menuBar.add(menuKontakt);
		
		JMenu menuHelp = new JMenu(Messages.getString("Common.MenuInfo")); //$NON-NLS-1$
		menuBar.add(menuHelp);
		
		
		//separator
		menuFile.addSeparator();
		
		//menu action: close
		Action aMenuClose = new AbstractAction(Messages.getString("Common.MenuBeenden")) { //$NON-NLS-1$
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}			
		};
		menuFile.add(aMenuClose);
		

		//menu action: languages
		ButtonGroup btnGroupLang = new ButtonGroup();
		List<Locale> listLanguages = new ArrayList<Locale>();
		listLanguages.add(new Locale("de")); //$NON-NLS-1$
		listLanguages.add(new Locale("fr")); //$NON-NLS-1$
		listLanguages.add(new Locale("it")); //$NON-NLS-1$
		listLanguages.add(new Locale("en")); //$NON-NLS-1$
		for (final Locale lang : listLanguages) {
			//create action
			Action aMenuLanguageX = new AbstractAction(lang.toString()) {
				@Override
				public void actionPerformed(ActionEvent e) {
					changeLanguage(lang);
				}
			};
			//create button
			JRadioButtonMenuItem menuLanguageX = new JRadioButtonMenuItem();
			menuLanguageX.setAction(aMenuLanguageX);
			menuLanguageX.setSelected( lang.equals(JuWaPflMainWindow.locale) );
			menuLanguageX.setText( lang.getDisplayLanguage(JuWaPflMainWindow.locale) );
			menuLanguageX.setName(lang.getLanguage());
			
			//add to menu and group
			menuLanguage.add(menuLanguageX);
			btnGroupLang.add(menuLanguageX);
		}
		
		
		//menu action: kontakt
		Action aMenuKontakt = new AbstractAction(Messages.getString("Common.MenuKontakt")) { //$NON-NLS-1$
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				sb.append("<html>"); //$NON-NLS-1$
				sb.append(Messages.getString("Common.EidgForschungsanstaltWSL") + "<br>");  //$NON-NLS-1$//$NON-NLS-2$
				sb.append("Zürcherstrasse 111<br>"); //$NON-NLS-1$
				sb.append("CH-8903 Birmensdorf<br>"); //$NON-NLS-1$
				sb.append("<br>"); //$NON-NLS-1$
				sb.append("<b>" + Messages.getString("Common.UeberschriftKontakt") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb.append("Janine Schweier / janine.schweier@wsl.ch / +41 44 739 24 78<br>"); //$NON-NLS-1$
				sb.append("<br>"); //$NON-NLS-1$
				sb.append("<b>" + Messages.getString("Common.UeberschriftSoftwareentwicklung") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb.append("\tStefan Holm / stefan.holm@wsl.ch / +41 44 739 22 63<br>"); //$NON-NLS-1$
				sb.append("</html>"); //$NON-NLS-1$
				
				JOptionPane.showMessageDialog(JuWaPflMainWindow.this, sb.toString(), Messages.getString("Common.MenuKontakt"), JOptionPane.NO_OPTION); //$NON-NLS-1$
			}
		};
		menuKontakt.add(aMenuKontakt);
		

		//menu action: info
		Action aMenuInfo = new AbstractAction(Messages.getString("Common.MenuInfo")) { //$NON-NLS-1$
			@Override
			public void actionPerformed(ActionEvent e) {
				int fontSize = AbstractMainWindow.SIZE > 1.3 ? 6 : 5;
				StringBuilder sb1 = new StringBuilder();
				sb1.append("<html>"); //$NON-NLS-1$
				sb1.append("<b><font size=" + fontSize + " color=#006666>JuWaPfl v" + versionNr + " / " + getJuWaPflDate() + "</font></b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				sb1.append("<br>"); //$NON-NLS-1$
				sb1.append("<b>" + Messages.getString("Common.UeberschriftBereitgestelltDurch") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append(Messages.getString("Common.EidgForschungsanstaltWSL") + "<br>");  //$NON-NLS-1$//$NON-NLS-2$
				sb1.append("Zürcherstrasse 111<br>"); //$NON-NLS-1$
				sb1.append("CH-8903 Birmensdorf<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				
				sb1.append("<b>" + Messages.getString("Common.UeberschriftModelle") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("Janine Schweier, Marc Werder, Fritz Frutig, Renato Lemm, Peter Ammann, Oliver Thees, Anton Bürgi<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				
				sb1.append("<b>" + Messages.getString("Common.UeberschriftSoftwareentwicklung") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("\tStefan Holm<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				
				sb1.append("<b>" + Messages.getString("Common.UeberschriftUebersetzungen") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("\tFritz Frutig, François Fahrni, Janine Schweier, Fabrizio Cioldi<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				
				sb1.append("<b>" + Messages.getString("Common.UeberschriftZitierung") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("\t- " + Messages.getString("Common.EidgForschungsanstaltWSL") + ", " + versionDate.getYear() + ": " + Messages.getString("Common.Zitierung") + ", " + Messages.getString("Common.Version") + " " + versionNr + "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
				sb1.append("</html>"); //$NON-NLS-1$
				JLabel label1 = new JLabel(sb1.toString());


				StringBuilder sb2 = new StringBuilder();
				sb2.append("<html>\t- <u style=\"color:blue;\">Holm, S., Werder, M., Thees, O., Lemm, R., & Schweier, J. (2023). JuWaPfl: A decision support tool </u><br>&nbsp;&nbsp;&nbsp;<u style=\"color:blue;\">to estimate times and costs of processes related to young-forest maintenance. SoftwareX, 24, 101581.</u><br><br>"); //$NON-NLS-1$ //$NON-NLS-2$
				sb2.append("</html>"); //$NON-NLS-1$
				JLabel label2 = new JLabel(sb2.toString());
				label2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label2.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				            try {
//				            	URI uri = new URI("https://doi.org/10.1016/j.softx.2023.101581");
				            	URI uri = new URI("https://www.sciencedirect.com/science/article/pii/S2352711023002777");
				                desktop.browse(uri);
				            } catch (Exception e1) {
				                e1.printStackTrace();
				            }
				        }
				    }
				});

				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.add(label1);
				panel.add(label2);
				
				JOptionPane.showMessageDialog(JuWaPflMainWindow.this, panel, Messages.getString("Common.MenuInfo"), JOptionPane.NO_OPTION); //$NON-NLS-1$
			}			
		};
		menuHelp.add(aMenuInfo);	
	}
	
	
	private void changeLanguage(Locale locale) {
		//Check ob neue Sprache gewählt
		if (locale.equals(JuWaPflMainWindow.locale) == false ) {
			//Locale überall anpassen
			setAllLocales(locale);
			
			//Neues Fenster öffnen / altes schliessen
			JuWaPflMainWindow mainWindow = new JuWaPflMainWindow();
			mainWindow.setLocation(this.getLocation());
			this.dispose();
		}
	}
	
	
	private static void setAllLocales(Locale newLocale){
		JuWaPflMainWindow.locale = newLocale;
//		Locale.setDefault(locale);
		JComponent.setDefaultLocale(locale);
		Messages.setLocale(locale);
	}
	
	
	private void initContent() {
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//WSL label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,10,10);
		ImageIcon wslLogo = getWslLogo();
		int fontSize = AbstractMainWindow.SIZE > 1.3 ? 6 : 5;
		String infoText = "<html><font size=" + fontSize + " color=#006666><b>" //$NON-NLS-1$ //$NON-NLS-2$
				+ Messages.getString("Common.TitelJungwaldpflege") + "<font size=0><br><br></font>" //$NON-NLS-1$ //$NON-NLS-2$
				+ Messages.getString("Common.Version") + " " + versionNr  + " / " + getJuWaPflDate() //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ "</b></font></html>"; //$NON-NLS-1$
		JLabel lblWslInfo = new JLabel(infoText, wslLogo, SwingConstants.LEFT);
		lblWslInfo.setIconTextGap(12);
		this.getContentPane().add(lblWslInfo, c);
		
		//JTree && waitLabel
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
		JScrollPane treeView = new JScrollPane(tree);
		tree.setBorder(new EmptyBorder(5,5,5,5));
	
        lblWait = new JLabel(getWaitImage());
        lblWait.setAlignmentX(0.5f);
        lblWait.setAlignmentY(0.5f);
		lblWait.setVisible(false);
        
        JPanel overlayPanel = new JPanel();
        overlayPanel.setLayout(new OverlayLayout(overlayPanel));
        overlayPanel.add(lblWait);
        overlayPanel.add(treeView);
        
		this.getContentPane().add(overlayPanel, c);
	}
	
	private JTree initJTree() {
		final JTree tree = new JTree(getRootNode());
		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		tree.expandRow(2);
		tree.expandRow(0);
		
		//correct text and selection colors
		tree.setCellRenderer(new DefaultTreeCellRenderer(){
			private final JPanel panel = new JPanel(new BorderLayout());
			{
        		panel.setOpaque(false);
				panel.add(this, BorderLayout.WEST);
				
				this.setBackgroundSelectionColor(null);
				this.setTextSelectionColor(null);
				this.setBorderSelectionColor(Color.LIGHT_GRAY);
			}
			
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
				label.setFont(tree.getFont());
				panel.removeAll();
				panel.add(this, BorderLayout.WEST);
				panel.setToolTipText(null);

				return panel;
		    }
		});
		
		// correct mouse pointer
		tree.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) {
		    	if (tree.isEnabled() == false) {
		    		return;
		    	}
		        int x = (int) e.getPoint().getX();
		        int y = (int) e.getPoint().getY();
		        TreePath path = tree.getPathForLocation(x, y);
		        
		        //set cursor
		        if (path != null && path.getLastPathComponent() instanceof ApplicationTreeNode) {
		        	tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        } else {
		        	tree.setCursor(Cursor.getDefaultCursor());
		        }
		    }
		});
		
		// start correct application
		tree.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if (tree.isEnabled()) {
			        int x = (int) e.getPoint().getX();
			        int y = (int) e.getPoint().getY();
			        TreePath path = tree.getPathForLocation(x, y);
			        if (path != null) {
			        	if (path.getLastPathComponent() instanceof ApplicationTreeNode) {
			        		ApplicationTreeNode selectedNode = (ApplicationTreeNode) path.getLastPathComponent();
			        		selectedNode.run();
			        	}
			        }
		    	}
		    }
		});

		tree.setFont(tree.getFont().deriveFont(tree.getFont().getSize() + 1f));
//		tree.setFont(tree.getFont().deriveFont(Font.BOLD));
		tree.setRowHeight(20);
		ToolTipManager.sharedInstance().registerComponent(tree);
		
		return tree;
	}
	
	private class ApplicationTreeNode extends DefaultMutableTreeNode {
		private static final boolean labelAsHtmlLink = true;
		private final Class<? extends AbstractMainWindow> clazz;
		private final String label;
		
		public ApplicationTreeNode(Class<? extends AbstractMainWindow> clazz, String label) {
			this.clazz = clazz;
			this.label = label;
		}
		
		public void run() {
			lblWait.setVisible(true);
			tree.setEnabled(false);

			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				@Override
				public Void doInBackground() {
	    			try {
	    				// this is not done in the EDT since otherwise lblWait is not properly animated.
	    				if (WildschutzMainWindow.class == clazz && JFreeChartPreloader.hasFinished() == false) {
    						System.out.println(LocalTime.now() + ": waiting for JFreeChartPreloader to finish...");
	    					while(JFreeChartPreloader.hasFinished() == false) {
	    						Thread.sleep(100);
	    					}
    						System.out.println(LocalTime.now() + ": JFreeChartPreloader finished");
	    				}
	    				Constructor<? extends AbstractMainWindow> constructor = clazz.getConstructor();
	    				constructor.newInstance();
	    			} 
	    			catch (Exception e) {
	    				JuWaPflExceptionHandler.handle(e);
	    			}
					return null;
				}

				@Override
				public void done() {
	    			lblWait.setVisible(false);
	    			tree.setEnabled(true);
				}
			};
			worker.execute();
		}
		
		@Override
		public String toString() {
			// Do not render labels as html while waiting screen is visible (so that labels are grayed out correctly).
			if (lblWait != null && lblWait.isVisible() == true) {
				return label;
			}
			
			if (labelAsHtmlLink && true) {
				return "<html><a href=\"\"><b>" + label + "</b></a></html>"; //$NON-NLS-1$ //$NON-NLS-2$
			} 
			else {
				return label;
			}
		}
	}
	
	private TreeNode getRootNode() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("JuWaPFL"); //$NON-NLS-1$

        // Wertastung
        rootNode.add(new ApplicationTreeNode(WertastungMainWindow.class, Messages.getString("Common.TitelWertastung"))); //$NON-NLS-1$

        // Z-Baum-Durchforstung
        rootNode.add(new ApplicationTreeNode(ZBaumDurchforstungMainWindow.class, Messages.getString("Common.TitelZBaumDurchforstung"))); //$NON-NLS-1$
        
        // Austrichtern
        rootNode.add(new ApplicationTreeNode(AustrichternMainWindow.class, Messages.getString("Common.TitelAustrichtern"))); //$NON-NLS-1$
        
        // Wildschutz
        rootNode.add(new ApplicationTreeNode(WildschutzMainWindow.class, Messages.getString("Common.TitelWildschutz"))); //$NON-NLS-1$
        
        // Schneegleiten
        rootNode.add(new ApplicationTreeNode(SchneegleitenMainWindow.class, Messages.getString("Common.TitelSchneegleiten"))); //$NON-NLS-1$
        
        // Begehungswege
        rootNode.add(new ApplicationTreeNode(BegehungswegeMainWindow.class, Messages.getString("Common.TitelBegehungswege"))); //$NON-NLS-1$
        
        // Pflanzung
        rootNode.add(new ApplicationTreeNode(PflanzungMainWindow.class, Messages.getString("Common.TitelPflanzung"))); //$NON-NLS-1$
        
        // Rückegassen planen
        rootNode.add(new ApplicationTreeNode(RueckegassenMainWindow.class, Messages.getString("Common.TitelRueckegassen"))); //$NON-NLS-1$
        
        return rootNode;
	}
	
    private ImageIcon getWaitImage() {
		return getImageIcon("data/loadinganimation.gif"); //$NON-NLS-1$
	}
	
	public static ImageIcon getWslLogo() {
		return getImageIcon("data/WSL64.png"); //$NON-NLS-1$
	}
	
	private static ImageIcon getImageIcon(String filePath) {
		//try to open from jar
		URL imgURL = JuWaPflMainWindow.class.getClassLoader().getResource(filePath);	
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		
		//otherwise, try to open from file system
		return new ImageIcon(filePath);
	}
	
	private String getJuWaPflDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", JuWaPflMainWindow.locale); //$NON-NLS-1$
		String juwapflDate = formatter.format(versionDate);
		return juwapflDate;
	}
	
	public static void setJuWaPflLocale(Locale locale) {
		setAllLocales(locale);
	}
	
}