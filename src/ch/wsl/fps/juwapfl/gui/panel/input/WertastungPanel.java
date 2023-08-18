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

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.WertastungMainWindow;
import ch.wsl.fps.juwapfl.model.WertastungModel.Astigkeit;
import ch.wsl.fps.juwapfl.model.WertastungModel.Etappe;
import ch.wsl.fps.juwapfl.model.WertastungModel.Methode;
import ch.wsl.fps.juwapfl.model.WertastungModel.MethodeEtappe1;
import ch.wsl.fps.juwapfl.model.WertastungModel.MethodeEtappe2;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WertastungPanel extends AbstractInputPanel {
	
	private final WertastungMainWindow mainWindow;

	private final JRadioButton radEtappe1 = new JRadioButton(Messages.getString("Wertastung.Etappe1mitMeterangaben")); //$NON-NLS-1$
	private final JRadioButton radEtappe2 = new JRadioButton(Messages.getString("Wertastung.Etappe2mitMeterangaben")); //$NON-NLS-1$
	private final JComboBox<MethodeEtappe1> cmbMethode1 = new JComboBox<>(MethodeEtappe1.values());
	private final JComboBox<MethodeEtappe2> cmbMethode2 = new JComboBox<>(MethodeEtappe2.values());
	private final JComboBox<Integer> cmbEtappe1HoeheUnten = new JComboBox<>(new Integer[] {0,1,2,3});
	private final JComboBox<Integer> cmbEtappe2HoeheUnten = new JComboBox<>(new Integer[] {5,6});
	private final JComboBox<Integer> cmbEtappe1HoeheOben = new JComboBox<>(new Integer[] {5,6});
	private final JComboBox<Integer> cmbEtappe2HoeheOben = new JComboBox<>(new Integer[] {10,11,12});
	private final JComboBox<Astigkeit> cmbAstigkeit1 = new JComboBox<>(Astigkeit.values(Etappe.Etappe1));
	private final JComboBox<Astigkeit> cmbAstigkeit2 = new JComboBox<>(Astigkeit.values(Etappe.Etappe2));
	
	
	public WertastungPanel(WertastungMainWindow mainWindow) {
		super(Messages.getString("Wertastung.Titel")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 3, 5, 0));
		
		this.add(new JLabel("")); //$NON-NLS-1$
		initRadioButtons();
		this.add(Utilities.getPanelWithoutInfoButton(radEtappe1));
		this.add(Utilities.getPanelWithoutInfoButton(radEtappe2));

		this.add(new JLabel(Messages.getString("Wertastung.Methode"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbMethode1));
		this.add(Utilities.getPanelWithoutInfoButton(cmbMethode2));

		this.add(new JLabel(Messages.getString("Wertastung.HoeheUnten"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbEtappe1HoeheUnten));
		this.add(Utilities.getPanelWithoutInfoButton(cmbEtappe2HoeheUnten));

		this.add(new JLabel(Messages.getString("Wertastung.HoeheOben"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbEtappe1HoeheOben));
		this.add(Utilities.getPanelWithoutInfoButton(cmbEtappe2HoeheOben));

		this.add(new JLabel(Messages.getString("Wertastung.Astigkeit"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(cmbAstigkeit1));
		this.add(Utilities.getPanelWithoutInfoButton(cmbAstigkeit2));

		cmbMethode1.setSelectedItem(MethodeEtappe1.getDefault());
		cmbMethode2.setSelectedItem(MethodeEtappe2.getDefault());
		cmbEtappe1HoeheUnten.setSelectedItem(0);
		cmbEtappe2HoeheUnten.setSelectedItem(6);
		cmbEtappe1HoeheOben.setSelectedItem(6);
		cmbEtappe2HoeheOben.setSelectedItem(12);
		cmbAstigkeit1.setSelectedItem(Astigkeit.getDefault());
		cmbAstigkeit2.setSelectedItem(Astigkeit.getDefault());
		
		initListeners();
	}
	
	
	private void initRadioButtons() {
	    ButtonGroup group = new ButtonGroup();
	    group.add(radEtappe1);
	    group.add(radEtappe2);
	    
		radEtappe1.setHorizontalAlignment(SwingConstants.CENTER);
		radEtappe2.setHorizontalAlignment(SwingConstants.CENTER);

		radEtappe1.addItemListener(e -> updateRadioButtonDependecies());
		radEtappe2.addItemListener(e -> updateRadioButtonDependecies());
		
		radEtappe1.setSelected(true);
	}
	
	
	private void updateRadioButtonDependecies() {
		boolean isEtappe1 = radEtappe1.isSelected();
		cmbMethode1.setEnabled(isEtappe1);
		cmbEtappe1HoeheUnten.setEnabled(isEtappe1);
		cmbEtappe1HoeheOben.setEnabled(isEtappe1);
		cmbAstigkeit1.setEnabled(isEtappe1);

		boolean isEtappe2 = radEtappe2.isSelected();
		cmbMethode2.setEnabled(isEtappe2);
		cmbEtappe2HoeheUnten.setEnabled(isEtappe2);
		cmbEtappe2HoeheOben.setEnabled(isEtappe2);
		cmbAstigkeit2.setEnabled(isEtappe2);
	}


	private void initListeners() {
		radEtappe1.addItemListener(mainWindow.getDefaultItemListener());
		radEtappe2.addItemListener(mainWindow.getDefaultItemListener());
		cmbMethode1.addActionListener(mainWindow.getDefaultActionListener());
		cmbMethode2.addActionListener(mainWindow.getDefaultActionListener());
		cmbEtappe1HoeheUnten.addActionListener(mainWindow.getDefaultActionListener());
		cmbEtappe2HoeheUnten.addActionListener(mainWindow.getDefaultActionListener());
		cmbEtappe1HoeheOben.addActionListener(mainWindow.getDefaultActionListener());
		cmbEtappe2HoeheOben.addActionListener(mainWindow.getDefaultActionListener());
		cmbAstigkeit1.addActionListener(mainWindow.getDefaultActionListener());
		cmbAstigkeit2.addActionListener(mainWindow.getDefaultActionListener());
	}
	
	
	public Etappe getEtappe() {
		if (radEtappe1.isSelected()) {
			return Etappe.Etappe1;
		}
		if (radEtappe2.isSelected()) {
			return Etappe.Etappe2;
		}
		return null;
	}
	
	public Methode getMethode() {
		if (getEtappe() == Etappe.Etappe1) {
			return cmbMethode1.getItemAt(cmbMethode1.getSelectedIndex());
		}
		else if (getEtappe() == Etappe.Etappe2) {
			return cmbMethode2.getItemAt(cmbMethode2.getSelectedIndex());
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	public Integer getHoeheUnten() {
		if (getEtappe() == Etappe.Etappe1) {
			return cmbEtappe1HoeheUnten.getItemAt(cmbEtappe1HoeheUnten.getSelectedIndex());
		}
		else if (getEtappe() == Etappe.Etappe2) {
			return cmbEtappe2HoeheUnten.getItemAt(cmbEtappe2HoeheUnten.getSelectedIndex());
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	public Integer getHoeheOben() {
		if (getEtappe() == Etappe.Etappe1) {
			return cmbEtappe1HoeheOben.getItemAt(cmbEtappe1HoeheOben.getSelectedIndex());
		}
		else if (getEtappe() == Etappe.Etappe2) {
			return cmbEtappe2HoeheOben.getItemAt(cmbEtappe2HoeheOben.getSelectedIndex());
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	public Astigkeit getAstigkeit() {
		if (getEtappe() == Etappe.Etappe1) {
			return cmbAstigkeit1.getItemAt(cmbAstigkeit1.getSelectedIndex());
		}
		else if (getEtappe() == Etappe.Etappe2) {
			return cmbAstigkeit2.getItemAt(cmbAstigkeit2.getSelectedIndex());
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Wertastung.Titel") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Wertastung.Etappe"), getEtappe())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Wertastung.Methode"), getMethode())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Wertastung.HoeheUnten"), getHoeheUnten())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Wertastung.HoeheOben"), getHoeheOben())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Wertastung.Astigkeit"), getAstigkeit())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}
	
}
