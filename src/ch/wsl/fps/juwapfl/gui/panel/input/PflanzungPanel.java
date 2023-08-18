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
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.PflanzungMainWindow;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Baumart;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Pflanztechnik;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Pflanzverfahren;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Schwierigkeitsgrad;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungPanel extends AbstractInputPanel {
	
	private final int DEFAULT_ANZAHL_PFLANZEN = 200;
	
	private final PflanzungMainWindow mainWindow;
	
	private final JLabel lblPflanztechnik = new JLabel(Messages.getString("Pflanzung.Pflanztechnik")); //$NON-NLS-1$
	private final JLabel lblBaumart = new JLabel(Messages.getString("Pflanzung.Baumart")); //$NON-NLS-1$

	private final JSpinner txtAnzahlPflanzen = new JSpinner(new SpinnerNumberModel(DEFAULT_ANZAHL_PFLANZEN, 0, 10000, 1));
	private final JComboBox<Pflanzverfahren> cmbPflanzverfahren = new JComboBox<>(Pflanzverfahren.values());
	private final JComboBox<Pflanztechnik> cmbPflanztechnik = new JComboBox<>(Pflanztechnik.values());
	private final JComboBox<Baumart> cmbBaumart = new JComboBox<>(Baumart.values());
	private final JComboBox<Schwierigkeitsgrad> cmbSchwierigkeitsgrad = new JComboBox<>(Schwierigkeitsgrad.values());
	
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlPflanzen, false);
	}

	
	public PflanzungPanel(PflanzungMainWindow mainWindow) {
		super(Messages.getString("Pflanzung.Titel"), Messages.getString("Pflanzung.InfoButtonPflanzungPanel")); //$NON-NLS-1$ //$NON-NLS-2$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(new JLabel(Messages.getString("Pflanzung.AnzahlPflanzen"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlPflanzen));

		this.add(new JLabel(Messages.getString("Pflanzung.Pflanzverfahren"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbPflanzverfahren));
		
		this.add(lblPflanztechnik);
		this.add(Utilities.getPanelWithoutInfoButton(cmbPflanztechnik));
		
		this.add(lblBaumart);
		this.add(Utilities.getPanelWithoutInfoButton(cmbBaumart));
		
		this.add(new JLabel(Messages.getString("Pflanzung.Schwierigkeitsgrad"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbSchwierigkeitsgrad));
		
		cmbPflanzverfahren.setSelectedItem(Pflanzverfahren.getDefault());
		cmbPflanztechnik.setSelectedItem(Pflanztechnik.getDefault());
		cmbBaumart.setSelectedItem(Baumart.getDefault());
		cmbSchwierigkeitsgrad.setSelectedItem(Schwierigkeitsgrad.getDefault());

		updateCombos();
		initListeners();
	}
	

	private void initListeners() {
		txtAnzahlPflanzen.addChangeListener(mainWindow.getDefaultChangeListener());
		cmbPflanzverfahren.addActionListener(mainWindow.getDefaultActionListener());
		cmbPflanztechnik.addActionListener(mainWindow.getDefaultActionListener());
		cmbBaumart.addActionListener(mainWindow.getDefaultActionListener());
		cmbSchwierigkeitsgrad.addActionListener(mainWindow.getDefaultActionListener());
	}

	
	public void onInputChangedBeforeCalculation() {
		updateCombos();
	}
	
	
	private void updateCombos() {
		updateCmbPflanztechnik();
		updateCmbBaumart();
		updateCmbSchwierigkeitsgrad();
	}
	
	private void updateCmbPflanztechnik() {
		// save selection
		Pflanztechnik selection = (Pflanztechnik) cmbPflanztechnik.getSelectedItem();
		
		// refill combo with correct items
		cmbPflanztechnik.removeAllItems();
		Pflanztechnik[] pflanztechniken = Pflanztechnik.getPflanztechniken((Pflanzverfahren) cmbPflanzverfahren.getSelectedItem());
		Arrays.asList(pflanztechniken).forEach(item -> cmbPflanztechnik.addItem(item));
		cmbPflanztechnik.setEnabled(cmbPflanztechnik.getItemCount() > 1);
		
		// restore selection
		if (selection != null) {
			cmbPflanztechnik.setSelectedItem(selection);
		}
	}
	
	private void updateCmbBaumart() {
		// save selection
		Baumart selection = (Baumart) cmbBaumart.getSelectedItem();
		
		// refill combo with correct items
		cmbBaumart.removeAllItems();
		boolean isWinkelpflanzung = cmbPflanzverfahren.getSelectedItem() == Pflanzverfahren.WINKELPFLANZUNG;
		if (isWinkelpflanzung) {
			Arrays.asList(Baumart.values()).forEach(item -> cmbBaumart.addItem(item));
		}
		cmbBaumart.setEnabled(cmbBaumart.getItemCount() > 1);
		
		// restore selection
		if (selection != null) {
			cmbBaumart.setSelectedItem(selection);
		}
	}
	
	private void updateCmbSchwierigkeitsgrad() {
		boolean isWinkelpflanzung = cmbPflanzverfahren.getSelectedItem() == Pflanzverfahren.WINKELPFLANZUNG;
		cmbSchwierigkeitsgrad.setEnabled(isWinkelpflanzung == false);
		if (isWinkelpflanzung && cmbSchwierigkeitsgrad.getItemCount() > 0) {
			cmbSchwierigkeitsgrad.removeAllItems();
		}
		if (isWinkelpflanzung == false && cmbSchwierigkeitsgrad.getItemCount() == 0) {
			Arrays.asList(Schwierigkeitsgrad.values()).forEach(item -> cmbSchwierigkeitsgrad.addItem(item));
			cmbSchwierigkeitsgrad.setSelectedItem(Schwierigkeitsgrad.getDefault());
		}
	}


	public int getAnzahlPflanzen() {
		return (Integer) txtAnzahlPflanzen.getValue();
	}
	
	public Pflanzverfahren getPflanzverfahren() {
		return cmbPflanzverfahren.getItemAt(cmbPflanzverfahren.getSelectedIndex());
	}
	
	public Pflanztechnik getPflanztechnik() {
		return cmbPflanztechnik.getItemAt(cmbPflanztechnik.getSelectedIndex());
	}
	
	public Baumart getBaumart() {
		return cmbBaumart.getItemAt(cmbBaumart.getSelectedIndex());
	}
	
	public Schwierigkeitsgrad getSchwierigkeitsgrad() {
		return cmbSchwierigkeitsgrad.getItemAt(cmbSchwierigkeitsgrad.getSelectedIndex());
	}
	

	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Pflanzung.Titel") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Pflanzung.AnzahlPflanzen"), getAnzahlPflanzen())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.Pflanzverfahren"), getPflanzverfahren())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.Pflanztechnik"), getPflanztechnik())); //$NON-NLS-1$
		
		if (cmbBaumart.isEnabled()) {
			sb.append(getXmlEntry(Messages.getString("Pflanzung.Baumart"), getBaumart())); //$NON-NLS-1$
		}
		
		if (cmbSchwierigkeitsgrad.isEnabled()) {
			sb.append(getXmlEntry(Messages.getString("Pflanzung.Schwierigkeitsgrad"), getSchwierigkeitsgrad())); //$NON-NLS-1$
		}
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
