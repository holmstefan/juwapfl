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
import ch.wsl.fps.juwapfl.gui.main.WertastungMainWindow;
import ch.wsl.fps.juwapfl.model.WertastungErgebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WertastungErgebnisPanel extends AbstractErgebnisPanel<WertastungMainWindow, WertastungErgebnis> {

	private final JTextField txtAstungszeitProBaum = getNewLockedTextField();
	private final JTextField txtAstungszeitProHektar = getNewLockedTextField();
	private final JTextField txtAstungszeitProBestand = getNewLockedTextField();

	private final JTextField txtGehzeitProBaum = getNewLockedTextField();
	private final JTextField txtGehzeitProHektar = getNewLockedTextField();
	private final JTextField txtGehzeitProBestand = getNewLockedTextField();

	private final JTextField txtZeitaufwandGesamtProBaum = getNewLockedTextField();
	private final JTextField txtZeitaufwandGesamtProHektar = getNewLockedTextField();
	private final JTextField txtZeitaufwandGesamtProBestand = getNewLockedTextFieldBold();

	private final JTextField txtKostenAstungszeitProBaum = getNewLockedTextField();
	private final JTextField txtKostenAstungszeitProHektar = getNewLockedTextField();
	private final JTextField txtKostenAstungszeitProBestand = getNewLockedTextField();

	private final JTextField txtKostenGehzeitProBaum = getNewLockedTextField();
	private final JTextField txtKostenGehzeitProHektar = getNewLockedTextField();
	private final JTextField txtKostenGehzeitProBestand = getNewLockedTextField();

	private final JTextField txtKostenMaterialProBaum = getNewLockedTextField();
	private final JTextField txtKostenMaterialProHektar = getNewLockedTextField();
	private final JTextField txtKostenMaterialProBestand = getNewLockedTextField();

	private final JTextField txtKostenGesamtProBaum = getNewLockedTextFieldBold();
	private final JTextField txtKostenGesamtProHektar = getNewLockedTextFieldBold();
	private final JTextField txtKostenGesamtProBestand = getNewLockedTextField();
	
	public WertastungErgebnisPanel(WertastungMainWindow mainWindow) {
		super(mainWindow);
		
		AbstractMainWindow.initTitledBorder(this, Messages.getString("Common.TitelErgebnis")); //$NON-NLS-1$
		this.setBackground(AbstractErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(), getGBC(0, 0));
//		this.add(new JLabel());
		this.add(new JLabel("<html><center>" + Messages.getString("Common.PersonalzeitenWPPH") + "</center></html>", SwingConstants.CENTER), getGBC(1, 0, 3)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//		this.add(new JLabel());
		this.add(new JLabel(), getGBC(4, 0));
//		this.add(new JLabel());
		this.add(lblKostenTitle, getGBC(5, 0, 3));
//		this.add(new JLabel());
		
		int row = 1;
		int col = 0;
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel("<html><center>" + Messages.getString("Wertastung.proBaum") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Wertastung.proHektare") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Wertastung.Bestand") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		col++;
		this.add(new JLabel("<html><center>" + Messages.getString("Wertastung.proBaum") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Wertastung.proHektare") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Wertastung.Bestand") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Wertastung.Astungszeit") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtAstungszeitProBaum, getGBC(col++, row));
		this.add(txtAstungszeitProHektar, getGBC(col++, row));
		this.add(txtAstungszeitProBestand, getGBC(col++, row));
		col++;
		this.add(txtKostenAstungszeitProBaum, getGBC(col++, row));
		this.add(txtKostenAstungszeitProHektar, getGBC(col++, row));
		this.add(txtKostenAstungszeitProBestand, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Wertastung.Gehzeit") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtGehzeitProBaum, getGBC(col++, row));
		this.add(txtGehzeitProHektar, getGBC(col++, row));
		this.add(txtGehzeitProBestand, getGBC(col++, row));
		col++;
		this.add(txtKostenGehzeitProBaum, getGBC(col++, row));
		this.add(txtKostenGehzeitProHektar, getGBC(col++, row));
		this.add(txtKostenGehzeitProBestand, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Common.TitelMaterial") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
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
		
		this.add(txtZeitaufwandGesamtProBaum, getGBC(col++, row));
		this.add(txtZeitaufwandGesamtProHektar, getGBC(col++, row));
		this.add(txtZeitaufwandGesamtProBestand, getGBC(col++, row));
		col++;
		this.add(txtKostenGesamtProBaum, getGBC(col++, row));
		this.add(txtKostenGesamtProHektar, getGBC(col++, row));
		this.add(txtKostenGesamtProBestand, getGBC(col++, row));
		
		
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	@Override
	public void setErgebnis(WertastungErgebnis ergebnis) {
		txtAstungszeitProBaum.setText(formatMinutes(ergebnis.getAstungszeit_minProBaum()));
		txtAstungszeitProHektar.setText(formatMinutes(ergebnis.getAstungszeit_minProHektar()));
		txtAstungszeitProBestand.setText(formatMinutes(ergebnis.getAstungszeit_minProBestand()));

		txtGehzeitProBaum.setText(formatMinutes(ergebnis.getGehzeit_minProBaum()));
		txtGehzeitProHektar.setText(formatMinutes(ergebnis.getGehzeit_minProHektar()));
		txtGehzeitProBestand.setText(formatMinutes(ergebnis.getGehzeit_minProBestand()));

		txtZeitaufwandGesamtProBaum.setText(formatMinutes(ergebnis.getZeitaufwandGesamt_minProBaum()));
		txtZeitaufwandGesamtProHektar.setText(formatMinutes(ergebnis.getZeitaufwandGesamt_minProHektar()));
		txtZeitaufwandGesamtProBestand.setText(formatMinutes(ergebnis.getZeitaufwandGesamt_minProBestand()));

		txtKostenAstungszeitProBaum.setText(format(ergebnis.getKostenAstungszeit_proBaum()));
		txtKostenAstungszeitProHektar.setText(format(ergebnis.getKostenAstungszeit_proHektar()));
		txtKostenAstungszeitProBestand.setText(format(ergebnis.getKostenAstungszeit_proBestand()));

		txtKostenGehzeitProBaum.setText(format(ergebnis.getKostenGehzeit_proBaum()));
		txtKostenGehzeitProHektar.setText(format(ergebnis.getKostenGehzeit_proHektar()));
		txtKostenGehzeitProBestand.setText(format(ergebnis.getKostenGehzeit_proBestand()));

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
				Messages.getString("Wertastung.Astungszeit"), //$NON-NLS-1$
				txtAstungszeitProBaum.getText(),
				txtAstungszeitProHektar.getText(),
				txtAstungszeitProBestand.getText(),
				txtKostenAstungszeitProBaum.getText(),
				txtKostenAstungszeitProHektar.getText(),
				txtKostenAstungszeitProBestand.getText()};
		
		strings[1] = new String[]{
				Messages.getString("Wertastung.Gehzeit"), //$NON-NLS-1$
				txtGehzeitProBaum.getText(),
				txtGehzeitProHektar.getText(),
				txtGehzeitProBestand.getText(),
				txtKostenGehzeitProBaum.getText(),
				txtKostenGehzeitProHektar.getText(),
				txtKostenGehzeitProBestand.getText()};
		
		strings[2] = new String[]{Messages.getString("Common.TitelMaterial"), //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				txtKostenMaterialProBaum.getText(),
				txtKostenMaterialProHektar.getText(),
				txtKostenMaterialProBestand.getText()};
		
		strings[3] = new String[]{Messages.getString("Common.ErgebnisGesamt"), //$NON-NLS-1$
				txtZeitaufwandGesamtProBaum.getText(),
				txtZeitaufwandGesamtProHektar.getText(),
				"<b>" + txtZeitaufwandGesamtProBestand.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				"<b>" + txtKostenGesamtProBaum.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				"<b>" + txtKostenGesamtProHektar.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				txtKostenGesamtProBestand.getText()};
		
		return strings;
	}
	
}
