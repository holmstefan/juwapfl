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

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.SchneegleitenErgebnisPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.SchneegleitenAbbauPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.SchneegleitenErstellenPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.SchneegleitenPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.SchneegleitenUnterhaltPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.SimplePersonalPanel;
import ch.wsl.fps.juwapfl.model.SchneegleitenErgebnis;
import ch.wsl.fps.juwapfl.model.SchneegleitenModel;
import ch.wsl.fps.juwapfl.model.SchneegleitenModel.Massnahme;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SchneegleitenMainWindow extends AbstractMainWindow {

	private SchneegleitenPanel panelSchneegleiten;
	private SchneegleitenErstellenPanel panelErstellen;
	private SchneegleitenUnterhaltPanel panelUnterhalt;
	private SchneegleitenAbbauPanel panelAbbau;
	private SimplePersonalPanel panelPersonal;
	private SchneegleitenErgebnisPanel panelErgebnis;

	
	public static void main(String[] args) {
		AbstractMainWindow.main(args, () -> new SchneegleitenMainWindow());
	}
	
	
	public SchneegleitenMainWindow() {
		this.setTitle(Messages.getString("Schneegleiten.FensterTitel")); //$NON-NLS-1$
		this.setSize(getWindowSize(730), getWindowSize(800));
		init();
	}

	
	@Override
	protected String[] getDokuPdfFileNames() {
		return new String[]{"DokuSchneegleiten.pdf", null}; //$NON-NLS-1$
	}

	
	@Override
	protected JPanel createPanelInput() {
		JPanel pnlInput = new JPanel();
		initTitledBorder(pnlInput, Messages.getString("Common.TitelEingaben")); //$NON-NLS-1$
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.PAGE_AXIS));

		pnlInput.add(Box.createVerticalStrut(5));
		
		panelSchneegleiten = new SchneegleitenPanel(this);
		pnlInput.add(panelSchneegleiten);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelErstellen = new SchneegleitenErstellenPanel(this);
		pnlInput.add(panelErstellen);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelUnterhalt = new SchneegleitenUnterhaltPanel(this);
		pnlInput.add(panelUnterhalt);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelAbbau = new SchneegleitenAbbauPanel(this);
		pnlInput.add(panelAbbau);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPersonal = new SimplePersonalPanel(this, false);
		pnlInput.add(panelPersonal);

		pnlInput.add(Box.createVerticalGlue());
		pnlInput.add(Box.createRigidArea(new Dimension(1, 220)));
		
		return pnlInput;
	}

	
	@Override
	protected JPanel createPanelOutput() {
		panelErgebnis = new SchneegleitenErgebnisPanel(this);
		return panelErgebnis;
	}

	
	@Override
	protected String getModelAsXmlString() {
		String xmlModelName = "<modellname>" + Messages.getString("Schneegleiten.Modellname") + "</modellname>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		
		String xmlEingaben = "<eingaben>"; //$NON-NLS-1$
		xmlEingaben += panelSchneegleiten.getAsXmlString();
		xmlEingaben += panelErstellen.getAsXmlString();
		xmlEingaben += panelUnterhalt.getAsXmlString();
		xmlEingaben += panelAbbau.getAsXmlString();
		xmlEingaben += panelPersonal.getAsXmlString();
		xmlEingaben += "</eingaben>"; //$NON-NLS-1$
		
		String xmlErgebnis = getErgebnisAsXmlString(panelErgebnis);

		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlEingaben + xmlErgebnis;
		return result;
	}

	
	@Override
	protected String getXsltFilePath() {
		return "data/kalkulation2fo-schneegleiten.xsl"; //$NON-NLS-1$
	}
	
	
	@Override
	protected Map<String, String> getAdditionalPdfTitles() {
		Map<String, String> result = new HashMap<>();

		result.put("aufbau", Messages.getString("Schneegleiten.Pdf.ErgebnisAufbau")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("unterhalt", Messages.getString("Schneegleiten.Pdf.ErgebnisUnterhalt")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("abbau", Messages.getString("Schneegleiten.Pdf.ErgebnisAbbau")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("gesamt", Messages.getString("Schneegleiten.Pdf.ErgebnisGesamt")); //$NON-NLS-1$ $NON-NLS-2$
		
		return result;
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation() {
		panelSchneegleiten.onInputChangedBeforeCalculation();
		panelErstellen.onInputChangedBeforeCalculation(panelSchneegleiten.getMassnahme());
		panelUnterhalt.onInputChangedBeforeCalculation(panelSchneegleiten.getMassnahme());
		
		boolean isBermen = panelSchneegleiten.getMassnahme() == Massnahme.BERMEN;
		panelAbbau.setEnabled(isBermen == false);
		if (isBermen) {
			panelAbbau.setAllFieldsToZero();
		}
	}
	
	
	@Override
	protected void displayErgebnis() {
		SchneegleitenModel model = new SchneegleitenModel();
		
		model.setMassnahme(panelSchneegleiten.getMassnahme());
		model.setAnzahlBauten(panelSchneegleiten.getAnzahlBauten());
		model.setGelaendeverhaeltnisse(panelSchneegleiten.getGelaendeverhaeltnisse());
		
		model.setErstellenPersonalzeit_minProEinheit(panelErstellen.getPersonalzeit_minProEinheit());
		model.setErstellenMaterialkosten_ChfProEinheit(panelErstellen.getMaterialkosten_ChfProEinheit());
		model.setErstellenMaschinenkosten_ChfProEinheit(panelErstellen.getMaschinenkosten_ChfProEinheit());
		model.setErstellenTransportkosten_ChfProEinheit(panelErstellen.getTransportkosten_ChfProEinheit());
		
		model.setUnterhaltPersonalzeit_minProEinheit(panelUnterhalt.getPersonalzeit_minProEinheit());
		model.setUnterhaltFahrzeugkosten_ChfProEinheit(panelUnterhalt.getFahrzeugkosten_ChfProEinheit());
		model.setUnterhaltMaterialkosten_ChfProEinheit(panelUnterhalt.getMaterialkosten_ChfProEinheit());
		
		model.setAbbauPersonalzeit_minProEinheit(panelAbbau.getPersonalzeit_minProEinheit());
		model.setAbbauFahrzeugkosten_ChfProEinheit(panelAbbau.getFahrzeugkosten_ChfProEinheit());
		model.setAbbauEntsorgungsgebuehren_ChfProEinheit(panelAbbau.getEntsorgungsgebuehren_ChfProEinheit());
		
		model.setKostenPersonalProPerson(panelPersonal.getKostenPersonalProPerson_proH());
		model.setFaktorWegzeitenUndPausen(panelPersonal.getFaktorWegzeitenUndPausen());
		
		SchneegleitenErgebnis ergebnis = model.getErgebnis();
		panelErgebnis.setErgebnis(ergebnis);
	}

}
