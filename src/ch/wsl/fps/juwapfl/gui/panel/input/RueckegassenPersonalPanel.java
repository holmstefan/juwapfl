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
public class RueckegassenPersonalPanel extends AbstractInputPanel implements CurrencySensitive {
	
	private final AbstractMainWindow mainWindow;

	private final JLabel lblKostenHauptperson = new JLabel();
	private final JSpinner txtKostenHauptperson = new JSpinner(new SpinnerNumberModel(80, 0, 1000, 1));
	private final JLabel lblKostenHilfskraft = new JLabel();
	private final JSpinner txtKostenHilfskraft = new JSpinner(new SpinnerNumberModel(50, 0, 1000, 1));
	private final JSpinner txtArbeitszeit = new JSpinner(new SpinnerNumberModel(510, 60, 1000, 10));
	private final JSpinner txtPausen = new JSpinner(new SpinnerNumberModel(60, 0, 1000, 10));
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenHauptperson, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenHilfskraft, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtArbeitszeit, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtPausen, false);
	}
	
	
	public RueckegassenPersonalPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Common.TitelPersonal")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(lblKostenHauptperson);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenHauptperson));

		this.add(lblKostenHilfskraft);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenHilfskraft));
		
		this.add(new JLabel(Messages.getString("Common.TaeglicheArbeitszeit"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtArbeitszeit));
		
		this.add(new JLabel("   " + Messages.getString("Common.DavonBezahlteWegzeitenUndPausen"))); //$NON-NLS-1$ //$NON-NLS-2$
		this.add(Utilities.getPanelWithoutInfoButton(txtPausen));

		updatePausenMaximum();
		initListeners();
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	private void initListeners() {
		txtKostenHauptperson.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenHilfskraft.addChangeListener(mainWindow.getDefaultChangeListener());
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
	
	
	public Integer getKostenHauptperson() {
		return (Integer) txtKostenHauptperson.getValue();
	}
	
	public Integer getKostenHilfskraft() {
		return (Integer) txtKostenHilfskraft.getValue();
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

		sb.append("<section name=\"" + Messages.getString("Common.TitelPersonal") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Rueckegassen.Hauptperson") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]", getKostenHauptperson())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		sb.append(getXmlEntry(Messages.getString("Rueckegassen.Hilfskraft") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]", getKostenHilfskraft())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		sb.append(getXmlEntry(Messages.getString("Common.TaeglicheArbeitszeit"), txtArbeitszeit.getValue())); //$NON-NLS-1$
		sb.append(getXmlEntry("&#160;&#160;" + Messages.getString("Common.DavonBezahlteWegzeitenUndPausen"), txtPausen.getValue())); //$NON-NLS-1$ //$NON-NLS-2$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


	@Override
	public void updateCurrency() {
		lblKostenHauptperson.setText(Messages.getString("Rueckegassen.Hauptperson") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		lblKostenHilfskraft.setText(Messages.getString("Rueckegassen.Hilfskraft") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
}
