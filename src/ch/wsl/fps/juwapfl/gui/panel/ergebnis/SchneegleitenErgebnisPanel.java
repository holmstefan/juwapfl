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
import ch.wsl.fps.juwapfl.gui.main.SchneegleitenMainWindow;
import ch.wsl.fps.juwapfl.model.SchneegleitenErgebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SchneegleitenErgebnisPanel extends AbstractErgebnisPanel<SchneegleitenMainWindow, SchneegleitenErgebnis> {

	private final JTextField txtZeitPersonalAufbau = getNewLockedTextField();
	private final JTextField txtZeitPersonalUnterhalt = getNewLockedTextField();
	private final JTextField txtZeitPersonalAbbau = getNewLockedTextField();
	private final JTextField txtZeitPersonalGesamt = getNewLockedTextFieldBold();

	private final JTextField txtZeitFahrtenAufbau = getNewLockedTextField(true);
	private final JTextField txtZeitFahrtenUnterhalt = getNewLockedTextField(true);
	private final JTextField txtZeitFahrtenAbbau = getNewLockedTextField(true);
	private final JTextField txtZeitFahrtenGesamt = getNewLockedTextField(true);

	private final JTextField txtZeitMaterialAufbau = getNewLockedTextField(true);
	private final JTextField txtZeitMaterialUnterhalt = getNewLockedTextField(true);
	private final JTextField txtZeitMaterialAbbau = getNewLockedTextField(true);
	private final JTextField txtZeitMaterialGesamt = getNewLockedTextField(true);


	private final JTextField txtKostenPersonalAufbau = getNewLockedTextField();
	private final JTextField txtKostenPersonalUnterhalt = getNewLockedTextField();
	private final JTextField txtKostenPersonalAbbau = getNewLockedTextField();
	private final JTextField txtKostenPersonalGesamt = getNewLockedTextField();

	private final JTextField txtKostenFahrtenAufbau = getNewLockedTextField();
	private final JTextField txtKostenFahrtenUnterhalt = getNewLockedTextField();
	private final JTextField txtKostenFahrtenAbbau = getNewLockedTextField();
	private final JTextField txtKostenFahrtenGesamt = getNewLockedTextField();

	private final JTextField txtKostenMaterialAufbau = getNewLockedTextField();
	private final JTextField txtKostenMaterialUnterhalt = getNewLockedTextField();
	private final JTextField txtKostenMaterialAbbau = getNewLockedTextField();
	private final JTextField txtKostenMaterialGesamt = getNewLockedTextField();

	private final JTextField txtKostenGesamtAufbau = getNewLockedTextField();
	private final JTextField txtKostenGesamtUnterhalt = getNewLockedTextField();
	private final JTextField txtKostenGesamtAbbau = getNewLockedTextField();
	private final JTextField txtKostenGesamtGesamt = getNewLockedTextFieldBold();
	

	public SchneegleitenErgebnisPanel(SchneegleitenMainWindow mainWindow) {
		super(mainWindow);
		
		AbstractMainWindow.initTitledBorder(this, Messages.getString("Schneegleiten.TitelErgebnis")); //$NON-NLS-1$
		this.setBackground(AbstractErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(), getGBC(0, 0));
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisZeiten") + "</center></html>", SwingConstants.CENTER), getGBC(1, 0, 4)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel(), getGBC(5, 0));
		this.add(lblKostenTitle, getGBC(6, 0, 4));
		
		int row = 1;
		int col = 0;
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisAufbau") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisUnterhalt") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisAbbau") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisGesamt") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		col++;
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisAufbau") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisUnterhalt") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisAbbau") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Schneegleiten.ErgebnisGesamt") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Common.TitelPersonalWPPH") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitPersonalAufbau, getGBC(col++, row));
		this.add(txtZeitPersonalUnterhalt, getGBC(col++, row));
		this.add(txtZeitPersonalAbbau, getGBC(col++, row));
		this.add(txtZeitPersonalGesamt, getGBC(col++, row));
		col++;
		this.add(txtKostenPersonalAufbau, getGBC(col++, row));
		this.add(txtKostenPersonalUnterhalt, getGBC(col++, row));
		this.add(txtKostenPersonalAbbau, getGBC(col++, row));
		this.add(txtKostenPersonalGesamt, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Schneegleiten.ErgebnisFahrtenTransporte") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitFahrtenAufbau, getGBC(col++, row));
		this.add(txtZeitFahrtenUnterhalt, getGBC(col++, row));
		this.add(txtZeitFahrtenAbbau, getGBC(col++, row));
		this.add(txtZeitFahrtenGesamt, getGBC(col++, row));
		col++;
		this.add(txtKostenFahrtenAufbau, getGBC(col++, row));
		this.add(txtKostenFahrtenUnterhalt, getGBC(col++, row));
		this.add(txtKostenFahrtenAbbau, getGBC(col++, row));
		this.add(txtKostenFahrtenGesamt, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Schneegleiten.ErgebnisMaterialGeraete") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitMaterialAufbau, getGBC(col++, row));
		this.add(txtZeitMaterialUnterhalt, getGBC(col++, row));
		this.add(txtZeitMaterialAbbau, getGBC(col++, row));
		this.add(txtZeitMaterialGesamt, getGBC(col++, row));
		col++;
		this.add(txtKostenMaterialAufbau, getGBC(col++, row));
		this.add(txtKostenMaterialUnterhalt, getGBC(col++, row));
		this.add(txtKostenMaterialAbbau, getGBC(col++, row));
		this.add(txtKostenMaterialGesamt, getGBC(col++, row));
		
		row++;
		col = 0;

		this.add(getLabelGesamt(), getGBC(col++, row));
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		col++;
		this.add(txtKostenGesamtAufbau, getGBC(col++, row));
		this.add(txtKostenGesamtUnterhalt, getGBC(col++, row));
		this.add(txtKostenGesamtAbbau, getGBC(col++, row));
		this.add(txtKostenGesamtGesamt, getGBC(col++, row));
		
		
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	@Override
	public void setErgebnis(SchneegleitenErgebnis ergebnis) {
		txtZeitPersonalAufbau.setText(formatMinutes(ergebnis.getZeitPersonalAufbau_min()));
		txtZeitPersonalUnterhalt.setText(formatMinutes(ergebnis.getZeitPersonalUnterhalt_min()));
		txtZeitPersonalAbbau.setText(formatMinutes(ergebnis.getZeitPersonalAbbau_min()));
		txtZeitPersonalGesamt.setText(formatMinutes(ergebnis.getZeitPersonalGesamt_min()));

		
		txtKostenPersonalAufbau.setText(format(ergebnis.getKostenPersonalAufbau()));
		txtKostenPersonalUnterhalt.setText(format(ergebnis.getKostenPersonalUnterhalt()));
		txtKostenPersonalAbbau.setText(format(ergebnis.getKostenPersonalAbbau()));
		txtKostenPersonalGesamt.setText(format(ergebnis.getKostenPersonalGesamt()));

		txtKostenFahrtenAufbau.setText(format(ergebnis.getKostenFahrtenAufbau()));
		txtKostenFahrtenUnterhalt.setText(format(ergebnis.getKostenFahrtenUnterhalt()));
		txtKostenFahrtenAbbau.setText(format(ergebnis.getKostenFahrtenAbbau()));
		txtKostenFahrtenGesamt.setText(format(ergebnis.getKostenFahrtenGesamt()));

		txtKostenMaterialAufbau.setText(format(ergebnis.getKostenMaterialAufbau()));
		txtKostenMaterialUnterhalt.setText(format(ergebnis.getKostenMaterialUnterhalt()));
		txtKostenMaterialAbbau.setText(format(ergebnis.getKostenMaterialAbbau()));
		txtKostenMaterialGesamt.setText(format(ergebnis.getKostenMaterialGesamt()));

		txtKostenGesamtAufbau.setText(format(ergebnis.getKostenGesamtAufbau()));
		txtKostenGesamtUnterhalt.setText(format(ergebnis.getKostenGesamtUnterhalt()));
		txtKostenGesamtAbbau.setText(format(ergebnis.getKostenGesamtAbbau()));
		txtKostenGesamtGesamt.setText(format(ergebnis.getKostenGesamtGesamt()));
	}

	
	@Override
	public String[][] getErgebnisAsStringArray() {
		String[][] strings = new String[4][];

		strings[0] = new String[]{
				Messages.getString("Common.TitelPersonalWPPH"), //$NON-NLS-1$
				txtZeitPersonalAufbau.getText(),
				txtZeitPersonalUnterhalt.getText(),
				txtZeitPersonalAbbau.getText(),
				"<b>" + txtZeitPersonalGesamt.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				txtKostenPersonalAufbau.getText(),
				txtKostenPersonalUnterhalt.getText(),
				txtKostenPersonalAbbau.getText(),
				txtKostenPersonalGesamt.getText()};
		
		strings[1] = new String[]{
				Messages.getString("Schneegleiten.ErgebnisFahrtenTransporte"), //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				txtKostenFahrtenAufbau.getText(),
				txtKostenFahrtenUnterhalt.getText(),
				txtKostenFahrtenAbbau.getText(),
				txtKostenFahrtenGesamt.getText()};
		
		strings[2] = new String[]{Messages.getString("Schneegleiten.ErgebnisMaterialGeraete"), //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				txtKostenMaterialAufbau.getText(),
				txtKostenMaterialUnterhalt.getText(),
				txtKostenMaterialAbbau.getText(),
				txtKostenMaterialGesamt.getText()};
		
		strings[3] = new String[]{Messages.getString("Schneegleiten.ErgebnisGesamt"), //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				txtKostenGesamtAufbau.getText(),
				txtKostenGesamtUnterhalt.getText(),
				txtKostenGesamtAbbau.getText(),
				"<b>" + txtKostenGesamtGesamt.getText() + "</b>"}; //$NON-NLS-1$ //$NON-NLS-2$
		
		return strings;
	}

}
