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
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.PflanzungErgebnisPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.PflanzungPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.PflanzungPersonalPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.PflanzungWinkelpflanzungPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.PflanzungZeitenKostenPanel;
import ch.wsl.fps.juwapfl.model.PflanzungErgebnis;
import ch.wsl.fps.juwapfl.model.PflanzungModel;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Pflanzverfahren;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungMainWindow extends AbstractMainWindow {

	private PflanzungPanel panelPflanzung;
	private PflanzungWinkelpflanzungPanel panelPflanzungWinkelpflanzung;
	private PflanzungZeitenKostenPanel panelPflanzungZeitenKosten;
	private PflanzungPersonalPanel panelPersonal;
	private PflanzungErgebnisPanel panelErgebnis;

	
	public static void main(String[] args) {
		AbstractMainWindow.main(args, () -> new PflanzungMainWindow());
	}
	
	
	public PflanzungMainWindow() {
		this.setTitle(Messages.getString("Pflanzung.FensterTitel")); //$NON-NLS-1$
		this.setSize(getWindowSize(780), getWindowSize(900));
		init();
	}

	
	@Override
	protected String[] getDokuPdfFileNames() {
		return new String[]{"DokuPflanzung.pdf", null}; //$NON-NLS-1$
	}

	
	@Override
	protected JPanel createPanelInput() {
		JPanel pnlInput = new JPanel();
		initTitledBorder(pnlInput, Messages.getString("Common.TitelEingaben")); //$NON-NLS-1$
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.PAGE_AXIS));

		pnlInput.add(Box.createVerticalStrut(5));
		
		panelPflanzung = new PflanzungPanel(this);
		pnlInput.add(panelPflanzung);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPflanzungWinkelpflanzung = new PflanzungWinkelpflanzungPanel(this);
		pnlInput.add(panelPflanzungWinkelpflanzung);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPflanzungZeitenKosten = new PflanzungZeitenKostenPanel(this);
		pnlInput.add(panelPflanzungZeitenKosten);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPersonal = new PflanzungPersonalPanel(this);
		pnlInput.add(panelPersonal);

		pnlInput.add(Box.createVerticalGlue());
		pnlInput.add(Box.createRigidArea(new Dimension(1, 220)));
		
		return pnlInput;
	}

	
	@Override
	protected JPanel createPanelOutput() {
		panelErgebnis = new PflanzungErgebnisPanel(this);
		return panelErgebnis;
	}

	
	@Override
	protected String getModelAsXmlString() {
		String xmlModelName = "<modellname>" + Messages.getString("Pflanzung.Titel") + "</modellname>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		
		String xmlEingaben = "<eingaben>"; //$NON-NLS-1$
		xmlEingaben += panelPflanzung.getAsXmlString();
		
		if (panelPflanzung.getPflanzverfahren() == Pflanzverfahren.WINKELPFLANZUNG) {
			xmlEingaben += panelPflanzungWinkelpflanzung.getAsXmlString();
		}

		xmlEingaben += panelPflanzungZeitenKosten.getAsXmlString();
		xmlEingaben += panelPersonal.getAsXmlString();
		xmlEingaben += "</eingaben>"; //$NON-NLS-1$
		
		String xmlErgebnis = getErgebnisAsXmlString(panelErgebnis);

		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlEingaben + xmlErgebnis;
		return result;
	}

	
	@Override
	protected String getXsltFilePath() {
		return "data/kalkulation2fo-pflanzung.xsl"; //$NON-NLS-1$
	}
	
	
	@Override
	protected Map<String, String> getAdditionalPdfTitles() {
		Map<String, String> result = new HashMap<>();

		result.put("beschaffung", Messages.getString("Pflanzung.Pdf.Beschaffung")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("transport", Messages.getString("Pflanzung.Pdf.Transport")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("pflanzung", Messages.getString("Pflanzung.Pdf.Pflanzung")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("unterhalt", Messages.getString("Pflanzung.Pdf.Unterhalt")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("gesamt", Messages.getString("Pflanzung.Pdf.Gesamt")); //$NON-NLS-1$ $NON-NLS-2$
		
		return result;
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation() {
		panelPflanzung.onInputChangedBeforeCalculation();
		panelPflanzungZeitenKosten.onInputChangedBeforeCalculation(
				panelPflanzung.getPflanzverfahren(), 
				panelPflanzung.getPflanztechnik(),  
				panelPflanzung.getBaumart(), 
				panelPflanzung.getSchwierigkeitsgrad(),
				panelPflanzungWinkelpflanzung.getWinkelpflanzungModel());
		
		boolean isWinkelpflanzung = panelPflanzung.getPflanzverfahren() == Pflanzverfahren.WINKELPFLANZUNG;
		panelPflanzungWinkelpflanzung.setEnabled(isWinkelpflanzung);
	}

	
	@Override
	protected void displayErgebnis() {
		PflanzungModel model = new PflanzungModel();
		
		model.setAnzahlPflanzen(panelPflanzung.getAnzahlPflanzen());
		model.setPflanzverfahren(panelPflanzung.getPflanzverfahren());
		model.setPflanztechnik(panelPflanzung.getPflanztechnik());
		model.setBaumart(panelPflanzung.getBaumart());
		model.setSchwierigkeitsgrad(panelPflanzung.getSchwierigkeitsgrad());

		model.setWinkelpflanzungModel(panelPflanzungWinkelpflanzung.getWinkelpflanzungModel());

		model.setZeitBeschaffung_h(panelPflanzungZeitenKosten.getZeitBeschaffung_h());
		model.setKostenProPflanze(panelPflanzungZeitenKosten.getKostenProPflanze());
		model.setZeitTransport_h(panelPflanzungZeitenKosten.getZeitTransport_h());
		model.setKostenTransportmittel(panelPflanzungZeitenKosten.getKostenTransportmittel());
		model.setZeitPflanzung_PflProH(panelPflanzungZeitenKosten.getZeitPflanzung_PflProH());
		model.setAnteilMaschinenlaufzeit_Prz(panelPflanzungZeitenKosten.getAnteilMaschinenlaufzeit_Prz());
		model.setKostensatzGeraet_proH(panelPflanzungZeitenKosten.getKostensatzGeraet_proH());
		model.setZeitUnterhalt_Prozent(panelPflanzungZeitenKosten.getZeitUnterhalt_Prozent());

		model.setKostenPersonalProPerson_proH(panelPersonal.getKostenPersonalProPerson_proH());
		model.setFaktorWegzeitenUndPausen(panelPersonal.getFaktorWegzeitenUndPausen());
		
		PflanzungErgebnis ergebnis = model.getErgebnis();
		panelErgebnis.setErgebnis(ergebnis);
	}

}
