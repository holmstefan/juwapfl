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

import javax.swing.JComboBox;
import javax.swing.JLabel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.model.RueckegassenModel.Hilfskraft;
import ch.wsl.fps.juwapfl.model.RueckegassenModel.Ortskenntnisse;
import ch.wsl.fps.juwapfl.model.RueckegassenModel.UebungPlanungsverfahren;

/**
 * 
 * @author Stefan Holm
 *
 */
public class RueckegassenVerfahrenPanel extends AbstractInputPanel {

	private final AbstractMainWindow mainWindow;
	
	private final JComboBox<Hilfskraft> cmbHilfskraft = new JComboBox<>(Hilfskraft.values());
	private final JComboBox<Ortskenntnisse> cmbOrtskenntnisse = new JComboBox<>(Ortskenntnisse.values());
	private final JComboBox<UebungPlanungsverfahren> cmbUebungPlanungsverfahren = new JComboBox<>(UebungPlanungsverfahren.values());
	
	
	public RueckegassenVerfahrenPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Rueckegassen.TitelVerfahren")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(new JLabel(Messages.getString("Rueckegassen.Hilfskraft"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(cmbHilfskraft, Messages.getString("Rueckegassen.HilfskraftInfoButton"))); //$NON-NLS-1$

		this.add(new JLabel(Messages.getString("Rueckegassen.Ortskenntnisse"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbOrtskenntnisse));
		
		this.add(new JLabel(Messages.getString("Rueckegassen.UebungPlanungsverfahren"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbUebungPlanungsverfahren));

		cmbHilfskraft.setSelectedItem(Hilfskraft.getDefault());
		cmbOrtskenntnisse.setSelectedItem(Ortskenntnisse.getDefault());
		cmbUebungPlanungsverfahren.setSelectedItem(UebungPlanungsverfahren.getDefault());
		
		initListeners();
	}
	
	
	private void initListeners() {
		cmbHilfskraft.addActionListener(mainWindow.getDefaultActionListener());
		cmbOrtskenntnisse.addActionListener(mainWindow.getDefaultActionListener());
		cmbUebungPlanungsverfahren.addActionListener(mainWindow.getDefaultActionListener());
	}
	
	
	public Hilfskraft getHilfskraft() {
		return cmbHilfskraft.getItemAt(cmbHilfskraft.getSelectedIndex());
	}
	
	public Ortskenntnisse getOrtskenntnisse() {
		return cmbOrtskenntnisse.getItemAt(cmbOrtskenntnisse.getSelectedIndex());
	}
	
	public UebungPlanungsverfahren getUebungPlanungsverfahren() {
		return cmbUebungPlanungsverfahren.getItemAt(cmbUebungPlanungsverfahren.getSelectedIndex());
	}

	
	@Override
	public String getAsXmlString() {

		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Rueckegassen.TitelVerfahren") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Rueckegassen.Hilfskraft"), getHilfskraft())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Rueckegassen.Ortskenntnisse"), getOrtskenntnisse())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Rueckegassen.UebungPlanungsverfahren"), getUebungPlanungsverfahren())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
