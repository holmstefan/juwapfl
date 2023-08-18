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
import ch.wsl.fps.juwapfl.gui.main.AustrichternMainWindow;
import ch.wsl.fps.juwapfl.model.AustrichternModel;
import ch.wsl.fps.juwapfl.model.AustrichternModel.MaschinenGeraete;

/**
 * 
 * @author Stefan Holm
 *
 */
public class AustrichternPersonalMaschinenPanel extends AbstractInputPanel implements CurrencySensitive {
	
	private final AustrichternMainWindow mainWindow;
	private final AustrichternBestandPanel panelBestand;

	private final JLabel lblKostenPersonalProPerson = new JLabel();
	private final JSpinner txtKostenPersonalProPerson = new JSpinner(new SpinnerNumberModel(70, 0, 500, 1));
	private final JLabel lblKostenMaschine = new JLabel();
	private final JSpinner txtKostenMaschine = new JSpinner(new SpinnerNumberModel(14.0, 0, 500, 1));
	private final JLabel lblKostenSichel = new JLabel();
	private final JSpinner txtKostenSichel = new JSpinner(new SpinnerNumberModel(AustrichternModel.DEFAULT_KOSTEN_SICHEL_PRO_HA, 0, 10, 0.05));
	private final JSpinner txtArbeitszeit = new JSpinner(new SpinnerNumberModel(510, 60, 1000, 10));
	private final JSpinner txtPausen = new JSpinner(new SpinnerNumberModel(60, 0, 1000, 10));
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenPersonalProPerson, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenMaschine, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenSichel, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtArbeitszeit, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtPausen, false);
		
		txtKostenSichel.setEditor(new NumberEditor(txtKostenSichel, CURRENCY_NUMBER_FORMAT));
	}
	
	
	public AustrichternPersonalMaschinenPanel(AustrichternMainWindow mainWindow, AustrichternBestandPanel panelBestand) {
		super(Messages.getString("Austrichtern.PersonalUndMaschinen")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.panelBestand = panelBestand;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(lblKostenPersonalProPerson);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenPersonalProPerson));

		this.add(lblKostenMaschine);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenMaschine));
		
		this.add(lblKostenSichel);
		String infoButtonText1 = Messages.getString("Austrichtern.InfoButtonSichel"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtKostenSichel, infoButtonText1));
		
		this.add(new JLabel(Messages.getString("Common.TaeglicheArbeitszeit"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtArbeitszeit));
		
		this.add(new JLabel("   " + Messages.getString("Common.DavonBezahlteWegzeitenUndPausen"))); //$NON-NLS-1$ //$NON-NLS-2$
		this.add(Utilities.getPanelWithoutInfoButton(txtPausen));

		updatePausenMaximum();
		initListeners();
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	private void initListeners() {
		txtKostenPersonalProPerson.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenMaschine.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenSichel.addChangeListener(mainWindow.getDefaultChangeListener());
		txtArbeitszeit.addChangeListener(mainWindow.getDefaultChangeListener());
		txtArbeitszeit.addChangeListener(e -> updatePausenMaximum());
		txtPausen.addChangeListener(mainWindow.getDefaultChangeListener());
	}
	
	private void updatePausenMaximum() {
		int arbeitszeit = (int) txtArbeitszeit.getValue();
		int maximum = arbeitszeit - 10;
		((SpinnerNumberModel)txtPausen.getModel()).setMaximum(maximum);
		if ((int)txtPausen.getValue() > maximum) {
			txtPausen.setValue(maximum);
		}
	}
	
	public void onInputChangedBeforeCalculation() {
		MaschinenGeraete maschinenGeraete = panelBestand.getMaschinenGeraete();
		if (maschinenGeraete == MaschinenGeraete.FREISCHNEIDER_MOTORSAEGE) {
			txtKostenMaschine.setEnabled(true);
			txtKostenSichel.setEnabled(false);
		}
		else {
			txtKostenMaschine.setEnabled(false);
			txtKostenSichel.setEnabled(true);
		}
	}
	
	
	public Integer getKostenPersonalProPerson() {
		return (Integer) txtKostenPersonalProPerson.getValue();
	}
	
	public Double getKostenMaschine() {
		return (Double) txtKostenMaschine.getValue();
	}
	
	public Double getKostenSichel() {
		return (Double) txtKostenSichel.getValue();
	}
	
	public double getFaktorWegzeitenUndPausen() {
		int arbeitszeit = (int) txtArbeitszeit.getValue();
		int pausen = (int) txtPausen.getValue();
		double faktor = (0d + arbeitszeit) / (arbeitszeit - pausen);
		return faktor;
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Common.Personal") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Common.Personalkostensatz") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]", getKostenPersonalProPerson()));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		if (panelBestand.getMaschinenGeraete() == MaschinenGeraete.FREISCHNEIDER_MOTORSAEGE) {
			sb.append(getXmlEntry(Messages.getString("Common.KostenMaschine") + " [" + mainWindow.getCurrency() + Messages.getString("Austrichtern.proLiterTreibstoff") + "]", getKostenMaschine()));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		else {
			sb.append(getXmlEntry(Messages.getString("Austrichtern.GeraetekostenSichel") + " [" + mainWindow.getCurrency() + Messages.getString("Common.proHektare") + "]", getKostenSichel()));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		sb.append(getXmlEntry(Messages.getString("Common.TaeglicheArbeitszeit"), txtArbeitszeit.getValue())); //$NON-NLS-1$
		sb.append(getXmlEntry("&#160;&#160;" + Messages.getString("Common.DavonBezahlteWegzeitenUndPausen"), txtPausen.getValue())); //$NON-NLS-1$ //$NON-NLS-2$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


	@Override
	public void updateCurrency() {
		lblKostenPersonalProPerson.setText(Messages.getString("Common.Personalkostensatz") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		lblKostenMaschine.setText(Messages.getString("Common.KostenMaschine") + " [" + mainWindow.getCurrency() + Messages.getString("Austrichtern.proLiterTreibstoff") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		
		lblKostenSichel.setText(Messages.getString("Austrichtern.GeraetekostenSichel") + " [" + mainWindow.getCurrency() + Messages.getString("Common.proHektare") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
}
