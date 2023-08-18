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
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.WildschutzErgebnisPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.FahrzeugkostenPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.SimplePersonalPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.WildschutzChartPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.WildschutzKorrekturfaktorenPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.WildschutzPanel;
import ch.wsl.fps.juwapfl.model.WildschutzErgebnis;
import ch.wsl.fps.juwapfl.model.WildschutzModel;
import ch.wsl.fps.juwapfl.model.WildschutzModel.Schutztyp;
import ch.wsl.fps.juwapfl.model.WildschutzModel.Subtyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WildschutzMainWindow extends AbstractMainWindow {
	
	private WildschutzPanel panelWildschutz;
	private SimplePersonalPanel panelPersonal;
	private FahrzeugkostenPanel panelFahrzeugkosten;
	private WildschutzKorrekturfaktorenPanel panelKorrekturfaktoren;

	private WildschutzErgebnisPanel panelErgebnisEinzelberechnung;
	private WildschutzChartPanel panelErgebnisKostenvergleich;
	private JPanel panelErgebnisAll;

	private JComboBox<Subtyp> cmbKostenvergleichZaunartSubtyp;
	private boolean isKostenvergleichActive = false;

	
	public static void main(String[] args) {
		AbstractMainWindow.main(args, () -> new WildschutzMainWindow());
	}
	
	
	public WildschutzMainWindow() {
		this.setTitle(Messages.getString("Wildschutz.FensterTitel")); //$NON-NLS-1$
		this.setSize(getWindowSize(720), getWindowSize(750));
		init();
	}

	
	@Override
	protected String[] getDokuPdfFileNames() {
		return new String[]{"DokuWildschutz.pdf", null}; //$NON-NLS-1$
	}
	

	@Override
	protected JPanel createPanelInput() {
		JPanel pnlInput = new JPanel();
		initTitledBorder(pnlInput, Messages.getString("Common.TitelEingaben")); //$NON-NLS-1$
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.PAGE_AXIS));

		pnlInput.add(Box.createVerticalStrut(5));
		
		panelWildschutz = new WildschutzPanel(this);
		pnlInput.add(panelWildschutz);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPersonal = new SimplePersonalPanel(this, false);
		pnlInput.add(panelPersonal);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelFahrzeugkosten = new FahrzeugkostenPanel(this);
		pnlInput.add(panelFahrzeugkosten);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelKorrekturfaktoren = new WildschutzKorrekturfaktorenPanel(this);
		pnlInput.add(panelKorrekturfaktoren);

		pnlInput.add(Box.createVerticalGlue());
		pnlInput.add(Box.createRigidArea(new Dimension(1, 220)));
		
		return pnlInput;
	}
	
	
	private JPanel initPanelSwitchButton() {
		// Zaunart-Combobox
		cmbKostenvergleichZaunartSubtyp = new JComboBox<>(Subtyp.getValues(Schutztyp.FLAECHENSCHUTZ));
		cmbKostenvergleichZaunartSubtyp.addActionListener(super.getDefaultActionListener());
		JPanel pnlZaunart = new JPanel();
		pnlZaunart.add(new JLabel(Messages.getString("Wildschutz.UeberschriftZaunSubtyp"))); //$NON-NLS-1$
		pnlZaunart.add(cmbKostenvergleichZaunartSubtyp);
		pnlZaunart.setVisible(false);

		// Switch-Button
		String txtSwitchToVergleich = Messages.getString("Wildschutz.KostenvergleichDurchfuehren"); //$NON-NLS-1$
		String txtSwitchToEinzelkalkulation = Messages.getString("Wildschutz.EinzelkalkulationDurchfuehren"); //$NON-NLS-1$
		JButton btnSwitchErgebnis = new JButton(txtSwitchToVergleich);
		btnSwitchErgebnis.addActionListener(e -> {
			boolean newStateIsKostenvergleich = btnSwitchErgebnis.getText().equals(txtSwitchToVergleich);
			btnSwitchErgebnis.setText(newStateIsKostenvergleich ? txtSwitchToEinzelkalkulation : txtSwitchToVergleich);
			CardLayout cardLayout = (CardLayout) panelErgebnisAll.getLayout();
			cardLayout.next(panelErgebnisAll);
			pnlZaunart.setVisible(newStateIsKostenvergleich);
			panelWildschutz.setModusKostenvergleich(newStateIsKostenvergleich);
			isKostenvergleichActive = newStateIsKostenvergleich;
			displayErgebnis();
			if (newStateIsKostenvergleich) {
				String message = Utilities.normalizeTooltipText(Messages.getString("Wildschutz.MessageKostenvergleich")); //$NON-NLS-1$
				message = message.replace("<html>", "").replace("</html>", "").replace("<br>", "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				JOptionPane.showMessageDialog(this, message);
			}
		});
		JPanel pnlButton = new JPanel();
		pnlButton.add(btnSwitchErgebnis);
		
		// Gesamt-Panel
		JPanel pnlSwitchButton = new JPanel();
		pnlSwitchButton.setLayout(new BorderLayout());
		pnlSwitchButton.add(pnlButton, BorderLayout.WEST);
		pnlSwitchButton.add(pnlZaunart, BorderLayout.CENTER);
		return pnlSwitchButton;
	}
	

	@Override
	protected JPanel createPanelOutput() {
		panelErgebnisAll = new JPanel(new CardLayout());

		panelErgebnisEinzelberechnung = new WildschutzErgebnisPanel(this);
		panelErgebnisKostenvergleich = new WildschutzChartPanel();
		
		panelErgebnisAll.add(panelErgebnisEinzelberechnung);
		panelErgebnisAll.add(panelErgebnisKostenvergleich);

		JPanel pnlOutput = new JPanel();
		pnlOutput.setLayout(new BoxLayout(pnlOutput, BoxLayout.PAGE_AXIS));
		pnlOutput.add(initPanelSwitchButton());
		pnlOutput.add(Box.createVerticalStrut(5));
		pnlOutput.add(panelErgebnisAll);
		
		return pnlOutput;
	}
	

	@Override
	protected String getModelAsXmlString() {
		String xmlModelName = "<modellname>" + Messages.getString("Wildschutz.Titel") + "</modellname>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		
		String xmlEingaben = "<eingaben>"; //$NON-NLS-1$
		xmlEingaben += panelWildschutz.getAsXmlString();
		xmlEingaben += panelPersonal.getAsXmlString();
		xmlEingaben += panelFahrzeugkosten.getAsXmlString();
		xmlEingaben += panelKorrekturfaktoren.getAsXmlString();
		xmlEingaben += "</eingaben>"; //$NON-NLS-1$
		
		String xmlErgebnis = getErgebnisAsXmlString(panelErgebnisEinzelberechnung);

		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlEingaben + xmlErgebnis;
		return result;
	}
	
	@Override
	protected String getXsltFilePath() {
		return "data/kalkulation2fo-wildschutz.xsl"; //$NON-NLS-1$
	}
	
	
	@Override
	protected Map<String, String> getAdditionalPdfTitles() {
		Map<String, String> result = new HashMap<>();

		result.put("aufbau", Messages.getString("Wildschutz.Pdf.Aufbau")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("unterhalt", Messages.getString("Wildschutz.Pdf.Unterhalt")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("abbau", Messages.getString("Wildschutz.Pdf.Abbau")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("gesamt", Messages.getString("Wildschutz.Pdf.Gesamt")); //$NON-NLS-1$ $NON-NLS-2$
		
		return result;
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation() {
		panelWildschutz.onInputChangedBeforeCalculation();
	}
	

	@Override
	protected void displayErgebnis() {
		WildschutzModel modelEinzelkalkulation = getNewModel();
		
		WildschutzErgebnis ergebnisEinzelkalkulation = modelEinzelkalkulation.getErgebnis();
		panelErgebnisEinzelberechnung.setErgebnis(ergebnisEinzelkalkulation);
		
		if (isKostenvergleichActive) {
			double kostenEinzelschutzGesamt = ergebnisEinzelkalkulation.getKostenGesamtGesamt();
			int anzahlPflanzenEinzelschutz = panelWildschutz.getAnzahlPflanzen();
			
			WildschutzModel modelZaun = getNewModel();
			modelZaun.setSchutztyp(Schutztyp.FLAECHENSCHUTZ);
			modelZaun.setSubtyp((Subtyp) cmbKostenvergleichZaunartSubtyp.getSelectedItem());
			modelZaun.setZaunlaenge_m(500);
			WildschutzErgebnis ergebnisZaun = modelZaun.getErgebnis();
			double kostenZaunGesamt = ergebnisZaun.getKostenGesamtGesamt();
			
			double anzahl = (0d + anzahlPflanzenEinzelschutz) * kostenZaunGesamt / kostenEinzelschutzGesamt;
			
			panelErgebnisKostenvergleich.setData(anzahl, anzahlPflanzenEinzelschutz, panelWildschutz.getZaunlaenge_m());
		}
	}
	
	
	private WildschutzModel getNewModel() {
		WildschutzModel model = new WildschutzModel();

		model.setSchutztyp(panelWildschutz.getSchutztyp());
		model.setSubtyp(panelWildschutz.getSubtyp());
		model.setWuchshuellentyp(panelWildschutz.getWuchshuellentyp());
		model.setAnzahlPflanzen(panelWildschutz.getAnzahlPflanzen());
		model.setZaunlaenge_m(panelWildschutz.getZaunlaenge_m());
		
		model.setKostenPersonalProPerson(panelPersonal.getKostenPersonalProPerson_proH());
		model.setFaktorWegzeitenUndPausen(panelPersonal.getFaktorWegzeitenUndPausen());

		model.setFahrzeugkostenAufbauPauschal(panelFahrzeugkosten.getFahrzeugkostenAufbauPauschal());
		model.setFahrzeugkostenUnterhaltPauschal(panelFahrzeugkosten.getFahrzeugkostenUnterhaltPauschal());
		model.setFahrzeugkostenAbbauPauschal(panelFahrzeugkosten.getFahrzeugkostenAbbauPauschal());

		model.setKorrekturfaktorZeitaufwand(panelKorrekturfaktoren.getKorrekturfaktorZeitaufwand());
		model.setKorrekturfaktorMaterialkosten(panelKorrekturfaktoren.getKorrekturfaktorMaterialkosten());
		
		return model;
	}

}
