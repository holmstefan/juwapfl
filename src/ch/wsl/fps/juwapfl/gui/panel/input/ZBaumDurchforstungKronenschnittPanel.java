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
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.ZBaumDurchforstungMainWindow;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.AusfuehrungshoeheKronenschnitt;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Entwicklungsstufe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ZBaumDurchforstungKronenschnittPanel extends AbstractInputPanel {

	private final ZBaumDurchforstungMainWindow mainWindow;
	private final ZBaumDurchforstungBestandPanel panelBestand;

	private final JSpinner txtAnzahlZBaeumeMitKronenschnittProHektare = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
	private final JComboBox<AusfuehrungshoeheKronenschnitt> cmbAusfuehrungshoeheKronenschnitt= new JComboBox<>(AusfuehrungshoeheKronenschnitt.values());
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlZBaeumeMitKronenschnittProHektare, false);
	}
	
	
	public ZBaumDurchforstungKronenschnittPanel(ZBaumDurchforstungMainWindow mainWindow, ZBaumDurchforstungBestandPanel panelBestand) {
		super(Messages.getString("ZBaumDurchforstung.Kronenschnitt")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.panelBestand = panelBestand;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.AnzahlZBaeumeMitKronenschnittProHa"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlZBaeumeMitKronenschnittProHektare));

		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.AusfuehrungshoeheKronenschnitt"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbAusfuehrungshoeheKronenschnitt));

		cmbAusfuehrungshoeheKronenschnitt.setSelectedItem(AusfuehrungshoeheKronenschnitt.getDefault());
		
		initListeners();
	}
	
	
	private void initListeners() {
		txtAnzahlZBaeumeMitKronenschnittProHektare.addChangeListener(mainWindow.getDefaultChangeListener());
		
		txtAnzahlZBaeumeMitKronenschnittProHektare.addChangeListener(event -> {
			int anzahlZ = panelBestand.getAnzahlZBaeumeProHektare();
			int anzahlZMitKS = (int) txtAnzahlZBaeumeMitKronenschnittProHektare.getValue();
			if (anzahlZMitKS > anzahlZ) {
				String msg = Messages.getString("ZBaumDurchforstung.WarnungAnzahlZBaeume"); //$NON-NLS-1$
				JOptionPane.showMessageDialog(this, msg);
			}
		});
		
		cmbAusfuehrungshoeheKronenschnitt.addActionListener(mainWindow.getDefaultActionListener());
	}

	
	public void onInputChangedBeforeCalculation() {
		final Entwicklungsstufe entwicklungsstufe = panelBestand.getEntwicklungsstufe();
		
		if (entwicklungsstufe == Entwicklungsstufe.DICKUNG) {
			txtAnzahlZBaeumeMitKronenschnittProHektare.setEnabled(true);
			
			int anzahlZ = panelBestand.getAnzahlZBaeumeProHektare();
			int anzahlZMitKS = (int) txtAnzahlZBaeumeMitKronenschnittProHektare.getValue();
			if (anzahlZMitKS > anzahlZ) {
				txtAnzahlZBaeumeMitKronenschnittProHektare.setValue(anzahlZ);
			}
		}
		else if (entwicklungsstufe == Entwicklungsstufe.STANGENHOLZ_1) {
			txtAnzahlZBaeumeMitKronenschnittProHektare.setEnabled(false);
			txtAnzahlZBaeumeMitKronenschnittProHektare.setValue(0);
		}
		
		
		if (entwicklungsstufe == Entwicklungsstufe.DICKUNG) {
			cmbAusfuehrungshoeheKronenschnitt.setEnabled(true);
			if (cmbAusfuehrungshoeheKronenschnitt.getItemCount() == 1) {
				cmbAusfuehrungshoeheKronenschnitt.removeAllItems();
				Arrays.asList(AusfuehrungshoeheKronenschnitt.values(Entwicklungsstufe.DICKUNG)).forEach(item -> cmbAusfuehrungshoeheKronenschnitt.addItem(item));
				cmbAusfuehrungshoeheKronenschnitt.setSelectedItem(AusfuehrungshoeheKronenschnitt.getDefault());
			}
		}
		else if (entwicklungsstufe == Entwicklungsstufe.STANGENHOLZ_1) {
			cmbAusfuehrungshoeheKronenschnitt.setEnabled(false);
			cmbAusfuehrungshoeheKronenschnitt.removeAllItems();
			cmbAusfuehrungshoeheKronenschnitt.addItem(AusfuehrungshoeheKronenschnitt.NICHT_VERFUEGBAR);
			cmbAusfuehrungshoeheKronenschnitt.setSelectedItem(AusfuehrungshoeheKronenschnitt.NICHT_VERFUEGBAR);
		}
	}

	
	public Integer getAnzahlZBaeumeMitKronenschnittProHektare() {
		return (Integer) txtAnzahlZBaeumeMitKronenschnittProHektare.getValue();
	}
	
	public AusfuehrungshoeheKronenschnitt getAusfuehrungshoeheKronenschnitt() {
		return cmbAusfuehrungshoeheKronenschnitt.getItemAt(cmbAusfuehrungshoeheKronenschnitt.getSelectedIndex());
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("ZBaumDurchforstung.Kronenschnitt") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.AnzahlZBaeumeMitKronenschnittProHa"), getAnzahlZBaeumeMitKronenschnittProHektare())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("ZBaumDurchforstung.AusfuehrungshoeheKronenschnitt"), getAusfuehrungshoeheKronenschnitt())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
