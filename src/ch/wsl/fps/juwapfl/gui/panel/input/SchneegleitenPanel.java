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
import ch.wsl.fps.juwapfl.gui.main.SchneegleitenMainWindow;
import ch.wsl.fps.juwapfl.model.SchneegleitenModel.Gelaendeverhaeltnisse;
import ch.wsl.fps.juwapfl.model.SchneegleitenModel.Massnahme;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SchneegleitenPanel extends AbstractInputPanel {
	
	private static final int DEFAULT_ANZAHL_BAUTEN = 30;
	
	private final SchneegleitenMainWindow mainWindow;

	private final JComboBox<Massnahme> cmbMassnahme = new JComboBox<>(Massnahme.values());
	private final JLabel lblAnzahlBauten = new JLabel(Messages.getString("Schneegleiten.AnzahlBauten")); //$NON-NLS-1$
	private final JSpinner txtAnzahlBauten = new JSpinner(new SpinnerNumberModel(DEFAULT_ANZAHL_BAUTEN, 1, 1000, 1));
	private final JComboBox<Gelaendeverhaeltnisse> cmbGelaendeverhaeltnisse = new JComboBox<>(Gelaendeverhaeltnisse.values());
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlBauten, false);
	}
	

	public SchneegleitenPanel(SchneegleitenMainWindow mainWindow) {
		super(Messages.getString("Schneegleiten.Titel")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(new JLabel(Messages.getString("Schneegleiten.Massnahme"))); //$NON-NLS-1$
		String msg = Messages.getString("Schneegleiten.InfoButtonMassnahme"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbMassnahme, msg));
		
		this.add(lblAnzahlBauten);
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlBauten));
		
		this.add(new JLabel(Messages.getString("Schneegleiten.Gelaendeverhaeltnisse"))); //$NON-NLS-1$
		String infoButtonGelaendeverhaeltnisse = Messages.getString("Schneegleiten.InfoButtonGelaendeVerhaeltnisse"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbGelaendeverhaeltnisse, infoButtonGelaendeverhaeltnisse));
		
		cmbMassnahme.setSelectedItem(Massnahme.getDefault());
		cmbGelaendeverhaeltnisse.setSelectedItem(Gelaendeverhaeltnisse.getDefault());
		
		initListeners();
	}

	
	public void onInputChangedBeforeCalculation() {
		lblAnzahlBauten.setText(getAnzahlBautenLabel(getMassnahme()));
	}
	
	
	private static String getAnzahlBautenLabel(Massnahme massnahme) {
		switch(massnahme) {
		case PFAEHLE:
			return Messages.getString("Schneegleiten.AnzahlPfaehle"); //$NON-NLS-1$
			
		case BERMEN:
			return Messages.getString("Schneegleiten.AnzahlLaufmeterBermen"); //$NON-NLS-1$

		case SCHWELLEN:
			return Messages.getString("Schneegleiten.AnzahlSchwellen"); //$NON-NLS-1$

		case DREIBEINBOECKE:
			return Messages.getString("Schneegleiten.AnzahlDreibeinboecke"); //$NON-NLS-1$

		default:
			throw new RuntimeException("Unbekannte Massnahme: " + massnahme.name()); //$NON-NLS-1$
		}
	}
	
	
	private void initListeners() {
		cmbMassnahme.addActionListener(mainWindow.getDefaultActionListener());
		txtAnzahlBauten.addChangeListener(mainWindow.getDefaultChangeListener());
		cmbGelaendeverhaeltnisse.addActionListener(mainWindow.getDefaultActionListener());
	}


	public Massnahme getMassnahme() {
		return cmbMassnahme.getItemAt(cmbMassnahme.getSelectedIndex());
	}
	
	public Integer getAnzahlBauten() {
		return (Integer) txtAnzahlBauten.getValue();
	}
	
	public Gelaendeverhaeltnisse getGelaendeverhaeltnisse() {
		return cmbGelaendeverhaeltnisse.getItemAt(cmbGelaendeverhaeltnisse.getSelectedIndex());
	}
	

	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Schneegleiten.Titel") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Schneegleiten.Massnahme"), getMassnahme())); //$NON-NLS-1$
		sb.append(getXmlEntry(getAnzahlBautenLabel(getMassnahme()), getAnzahlBauten()));
		sb.append(getXmlEntry(Messages.getString("Schneegleiten.Gelaendeverhaeltnisse"), getGelaendeverhaeltnisse())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
