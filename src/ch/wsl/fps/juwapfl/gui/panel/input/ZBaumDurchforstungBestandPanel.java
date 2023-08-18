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
package ch.wsl.fps.juwapfl.gui.panel.input;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.ZBaumDurchforstungMainWindow;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Baendeln;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Belaubung;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Entwicklungsstufe;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Hangneigung;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.RueckegassenVorhanden;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Temperatur;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Verunkrautung;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Zersaegen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ZBaumDurchforstungBestandPanel extends AbstractInputPanel {
	
	private final ZBaumDurchforstungMainWindow mainWindow;

	private final JSpinner txtFlaeche_ha = new JSpinner(new SpinnerNumberModel(0.8, 0.1, 10, 0.1));
	private final JComboBox<Entwicklungsstufe> cmbEntwicklungsstufe = new JComboBox<>(Entwicklungsstufe.values());
	private final JSpinner txtAnzahlZBaeumeProHektar = new JSpinner(new SpinnerNumberModel(80, 1, 2000, 1));
	private final JSpinner txtAnzahlKonkurrentenProZBaum = new JSpinner(new SpinnerNumberModel(2.5, 1, 15, 0.5));
	private final JComboBox<Baendeln> cmbBaendeln = new JComboBox<>(Baendeln.values());
	private final JComboBox<Hangneigung> cmbHangneigung = new JComboBox<>(Hangneigung.values());
	private final JComboBox<Belaubung> cmbBelaubung = new JComboBox<>(Belaubung.values());
	private final JComboBox<Verunkrautung> cmbVerunkrautung = new JComboBox<>(Verunkrautung.values());
	private final JComboBox<RueckegassenVorhanden> cmbRueckegassenVorhanden = new JComboBox<>(RueckegassenVorhanden.values());
	private final JComboBox<Temperatur> cmbTemperatur = new JComboBox<>(Temperatur.values());
	private final JComboBox<Zersaegen> cmbZersaegen = new JComboBox<>(Zersaegen.values());
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtFlaeche_ha, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlZBaeumeProHektar, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlKonkurrentenProZBaum, false);
	}
	
	
	public ZBaumDurchforstungBestandPanel(ZBaumDurchforstungMainWindow mainWindow) {
		super(Messages.getString("ZBaumDurchforstung.Bestand")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.Flaeche"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtFlaeche_ha));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.Entwicklungsstufe"))); //$NON-NLS-1$
		String infoTextEntwicklungsstufe = Messages.getString("ZBaumDurchforstung.InfoButtonEntwicklungsstufe"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbEntwicklungsstufe, infoTextEntwicklungsstufe));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.AnzahlZBaeumeProHa"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlZBaeumeProHektar));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.AnzahlKonkurrentenProZBaum"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlKonkurrentenProZBaum));

		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.Baendeln"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbBaendeln));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.Hangneigung"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbHangneigung));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.Belaubung"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbBelaubung));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.Verunkrautung"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbVerunkrautung));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.RueckegassenVorhanden"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbRueckegassenVorhanden));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.Lufttemperatur"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbTemperatur));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.Zersaegen"))); //$NON-NLS-1$
		String msg = "<html>" //$NON-NLS-1$
				+ "<li><b>" + Messages.getString("ZBaumDurchforstung.Zersaegen.minimal") + ":</b> " + Messages.getString("ZBaumDurchforstung.Zersaegen.Hinweis.minimal") + "</li>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ "<li><b>" + Messages.getString("ZBaumDurchforstung.Zersaegen.mittel") + ":</b> " + Messages.getString("ZBaumDurchforstung.Zersaegen.Hinweis.mittel") + "</li>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ "<li><b>" + Messages.getString("ZBaumDurchforstung.Zersaegen.hoch") + ":</b> " + Messages.getString("ZBaumDurchforstung.Zersaegen.Hinweis.hoch") + "</li>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ "<li><b>" + Messages.getString("ZBaumDurchforstung.Zersaegen.extrem") + ":</b> " + Messages.getString("ZBaumDurchforstung.Zersaegen.Hinweis.extrem") + "</li>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ "</html>"; //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbZersaegen, msg));
		
		cmbEntwicklungsstufe.setSelectedItem(Entwicklungsstufe.getDefault());
		cmbBaendeln.setSelectedItem(Baendeln.getDefault());
		cmbHangneigung.setSelectedItem(Hangneigung.getDefault());
		cmbBelaubung.setSelectedItem(Belaubung.getDefault());
		cmbVerunkrautung.setSelectedItem(Verunkrautung.getDefault());
		cmbRueckegassenVorhanden.setSelectedItem(RueckegassenVorhanden.getDefault());
		cmbTemperatur.setSelectedItem(Temperatur.getDefault());
		cmbZersaegen.setSelectedItem(Zersaegen.getDefault());
		
		initListeners();
	}
	
	
	private void initListeners() {
		txtFlaeche_ha.addChangeListener(mainWindow.getDefaultChangeListener());
		cmbEntwicklungsstufe.addActionListener(mainWindow.getDefaultActionListener());
		txtAnzahlZBaeumeProHektar.addChangeListener(mainWindow.getDefaultChangeListener());
		txtAnzahlKonkurrentenProZBaum.addChangeListener(mainWindow.getDefaultChangeListener());
		cmbBaendeln.addActionListener(mainWindow.getDefaultActionListener());
		cmbHangneigung.addActionListener(mainWindow.getDefaultActionListener());
		cmbBelaubung.addActionListener(mainWindow.getDefaultActionListener());
		cmbVerunkrautung.addActionListener(mainWindow.getDefaultActionListener());
		cmbRueckegassenVorhanden.addActionListener(mainWindow.getDefaultActionListener());
		cmbTemperatur.addActionListener(mainWindow.getDefaultActionListener());
		cmbZersaegen.addActionListener(mainWindow.getDefaultActionListener());
	}

	
	public void onInputChangedBeforeCalculation() {
		adaptCmbVerunkrautung();
	}
	
	private void adaptCmbVerunkrautung() {
		// Rückstufung bei Dickung -> Stangenholz 1.
		if (getEntwicklungsstufe() == Entwicklungsstufe.STANGENHOLZ_1 && getVerunkrautung() == Verunkrautung.EXTREM_STARK) {
			cmbVerunkrautung.setSelectedItem(Verunkrautung.STARK);
		}
		
		// Element "EXTREM_STARK" hinzufügen bzw. entfernen.
		if (getEntwicklungsstufe() == Entwicklungsstufe.DICKUNG && cmbVerunkrautung.getItemCount() == 3) {
			cmbVerunkrautung.addItem(Verunkrautung.EXTREM_STARK);
		}
		else if (getEntwicklungsstufe() == Entwicklungsstufe.STANGENHOLZ_1) {
			cmbVerunkrautung.removeItem(Verunkrautung.EXTREM_STARK);
			
		}
	}
	
	
	public Double getFlaeche_ha() {
		return (Double) txtFlaeche_ha.getValue();
	}
	
	public Entwicklungsstufe getEntwicklungsstufe() {
		return cmbEntwicklungsstufe.getItemAt(cmbEntwicklungsstufe.getSelectedIndex());
	}
	
	public Integer getAnzahlZBaeumeProHektare() {
		return (Integer) txtAnzahlZBaeumeProHektar.getValue();
	}
	
	public Double getAnzahlKonkurrentenProZBaum() {
		return (Double) txtAnzahlKonkurrentenProZBaum.getValue();
	}
	
	public Baendeln getBaendeln() {
		return cmbBaendeln.getItemAt(cmbBaendeln.getSelectedIndex());
	}
	
	public Hangneigung getHangneigung() {
		return cmbHangneigung.getItemAt(cmbHangneigung.getSelectedIndex());
	}
	
	public Belaubung getBelaubung() {
		return cmbBelaubung.getItemAt(cmbBelaubung.getSelectedIndex());
	}
	
	public Verunkrautung getVerunkrautung() {
		return cmbVerunkrautung.getItemAt(cmbVerunkrautung.getSelectedIndex());
	}
	
	public RueckegassenVorhanden getRueckegassenVorhanden() {
		return cmbRueckegassenVorhanden.getItemAt(cmbRueckegassenVorhanden.getSelectedIndex());
	}
	
	public Temperatur getTemperatur() {
		return cmbTemperatur.getItemAt(cmbTemperatur.getSelectedIndex());
	}
	
	public Zersaegen getZersaegen() {
		return cmbZersaegen.getItemAt(cmbZersaegen.getSelectedIndex());
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("ZBaumDurchforstung.Bestand") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.Flaeche"), getFlaeche_ha())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.Entwicklungsstufe"), getEntwicklungsstufe())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.AnzahlZBaeumeProHa"), getAnzahlZBaeumeProHektare())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.AnzahlKonkurrentenProZBaum"), getAnzahlKonkurrentenProZBaum())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.Baendeln"), getBaendeln())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.Hangneigung"), getHangneigung())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.Belaubung"), getBelaubung())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.Verunkrautung"), getVerunkrautung())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.RueckegassenVorhanden"), getRueckegassenVorhanden())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.Lufttemperatur"), getTemperatur())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.Zersaegen"), getZersaegen())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
