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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.WildschutzMainWindow;
import ch.wsl.fps.juwapfl.model.WildschutzModel.Schutztyp;
import ch.wsl.fps.juwapfl.model.WildschutzModel.Subtyp;
import ch.wsl.fps.juwapfl.model.WildschutzModel.Wuchshuellentyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WildschutzPanel extends AbstractInputPanel {
	
	private final WildschutzMainWindow mainWindow;

	private final JComboBox<Schutztyp> cmbSchutztyp = new JComboBox<>(Schutztyp.values());
	private final JComboBox<Subtyp> cmbSubtyp = new JComboBox<>();
	private final JComboBox<Wuchshuellentyp> cmbWuchshuellentyp = new JComboBox<>();
	private final JSpinner txtAnzahlPflanzen = new JSpinner(new SpinnerNumberModel(50, 1, 10_000, 1));
	private final JSpinner txtZaunlaenge_m = new JSpinner(new SpinnerNumberModel(100, 1, 10_000, 1));
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlPflanzen, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtZaunlaenge_m, false);
	}
	
	private boolean isInModusKostenVergleich = false;
	
	
	public WildschutzPanel(WildschutzMainWindow mainWindow) {
		super(Messages.getString("Wildschutz.Wildschutz")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(new JLabel(Messages.getString("Wildschutz.Schutztyp"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbSchutztyp));

		this.add(new JLabel(Messages.getString("Wildschutz.Subtyp"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbSubtyp));

		this.add(new JLabel(Messages.getString("Wildschutz.Wuchshuellentyp"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbWuchshuellentyp));

		this.add(new JLabel(Messages.getString("Wildschutz.AnzahlPflanzen"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlPflanzen));

		this.add(new JLabel(Messages.getString("Wildschutz.Zaunlaenge"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtZaunlaenge_m));
		
		cmbSchutztyp.setSelectedItem(Schutztyp.getDefault());
		cmbSchutztyp.setMaximumRowCount(10);
		
		onInputChangedBeforeCalculation();
		initListeners();
	}


	private void initListeners() {
		cmbSchutztyp.addActionListener(mainWindow.getDefaultActionListener());
		cmbSubtyp.addActionListener(mainWindow.getDefaultActionListener());
		cmbWuchshuellentyp.addActionListener(mainWindow.getDefaultActionListener());
		txtAnzahlPflanzen.addChangeListener(mainWindow.getDefaultChangeListener());
		txtZaunlaenge_m.addChangeListener(mainWindow.getDefaultChangeListener());
	}
	
	public void onInputChangedBeforeCalculation() {
		// check if cmbSubtyp needs to be updated
		boolean updateOfCmbSubtypNecessary = false;
		if (cmbSubtyp.getItemCount() != Subtyp.getValues(getSchutztyp()).length) {
			updateOfCmbSubtypNecessary = true;
		}
		else {
			for (int i=0; i<cmbSubtyp.getItemCount(); i++) {
				if (cmbSubtyp.getItemAt(i) != Subtyp.getValues(getSchutztyp())[i]) {
					updateOfCmbSubtypNecessary = true;
					break;
				}
			}
		}
		
		// update subtyp
		if (updateOfCmbSubtypNecessary) {
			cmbSubtyp.removeAllItems();
			Arrays.asList(Subtyp.getValues(getSchutztyp())).forEach(cmbSubtyp::addItem);
			cmbSubtyp.setEnabled(cmbSubtyp.getItemCount() > 1);
		}
		
		// update Wuchshüllentyp
		cmbWuchshuellentyp.setEnabled(cmbSchutztyp.getSelectedItem() == Schutztyp.WUCHSHUELLE);
		if (cmbSchutztyp.getSelectedItem() != Schutztyp.WUCHSHUELLE) {
			cmbWuchshuellentyp.removeAllItems();
		}
		else {
			// check if cmbWuchshuellentyp needs to be updated
			boolean updateOfCmbWuchshuellentypNecessary = false;
			if (cmbWuchshuellentyp.getItemCount() != Wuchshuellentyp.getValues(getSubtyp()).length) {
				updateOfCmbWuchshuellentypNecessary = true;
			}
			else {
				for (int i=0; i<cmbWuchshuellentyp.getItemCount(); i++) {
					if (cmbWuchshuellentyp.getItemAt(i) != Wuchshuellentyp.getValues(getSubtyp())[i]) {
						updateOfCmbWuchshuellentypNecessary = true;
						break;
					}
				}
			}
			
			// update cmbWuchshuellentyp
			if (updateOfCmbWuchshuellentypNecessary) {
				cmbWuchshuellentyp.removeAllItems();
				Arrays.asList(Wuchshuellentyp.getValues(getSubtyp())).forEach(cmbWuchshuellentyp::addItem);
				cmbWuchshuellentyp.setSelectedItem(Wuchshuellentyp.getDefault(getSubtyp()));
				cmbWuchshuellentyp.setEnabled(cmbWuchshuellentyp.getItemCount() > 1);
			}
		}
		
		// update Anzahl Pflanzen / Zaunlänge
		if (cmbSchutztyp.getSelectedItem() == null) {
			txtAnzahlPflanzen.setEnabled(false);
			txtZaunlaenge_m.setEnabled(false || isInModusKostenVergleich);
		}
		else if (cmbSchutztyp.getSelectedItem() == Schutztyp.FLAECHENSCHUTZ) {
			txtAnzahlPflanzen.setEnabled(false);
			txtZaunlaenge_m.setEnabled(true);
		}
		else {
			txtAnzahlPflanzen.setEnabled(true);
			txtZaunlaenge_m.setEnabled(false || isInModusKostenVergleich);
		}
	}
	
	
	public void setModusKostenvergleich(boolean flag) {
		this.isInModusKostenVergleich = flag;
		
		txtZaunlaenge_m.setEnabled(isInModusKostenVergleich);
		
		if (isInModusKostenVergleich) {
			cmbSchutztyp.removeItem(Schutztyp.FLAECHENSCHUTZ);
		}
		else {
			cmbSchutztyp.addItem(Schutztyp.FLAECHENSCHUTZ);
		}
	}

	
	public Schutztyp getSchutztyp() {
		return cmbSchutztyp.getItemAt(cmbSchutztyp.getSelectedIndex());
	}
	
	public Subtyp getSubtyp() {
		return cmbSubtyp.getItemAt(cmbSubtyp.getSelectedIndex());
	}
	
	public Wuchshuellentyp getWuchshuellentyp() {
		return cmbWuchshuellentyp.getItemAt(cmbWuchshuellentyp.getSelectedIndex());
	}
	
	public Integer getAnzahlPflanzen() {
		return (Integer) txtAnzahlPflanzen.getValue();
	}
	
	public Integer getZaunlaenge_m() {
		return (Integer) txtZaunlaenge_m.getValue();
	}
	

	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Wildschutz.Wildschutz") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Wildschutz.Schutztyp"), getSchutztyp())); //$NON-NLS-1$
		
		if (getSchutztyp() != Schutztyp.CHEMISCHER_SCHUTZ && getSchutztyp() != Schutztyp.SCHAELSCHUTZ) {
			sb.append(getXmlEntry(Messages.getString("Wildschutz.Subtyp"), getSubtyp())); //$NON-NLS-1$
		}
		
		if (getSchutztyp() == Schutztyp.WUCHSHUELLE) {
			sb.append(getXmlEntry(Messages.getString("Wildschutz.Wuchshuellentyp"), getWuchshuellentyp())); //$NON-NLS-1$
		}
		
		if (getSchutztyp() != Schutztyp.FLAECHENSCHUTZ) {
			sb.append(getXmlEntry(Messages.getString("Wildschutz.AnzahlPflanzen"), getAnzahlPflanzen())); //$NON-NLS-1$
		}
		
		if (getSchutztyp() == Schutztyp.FLAECHENSCHUTZ) {
			sb.append(getXmlEntry(Messages.getString("Wildschutz.Zaunlaenge"), getZaunlaenge_m())); //$NON-NLS-1$
		}
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
