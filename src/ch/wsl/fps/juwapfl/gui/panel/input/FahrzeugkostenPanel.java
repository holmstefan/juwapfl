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

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.CurrencySensitive;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;

/**
 * 
 * @author Stefan Holm
 *
 */
public class FahrzeugkostenPanel extends AbstractInputPanel implements CurrencySensitive {
	
	private final AbstractMainWindow mainWindow;

	private final JLabel lblKostenAufbau = new JLabel();
	private final JLabel lblKostenUnterhalt = new JLabel();
	private final JLabel lblKostenAbbau = new JLabel();
	
	private final JSpinner txtKostenAufbau = new JSpinner(new SpinnerNumberModel(0d, 0, 10000, 1));
	private final JSpinner txtKostenUnterhalt = new JSpinner(new SpinnerNumberModel(0d, 0, 10000, 1));
	private final JSpinner txtKostenAbbau = new JSpinner(new SpinnerNumberModel(0d, 0, 10000, 1));
	
	
	public FahrzeugkostenPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Common.TitelFahrzeugkosten")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(lblKostenAufbau);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenAufbau));
		
		this.add(lblKostenUnterhalt);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenUnterhalt));
		
		this.add(lblKostenAbbau);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenAbbau));
		
		initFields();
		initListeners();
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	private void initFields() {
		txtKostenAufbau.setEditor(new NumberEditor(txtKostenAufbau, CURRENCY_NUMBER_FORMAT));
		txtKostenUnterhalt.setEditor(new NumberEditor(txtKostenUnterhalt, CURRENCY_NUMBER_FORMAT));
		txtKostenAbbau.setEditor(new NumberEditor(txtKostenAbbau, CURRENCY_NUMBER_FORMAT));

		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenAufbau, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenUnterhalt, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenAbbau, false);
	}


	private void initListeners() {
		txtKostenAufbau.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenUnterhalt.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenAbbau.addChangeListener(mainWindow.getDefaultChangeListener());
	}
	
	
	public Double getFahrzeugkostenAufbauPauschal() {
		return (Double) txtKostenAufbau.getValue();
	}

	public Double getFahrzeugkostenUnterhaltPauschal() {
		return (Double) txtKostenUnterhalt.getValue();
	}
	
	public Double getFahrzeugkostenAbbauPauschal() {
		return (Double) txtKostenAbbau.getValue();
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Common.TitelFahrzeugkosten") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Common.FahrzeugkostenAufbauPauschal") + " [" + mainWindow.getCurrency() + "]", getFahrzeugkostenAufbauPauschal())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append(getXmlEntry(Messages.getString("Common.FahrzeugkostenUnterhaltPauschal") + " [" + mainWindow.getCurrency() + "]", getFahrzeugkostenUnterhaltPauschal())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append(getXmlEntry(Messages.getString("Common.FahrzeugkostenAbbauPauschal") + " [" + mainWindow.getCurrency() + "]", getFahrzeugkostenAbbauPauschal())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


	@Override
	public void updateCurrency() {
		lblKostenAufbau.setText(Messages.getString("Common.FahrzeugkostenAufbauPauschal") + " [" + mainWindow.getCurrency() + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		lblKostenUnterhalt.setText(Messages.getString("Common.FahrzeugkostenUnterhaltPauschal") + " [" + mainWindow.getCurrency() + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		lblKostenAbbau.setText(Messages.getString("Common.FahrzeugkostenAbbauPauschal") + " [" + mainWindow.getCurrency() + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
	}
	
}
