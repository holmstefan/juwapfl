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
import ch.wsl.fps.juwapfl.gui.main.WertastungMainWindow;
import ch.wsl.fps.juwapfl.model.WertastungModel.Baumart;
import ch.wsl.fps.juwapfl.model.WertastungModel.Hangneigung;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WertastungBestandPanel extends AbstractInputPanel {
	
	private final WertastungMainWindow mainWindow;
	
	private final JComboBox<Baumart> cmbBaumart = new JComboBox<>(Baumart.values());
	private final JSpinner txtAnzahlBaeume = new JSpinner(new SpinnerNumberModel(30, 1, 200, 1));
	private final JSpinner txtAnzahlHektare = new JSpinner(new SpinnerNumberModel(1.5, 0.1, 1000, 0.5));
	private final JComboBox<Hangneigung> cmbHangneigung = new JComboBox<>(Hangneigung.values());
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlBaeume, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlHektare, false);
	}
	
	
	public WertastungBestandPanel(WertastungMainWindow mainWindow) {
		super(Messages.getString("Wertastung.Bestand")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(new JLabel(Messages.getString("Wertastung.Baumart"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbBaumart));
		
		this.add(new JLabel(Messages.getString("Wertastung.AnzahlAstungsbaeumeProHa"))); //$NON-NLS-1$
		String infoButtonText = Messages.getString("Wertastung.InfoButtonAnzahlAstungsbaeume"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtAnzahlBaeume, infoButtonText));
		
		this.add(new JLabel(Messages.getString("Wertastung.FlaecheBestand"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlHektare));
		
		this.add(new JLabel(Messages.getString("Wertastung.Hangneigung"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbHangneigung));

		cmbBaumart.setSelectedItem(Baumart.getDefault());
		cmbHangneigung.setSelectedItem(Hangneigung.getDefault());
		
		initListeners();
	}
	
	
	private void initListeners() {
		cmbBaumart.addActionListener(mainWindow.getDefaultActionListener());
		txtAnzahlBaeume.addChangeListener(mainWindow.getDefaultChangeListener());
		txtAnzahlHektare.addChangeListener(mainWindow.getDefaultChangeListener());
		cmbHangneigung.addActionListener(mainWindow.getDefaultActionListener());
	}
	
	
	public Baumart getBaumart() {
		return cmbBaumart.getItemAt(cmbBaumart.getSelectedIndex());
	}
	
	public Integer getAnzahlBaeume() {
		return (Integer) txtAnzahlBaeume.getValue();
	}
	
	public Double getAnzahlHektare() {
		return (Double) txtAnzahlHektare.getValue();
	}
	
	public Hangneigung getHangneigung() {
		return cmbHangneigung.getItemAt(cmbHangneigung.getSelectedIndex());
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Wertastung.Bestand") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Wertastung.Baumart"), getBaumart())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Wertastung.AnzahlAstungsbaeumeProHa"), getAnzahlBaeume())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Wertastung.FlaecheBestand"), getAnzahlHektare())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Wertastung.Hangneigung"), getHangneigung())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}
	
}
