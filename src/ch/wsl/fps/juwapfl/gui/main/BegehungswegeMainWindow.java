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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.BegehungswegeErgebnisPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.BegehungswegePanel;
import ch.wsl.fps.juwapfl.gui.panel.input.BegehungswegePersonalPanel;
import ch.wsl.fps.juwapfl.gui.panel.input.BegehungswegeZeitenPanel;
import ch.wsl.fps.juwapfl.model.BegehungswegeErgebnis;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BegehungswegeMainWindow extends AbstractMainWindow {

	private BegehungswegePanel panelBegehungswege;
	private BegehungswegeZeitenPanel panelZeiten;
	private BegehungswegePersonalPanel panelPersonal;
	private BegehungswegeErgebnisPanel panelErgebnis;

	
	public static void main(String[] args) {
		AbstractMainWindow.main(args, () -> new BegehungswegeMainWindow());
	}
	
	
	public BegehungswegeMainWindow() {
		this.setTitle(Messages.getString("Begehungswege.FensterTitel")); //$NON-NLS-1$
		this.setSize(getWindowSize(540), getWindowSize(550));
		init();
	}

	
	@Override
	protected String[] getDokuPdfFileNames() {
		return new String[]{"DokuBegehungswege.pdf", null}; //$NON-NLS-1$
	}

	
	@Override
	protected JPanel createPanelInput() {
		JPanel pnlInput = new JPanel();
		initTitledBorder(pnlInput, Messages.getString("Common.TitelEingaben")); //$NON-NLS-1$
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.PAGE_AXIS));

		pnlInput.add(Box.createVerticalStrut(5));
		
		panelBegehungswege = new BegehungswegePanel(this);
		pnlInput.add(panelBegehungswege);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelZeiten = new BegehungswegeZeitenPanel(this);
		pnlInput.add(panelZeiten);
		
		pnlInput.add(Box.createVerticalStrut(10));
		
		panelPersonal = new BegehungswegePersonalPanel(this);
		pnlInput.add(panelPersonal);

		pnlInput.add(Box.createVerticalGlue());
		pnlInput.add(Box.createRigidArea(new Dimension(1, 220)));
		
		return pnlInput;
	}

	
	@Override
	protected JPanel createPanelOutput() {
		panelErgebnis = new BegehungswegeErgebnisPanel(this);
		return panelErgebnis;
	}

	
	@Override
	protected String getModelAsXmlString() {
		String xmlModelName = "<modellname>" + Messages.getString("Begehungswege.Titel") + "</modellname>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		
		String xmlEingaben = "<eingaben>"; //$NON-NLS-1$
		xmlEingaben += panelBegehungswege.getAsXmlString();
		xmlEingaben += panelZeiten.getAsXmlString();
		xmlEingaben += panelPersonal.getAsXmlString();
		xmlEingaben += "</eingaben>"; //$NON-NLS-1$
		
		String xmlErgebnis = getErgebnisAsXmlString(panelErgebnis);

		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlEingaben + xmlErgebnis;
		return result;
	}

	
	@Override
	protected String getXsltFilePath() {
		return "data/kalkulation2fo-begehungswege.xsl"; //$NON-NLS-1$
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation() {
		panelZeiten.onInputChangedBeforeCalculation(panelBegehungswege.getBegehungsweg(), panelBegehungswege.getAusfuehrung(), panelBegehungswege.getGelaendeschwierigkeit());
	}
	
	
	@Override
	protected void displayErgebnis() {
		BegehungswegeModel model = new BegehungswegeModel();

		model.setBegehungsweg(panelBegehungswege.getBegehungsweg());
		model.setAusfuehrung(panelBegehungswege.getAusfuehrung());
		model.setAnzahlLaufmeter(panelBegehungswege.getAnzahlLaufmeter());
		model.setGelaendeSchwierigkeit(panelBegehungswege.getGelaendeschwierigkeit());
		
		model.setZeitaufwand_minProM(panelZeiten.getZeitaufwand_minProM());

		model.setKostenPersonal(panelPersonal.getKostenPersonal());
		model.setKostenMaschine(panelPersonal.getKostenMaschine());
		model.setFaktorWegzeitenUndPausen(panelPersonal.getFaktorWegzeitenUndPausen());
		
		BegehungswegeErgebnis ergebnis = model.getErgebnis();
		panelErgebnis.setErgebnis(ergebnis);
	}

}
