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
import ch.wsl.fps.juwapfl.gui.main.WertastungMainWindow;
import ch.wsl.fps.juwapfl.gui.main.ZBaumDurchforstungMainWindow;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungErgebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ZBaumDurchforstungErgebnisPanel extends AbstractErgebnisPanel<ZBaumDurchforstungMainWindow, ZBaumDurchforstungErgebnis> {

	private final JTextField txtZeitaufwandPersonalProBaum = getNewLockedTextField();
	private final JTextField txtZeitaufwandPersonalProHektar = getNewLockedTextField();
	private final JTextField txtZeitaufwandPersonalProBestand = getNewLockedTextFieldBold();

	private final JTextField txtKostenPersonalProBaum = getNewLockedTextField();
	private final JTextField txtKostenPersonalProHektar = getNewLockedTextField();
	private final JTextField txtKostenPersonalProBestand = getNewLockedTextField();

	private final JTextField txtKostenMotorsaegeProBaum = getNewLockedTextField();
	private final JTextField txtKostenMotorsaegeProHektar = getNewLockedTextField();
	private final JTextField txtKostenMotorsaegeProBestand = getNewLockedTextField();

	private final JTextField txtKostenMaterialProBaum = getNewLockedTextField();
	private final JTextField txtKostenMaterialProHektar = getNewLockedTextField();
	private final JTextField txtKostenMaterialProBestand = getNewLockedTextField();

	private final JTextField txtKostenGesamtProBaum = getNewLockedTextFieldBold();
	private final JTextField txtKostenGesamtProHektar = getNewLockedTextFieldBold();
	private final JTextField txtKostenGesamtProBestand = getNewLockedTextField();
	
	public ZBaumDurchforstungErgebnisPanel(ZBaumDurchforstungMainWindow mainWindow) {
		super(mainWindow);
		
		WertastungMainWindow.initTitledBorder(this, Messages.getString("Common.TitelErgebnis")); //$NON-NLS-1$
		this.setBackground(AbstractErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(), getGBC(0, 0));
//		this.add(new JLabel());
		this.add(new JLabel("<html><center>" + Messages.getString("Common.TitelZeiten") + "</center></html>", SwingConstants.CENTER), getGBC(1, 0, 3)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//		this.add(new JLabel());
		this.add(new JLabel(), getGBC(4, 0));
//		this.add(new JLabel());
		this.add(lblKostenTitle, getGBC(5, 0, 3));
//		this.add(new JLabel());
		
		int row = 1;
		int col = 0;
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel("<html><center>" + Messages.getString("ZBaumDurchforstung.proZBaum") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("ZBaumDurchforstung.proHektare") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("ZBaumDurchforstung.Bestand") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		col++;
		this.add(new JLabel("<html><center>" + Messages.getString("ZBaumDurchforstung.proZBaum") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("ZBaumDurchforstung.proHektare") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("ZBaumDurchforstung.Bestand") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Common.TitelPersonalWPPH") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitaufwandPersonalProBaum, getGBC(col++, row));
		this.add(txtZeitaufwandPersonalProHektar, getGBC(col++, row));
		this.add(txtZeitaufwandPersonalProBestand, getGBC(col++, row));
		col++;
		this.add(txtKostenPersonalProBaum, getGBC(col++, row));
		this.add(txtKostenPersonalProHektar, getGBC(col++, row));
		this.add(txtKostenPersonalProBestand, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Common.TitelMotorsaege") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(getNewLockedTextField(true), getGBC(col++, row));
		this.add(getNewLockedTextField(true), getGBC(col++, row));
		this.add(getNewLockedTextField(true), getGBC(col++, row));
		col++;
		this.add(txtKostenMotorsaegeProBaum, getGBC(col++, row));
		this.add(txtKostenMotorsaegeProHektar, getGBC(col++, row));
		this.add(txtKostenMotorsaegeProBestand, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("ZBaumDurchforstung.AusruestungKronenschnitte") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(getNewLockedTextField(true), getGBC(col++, row));
		this.add(getNewLockedTextField(true), getGBC(col++, row));
		this.add(getNewLockedTextField(true), getGBC(col++, row));
		col++;
		this.add(txtKostenMaterialProBaum, getGBC(col++, row));
		this.add(txtKostenMaterialProHektar, getGBC(col++, row));
		this.add(txtKostenMaterialProBestand, getGBC(col++, row));
		
		row++;
		col = 0;

		this.add(getLabelGesamt(), getGBC(col++, row));
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		col++;
		this.add(txtKostenGesamtProBaum, getGBC(col++, row));
		this.add(txtKostenGesamtProHektar, getGBC(col++, row));
		this.add(txtKostenGesamtProBestand, getGBC(col++, row));
		
		
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	@Override
	public void setErgebnis(ZBaumDurchforstungErgebnis ergebnis) {
		txtZeitaufwandPersonalProBaum.setText(formatMinutes(ergebnis.getZeitaufwandPersonal_minProBaum()));
		txtZeitaufwandPersonalProHektar.setText(formatMinutes(ergebnis.getZeitaufwandPersonal_minProHektar()));
		txtZeitaufwandPersonalProBestand.setText(formatMinutes(ergebnis.getZeitaufwandPersonal_minProBestand()));

		txtKostenPersonalProBaum.setText(format(ergebnis.getKostenPersonal_proBaum()));
		txtKostenPersonalProHektar.setText(format(ergebnis.getKostenPersonal_proHektar()));
		txtKostenPersonalProBestand.setText(format(ergebnis.getKostenPersonal_proBestand()));

		txtKostenMotorsaegeProBaum.setText(format(ergebnis.getKostenMotorsaege_proBaum()));
		txtKostenMotorsaegeProHektar.setText(format(ergebnis.getKostenMotorsaege_proHektar()));
		txtKostenMotorsaegeProBestand.setText(format(ergebnis.getKostenMotorsaege_proBestand()));

		txtKostenMaterialProBaum.setText(format(ergebnis.getKostenMaterial_proBaum()));
		txtKostenMaterialProHektar.setText(format(ergebnis.getKostenMaterial_proHektar()));
		txtKostenMaterialProBestand.setText(format(ergebnis.getKostenMaterial_proBestand()));

		txtKostenGesamtProBaum.setText(format(ergebnis.getKostenGesamt_proBaum()));
		txtKostenGesamtProHektar.setText(format(ergebnis.getKostenGesamt_proHektar()));
		txtKostenGesamtProBestand.setText(format(ergebnis.getKostenGesamt_proBestand()));
	}
	
	
	@Override
	public String[][] getErgebnisAsStringArray() {
		String[][] strings = new String[4][];

		strings[0] = new String[]{
				Messages.getString("Common.TitelPersonalWPPH"), //$NON-NLS-1$
				txtZeitaufwandPersonalProBaum.getText(),
				txtZeitaufwandPersonalProHektar.getText(),
				"<b>" + txtZeitaufwandPersonalProBestand.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				txtKostenPersonalProBaum.getText(),
				txtKostenPersonalProHektar.getText(),
				txtKostenPersonalProBestand.getText()};
		
		strings[1] = new String[]{
				Messages.getString("Common.TitelMotorsaege"), //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				txtKostenMotorsaegeProBaum.getText(),
				txtKostenMotorsaegeProHektar.getText(),
				txtKostenMotorsaegeProBestand.getText()};
		
		strings[2] = new String[]{Messages.getString("ZBaumDurchforstung.AusruestungKronenschnitte"), //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				txtKostenMaterialProBaum.getText(),
				txtKostenMaterialProHektar.getText(),
				txtKostenMaterialProBestand.getText()};
		
		strings[3] = new String[]{Messages.getString("Common.TitelGesamt"), //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				"<b>" + txtKostenGesamtProBaum.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				"<b>" + txtKostenGesamtProHektar.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				txtKostenGesamtProBestand.getText()};
		
		return strings;
	}
	
}
