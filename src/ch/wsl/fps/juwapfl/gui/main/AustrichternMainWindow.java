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
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.AustrichternErgebnisPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.AustrichternBestandPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.AustrichternPersonalMaschinenPanel;
import ch.wsl.fps.juwapfl.model.AustrichternErgebnis;
import ch.wsl.fps.juwapfl.model.AustrichternModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class AustrichternMainWindow extends AbstractMainWindow {

	private AustrichternBestandPanel panelBestand;
	private AustrichternPersonalMaschinenPanel panelPersonalMaschinen;
	private AustrichternErgebnisPanel panelErgebnis;

	
	public static void main(String[] args) {
		AbstractMainWindow.main(args, () -> new AustrichternMainWindow());
	}
	
	
	public AustrichternMainWindow() {
		this.setTitle(Messages.getString("Austrichtern.FensterTitel")); //$NON-NLS-1$
		this.setSize(getWindowSize(520), getWindowSize(655));
		init();
	}


	@Override
	protected String[] getDokuPdfFileNames() {
		return new String[]{"DokuAustrichtern.pdf", null}; //$NON-NLS-1$
	}
	
	
	@Override
	protected String getModelAsXmlString() {
		String xmlModelName = "<modellname>" + Messages.getString("Austrichtern.Titel") + "</modellname>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		
		String xmlEingaben = "<eingaben>"; //$NON-NLS-1$
		xmlEingaben += panelBestand.getAsXmlString();
		xmlEingaben += panelPersonalMaschinen.getAsXmlString();
		xmlEingaben += "</eingaben>"; //$NON-NLS-1$
		
		String xmlErgebnis = getErgebnisAsXmlString(panelErgebnis);

		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlEingaben + xmlErgebnis;
		return result;
	}
	
	@Override
	protected String getXsltFilePath() {
		return "data/kalkulation2fo-austrichtern.xsl"; //$NON-NLS-1$
	}
	
	
	@Override
	protected Map<String, String> getAdditionalPdfTitles() {
		Map<String, String> result = new HashMap<>();

		result.put("pro-pflanze", Messages.getString("Austrichtern.proPflanze")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("pro-hektare", Messages.getString("Austrichtern.proHektare")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("bestand", Messages.getString("Austrichtern.Bestand")); //$NON-NLS-1$ $NON-NLS-2$
		
		return result;
	}
	
	
	@Override
	protected JPanel createPanelInput() {
		JPanel pnlInput = new JPanel();
		initTitledBorder(pnlInput, Messages.getString("Common.TitelEingaben")); //$NON-NLS-1$
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.PAGE_AXIS));

		pnlInput.add(Box.createVerticalStrut(5));
		
		panelBestand = new AustrichternBestandPanel(this);
		pnlInput.add(panelBestand);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPersonalMaschinen = new AustrichternPersonalMaschinenPanel(this, panelBestand);
		pnlInput.add(panelPersonalMaschinen);

		pnlInput.add(Box.createVerticalGlue());
		pnlInput.add(Box.createRigidArea(new Dimension(1, 220)));
		
		return pnlInput;
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation() {
		panelPersonalMaschinen.onInputChangedBeforeCalculation();
	}
	
	
	@Override
	protected void displayErgebnis() {
		AustrichternModel model = new AustrichternModel();
		
		model.setFlaeche_ha(panelBestand.getFlaeche_ha());
		model.setMaschinenGeraete(panelBestand.getMaschinenGeraete());
		model.setAnzahlPflanzenProHa(panelBestand.getAnzahlPflanzenProHektar());
		model.setVerunkrautung(panelBestand.getVerunkrautung());
		model.setHangneigung(panelBestand.getHangneigung());
		model.setRueckegassenVorhanden(panelBestand.getRueckegassenVorhanden());
		model.setSchutzsystem(panelBestand.getSchutzsystem());
		model.setTemperatur(panelBestand.getTemperatur());

		model.setKostenPersonal_proH(panelPersonalMaschinen.getKostenPersonalProPerson());
		model.setKostenMotorsaege_proLiter(panelPersonalMaschinen.getKostenMaschine());
		model.setKostenSichel_proHektar(panelPersonalMaschinen.getKostenSichel());
		model.setFaktorWegzeitenUndPausen(panelPersonalMaschinen.getFaktorWegzeitenUndPausen());
		
		AustrichternErgebnis ergebnis = model.getErgebnis();
		panelErgebnis.setErgebnis(ergebnis);
	}
	
	
	@Override
	protected JPanel createPanelOutput() {
		panelErgebnis = new AustrichternErgebnisPanel(this);
		return panelErgebnis;
	}

}
