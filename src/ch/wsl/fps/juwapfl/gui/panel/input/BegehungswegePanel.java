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

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.BegehungswegeMainWindow;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel.Ausfuehrung;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel.Begehungsweg;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel.GelaendeSchwierigkeit;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BegehungswegePanel extends AbstractInputPanel {
	
	private final BegehungswegeMainWindow mainWindow;

	private final JRadioButton radBegehungswegNeuErstellen = new JRadioButton(Begehungsweg.NEU_ERSTELLEN.toString());
	private final JRadioButton radBegehungswegInstandStellen = new JRadioButton(Begehungsweg.INSTAND_STELLEN.toString());
	private final JComboBox<Ausfuehrung> cmbAusfuehrung = new JComboBox<>(Ausfuehrung.values());
	private final JSpinner txtAnzahlLaufmeter = new JSpinner(new SpinnerNumberModel(50, 1, 10000, 1));
	private final JComboBox<GelaendeSchwierigkeit> cmbGelaendeschwierigkeit = new JComboBox<>(GelaendeSchwierigkeit.values());
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnzahlLaufmeter, false);
	}
	

	public BegehungswegePanel(BegehungswegeMainWindow mainWindow) {
		super(Messages.getString("Begehungswege.Titel")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(new JLabel(Messages.getString("Begehungswege.Begehungsweg"))); //$NON-NLS-1$
		initRadioButtons();
		JPanel pnlRadioButtons = new JPanel(new BorderLayout());
		pnlRadioButtons.add(radBegehungswegNeuErstellen, BorderLayout.WEST);
		pnlRadioButtons.add(radBegehungswegInstandStellen, BorderLayout.EAST);
		String msg = Messages.getString("Begehungswege.InfoButtonBegehungswege"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(pnlRadioButtons, msg));

		this.add(new JLabel(Messages.getString("Begehungswege.Ausfuehrung"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbAusfuehrung));
		
		this.add(new JLabel(Messages.getString("Begehungswege.AnzahlLaufmeter"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnzahlLaufmeter));
		
		this.add(new JLabel(Messages.getString("Begehungswege.Gelaendeschwierigkeit"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbGelaendeschwierigkeit));

		cmbAusfuehrung.setSelectedItem(Ausfuehrung.getDefault());
		cmbGelaendeschwierigkeit.setSelectedItem(GelaendeSchwierigkeit.getDefault());
		
		initListeners();
	}
	
	
	private void initRadioButtons() {
	    ButtonGroup group = new ButtonGroup();
	    group.add(radBegehungswegNeuErstellen);
	    group.add(radBegehungswegInstandStellen);
	    
	    radBegehungswegNeuErstellen.setHorizontalAlignment(SwingConstants.CENTER);
	    radBegehungswegInstandStellen.setHorizontalAlignment(SwingConstants.CENTER);
		
		radBegehungswegNeuErstellen.setSelected(true);
	}
	
	
	private void initListeners() {
		radBegehungswegNeuErstellen.addItemListener(mainWindow.getDefaultItemListener());
		radBegehungswegInstandStellen.addItemListener(mainWindow.getDefaultItemListener());
		cmbAusfuehrung.addActionListener(mainWindow.getDefaultActionListener());
		txtAnzahlLaufmeter.addChangeListener(mainWindow.getDefaultChangeListener());
		cmbGelaendeschwierigkeit.addActionListener(mainWindow.getDefaultActionListener());
	}


	public Begehungsweg getBegehungsweg() {
		if (radBegehungswegNeuErstellen.isSelected()) {
			return Begehungsweg.NEU_ERSTELLEN;
		}
		else {
			return Begehungsweg.INSTAND_STELLEN;
		}
	}
	
	public Ausfuehrung getAusfuehrung() {
		return cmbAusfuehrung.getItemAt(cmbAusfuehrung.getSelectedIndex());
	}
	
	public Integer getAnzahlLaufmeter() {
		return (Integer) txtAnzahlLaufmeter.getValue();
	}
	
	public GelaendeSchwierigkeit getGelaendeschwierigkeit() {
		return cmbGelaendeschwierigkeit.getItemAt(cmbGelaendeschwierigkeit.getSelectedIndex());
	}
	

	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Begehungswege.Titel") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Begehungswege.Begehungsweg"), getBegehungsweg())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Begehungswege.Ausfuehrung"), getAusfuehrung())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Begehungswege.AnzahlLaufmeter"), getAnzahlLaufmeter())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Begehungswege.Gelaendeschwierigkeit"), getGelaendeschwierigkeit())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


}
