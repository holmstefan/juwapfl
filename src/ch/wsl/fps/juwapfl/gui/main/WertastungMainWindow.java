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
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.WertastungErgebnisPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.MaterialkostenPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.SimplePersonalPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.WertastungBestandPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.WertastungPanel;
import ch.wsl.fps.juwapfl.model.WertastungErgebnis;
import ch.wsl.fps.juwapfl.model.WertastungModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WertastungMainWindow extends AbstractMainWindow {

	private WertastungBestandPanel panelBestand;
	private WertastungPanel panelWertastung;
	private SimplePersonalPanel panelPersonal;
	private MaterialkostenPanel panelMaterialkosten;
	private WertastungErgebnisPanel panelErgebnis;

	
	public static void main(String[] args) {
		AbstractMainWindow.main(args, () -> new WertastungMainWindow());
	}
	
	
	public WertastungMainWindow() {
		this.setTitle(Messages.getString("Wertastung.FensterTitel")); //$NON-NLS-1$
		this.setSize(getWindowSize(550), getWindowSize(713));
		init();
	}


	@Override
	protected String[] getDokuPdfFileNames() {
		return new String[]{"DokuWertastung.pdf", null}; //$NON-NLS-1$
	}
	
	
	@Override
	protected String getModelAsXmlString() {
		String xmlModelName = "<modellname>" + Messages.getString("Wertastung.Titel") + "</modellname>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		
		String xmlEingaben = "<eingaben>"; //$NON-NLS-1$
		xmlEingaben += panelBestand.getAsXmlString();
		xmlEingaben += panelWertastung.getAsXmlString();
		xmlEingaben += panelPersonal.getAsXmlString();
		xmlEingaben += panelMaterialkosten.getAsXmlString();
		xmlEingaben += "</eingaben>"; //$NON-NLS-1$
		
		String xmlErgebnis = getErgebnisAsXmlString(panelErgebnis);

		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlEingaben + xmlErgebnis;
		return result;
	}
	
	@Override
	protected String getXsltFilePath() {
		return "data/kalkulation2fo-wertastung.xsl"; //$NON-NLS-1$
	}
	
	
	@Override
	protected Map<String, String> getAdditionalPdfTitles() {
		Map<String, String> result = new HashMap<>();
		
		result.put("personalzeiten-wpph", Messages.getString("Common.PersonalzeitenWPPH")); //$NON-NLS-1$ $NON-NLS-2$

		result.put("pro-baum", Messages.getString("Wertastung.proBaum")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("pro-hektare", Messages.getString("Wertastung.proHektare")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("bestand", Messages.getString("Wertastung.Bestand")); //$NON-NLS-1$ $NON-NLS-2$
		
		return result;
	}
	
	
	@Override
	protected JPanel createPanelInput() {
		JPanel pnlInput = new JPanel();
		initTitledBorder(pnlInput, Messages.getString("Common.TitelEingaben")); //$NON-NLS-1$
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.PAGE_AXIS));

		pnlInput.add(Box.createVerticalStrut(5));
		
		panelBestand = new WertastungBestandPanel(this);
		pnlInput.add(panelBestand);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelWertastung = new WertastungPanel(this);
		pnlInput.add(panelWertastung);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPersonal = new SimplePersonalPanel(this, false);
		pnlInput.add(panelPersonal);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelMaterialkosten = new MaterialkostenPanel(this);
		pnlInput.add(panelMaterialkosten);

		pnlInput.add(Box.createVerticalGlue());
		pnlInput.add(Box.createRigidArea(new Dimension(1, 220)));
		
		return pnlInput;
	}
	
	
	@Override
	protected void displayErgebnis() {
		WertastungModel model = new WertastungModel();
		
		model.setBaumart(panelBestand.getBaumart());
		model.setAnzahlAstungsbaeumeProHektar(panelBestand.getAnzahlBaeume());
		model.setFlaeche_ha(panelBestand.getAnzahlHektare());
		model.setHangneigung(panelBestand.getHangneigung());

		model.setEtappe(panelWertastung.getEtappe());
		model.setMethode(panelWertastung.getMethode());
		model.setHoehe1(panelWertastung.getHoeheUnten());
		model.setHoehe2(panelWertastung.getHoeheOben());
		model.setAstigkeit(panelWertastung.getAstigkeit());

		model.setKostenPersonalProPerson(panelPersonal.getKostenPersonalProPerson_proH());
		model.setFaktorWegzeitenUndPausen(panelPersonal.getFaktorWegzeitenUndPausen());

		model.setKorrekturfaktorMaterialkosten(panelMaterialkosten.getKorrekturfaktorMaterialkosten());

		WertastungErgebnis ergebnis = model.getErgebnis();
		panelErgebnis.setErgebnis(ergebnis);
	}
	
	
	@Override
	protected JPanel createPanelOutput() {
		panelErgebnis = new WertastungErgebnisPanel(this);
		return panelErgebnis;
	}

}
