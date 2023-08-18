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
public class SchneegleitenAbbauPanel extends AbstractInputPanel implements CurrencySensitive {
	
	private final AbstractMainWindow mainWindow;

	private final JLabel lblFahrzeugkosten_ChfProEinheit = new JLabel();
	private final JLabel lblEntsorgunggebuehren_ChfProEinheit = new JLabel();

	private final JSpinner txtPersonalzeit_minProEinheit = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1d));
	private final JSpinner txtFahrzeugkosten_ChfProEinheit = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1d));
	private final JSpinner txtEntsorgungsgebuehren_ChfProEinheit = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1d));
	
	
	public SchneegleitenAbbauPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Schneegleiten.TitelZeitenKostenAbbauEntsorgung")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(new JLabel(Messages.getString("Schneegleiten.ReineArbeitszeit"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtPersonalzeit_minProEinheit));
		
		this.add(lblFahrzeugkosten_ChfProEinheit);
		this.add(Utilities.getPanelWithoutInfoButton(txtFahrzeugkosten_ChfProEinheit));
		
		this.add(lblEntsorgunggebuehren_ChfProEinheit);
		this.add(Utilities.getPanelWithoutInfoButton(txtEntsorgungsgebuehren_ChfProEinheit));
		
		initFields();
		initListeners();
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	private void initFields() {
		txtPersonalzeit_minProEinheit.setEditor(new NumberEditor(txtPersonalzeit_minProEinheit, CURRENCY_NUMBER_FORMAT));
		txtFahrzeugkosten_ChfProEinheit.setEditor(new NumberEditor(txtFahrzeugkosten_ChfProEinheit, CURRENCY_NUMBER_FORMAT));
		txtEntsorgungsgebuehren_ChfProEinheit.setEditor(new NumberEditor(txtEntsorgungsgebuehren_ChfProEinheit, CURRENCY_NUMBER_FORMAT));

		AbstractMainWindow.adjustJSpinnerFormatter(txtPersonalzeit_minProEinheit, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtFahrzeugkosten_ChfProEinheit, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtEntsorgungsgebuehren_ChfProEinheit, false);
	}


	private void initListeners() {
		txtPersonalzeit_minProEinheit.addChangeListener(mainWindow.getDefaultChangeListener());
		txtFahrzeugkosten_ChfProEinheit.addChangeListener(mainWindow.getDefaultChangeListener());
		txtEntsorgungsgebuehren_ChfProEinheit.addChangeListener(mainWindow.getDefaultChangeListener());
	}
	
	
	public Double getPersonalzeit_minProEinheit() {
		return (Double) txtPersonalzeit_minProEinheit.getValue();
	}
	
	public Double getFahrzeugkosten_ChfProEinheit() {
		return (Double) txtFahrzeugkosten_ChfProEinheit.getValue();
	}
	
	public Double getEntsorgungsgebuehren_ChfProEinheit() {
		return (Double) txtEntsorgungsgebuehren_ChfProEinheit.getValue();
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Schneegleiten.TitelZeitenKostenAbbauEntsorgung") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Schneegleiten.ReineArbeitszeit"), getPersonalzeit_minProEinheit())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Schneegleiten.Fahrzeugkosten") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]", getFahrzeugkosten_ChfProEinheit())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		sb.append(getXmlEntry(Messages.getString("Schneegleiten.Entsorgungsgebuehren") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]", getFahrzeugkosten_ChfProEinheit())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


	@Override
	public void updateCurrency() {
		lblFahrzeugkosten_ChfProEinheit.setText(Messages.getString("Schneegleiten.Fahrzeugkosten") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		lblEntsorgunggebuehren_ChfProEinheit.setText(Messages.getString("Schneegleiten.Entsorgungsgebuehren") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		Arrays.asList(this.getComponents()).forEach(c -> c.setEnabled(enabled));
	}
	
	public void setAllFieldsToZero() {
		txtPersonalzeit_minProEinheit.setValue(0d);
		txtFahrzeugkosten_ChfProEinheit.setValue(0d);
		txtEntsorgungsgebuehren_ChfProEinheit.setValue(0d);
	}

}
