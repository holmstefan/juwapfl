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
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.RueckegassenMainWindow;
import ch.wsl.fps.juwapfl.model.RueckegassenErgebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public class RueckegassenErgebnisPanel extends AbstractErgebnisPanel<RueckegassenMainWindow, RueckegassenErgebnis> {

	private final JTextField txtZeitaufwandHauptpersonProHektar = getNewLockedTextField();
	private final JTextField txtZeitaufwandHauptpersonGesamtflaeche = getNewLockedTextField();

	private final JTextField txtZeitaufwandHilfskraftProHektar = getNewLockedTextField();
	private final JTextField txtZeitaufwandHilfskraftGesamtflaeche = getNewLockedTextField();

	
	private final JTextField txtKostenHauptpersonProHektar = getNewLockedTextField();
	private final JTextField txtKostenHauptpersonGesamtflaeche = getNewLockedTextField();

	private final JTextField txtKostenHilfskraftProHektar = getNewLockedTextField();
	private final JTextField txtKostenHilfskraftGesamtflaeche = getNewLockedTextField();

	private final JTextField txtKostenMaterialProHektar = getNewLockedTextField();
	private final JTextField txtKostenMaterialGesamtflaeche = getNewLockedTextField();

	private final JTextField txtKostenGesamtProHektar = getNewLockedTextField();
	private final JTextField txtKostenGesamtGesamtflaeche = getNewLockedTextFieldBold();

	
	public RueckegassenErgebnisPanel(RueckegassenMainWindow mainWindow) {
		super(mainWindow);
		
		AbstractMainWindow.initTitledBorder(this, Messages.getString("Common.TitelErgebnis")); //$NON-NLS-1$
		this.setBackground(AbstractErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(), getGBC(0, 0));
		this.add(new JLabel("<html><center>" + Messages.getString("Common.PersonalzeitenWPPH") + "</center></html>", SwingConstants.CENTER), getGBC(1, 0, 2)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel(), getGBC(3, 0));
		this.add(lblKostenTitle, getGBC(4, 0, 2));
		
		int row = 1;
		int col = 0;
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel("<html><center>" + Messages.getString("Rueckegassen.proHektare") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Rueckegassen.proGesamtflaeche") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		col++;
		this.add(new JLabel("<html><center>" + Messages.getString("Rueckegassen.proHektare") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Rueckegassen.proGesamtflaeche") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Rueckegassen.Hauptperson") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitaufwandHauptpersonProHektar, getGBC(col++, row));
		this.add(txtZeitaufwandHauptpersonGesamtflaeche, getGBC(col++, row));
		col++;
		this.add(txtKostenHauptpersonProHektar, getGBC(col++, row));
		this.add(txtKostenHauptpersonGesamtflaeche, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Rueckegassen.Hilfskraft") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitaufwandHilfskraftProHektar, getGBC(col++, row));
		this.add(txtZeitaufwandHilfskraftGesamtflaeche, getGBC(col++, row));
		col++;
		this.add(txtKostenHilfskraftProHektar, getGBC(col++, row));
		this.add(txtKostenHilfskraftGesamtflaeche, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Common.TitelMaterial") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(getNewLockedTextField(true), getGBC(col++, row));
		this.add(getNewLockedTextField(true), getGBC(col++, row));
		col++;
		this.add(txtKostenMaterialProHektar, getGBC(col++, row));
		this.add(txtKostenMaterialGesamtflaeche, getGBC(col++, row));
		
		row++;
		col = 0;

		this.add(getLabelGesamt(), getGBC(col++, row));
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		col++;
		this.add(txtKostenGesamtProHektar, getGBC(col++, row));
		this.add(txtKostenGesamtGesamtflaeche, getGBC(col++, row));
		
		
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	@Override
	protected JLabel getLabelGesamt() {
		final String infoButtonText = Utilities.normalizeTooltipText(Messages.getString("Rueckegassen.InfoButtonRundung")); //$NON-NLS-1$
		final JLabel lblGesamt = Utilities.getInfoButtonBlue(infoButtonText);
		lblGesamt.setText(Messages.getString("Common.ErgebnisGesamt") + TWO_SPACES); //$NON-NLS-1$
		lblGesamt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGesamt.setHorizontalTextPosition(SwingConstants.RIGHT);
		return lblGesamt;
	}
	
	
	@Override
	public void setErgebnis(RueckegassenErgebnis ergebnis) {
		txtZeitaufwandHauptpersonProHektar.setText(formatMinutes(ergebnis.getZeitaufwandHauptperson_minProHektar()));
		txtZeitaufwandHauptpersonGesamtflaeche.setText(formatMinutes(ergebnis.getZeitaufwandHauptperson_minGesamtflaeche()));

		txtZeitaufwandHilfskraftProHektar.setText(formatMinutes(ergebnis.getZeitaufwandHilfskraft_minProHektar()));
		txtZeitaufwandHilfskraftGesamtflaeche.setText(formatMinutes(ergebnis.getZeitaufwandHilfskraft_minGesamtflaeche()));

		txtKostenHauptpersonProHektar.setText(format(ergebnis.getKostenHauptperson_proHektar()));
		txtKostenHauptpersonGesamtflaeche.setText(format(ergebnis.getKostenHauptperson_Gesamtflaeche()));

		txtKostenHilfskraftProHektar.setText(format(ergebnis.getKostenHilfskraft_proHektar()));
		txtKostenHilfskraftGesamtflaeche.setText(format(ergebnis.getKostenHilfskraft_Gesamtflaeche()));

		txtKostenMaterialProHektar.setText(format(ergebnis.getKostenMaterial_proHektar()));
		txtKostenMaterialGesamtflaeche.setText(format(ergebnis.getKostenMaterial_Gesamtflaeche()));

		txtKostenGesamtProHektar.setText(format(ergebnis.getKostenGesamt_proHektar()));
		txtKostenGesamtGesamtflaeche.setText(format(ergebnis.getKostenGesamt_Gesamtflaeche()));
	}

	
	@Override
	public String[][] getErgebnisAsStringArray() {
		String[][] strings = new String[4][];

		strings[0] = new String[]{
				Messages.getString("Rueckegassen.Hauptperson"), //$NON-NLS-1$
				txtZeitaufwandHauptpersonProHektar.getText(),
				txtZeitaufwandHauptpersonGesamtflaeche.getText(),
				txtKostenHauptpersonProHektar.getText(),
				txtKostenHauptpersonGesamtflaeche.getText()};
		
		strings[1] = new String[]{
				Messages.getString("Rueckegassen.Hilfskraft"), //$NON-NLS-1$
				txtZeitaufwandHilfskraftProHektar.getText(),
				txtZeitaufwandHilfskraftGesamtflaeche.getText(),
				txtKostenHilfskraftProHektar.getText(),
				txtKostenHilfskraftGesamtflaeche.getText()};
		
		strings[2] = new String[]{Messages.getString("Common.TitelMaterial"), //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				txtKostenMaterialProHektar.getText(),
				txtKostenMaterialGesamtflaeche.getText()};
		
		strings[3] = new String[]{Messages.getString("Common.ErgebnisGesamt"), //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				txtKostenGesamtProHektar.getText(),
				"<b>" + txtKostenGesamtGesamtflaeche.getText() + "</b>"}; //$NON-NLS-1$ //$NON-NLS-2$
		
		return strings;
	}

}
