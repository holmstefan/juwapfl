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
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.ZBaumDurchforstungErgebnisPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.ZBaumDurchforstungBestandPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.ZBaumDurchforstungKronenschnittPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.ZBaumDurchforstungPersonalMaschinenPanel;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungErgebnis;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ZBaumDurchforstungMainWindow extends AbstractMainWindow {

	private ZBaumDurchforstungBestandPanel panelBestand;
	private ZBaumDurchforstungKronenschnittPanel panelKronenschnitt;
	private ZBaumDurchforstungPersonalMaschinenPanel panelPersonalMaschinen;
	private ZBaumDurchforstungErgebnisPanel panelErgebnis;

	
	public static void main(String[] args) {
		AbstractMainWindow.main(args, () -> new ZBaumDurchforstungMainWindow());
	}
	
	
	public ZBaumDurchforstungMainWindow() {
		this.setTitle(Messages.getString("ZBaumDurchforstung.FensterTitel")); //$NON-NLS-1$
		this.setSize(getWindowSize(760), getWindowSize(800));
		init();
	}


	@Override
	protected String[] getDokuPdfFileNames() {
		return new String[]{"DokuZBaumDurchforstung.pdf", null}; //$NON-NLS-1$
	}
	
	
	@Override
	protected String getModelAsXmlString() {
		String xmlModelName = "<modellname>" + Messages.getString("ZBaumDurchforstung.Titel") + "</modellname>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		
		String xmlEingaben = "<eingaben>"; //$NON-NLS-1$
		xmlEingaben += panelBestand.getAsXmlString();
		xmlEingaben += panelKronenschnitt.getAsXmlString();
		xmlEingaben += panelPersonalMaschinen.getAsXmlString();
		xmlEingaben += "</eingaben>"; //$NON-NLS-1$
		
		String xmlErgebnis = getErgebnisAsXmlString(panelErgebnis);

		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlEingaben + xmlErgebnis;
		return result;
	}
	
	@Override
	protected String getXsltFilePath() {
		return "data/kalkulation2fo-zbaum.xsl"; //$NON-NLS-1$
	}
	
	
	@Override
	protected Map<String, String> getAdditionalPdfTitles() {
		Map<String, String> result = new HashMap<>();

		result.put("pro-z-baum", Messages.getString("ZBaumDurchforstung.proZBaum")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("pro-hektare", Messages.getString("ZBaumDurchforstung.proHektare")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("bestand", Messages.getString("ZBaumDurchforstung.Bestand")); //$NON-NLS-1$ $NON-NLS-2$
		
		return result;
	}
	
	
	@Override
	protected JPanel createPanelInput() {
		JPanel pnlInput = new JPanel();
		initTitledBorder(pnlInput, Messages.getString("Common.TitelEingaben")); //$NON-NLS-1$
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.PAGE_AXIS));

		pnlInput.add(Box.createVerticalStrut(5));
		
		panelBestand = new ZBaumDurchforstungBestandPanel(this);
		pnlInput.add(panelBestand);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelKronenschnitt = new ZBaumDurchforstungKronenschnittPanel(this, panelBestand);
		pnlInput.add(panelKronenschnitt);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPersonalMaschinen = new ZBaumDurchforstungPersonalMaschinenPanel(this, panelBestand);
		pnlInput.add(panelPersonalMaschinen);

		pnlInput.add(Box.createVerticalGlue());
		pnlInput.add(Box.createRigidArea(new Dimension(1, 220)));
		
		return pnlInput;
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation() {
		panelBestand.onInputChangedBeforeCalculation();
		panelKronenschnitt.onInputChangedBeforeCalculation();
		panelPersonalMaschinen.onInputChangedBeforeCalculation();
	}
	
	
	@Override
	protected void displayErgebnis() {
		ZBaumDurchforstungModel model = new ZBaumDurchforstungModel();
		
		model.setFlaeche_ha(panelBestand.getFlaeche_ha());
		model.setEntwicklungsstufe(panelBestand.getEntwicklungsstufe());
		model.setAnzahlZBaeumeProHa(panelBestand.getAnzahlZBaeumeProHektare());
		model.setAnzahlKonkurrentenProZBaum(panelBestand.getAnzahlKonkurrentenProZBaum());
		model.setBaendeln(panelBestand.getBaendeln());
		model.setHangneigung(panelBestand.getHangneigung());
		model.setBelaubung(panelBestand.getBelaubung());
		model.setVerunkrautung(panelBestand.getVerunkrautung());
		model.setRueckegassenVorhanden(panelBestand.getRueckegassenVorhanden());
		model.setTemperatur(panelBestand.getTemperatur());
		model.setZersaegen(panelBestand.getZersaegen());

		model.setAnzahlZBaeumeMitKronenschnittProHektare(panelKronenschnitt.getAnzahlZBaeumeMitKronenschnittProHektare());
		model.setAusfuehrungshoeheKronenschnitt(panelKronenschnitt.getAusfuehrungshoeheKronenschnitt());
		
		model.setKostenPersonal_proH(panelPersonalMaschinen.getKostenPersonalProPerson());
		model.setKostenMotorsaege_proLiter(panelPersonalMaschinen.getKostenMotorsaege());
		model.setKostenKronenschnittUnten_proBaum(panelPersonalMaschinen.getKostenKronenschnittUnten());
		model.setKostenKronenschnittOben_proBaum(panelPersonalMaschinen.getKostenKronenschnittOben());
		model.setFaktorWegzeitenUndPausen(panelPersonalMaschinen.getFaktorWegzeitenUndPausen());
		
		ZBaumDurchforstungErgebnis ergebnis = model.getErgebnis();
		panelErgebnis.setErgebnis(ergebnis);
	}
	
	
	@Override
	protected JPanel createPanelOutput() {
		panelErgebnis = new ZBaumDurchforstungErgebnisPanel(this);
		return panelErgebnis;
	}

}
