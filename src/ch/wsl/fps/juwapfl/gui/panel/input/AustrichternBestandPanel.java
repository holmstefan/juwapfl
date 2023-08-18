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

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.AustrichternMainWindow;
import ch.wsl.fps.juwapfl.model.AustrichternModel.Hangneigung;
import ch.wsl.fps.juwapfl.model.AustrichternModel.MaschinenGeraete;
import ch.wsl.fps.juwapfl.model.AustrichternModel.RueckegassenVorhanden;
import ch.wsl.fps.juwapfl.model.AustrichternModel.Schutzsystem;
import ch.wsl.fps.juwapfl.model.AustrichternModel.Temperatur;
import ch.wsl.fps.juwapfl.model.AustrichternModel.Verunkrautung;

/**
 * 
 * @author Stefan Holm
 *
 */
public class AustrichternBestandPanel extends AbstractInputPanel {
	
	private final AustrichternMainWindow mainWindow;

	private final JSpinner txtFlaeche_ha = new JSpinner(new SpinnerNumberModel(0.8, 0.1, 10, 0.1));
	private final JComboBox<MaschinenGeraete> cmbMaschinenGeraete = new JComboBox<>(MaschinenGeraete.values());
	private final JSpinner txtPflanzabstandInReihen_m = new JSpinner(new SpinnerNumberModel(5, 2, 20, 1));
	private final JSpinner txtPflanzabstandZwischenReihen_m = new JSpinner(new SpinnerNumberModel(5, 2, 20, 1));
	private final JSpinner txtAnzahlPflanzenProHektar = new JSpinner(new SpinnerNumberModel(400, 1, 2500, 1));
	private final JCheckBox chkDirekteingabe = new JCheckBox(Messages.getString("Austrichtern.Direkteingabe")); //$NON-NLS-1$
	private final JComboBox<Verunkrautung> cmbVerunkrautung = new JComboBox<>(Verunkrautung.values());
	private final JComboBox<Hangneigung> cmbHangneigung = new JComboBox<>(Hangneigung.values());
	private final JComboBox<RueckegassenVorhanden> cmbRueckegassenVorhanden = new JComboBox<>(RueckegassenVorhanden.values());
	private final JComboBox<Schutzsystem> cmbSchutzsystem = new JComboBox<>(Schutzsystem.values());
	private final JComboBox<Temperatur> cmbTemperatur = new JComboBox<>(Temperatur.values());
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtFlaeche_ha, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtPflanzabstandInReihen_m, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtPflanzabstandZwischenReihen_m, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlPflanzenProHektar, false);
	}
	
	
	public AustrichternBestandPanel(AustrichternMainWindow mainWindow) {
		super(Messages.getString("Austrichtern.Bestand")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(new JLabel(Messages.getString("Austrichtern.Flaeche"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtFlaeche_ha));
		
		this.add(new JLabel(Messages.getString("Austrichtern.MaschinenOderGeraete"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbMaschinenGeraete));
		
		this.add(new JLabel(Messages.getString("Austrichtern.PflanzabstandInReihen"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtPflanzabstandInReihen_m));
		
		this.add(new JLabel(Messages.getString("Austrichtern.PflanzabstandZwischenReihen"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtPflanzabstandZwischenReihen_m));
		
		this.add(new JLabel(Messages.getString("Austrichtern.AnzahlPflanzenProHa"))); //$NON-NLS-1$
		JPanel pnlAnzahlPflanzen = new JPanel(new GridLayout(0, 2, 5, 0));
		pnlAnzahlPflanzen.add(Utilities.getPanelWithoutInfoButton(txtAnzahlPflanzenProHektar));
		pnlAnzahlPflanzen.add(chkDirekteingabe);
		this.add(pnlAnzahlPflanzen);
		
		this.add(new JLabel(Messages.getString("Austrichtern.Verunkrautung"))); //$NON-NLS-1$
		String msg = "<html>" //$NON-NLS-1$
				+ "<li><b>" + Messages.getString("Austrichtern.Verunkrautung.schwach") + ":</b> " + Messages.getString("Austrichtern.Verunkrautung.Hinweis.schwach") + "</li>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ "<li><b>" + Messages.getString("Austrichtern.Verunkrautung.mittel") + ":</b> " + Messages.getString("Austrichtern.Verunkrautung.Hinweis.mittel") + "</li>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ "<li><b>" + Messages.getString("Austrichtern.Verunkrautung.stark") + ":</b> " + Messages.getString("Austrichtern.Verunkrautung.Hinweis.stark") + "</li>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ "<li><b>" + Messages.getString("Austrichtern.Verunkrautung.extremStark") + ":</b> " + Messages.getString("Austrichtern.Verunkrautung.Hinweis.extremStark") + "</li>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ "</html>"; //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbVerunkrautung, msg));
		
		this.add(new JLabel(Messages.getString("Austrichtern.Hangneigung"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbHangneigung));
		
		this.add(new JLabel(Messages.getString("Austrichtern.RueckegassenVorhanden"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbRueckegassenVorhanden));

		this.add(new JLabel(Messages.getString("Austrichtern.Schutzsystem"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbSchutzsystem));
		
		this.add(new JLabel(Messages.getString("Austrichtern.Lufttemperatur"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbTemperatur));
		
		cmbMaschinenGeraete.setSelectedItem(MaschinenGeraete.getDefault());
		cmbVerunkrautung.setSelectedItem(Verunkrautung.getDefault());
		cmbHangneigung.setSelectedItem(Hangneigung.getDefault());
		cmbRueckegassenVorhanden.setSelectedItem(RueckegassenVorhanden.getDefault());
		cmbSchutzsystem.setSelectedItem(Schutzsystem.getDefault());
		cmbTemperatur.setSelectedItem(Temperatur.getDefault());
		
		initListeners();
	}
	
	
	private void initListeners() {
		txtFlaeche_ha.addChangeListener(mainWindow.getDefaultChangeListener());
		cmbMaschinenGeraete.addActionListener(mainWindow.getDefaultActionListener());
		
		txtPflanzabstandInReihen_m.addChangeListener(e -> calculateAnzahlPflanzen());
		txtPflanzabstandZwischenReihen_m.addChangeListener(e -> calculateAnzahlPflanzen());
		
		txtAnzahlPflanzenProHektar.addChangeListener(mainWindow.getDefaultChangeListener());
		
		chkDirekteingabe.addItemListener(event -> {
			if (chkDirekteingabe.isSelected()) {
				txtPflanzabstandInReihen_m.setEnabled(false);
				txtPflanzabstandZwischenReihen_m.setEnabled(false);
				
				txtAnzahlPflanzenProHektar.setEnabled(true);
			}
			else {
				txtPflanzabstandInReihen_m.setEnabled(true);
				txtPflanzabstandZwischenReihen_m.setEnabled(true);
				
				txtAnzahlPflanzenProHektar.setEnabled(false);
			}
		});
		chkDirekteingabe.setSelected(true);
		chkDirekteingabe.setSelected(false);
		
		cmbVerunkrautung.addActionListener(mainWindow.getDefaultActionListener());
		cmbHangneigung.addActionListener(mainWindow.getDefaultActionListener());
		cmbRueckegassenVorhanden.addActionListener(mainWindow.getDefaultActionListener());
		cmbSchutzsystem.addActionListener(mainWindow.getDefaultActionListener());
		cmbTemperatur.addActionListener(mainWindow.getDefaultActionListener());
	}
	
	
	private void calculateAnzahlPflanzen() {
		if (chkDirekteingabe.isSelected() == false) {
			Integer a = (Integer) txtPflanzabstandInReihen_m.getValue();
			Integer b = (Integer) txtPflanzabstandZwischenReihen_m.getValue();
			
			if (a != null && b != null) {
				txtAnzahlPflanzenProHektar.setValue((int) Math.round(10000d / (a * b)));
			}
		}
	}
	
	
	public Double getFlaeche_ha() {
		return (Double) txtFlaeche_ha.getValue();
	}
	
	public MaschinenGeraete getMaschinenGeraete() {
		return cmbMaschinenGeraete.getItemAt(cmbMaschinenGeraete.getSelectedIndex());
	}
	
	private Integer getPflanzabstandInReihen_m() {
		return (Integer) txtPflanzabstandInReihen_m.getValue();
	}
	
	private Integer getPflanzabstandZwischenReihen_m() {
		return (Integer) txtPflanzabstandZwischenReihen_m.getValue();
	}
	
	public Integer getAnzahlPflanzenProHektar() {
		return (Integer) txtAnzahlPflanzenProHektar.getValue();
	}
	
	public Verunkrautung getVerunkrautung() {
		return cmbVerunkrautung.getItemAt(cmbVerunkrautung.getSelectedIndex());
	}
	
	public Hangneigung getHangneigung() {
		return cmbHangneigung.getItemAt(cmbHangneigung.getSelectedIndex());
	}
	
	public RueckegassenVorhanden getRueckegassenVorhanden() {
		return cmbRueckegassenVorhanden.getItemAt(cmbRueckegassenVorhanden.getSelectedIndex());
	}
	
	public Schutzsystem getSchutzsystem() {
		return cmbSchutzsystem.getItemAt(cmbSchutzsystem.getSelectedIndex());
	}
	
	public Temperatur getTemperatur() {
		return cmbTemperatur.getItemAt(cmbTemperatur.getSelectedIndex());
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Austrichtern.Bestand") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Austrichtern.Flaeche"), getFlaeche_ha())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Austrichtern.MaschinenOderGeraete"), getMaschinenGeraete())); //$NON-NLS-1$
		if (chkDirekteingabe.isSelected() == false) {
			sb.append(getXmlEntry(Messages.getString("Austrichtern.PflanzabstandInReihen"), getPflanzabstandInReihen_m())); //$NON-NLS-1$
			sb.append(getXmlEntry(Messages.getString("Austrichtern.PflanzabstandZwischenReihen"), getPflanzabstandZwischenReihen_m())); //$NON-NLS-1$
		}
		sb.append(getXmlEntry(Messages.getString("Austrichtern.AnzahlPflanzenProHa"), getAnzahlPflanzenProHektar())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Austrichtern.Verunkrautung"), getVerunkrautung())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Austrichtern.Hangneigung"), getHangneigung())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Austrichtern.RueckegassenVorhanden"), getRueckegassenVorhanden())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Austrichtern.Schutzsystem"), getSchutzsystem())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Austrichtern.Lufttemperatur"), getTemperatur())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
