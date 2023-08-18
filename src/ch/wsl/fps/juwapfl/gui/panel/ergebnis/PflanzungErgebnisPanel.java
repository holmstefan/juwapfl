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
import ch.wsl.fps.juwapfl.gui.main.PflanzungMainWindow;
import ch.wsl.fps.juwapfl.model.PflanzungErgebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungErgebnisPanel extends AbstractErgebnisPanel<PflanzungMainWindow, PflanzungErgebnis> {

	private final JTextField txtZeitPersonalBeschaffung = getNewLockedTextField();
	private final JTextField txtZeitPersonalTransport = getNewLockedTextField();
	private final JTextField txtZeitPersonalPflanzung = getNewLockedTextField();
	private final JTextField txtZeitPersonalUnterhalt = getNewLockedTextField();
	private final JTextField txtZeitPersonalGesamt = getNewLockedTextFieldBold();

	private final JTextField txtZeitTransportmittelBeschaffung = getNewLockedTextField(true);
	private final JTextField txtZeitTransportmittelTransport = getNewLockedTextField(true);
	private final JTextField txtZeitTransportmittelPflanzung = getNewLockedTextField(true);
	private final JTextField txtZeitTransportmittelUnterhalt = getNewLockedTextField(true);
	private final JTextField txtZeitTransportmittelGesamt = getNewLockedTextField(true);

	private final JTextField txtZeitGeraeteBeschaffung = getNewLockedTextField(true);
	private final JTextField txtZeitGeraeteTransport = getNewLockedTextField(true);
	private final JTextField txtZeitGeraetePflanzung = getNewLockedTextField();
	private final JTextField txtZeitGeraeteUnterhalt = getNewLockedTextField(true);
	private final JTextField txtZeitGeraeteGesamt = getNewLockedTextFieldBold();


	private final JTextField txtKostenPersonalBeschaffung = getNewLockedTextField();
	private final JTextField txtKostenPersonalTransport = getNewLockedTextField();
	private final JTextField txtKostenPersonalPflanzung = getNewLockedTextField();
	private final JTextField txtKostenPersonalUnterhalt = getNewLockedTextField();
	private final JTextField txtKostenPersonalGesamt = getNewLockedTextField();

	private final JTextField txtKostenTransportmittelBeschaffung = getNewLockedTextField(true);
	private final JTextField txtKostenTransportmittelTransport = getNewLockedTextField();
	private final JTextField txtKostenTransportmittelPflanzung = getNewLockedTextField(true);
	private final JTextField txtKostenTransportmittelUnterhalt = getNewLockedTextField(true);
	private final JTextField txtKostenTransportmittelGesamt = getNewLockedTextField();

	private final JTextField txtKostenGeraeteBeschaffung = getNewLockedTextField();
	private final JTextField txtKostenGeraeteTransport = getNewLockedTextField(true);
	private final JTextField txtKostenGeraetePflanzung = getNewLockedTextField();
	private final JTextField txtKostenGeraeteUnterhalt = getNewLockedTextField(true);
	private final JTextField txtKostenGeraeteGesamt = getNewLockedTextField();

	private final JTextField txtKostenGesamtBeschaffung = getNewLockedTextField();
	private final JTextField txtKostenGesamtTransport = getNewLockedTextField();
	private final JTextField txtKostenGesamtPflanzung = getNewLockedTextField();
	private final JTextField txtKostenGesamtUnterhalt = getNewLockedTextField();
	private final JTextField txtKostenGesamtGesamt = getNewLockedTextFieldBold();
	

	public PflanzungErgebnisPanel(PflanzungMainWindow mainWindow) {
		super(mainWindow);
		
		AbstractMainWindow.initTitledBorder(this, Messages.getString("Common.TitelErgebnis")); //$NON-NLS-1$
		this.setBackground(AbstractErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(), getGBC(0, 0));
		this.add(new JLabel("<html><center>" + Messages.getString("Common.TitelZeiten") + "</center></html>", SwingConstants.CENTER), getGBC(1, 0, 5)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel(), getGBC(6, 0));
		this.add(lblKostenTitle, getGBC(7, 0, 5));
		
		int row = 1;
		int col = 0;
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel("<html><center>" + Messages.getString("Pflanzung.Beschaffung") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Pflanzung.Transport") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Pflanzung.Pflanzung") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Pflanzung.Unterhalt") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Common.TitelGesamt") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		col++;
		this.add(new JLabel("<html><center>" + Messages.getString("Pflanzung.Beschaffung") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Pflanzung.Transport") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Pflanzung.Pflanzung") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Pflanzung.Unterhalt") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(new JLabel("<html><center>" + Messages.getString("Common.TitelGesamt") + "</center></html>", SwingConstants.CENTER), getGBC(col++, row)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Common.TitelPersonalWPPH") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitPersonalBeschaffung, getGBC(col++, row));
		this.add(txtZeitPersonalTransport, getGBC(col++, row));
		this.add(txtZeitPersonalPflanzung, getGBC(col++, row));
		this.add(txtZeitPersonalUnterhalt, getGBC(col++, row));
		this.add(txtZeitPersonalGesamt, getGBC(col++, row));
		col++;
		this.add(txtKostenPersonalBeschaffung, getGBC(col++, row));
		this.add(txtKostenPersonalTransport, getGBC(col++, row));
		this.add(txtKostenPersonalPflanzung, getGBC(col++, row));
		this.add(txtKostenPersonalUnterhalt, getGBC(col++, row));
		this.add(txtKostenPersonalGesamt, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Pflanzung.Transportmittel") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitTransportmittelBeschaffung, getGBC(col++, row));
		this.add(txtZeitTransportmittelTransport, getGBC(col++, row));
		this.add(txtZeitTransportmittelPflanzung, getGBC(col++, row));
		this.add(txtZeitTransportmittelUnterhalt, getGBC(col++, row));
		this.add(txtZeitTransportmittelGesamt, getGBC(col++, row));
		col++;
		this.add(txtKostenTransportmittelBeschaffung, getGBC(col++, row));
		this.add(txtKostenTransportmittelTransport, getGBC(col++, row));
		this.add(txtKostenTransportmittelPflanzung, getGBC(col++, row));
		this.add(txtKostenTransportmittelUnterhalt, getGBC(col++, row));
		this.add(txtKostenTransportmittelGesamt, getGBC(col++, row));
		
		row++;
		col = 0;
		
		this.add(new JLabel(Messages.getString("Pflanzung.PflanzenGeraetePMH15") + TWO_SPACES, SwingConstants.RIGHT), getGBC(col++, row)); //$NON-NLS-1$
		this.add(txtZeitGeraeteBeschaffung, getGBC(col++, row));
		this.add(txtZeitGeraeteTransport, getGBC(col++, row));
		this.add(txtZeitGeraetePflanzung, getGBC(col++, row));
		this.add(txtZeitGeraeteUnterhalt, getGBC(col++, row));
		this.add(txtZeitGeraeteGesamt, getGBC(col++, row));
		col++;
		this.add(txtKostenGeraeteBeschaffung, getGBC(col++, row));
		this.add(txtKostenGeraeteTransport, getGBC(col++, row));
		this.add(txtKostenGeraetePflanzung, getGBC(col++, row));
		this.add(txtKostenGeraeteUnterhalt, getGBC(col++, row));
		this.add(txtKostenGeraeteGesamt, getGBC(col++, row));
		
		row++;
		col = 0;

		this.add(getLabelGesamt(), getGBC(col++, row));
		
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		this.add(new JLabel(), getGBC(col++, row));
		col++;
		this.add(txtKostenGesamtBeschaffung, getGBC(col++, row));
		this.add(txtKostenGesamtTransport, getGBC(col++, row));
		this.add(txtKostenGesamtPflanzung, getGBC(col++, row));
		this.add(txtKostenGesamtUnterhalt, getGBC(col++, row));
		this.add(txtKostenGesamtGesamt, getGBC(col++, row));
		
		
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	@Override
	public void setErgebnis(PflanzungErgebnis ergebnis) {
		txtZeitPersonalBeschaffung.setText(formatMinutes(ergebnis.getZeitPersonalBeschaffung_min()));
		txtZeitPersonalTransport.setText(formatMinutes(ergebnis.getZeitPersonalTransport_min()));
		txtZeitPersonalPflanzung.setText(formatMinutes(ergebnis.getZeitPersonalPflanzung_min()));
		txtZeitPersonalUnterhalt.setText(formatMinutes(ergebnis.getZeitPersonalUnterhalt_min()));
		txtZeitPersonalGesamt.setText(formatMinutes(ergebnis.getZeitPersonalGesamt_min()));

		txtZeitGeraetePflanzung.setText(formatMinutes(ergebnis.getZeitGeraetePflanzung_min()));
		txtZeitGeraeteGesamt.setText(formatMinutes(ergebnis.getZeitGeraeteGesamt_min()));


		txtKostenPersonalBeschaffung.setText(format(ergebnis.getKostenPersonalBeschaffung()));
		txtKostenPersonalTransport.setText(format(ergebnis.getKostenPersonalTransport()));
		txtKostenPersonalPflanzung.setText(format(ergebnis.getKostenPersonalPflanzung()));
		txtKostenPersonalUnterhalt.setText(format(ergebnis.getKostenPersonalUnterhalt()));
		txtKostenPersonalGesamt.setText(format(ergebnis.getKostenPersonalGesamt()));

		txtKostenTransportmittelTransport.setText(format(ergebnis.getKostenTransportmittelTransport()));
		txtKostenTransportmittelGesamt.setText(format(ergebnis.getKostenTransportmittelGesamt()));

		txtKostenGeraeteBeschaffung.setText(format(ergebnis.getKostenPflanzenBeschaffung()));
		txtKostenGeraetePflanzung.setText(format(ergebnis.getKostenGeraetePflanzung()));
		txtKostenGeraeteGesamt.setText(format(ergebnis.getKostenGeraeteGesamt()));

		txtKostenGesamtBeschaffung.setText(format(ergebnis.getKostenGesamtBeschaffung()));
		txtKostenGesamtTransport.setText(format(ergebnis.getKostenGesamtTransport()));
		txtKostenGesamtPflanzung.setText(format(ergebnis.getKostenGesamtPflanzung()));
		txtKostenGesamtUnterhalt.setText(format(ergebnis.getKostenGesamtUnterhalt()));
		txtKostenGesamtGesamt.setText(format(ergebnis.getKostenGesamtGesamt()));
	}

	@Override
	public String[][] getErgebnisAsStringArray() {
		String[][] strings = new String[4][];

		strings[0] = new String[]{
				Messages.getString("Common.TitelPersonalWPPH"), //$NON-NLS-1$
				txtZeitPersonalBeschaffung.getText(),
				txtZeitPersonalTransport.getText(),
				txtZeitPersonalPflanzung.getText(),
				txtZeitPersonalUnterhalt.getText(),
				"<b>" + txtZeitPersonalGesamt.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				txtKostenPersonalBeschaffung.getText(),
				txtKostenPersonalTransport.getText(),
				txtKostenPersonalPflanzung.getText(),
				txtKostenPersonalUnterhalt.getText(),
				txtKostenPersonalGesamt.getText()};
		
		strings[1] = new String[]{
				Messages.getString("Pflanzung.Transportmittel"), //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				txtKostenTransportmittelBeschaffung.getText(),
				txtKostenTransportmittelTransport.getText(),
				txtKostenTransportmittelPflanzung.getText(),
				txtKostenTransportmittelUnterhalt.getText(),
				txtKostenTransportmittelGesamt.getText()};
		
		strings[2] = new String[]{Messages.getString("Pflanzung.PflanzenGeraetePMH15"), //$NON-NLS-1$
				"-", //$NON-NLS-1$
				"-", //$NON-NLS-1$
				txtZeitGeraetePflanzung.getText(),
				"-", //$NON-NLS-1$
				"<b>" + txtZeitGeraeteGesamt.getText() + "</b>", //$NON-NLS-1$ //$NON-NLS-2$
				txtKostenGeraeteBeschaffung.getText(),
				txtKostenGeraeteTransport.getText(),
				txtKostenGeraetePflanzung.getText(),
				txtKostenGeraeteUnterhalt.getText(),
				txtKostenGeraeteGesamt.getText()};
		
		strings[3] = new String[]{Messages.getString("Common.TitelGesamt"), //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				"", //$NON-NLS-1$
				txtKostenGesamtBeschaffung.getText(),
				txtKostenGesamtTransport.getText(),
				txtKostenGesamtPflanzung.getText(),
				txtKostenGesamtUnterhalt.getText(),
				"<b>" + txtKostenGesamtGesamt.getText() + "</b>"}; //$NON-NLS-1$ //$NON-NLS-2$
		
		return strings;
	}

}
