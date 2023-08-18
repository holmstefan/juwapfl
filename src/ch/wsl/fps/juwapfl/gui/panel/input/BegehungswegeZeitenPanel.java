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
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.controls.JFlashingSpinner;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel.Ausfuehrung;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel.Begehungsweg;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel.GelaendeSchwierigkeit;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BegehungswegeZeitenPanel extends AbstractInputPanel {
	
	private final AbstractMainWindow mainWindow;

	private final JFlashingSpinner txtZeitaufwand_minProM = new JFlashingSpinner(new SpinnerNumberModel(0, 0, 1000, 1d));
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtZeitaufwand_minProM, false);
	}
	
	private Begehungsweg begehungsweg;
	private Ausfuehrung ausfuehrung;
	private GelaendeSchwierigkeit schwierigkeit;
	
	
	public BegehungswegeZeitenPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Begehungswege.Zeitaufwaende")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(new JLabel(Messages.getString("Begehungswege.ReineArbeitszeit"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtZeitaufwand_minProM));

		initListeners();
	}
	
	
	private void initListeners() {
		txtZeitaufwand_minProM.addChangeListener(mainWindow.getDefaultChangeListener());
	}

	
	public void onInputChangedBeforeCalculation(Begehungsweg begehungsweg, Ausfuehrung ausfuehrung, GelaendeSchwierigkeit schwierigkeit) {
		if (this.begehungsweg != begehungsweg || this.ausfuehrung != ausfuehrung || this.schwierigkeit != schwierigkeit) {
			this.begehungsweg = begehungsweg;
			this.ausfuehrung = ausfuehrung;
			this.schwierigkeit = schwierigkeit;
			
			txtZeitaufwand_minProM.setValue(BegehungswegeModel.getDefaultZeitaufwand_minProM(begehungsweg, ausfuehrung, schwierigkeit));
			
			if (mainWindow.isInitializing == false) {
				txtZeitaufwand_minProM.flash();
			}
		}
	}
	
	
	public Double getZeitaufwand_minProM() {
		return (Double) txtZeitaufwand_minProM.getValue();
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Begehungswege.Zeitaufwaende") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Begehungswege.ReineArbeitszeit"), getZeitaufwand_minProM())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}

}
