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
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.CurrencySensitive;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.controls.JFlashingSpinner;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.model.SchneegleitenModel;
import ch.wsl.fps.juwapfl.model.SchneegleitenModel.Massnahme;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SchneegleitenErstellenPanel extends AbstractInputPanel implements CurrencySensitive {
	
	private final AbstractMainWindow mainWindow;
	
	private final JLabel lblMaterialkosten_ChfProEinheit = new JLabel();
	private final JLabel lblMaschinenkosten_ChfProEinheit = new JLabel();
	private final JLabel lblTransportkosten_ChfProEinheit = new JLabel();

	private final JFlashingSpinner txtPersonalzeit_minProEinheit = new JFlashingSpinner(new SpinnerNumberModel(0, 0, 1000, 1d));
	private final JFlashingSpinner txtMaterialkosten_ChfProEinheit = new JFlashingSpinner(new SpinnerNumberModel(0, 0, 1000, 1d));
	private final JFlashingSpinner txtMaschinenkosten_ChfProEinheit = new JFlashingSpinner(new SpinnerNumberModel(0, 0, 1000, 1d));
	private final JFlashingSpinner txtTransportkosten_ChfProEinheit = new JFlashingSpinner(new SpinnerNumberModel(0, 0, 1000, 1d));
	
	private Massnahme massnahme;
	
	
	public SchneegleitenErstellenPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Schneegleiten.TitelZeitenUndKostenErstellen")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(new JLabel(Messages.getString("Schneegleiten.ReineArbeitszeit"))); //$NON-NLS-1$
		String infoButtonTextReineArbeitszeit = Messages.getString("Schneegleiten.InfoButtonReineArbeitszeit"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtPersonalzeit_minProEinheit, infoButtonTextReineArbeitszeit));
		
		this.add(lblMaterialkosten_ChfProEinheit);
		this.add(Utilities.getPanelWithoutInfoButton(txtMaterialkosten_ChfProEinheit));
		
		this.add(lblMaschinenkosten_ChfProEinheit);
		this.add(Utilities.getPanelWithoutInfoButton(txtMaschinenkosten_ChfProEinheit));
		
		this.add(lblTransportkosten_ChfProEinheit);
		String infoButtonTextTransportkosten = Messages.getString("Schneegleiten.InfoButtonTransportkosten"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtTransportkosten_ChfProEinheit, infoButtonTextTransportkosten));
		
		initFields();
		initListeners();
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	private void initFields() {
		txtPersonalzeit_minProEinheit.setEditor(new NumberEditor(txtPersonalzeit_minProEinheit, CURRENCY_NUMBER_FORMAT));
		txtMaterialkosten_ChfProEinheit.setEditor(new NumberEditor(txtMaterialkosten_ChfProEinheit, CURRENCY_NUMBER_FORMAT));
		txtMaschinenkosten_ChfProEinheit.setEditor(new NumberEditor(txtMaschinenkosten_ChfProEinheit, CURRENCY_NUMBER_FORMAT));
		txtTransportkosten_ChfProEinheit.setEditor(new NumberEditor(txtTransportkosten_ChfProEinheit, CURRENCY_NUMBER_FORMAT));

		AbstractMainWindow.adjustJSpinnerFormatter(txtPersonalzeit_minProEinheit, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtMaterialkosten_ChfProEinheit, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtMaschinenkosten_ChfProEinheit, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtTransportkosten_ChfProEinheit, false);
	}


	private void initListeners() {
		txtPersonalzeit_minProEinheit.addChangeListener(mainWindow.getDefaultChangeListener());
		txtMaterialkosten_ChfProEinheit.addChangeListener(mainWindow.getDefaultChangeListener());
		txtMaschinenkosten_ChfProEinheit.addChangeListener(mainWindow.getDefaultChangeListener());
		txtTransportkosten_ChfProEinheit.addChangeListener(mainWindow.getDefaultChangeListener());
	}

	
	public void onInputChangedBeforeCalculation(Massnahme massnahme) {
		if (this.massnahme != massnahme) {
			this.massnahme = massnahme;
			txtPersonalzeit_minProEinheit.setValue(SchneegleitenModel.getDefaultErstellenPersonalzeit_minProEinheit(massnahme));
			txtMaterialkosten_ChfProEinheit.setValue(SchneegleitenModel.getDefaultErstellenMaterialkosten_ChfProEinheit(massnahme));
			txtMaschinenkosten_ChfProEinheit.setValue(SchneegleitenModel.getDefaultErstellenMaschinenkosten_ChfProEinheit(massnahme));
			
			if (mainWindow.isInitializing == false) {
				txtPersonalzeit_minProEinheit.flash();
				txtMaterialkosten_ChfProEinheit.flash();
				txtMaschinenkosten_ChfProEinheit.flash();
			}
		}
	}
	
	
	public Double getPersonalzeit_minProEinheit() {
		return (Double) txtPersonalzeit_minProEinheit.getValue();
	}
	
	public Double getMaterialkosten_ChfProEinheit() {
		return (Double) txtMaterialkosten_ChfProEinheit.getValue();
	}
	
	public Double getMaschinenkosten_ChfProEinheit() {
		return (Double) txtMaschinenkosten_ChfProEinheit.getValue();
	}
	
	public Double getTransportkosten_ChfProEinheit() {
		return (Double) txtTransportkosten_ChfProEinheit.getValue();
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Schneegleiten.TitelZeitenUndKostenErstellen") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Schneegleiten.ReineArbeitszeit"), getPersonalzeit_minProEinheit())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Schneegleiten.Materialkosten") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]", getMaterialkosten_ChfProEinheit())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		sb.append(getXmlEntry(Messages.getString("Schneegleiten.Geraetekosten") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]", getMaschinenkosten_ChfProEinheit())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		sb.append(getXmlEntry(Messages.getString("Schneegleiten.Transportkosten") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]", getTransportkosten_ChfProEinheit())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


	@Override
	public void updateCurrency() {
		lblMaterialkosten_ChfProEinheit.setText(Messages.getString("Schneegleiten.Materialkosten") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		lblMaschinenkosten_ChfProEinheit.setText(Messages.getString("Schneegleiten.Geraetekosten") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		lblTransportkosten_ChfProEinheit.setText(Messages.getString("Schneegleiten.Transportkosten") + " [" + mainWindow.getCurrency() + Messages.getString("Schneegleiten.ProEinheit") + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}
