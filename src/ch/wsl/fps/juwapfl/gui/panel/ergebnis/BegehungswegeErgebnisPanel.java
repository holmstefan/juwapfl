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
package ch.wsl.fps.juwapfl.gui.panel.ergebnis;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.BegehungswegeMainWindow;
import ch.wsl.fps.juwapfl.model.BegehungswegeErgebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BegehungswegeErgebnisPanel extends AbstractErgebnisPanel<BegehungswegeMainWindow, BegehungswegeErgebnis> {

	private final JTextField txtZeitPersonal = getNewLockedTextField();
	private final JTextField txtZeitMaschine = getNewLockedTextField();

	private final JTextField txtKostenPersonal = getNewLockedTextField();
	private final JTextField txtKostenMaschine = getNewLockedTextField();
	private final JTextField txtKostenGesamt = getNewLockedTextFieldBold();
	

	public BegehungswegeErgebnisPanel(BegehungswegeMainWindow mainWindow) {
		super(mainWindow);
		
		AbstractMainWindow.initTitledBorder(this, Messages.getString("Common.TitelErgebnis")); //$NON-NLS-1$
		this.setBackground(AbstractErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		int row = 0;
		int col = 0;
		
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel("<html><center>" + Messages.getString("Common.TitelZeiten") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel(), getGBC(col++, row));
		this.add(lblKostenTitle, getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row)); // placeholder

		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Common.TitelPersonalWPPH") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitPersonal, getGBC(col++, row));
		col++;
		this.add(txtKostenPersonal, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Common.TitelMaschinePMH15") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitMaschine, getGBC(col++, row));
		col++;
		this.add(txtKostenMaschine, getGBC(col++, row));
		
		row++;
		col = 0;

		this.add(getLabelGesamt(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		col++;
		this.add(txtKostenGesamt, getGBC(col++, row));
		
		
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	@Override
	public void setErgebnis(BegehungswegeErgebnis ergebnis) {
		txtZeitPersonal.setText(formatMinutes(ergebnis.getZeitPersonal_min()));
		txtZeitMaschine.setText(formatMinutes(ergebnis.getZeitMaschine_min()));
		
		txtKostenPersonal.setText(format(ergebnis.getKostenPersonal()));
		txtKostenMaschine.setText(format(ergebnis.getKostenMaschine()));
		txtKostenGesamt.setText(format(ergebnis.getKostenGesamt()));
	}

	
	@Override
	public String[][] getErgebnisAsStringArray() {
		String[][] strings = new String[3][];

		strings[0] = new String[]{
				Messages.getString("Common.TitelPersonalWPPH"), //$NON-NLS-1$
				txtZeitPersonal.getText(),
				txtKostenPersonal.getText()};
		
		strings[1] = new String[]{Messages.getString("Common.TitelMaschinePMH15"), //$NON-NLS-1$
				txtZeitMaschine.getText(),
				txtKostenMaschine.getText()};
		
		strings[2] = new String[]{Messages.getString("Common.TitelGesamt"), //$NON-NLS-1$
				"", //$NON-NLS-1$
				"<b>" + txtKostenGesamt.getText() + "</b>"}; //$NON-NLS-1$ //$NON-NLS-2$
		
		return strings;
	}

}
