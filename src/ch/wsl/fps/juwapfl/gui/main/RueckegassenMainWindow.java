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
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.RueckegassenErgebnisPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.MaterialkostenAbsolutPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.RueckegassenObjektPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.RueckegassenPersonalPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.RueckegassenVerfahrenPanel;
import ch.wsl.fps.juwapfl.model.RueckegassenErgebnis;
import ch.wsl.fps.juwapfl.model.RueckegassenModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class RueckegassenMainWindow extends AbstractMainWindow {

	private RueckegassenObjektPanel panelObjekt;
	private RueckegassenVerfahrenPanel panelVerfahren;
	private RueckegassenPersonalPanel panelPersonal;
	private MaterialkostenAbsolutPanel panelMaterialkosten;
	private RueckegassenErgebnisPanel panelErgebnis;

	
	public static void main(String[] args) {
		RueckegassenMainWindow.main(args, () -> new RueckegassenMainWindow());
	}
	
	
	public RueckegassenMainWindow() {
		this.setTitle(Messages.getString("Rueckegassen.FensterTitel")); //$NON-NLS-1$
		this.setSize(getWindowSize(520), getWindowSize(670));
		init();
	}
	
	
	@Override
	protected String[] getDokuPdfFileNames() {
		return new String[]{"DokuRueckegassenA.pdf", "DokuRueckegassenB.pdf"}; //$NON-NLS-1$ //$NON-NLS-2$
	}

	
	@Override
	protected JPanel createPanelInput() {
		JPanel pnlInput = new JPanel();
		initTitledBorder(pnlInput, Messages.getString("Common.TitelEingaben")); //$NON-NLS-1$
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.PAGE_AXIS));

		pnlInput.add(Box.createVerticalStrut(5));
		
		panelObjekt = new RueckegassenObjektPanel(this);
		pnlInput.add(panelObjekt);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelVerfahren = new RueckegassenVerfahrenPanel(this);
		pnlInput.add(panelVerfahren);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPersonal = new RueckegassenPersonalPanel(this);
		pnlInput.add(panelPersonal);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelMaterialkosten = new MaterialkostenAbsolutPanel(this);
		pnlInput.add(panelMaterialkosten);

		pnlInput.add(Box.createVerticalGlue());
		pnlInput.add(Box.createRigidArea(new Dimension(1, 220)));
		
		return pnlInput;
	}

	
	@Override
	protected JPanel createPanelOutput() {
		panelErgebnis = new RueckegassenErgebnisPanel(this);
		return panelErgebnis;
	}

	
	@Override
	protected String getModelAsXmlString() {
		String xmlModelName = "<modellname>" + Messages.getString("Rueckegassen.Titel") + "</modellname>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		
		String xmlEingaben = "<eingaben>"; //$NON-NLS-1$
		xmlEingaben += panelObjekt.getAsXmlString();
		xmlEingaben += panelVerfahren.getAsXmlString();
		xmlEingaben += panelPersonal.getAsXmlString();
		xmlEingaben += panelMaterialkosten.getAsXmlString();
		xmlEingaben += "</eingaben>"; //$NON-NLS-1$
		
		String xmlErgebnis = getErgebnisAsXmlString(panelErgebnis);

		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlEingaben + xmlErgebnis;
		return result;
	}

	
	@Override
	protected String getXsltFilePath() {
		return "data/kalkulation2fo-rueckegassen.xsl"; //$NON-NLS-1$
	}
	
	
	@Override
	protected Map<String, String> getAdditionalPdfTitles() {
		Map<String, String> result = new HashMap<>();
		
		result.put("personalzeiten-wpph", Messages.getString("Common.PersonalzeitenWPPH")); //$NON-NLS-1$ $NON-NLS-2$

		result.put("pro-hektare", Messages.getString("Rueckegassen.proHektare")); //$NON-NLS-1$ $NON-NLS-2$
		result.put("gesamtflaeche", Messages.getString("Rueckegassen.proGesamtflaeche")); //$NON-NLS-1$ $NON-NLS-2$
		
		return result;
	}

	
	@Override
	protected void displayErgebnis() {
		RueckegassenModel model = new RueckegassenModel();
		
		model.setFlaeche_ha(panelObjekt.getAnzahlHektare());
		model.setVerhaeltnisse(panelObjekt.getVerhaeltnisse());
		model.setAbstandRueckegassen_m(panelObjekt.getAbstandRueckegassen_m());
		
		model.setHilfskraft(panelVerfahren.getHilfskraft());
		model.setOrtskenntnisse(panelVerfahren.getOrtskenntnisse());
		model.setUebungPlanungsverfahren(panelVerfahren.getUebungPlanungsverfahren());
		
		model.setKostenHauptperson_proH(panelPersonal.getKostenHauptperson());
		model.setKostenHilfskraft_proH(panelPersonal.getKostenHilfskraft());
		model.setKostenGeraete_proH(panelMaterialkosten.getMaterialkosten());
		model.setFaktorWegzeitenUndPausen(panelPersonal.getFaktorWegzeitenUndPausen());

		RueckegassenErgebnis ergebnis = model.getErgebnis();
		panelErgebnis.setErgebnis(ergebnis);
	}

}
