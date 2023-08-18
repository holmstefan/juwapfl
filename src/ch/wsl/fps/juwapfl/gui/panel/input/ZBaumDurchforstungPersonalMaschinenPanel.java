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
import ch.wsl.fps.juwapfl.gui.main.ZBaumDurchforstungMainWindow;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Entwicklungsstufe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ZBaumDurchforstungPersonalMaschinenPanel extends AbstractInputPanel implements CurrencySensitive {
	
	private final ZBaumDurchforstungMainWindow mainWindow;
	private final ZBaumDurchforstungBestandPanel panelBestand;

	private final JLabel lblKostenPersonalProPerson = new JLabel();
	private final JSpinner txtKostenPersonalProPerson = new JSpinner(new SpinnerNumberModel(70, 0, 500, 1));
	private final JLabel lblKostenMotorsaege = new JLabel();
	private final JSpinner txtKostenMotorsaege = new JSpinner(new SpinnerNumberModel(14.0, 0, 500, 1));
	private final JLabel lblKostenKronenschnittUnten = new JLabel();
	private final JSpinner txtKostenKronenschnittUnten = new JSpinner(new SpinnerNumberModel(ZBaumDurchforstungModel.DEFAULT_KOSTEN_KRONENSCHNITT_PRO_BAUM_UNTEN, 0, 10, 0.05));
	private final JLabel lblKostenKronenschnittOben = new JLabel();
	private final JSpinner txtKostenKronenschnittOben = new JSpinner(new SpinnerNumberModel(ZBaumDurchforstungModel.DEFAULT_KOSTEN_KRONENSCHNITT_PRO_BAUM_OBEN, 0, 10, 0.05));
	private final JSpinner txtArbeitszeit = new JSpinner(new SpinnerNumberModel(510, 60, 1000, 10));
	private final JSpinner txtPausen = new JSpinner(new SpinnerNumberModel(60, 0, 1000, 10));
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenPersonalProPerson, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenMotorsaege, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenKronenschnittUnten, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenKronenschnittOben, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtArbeitszeit, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtPausen, false);
		
		txtKostenKronenschnittUnten.setEditor(new NumberEditor(txtKostenKronenschnittUnten, CURRENCY_NUMBER_FORMAT));
		txtKostenKronenschnittOben.setEditor(new NumberEditor(txtKostenKronenschnittOben, CURRENCY_NUMBER_FORMAT));
	}
	
	
	public ZBaumDurchforstungPersonalMaschinenPanel(ZBaumDurchforstungMainWindow mainWindow, ZBaumDurchforstungBestandPanel panelBestand) {
		super(Messages.getString("ZBaumDurchforstung.PersonalUndMaschinen")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.panelBestand = panelBestand;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(lblKostenPersonalProPerson);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenPersonalProPerson));

		this.add(lblKostenMotorsaege);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenMotorsaege));
		
		this.add(lblKostenKronenschnittUnten);
		String infoButtonText1 = Messages.getString("ZBaumDurchforstung.InfoButtonKostenKronenschnitt"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtKostenKronenschnittUnten, infoButtonText1));
		
		this.add(lblKostenKronenschnittOben);
		String infoButtonText2 = Messages.getString("ZBaumDurchforstung.InfoButtonKostenKronenschnitt"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtKostenKronenschnittOben, infoButtonText2));
		
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
		txtKostenMotorsaege.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenKronenschnittUnten.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenKronenschnittOben.addChangeListener(mainWindow.getDefaultChangeListener());
		txtArbeitszeit.addChangeListener(mainWindow.getDefaultChangeListener());
		txtArbeitszeit.addChangeListener(e -> updatePausenMaximum());
		txtPausen.addChangeListener(mainWindow.getDefaultChangeListener());
	}

	
	public void onInputChangedBeforeCalculation() {
		final Entwicklungsstufe entwicklungsstufe = panelBestand.getEntwicklungsstufe();
		
		boolean isEntwicklungsstufeDickung = entwicklungsstufe == Entwicklungsstufe.DICKUNG;
		
		txtKostenKronenschnittUnten.setEnabled(isEntwicklungsstufeDickung);
		txtKostenKronenschnittOben.setEnabled(isEntwicklungsstufeDickung);
	}
	
	private void updatePausenMaximum() {
		int arbeitszeit = (int) txtArbeitszeit.getValue();
		int maximum = arbeitszeit - 10;
		((SpinnerNumberModel)txtPausen.getModel()).setMaximum(maximum);
		if ((int)txtPausen.getValue() > maximum) {
			txtPausen.setValue(maximum);
		}
	}
	
	
	public Integer getKostenPersonalProPerson() {
		return (Integer) txtKostenPersonalProPerson.getValue();
	}
	
	public Double getKostenMotorsaege() {
		return (Double) txtKostenMotorsaege.getValue();
	}
	
	public Double getKostenKronenschnittUnten() {
		return (Double) txtKostenKronenschnittUnten.getValue();
	}
	
	public Double getKostenKronenschnittOben() {
		return (Double) txtKostenKronenschnittOben.getValue();
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
		sb.append(getXmlEntry(Messages.getString("Common.KostenMotorsaege") + " [" + mainWindow.getCurrency() + Messages.getString("ZBaumDurchforstung.proLiterTreibstoff") + "]", getKostenMotorsaege()));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.GeraetekostenKronenschnitteUnten") + " [" + mainWindow.getCurrency() + Messages.getString("ZBaumDurchforstung.proBaum") + "]", getKostenKronenschnittUnten()));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.GeraetekostenKronenschnitteOben") + " [" + mainWindow.getCurrency() + Messages.getString("ZBaumDurchforstung.proBaum") + "]", getKostenKronenschnittOben()));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		sb.append(getXmlEntry(Messages.getString("Common.TaeglicheArbeitszeit"), txtArbeitszeit.getValue())); //$NON-NLS-1$
		sb.append(getXmlEntry("&#160;&#160;" + Messages.getString("Common.DavonBezahlteWegzeitenUndPausen"), txtPausen.getValue())); //$NON-NLS-1$ //$NON-NLS-2$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


	@Override
	public void updateCurrency() {
		lblKostenPersonalProPerson.setText(Messages.getString("Common.Personalkostensatz") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		lblKostenMotorsaege.setText(Messages.getString("Common.KostenMotorsaege") + " [" + mainWindow.getCurrency() + Messages.getString("ZBaumDurchforstung.proLiterTreibstoff") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		
		lblKostenKronenschnittUnten.setText(Messages.getString("ZBaumDurchforstung.GeraetekostenKronenschnitteUnten") + " [" + mainWindow.getCurrency() + Messages.getString("ZBaumDurchforstung.proBaum") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		lblKostenKronenschnittOben.setText(Messages.getString("ZBaumDurchforstung.GeraetekostenKronenschnitteOben") + " [" + mainWindow.getCurrency() + Messages.getString("ZBaumDurchforstung.proBaum") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
}
