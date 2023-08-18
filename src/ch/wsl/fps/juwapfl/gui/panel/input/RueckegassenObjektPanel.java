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
import ch.wsl.fps.juwapfl.model.RueckegassenModel.Verhaeltnisse;

/**
 * 
 * @author Stefan Holm
 *
 */
public class RueckegassenObjektPanel extends AbstractInputPanel {
	
	private final AbstractMainWindow mainWindow;

	private final JSpinner txtAnzahlHektare = new JSpinner(new SpinnerNumberModel(50, 1.0, 1000, 0.5));
	private final JComboBox<Verhaeltnisse> cmbVerhaeltnisse = new JComboBox<>(Verhaeltnisse.values());
	private final JSpinner txtAbstandRueckegassen = new JSpinner(new SpinnerNumberModel(30, 20, 60, 1));
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlHektare, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtAbstandRueckegassen, false);
	}

	
	public RueckegassenObjektPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Rueckegassen.TitelObjekt")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(new JLabel(Messages.getString("Rueckegassen.FlaecheHa"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlHektare));

		this.add(new JLabel(Messages.getString("Rueckegassen.Verhaeltnisse"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbVerhaeltnisse, Messages.getString("Rueckegassen.VerhaeltnisseInfoButton"))); //$NON-NLS-1$
		
		this.add(new JLabel(Messages.getString("Rueckegassen.AbstandRueckegassen"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAbstandRueckegassen));

		cmbVerhaeltnisse.setSelectedItem(Verhaeltnisse.getDefault());
		
		initListeners();
	}
	
	
	private void initListeners() {
		txtAnzahlHektare.addChangeListener(mainWindow.getDefaultChangeListener());
		cmbVerhaeltnisse.addActionListener(mainWindow.getDefaultActionListener());
		txtAbstandRueckegassen.addChangeListener(mainWindow.getDefaultChangeListener());
	}
	
	
	public Double getAnzahlHektare() {
		return (Double) txtAnzahlHektare.getValue();
	}
	
	public Verhaeltnisse getVerhaeltnisse() {
		return cmbVerhaeltnisse.getItemAt(cmbVerhaeltnisse.getSelectedIndex());
	}
	
	public Integer getAbstandRueckegassen_m() {
		return (Integer) txtAbstandRueckegassen.getValue();
	}
	

	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Rueckegassen.TitelObjekt") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Rueckegassen.FlaecheHa"), getAnzahlHektare())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Rueckegassen.Verhaeltnisse"), getVerhaeltnisse())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Rueckegassen.AbstandRueckegassen"), getAbstandRueckegassen_m())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
