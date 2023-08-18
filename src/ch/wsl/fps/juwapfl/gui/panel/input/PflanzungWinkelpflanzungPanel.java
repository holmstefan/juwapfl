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

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.PflanzungMainWindow;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Bodenvegetation;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Hangneigung;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Pflanzenhoehe;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Schlagabraum;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Transportdistanz;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungWinkelpflanzungPanel extends AbstractInputPanel {
	
	private final PflanzungMainWindow mainWindow;

	private final JComboBox<Bodenvegetation> cmbBodenvegetation = new JComboBox<>(Bodenvegetation.values());
	private final JComboBox<Schlagabraum> cmbSchlagabraum = new JComboBox<>(Schlagabraum.values());
	private final JComboBox<Hangneigung> cmbHangneigung = new JComboBox<>(Hangneigung.values());
	private final JComboBox<Pflanzenhoehe> cmbPflanzenhoehe = new JComboBox<>(Pflanzenhoehe.values());
	private final JComboBox<Transportdistanz> cmbTransportdistanz = new JComboBox<>(Transportdistanz.values());

	
	public PflanzungWinkelpflanzungPanel(PflanzungMainWindow mainWindow) {
		super(Messages.getString("Pflanzung.Winkelpflanzung.Titel")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));

		this.add(new JLabel(Messages.getString("Pflanzung.Winkelpflanzung.HinderlicheBodenvegetation"))); //$NON-NLS-1$
		String msg = Messages.getString("Pflanzung.Winkelpflanzung.InfoButtonBodenvegetation"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbBodenvegetation, msg));
		
		this.add(new JLabel(Messages.getString("Pflanzung.Winkelpflanzung.BeseitigungSchlagabraum"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbSchlagabraum));
		
		this.add(new JLabel(Messages.getString("Pflanzung.Winkelpflanzung.Hangneigung"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbHangneigung));
		
		this.add(new JLabel(Messages.getString("Pflanzung.Winkelpflanzung.Pflanzenhoehe"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbPflanzenhoehe));
		
		this.add(new JLabel(Messages.getString("Pflanzung.Winkelpflanzung.PflanzentransportZuFuss"))); //$NON-NLS-1$
		String infoButtonTransportdistanz = Messages.getString("Pflanzung.Winkelpflanzung.InfoButtonPflanzentransportZuFuss"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbTransportdistanz, infoButtonTransportdistanz));
		
		cmbBodenvegetation.setSelectedItem(Bodenvegetation.getDefault());
		cmbSchlagabraum.setSelectedItem(Schlagabraum.getDefault());
		cmbHangneigung.setSelectedItem(Hangneigung.getDefault());
		cmbPflanzenhoehe.setSelectedItem(Pflanzenhoehe.getDefault());
		cmbTransportdistanz.setSelectedItem(Transportdistanz.getDefault());

		initListeners();
	}
	

	private void initListeners() {
		cmbBodenvegetation.addActionListener(mainWindow.getDefaultActionListener());
		cmbSchlagabraum.addActionListener(mainWindow.getDefaultActionListener());
		cmbHangneigung.addActionListener(mainWindow.getDefaultActionListener());
		cmbPflanzenhoehe.addActionListener(mainWindow.getDefaultActionListener());
		cmbTransportdistanz.addActionListener(mainWindow.getDefaultActionListener());
	}


	private Bodenvegetation getBodenvegetation() {
		return cmbBodenvegetation.getItemAt(cmbBodenvegetation.getSelectedIndex());
	}
	
	private Schlagabraum getSchlagabraum() {
		return cmbSchlagabraum.getItemAt(cmbSchlagabraum.getSelectedIndex());
	}
	
	private Hangneigung getHangneigung() {
		return cmbHangneigung.getItemAt(cmbHangneigung.getSelectedIndex());
	}
	
	private Pflanzenhoehe getPflanzenhoehe() {
		return cmbPflanzenhoehe.getItemAt(cmbPflanzenhoehe.getSelectedIndex());
	}
	
	private Transportdistanz getTransportdistanz() {
		return cmbTransportdistanz.getItemAt(cmbTransportdistanz.getSelectedIndex());
	}
	
	public PflanzungWinkelpflanzungModel getWinkelpflanzungModel() {
		PflanzungWinkelpflanzungModel result = new PflanzungWinkelpflanzungModel();
		
		result.setBodenvegetation(getBodenvegetation());
		result.setSchlagabraum(getSchlagabraum());
		result.setHangneigung(getHangneigung());
		result.setPflanzenhoehe(getPflanzenhoehe());
		result.setTransportdistanz(getTransportdistanz());
		
		return result;
	}
	

	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Pflanzung.Winkelpflanzung.Titel") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Pflanzung.Winkelpflanzung.HinderlicheBodenvegetation"), getBodenvegetation())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.Winkelpflanzung.BeseitigungSchlagabraum"), getSchlagabraum())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.Winkelpflanzung.Hangneigung"), getHangneigung())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.Winkelpflanzung.Pflanzenhoehe"), getPflanzenhoehe())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.Winkelpflanzung.PflanzentransportZuFuss"), getTransportdistanz())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		Arrays.asList(this.getComponents()).forEach(c -> c.setEnabled(enabled));
	}

}
