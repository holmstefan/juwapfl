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

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.text.DecimalFormat;
import java.time.Duration;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.CurrencySensitive;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.model.AbstractErgebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractErgebnisPanel<M extends AbstractMainWindow, E extends AbstractErgebnis> extends JPanel implements CurrencySensitive {
	
	public static final Color ERGEBNIS_PANEL_BACKGROUND_COLOR = new Color(255,255,225);
	
	protected static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(",##0.00"); //$NON-NLS-1$
	
	protected static final String TWO_SPACES = "  "; //$NON-NLS-1$
	
	protected final M mainWindow;
	
	protected final JLabel lblKostenTitle = new JLabel("", SwingConstants.CENTER); //$NON-NLS-1$
	
	
	protected AbstractErgebnisPanel(M mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	
	protected static GridBagConstraints getGBC(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.ipady = 8;
		return gbc;
	}
	
	
	protected static GridBagConstraints getGBC(int x, int y, int gridwidth) {
		GridBagConstraints gbc = getGBC(x, y);
		gbc.gridwidth = gridwidth;
		gbc.ipady = 0;
		return gbc;
	}
	
	
	protected JLabel getLabelGesamt() {
		final String infoButtonText = Utilities.normalizeTooltipText(Messages.getString("Common.InfoButtonRundung")); //$NON-NLS-1$
		final JLabel lblGesamt = Utilities.getInfoButtonBlue(infoButtonText);
		lblGesamt.setText(Messages.getString("Common.ErgebnisGesamt") + TWO_SPACES); //$NON-NLS-1$
		lblGesamt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGesamt.setHorizontalTextPosition(SwingConstants.RIGHT);
		return lblGesamt;
	}
	
	
	protected static String format(double value) {
		return DECIMAL_FORMAT.format(value);
	}
	
	
	protected static String formatMinutes(double totalMinutes) {
		final boolean SHOW_SECONDS = false;
		Duration d = Duration.ofSeconds(Math.round(totalMinutes * 60));
		long hours = d.toHours();
		long minutes = d.toMinutes() - (60 * d.toHours());
		long seconds = d.getSeconds() - (60 * d.toMinutes());
		if (seconds >= 30 && SHOW_SECONDS == false) {
			minutes += 1;
			if (minutes == 60) {
				hours += 1;
				minutes = 0;
			}
		}
		if (hours > 0) {
			return String.format("%dh %dmin", hours, minutes); //$NON-NLS-1$
		}
		else {
			if (SHOW_SECONDS) {
				return String.format("%dmin %ds", minutes, seconds); //$NON-NLS-1$
			}
			else {
				return String.format("%dmin", minutes); //$NON-NLS-1$
			}
		}
	}
	
	
	protected static JTextField getNewLockedTextFieldBold() {
		JTextField result = getNewLockedTextField();
		result.setFont(result.getFont().deriveFont(Font.BOLD));
		return result;
	}
	
	
	protected static JTextField getNewLockedTextField() {
		return getNewLockedTextField(false);
	}
	
	
	protected static JTextField getNewLockedTextField(boolean isAlwaysEmpty) {
		JTextField textField = new JTextField();
		textField.setEditable(false);
		if (isAlwaysEmpty) {
			textField.setText("-"); //$NON-NLS-1$
			textField.setHorizontalAlignment(JTextField.CENTER);
		}
		else {
			textField.setBackground(Color.WHITE);
			textField.setHorizontalAlignment(JTextField.RIGHT);
		}
//		textField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		return textField;
	}
	
	
	public abstract void setErgebnis(E ergebnis);
	
	
	public abstract String[][] getErgebnisAsStringArray();
	

	@Override
	public void updateCurrency() {
		lblKostenTitle.setText("<html><center>" + Messages.getString("Common.ErgebnisKosten") + " [" + mainWindow.getCurrency() + "]</center></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
