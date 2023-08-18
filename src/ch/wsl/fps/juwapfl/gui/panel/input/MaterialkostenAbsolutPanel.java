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
public class MaterialkostenAbsolutPanel extends AbstractInputPanel implements CurrencySensitive {
	
	private final AbstractMainWindow mainWindow;

	private final JLabel lblMaterialkosten = new JLabel();
	private final JSpinner txtMaterialkosten = new JSpinner(new SpinnerNumberModel(3.85, 0, 10000, 0.1));
	{
		txtMaterialkosten.setEditor(new NumberEditor(txtMaterialkosten, CURRENCY_NUMBER_FORMAT));
		AbstractMainWindow.adjustJSpinnerFormatter(txtMaterialkosten, false);
	}
	
	
	public MaterialkostenAbsolutPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Common.TitelMaterial")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(lblMaterialkosten);
		this.add(Utilities.getPanelWithoutInfoButton(txtMaterialkosten));
		
		initListeners();
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	private void initListeners() {
		txtMaterialkosten.addChangeListener(mainWindow.getDefaultChangeListener());
	}
	
	
	public Double getMaterialkosten() {
		return (Double) txtMaterialkosten.getValue();
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Common.TitelMaterialkosten") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Rueckegassen.Geraetekosten") + " [" + mainWindow.getCurrency() + Messages.getString("Common.proHektareKurz") + "]", getMaterialkosten())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


	@Override
	public void updateCurrency() {
		lblMaterialkosten.setText(Messages.getString("Rueckegassen.Geraetekosten") + " [" + mainWindow.getCurrency() + Messages.getString("Common.proHektareKurz") + "]");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
}
