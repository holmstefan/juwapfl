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
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;

/**
 * 
 * @author Stefan Holm
 *
 */
public class MaterialkostenPanel extends AbstractInputPanel {
	
	private final AbstractMainWindow mainWindow;
	
	private final JSpinner txtKorrekturfaktorMaterialkosten = new JSpinner(new SpinnerNumberModel(1, 0, 10, 0.1));
	{
		txtKorrekturfaktorMaterialkosten.setEditor(new NumberEditor(txtKorrekturfaktorMaterialkosten, CURRENCY_NUMBER_FORMAT));
		AbstractMainWindow.adjustJSpinnerFormatter(txtKorrekturfaktorMaterialkosten, false);
	}
	
	
	public MaterialkostenPanel(AbstractMainWindow mainWindow) {
		super(Messages.getString("Common.TitelMaterialkosten")); //$NON-NLS-1$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		this.add(new JLabel(Messages.getString("Common.TitelKorrekturfaktorMaterialkosten"))); //$NON-NLS-1$
		String infoButtonText = Messages.getString("Common.InfoButtonKorrekturfaktorMaterialkosten"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtKorrekturfaktorMaterialkosten, infoButtonText));
		
		initListeners();
	}
	
	
	private void initListeners() {
		txtKorrekturfaktorMaterialkosten.addChangeListener(mainWindow.getDefaultChangeListener());
	}
	
	
	public Double getKorrekturfaktorMaterialkosten() {
		return (Double) txtKorrekturfaktorMaterialkosten.getValue();
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Common.TitelMaterialkosten") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Common.TitelKorrekturfaktorMaterialkosten"), getKorrekturfaktorMaterialkosten())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}
	
}
