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

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.CurrencySensitive;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.controls.JFlashingSpinner;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;
import ch.wsl.fps.juwapfl.gui.main.PflanzungMainWindow;
import ch.wsl.fps.juwapfl.model.PflanzungModel;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Baumart;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Pflanztechnik;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Pflanzwerkzeug;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Schwierigkeitsgrad;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungZeitenKostenPanel extends AbstractInputPanel implements CurrencySensitive {
	
	private static final double DEFAULT_ZEIT_BESCHAFFUNG_H = 1;
	
	private final PflanzungMainWindow mainWindow;

	private final JSpinner txtZeitBeschaffung_h = new JSpinner(new SpinnerNumberModel(DEFAULT_ZEIT_BESCHAFFUNG_H, 0.00, 10000, 1));
	private final JLabel lblKostenProPflanze = new JLabel();
	private final JFlashingSpinner txtKostenProPflanze = new JFlashingSpinner(new SpinnerNumberModel(4.50, 0.00, 100000, 1));
	
	private final JSpinner txtZeitTransport_h = new JSpinner(new SpinnerNumberModel(3, 0.00, 10000, 1));
	private final JLabel lblKostenTransportmittel = new JLabel();
	private final JSpinner txtKostenTransportmittel = new JSpinner(new SpinnerNumberModel(120, 0.00, 1000000, 1));
	
	private final JFlashingSpinner txtZeitPflanzung_PflProH = new JFlashingSpinner(new SpinnerNumberModel(1, 0.01, 10000, 1));
	private final JFlashingSpinner txtAnteilMaschinenlaufzeit_Prz = new JFlashingSpinner(new SpinnerNumberModel(100, 0, 100, 1));
	private final JLabel lblKostensatzGeraet_proH = new JLabel();
	private final JFlashingSpinner txtKostensatzGeraet_proH = new JFlashingSpinner(new SpinnerNumberModel(1, 0.00, 100000, 1));
	
	private final JSpinner txtZeitUnterhalt_Prozent = new JSpinner(new SpinnerNumberModel(10, 0, 2000, 10));
	
	{
		AbstractMainWindow.adjustJSpinnerFormatter(txtZeitBeschaffung_h, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenProPflanze, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtZeitTransport_h, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostenTransportmittel, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtZeitPflanzung_PflProH, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtAnteilMaschinenlaufzeit_Prz, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtKostensatzGeraet_proH, false);
		AbstractMainWindow.adjustJSpinnerFormatter(txtZeitUnterhalt_Prozent, false);
	}
	
	private Pflanzwerkzeug pflanzwerkzeug;
	private Pflanztechnik pflanztechnik;
	private Baumart baumart;
	private Schwierigkeitsgrad schwierigkeit;
	private PflanzungWinkelpflanzungModel winkelpflanzungModel;
	
	
	public PflanzungZeitenKostenPanel(PflanzungMainWindow mainWindow) {
		super(Messages.getString("Pflanzung.TitelZeitaufwaendeKosten"), Messages.getString("Pflanzung.InfoButtonZeitenKosten")); //$NON-NLS-1$ //$NON-NLS-2$
		this.mainWindow = mainWindow;
		this.setLayout(new GridLayout(0, 2, 5, 0));
		
		final int strutSize = 2; //remember that in GridLayout all slots have equal size.

		this.add(new JLabel(Messages.getString("Pflanzung.ZeitaufwandBeschaffung"))); //$NON-NLS-1$
		String infoButtonTextZeitBeschaffung = Messages.getString("Pflanzung.InfoButtonZeitaufwandBeschaffung"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtZeitBeschaffung_h, infoButtonTextZeitBeschaffung));
		
		this.add(lblKostenProPflanze);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostenProPflanze));

		this.add(Box.createVerticalStrut(strutSize));
		this.add(Box.createVerticalStrut(strutSize));

		this.add(new JLabel(Messages.getString("Pflanzung.ZeitaufwandTransport"))); //$NON-NLS-1$
		String infoButtonTextZeitTransport = Messages.getString("Pflanzung.InfoButtonZeitaufwandTransport"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtZeitTransport_h, infoButtonTextZeitTransport));
		
		this.add(lblKostenTransportmittel);
		String infoButtonTextKostenTransportmittel = Messages.getString("Pflanzung.InfoButtonKostenTransport"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtKostenTransportmittel, infoButtonTextKostenTransportmittel));

		this.add(Box.createVerticalStrut(strutSize));
		this.add(Box.createVerticalStrut(strutSize));
		
		this.add(new JLabel(Messages.getString("Pflanzung.Pflanzleistung"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtZeitPflanzung_PflProH));
		
		this.add(new JLabel(Messages.getString("Pflanzung.Maschinenlaufzeit"))); //$NON-NLS-1$
		this.add(Utilities.getPanelWithoutInfoButton(txtAnteilMaschinenlaufzeit_Prz));
		
		this.add(lblKostensatzGeraet_proH);
		this.add(Utilities.getPanelWithoutInfoButton(txtKostensatzGeraet_proH));

		this.add(Box.createVerticalStrut(strutSize));
		this.add(Box.createVerticalStrut(strutSize));
		
		this.add(new JLabel(Messages.getString("Pflanzung.ZeitaufwandUnterhalt"))); //$NON-NLS-1$
		String infoButtonTextZeitUnterhalt = Messages.getString("Pflanzung.InfoButtonZeitaufwandUnterhalt"); //$NON-NLS-1$
		this.add(Utilities.getPanelWithInfoButton(txtZeitUnterhalt_Prozent, infoButtonTextZeitUnterhalt));
		
		initListeners();
		mainWindow.registerForCurrencyUpdates(this);
	}
	
	
	private void initListeners() {
		txtZeitBeschaffung_h.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenProPflanze.addChangeListener(mainWindow.getDefaultChangeListener());
		txtZeitTransport_h.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostenTransportmittel.addChangeListener(mainWindow.getDefaultChangeListener());
		txtZeitPflanzung_PflProH.addChangeListener(mainWindow.getDefaultChangeListener());
		txtAnteilMaschinenlaufzeit_Prz.addChangeListener(mainWindow.getDefaultChangeListener());
		txtKostensatzGeraet_proH.addChangeListener(mainWindow.getDefaultChangeListener());
		txtZeitUnterhalt_Prozent.addChangeListener(mainWindow.getDefaultChangeListener());
	}

	
	public void onInputChangedBeforeCalculation(Pflanzwerkzeug pflanzwerkzeug, Pflanztechnik pflanztechnik, Baumart baumart, Schwierigkeitsgrad schwierigkeit, PflanzungWinkelpflanzungModel winkelpflanzungModel) {
		if (this.pflanzwerkzeug != pflanzwerkzeug || this.pflanztechnik != pflanztechnik || this.baumart != baumart || this.schwierigkeit != schwierigkeit) {
			txtKostenProPflanze.setValue(PflanzungModel.getDefaultKostenProPflanze(pflanzwerkzeug, pflanztechnik));
			txtZeitPflanzung_PflProH.setValue(PflanzungModel.getDefaultZeitaufwandPflanzung_PflProH(pflanzwerkzeug, pflanztechnik, baumart, schwierigkeit, winkelpflanzungModel));
			txtAnteilMaschinenlaufzeit_Prz.setValue(PflanzungModel.getDefaultAnteilMaschinenlaufzeit_Prz(pflanzwerkzeug, pflanztechnik, schwierigkeit));
			txtKostensatzGeraet_proH.setValue(PflanzungModel.getDefaultKostensatzGeraet_proH(pflanzwerkzeug, pflanztechnik));
			
			if (mainWindow.isInitializing == false) {
				txtKostenProPflanze.flash();
				txtZeitPflanzung_PflProH.flash();
				txtAnteilMaschinenlaufzeit_Prz.flash();
				txtKostensatzGeraet_proH.flash();
			}
		}
		else if (this.winkelpflanzungModel.equals(winkelpflanzungModel) == false) {
			txtZeitPflanzung_PflProH.setValue(PflanzungModel.getDefaultZeitaufwandPflanzung_PflProH(pflanzwerkzeug, pflanztechnik, baumart, schwierigkeit, winkelpflanzungModel));
			
			if (mainWindow.isInitializing == false) {
				txtZeitPflanzung_PflProH.flash();
			}
		}
		
		// update fields
		this.pflanzwerkzeug = pflanzwerkzeug;
		this.pflanztechnik = pflanztechnik;
		this.baumart = baumart;
		this.schwierigkeit = schwierigkeit;
		this.winkelpflanzungModel = winkelpflanzungModel;


		txtAnteilMaschinenlaufzeit_Prz.setEnabled(pflanztechnik == Pflanztechnik.MASCHINELL);
		txtKostensatzGeraet_proH.setEnabled(pflanztechnik == Pflanztechnik.MASCHINELL);
	}

	
	public double getZeitBeschaffung_h() {
		return (Double) txtZeitBeschaffung_h.getValue();
	}
	
	public double getKostenProPflanze() {
		return (Double) txtKostenProPflanze.getValue();
	}
	
	public double getZeitTransport_h() {
		return (Double) txtZeitTransport_h.getValue();
	}
	
	public double getKostenTransportmittel() {
		return (Double) txtKostenTransportmittel.getValue();
	}
	
	public double getZeitPflanzung_PflProH() {
		return (Double) txtZeitPflanzung_PflProH.getValue();
	}
	
	public int getAnteilMaschinenlaufzeit_Prz() {
		return (Integer) txtAnteilMaschinenlaufzeit_Prz.getValue();
	}
	
	public double getKostensatzGeraet_proH() {
		return (Double) txtKostensatzGeraet_proH.getValue();
	}
	
	public int getZeitUnterhalt_Prozent() {
		return (Integer) txtZeitUnterhalt_Prozent.getValue();
	}
	
	
	@Override
	public String getAsXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<section name=\"" + Messages.getString("Pflanzung.TitelZeitaufwaendeKosten") + "\"> "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		sb.append(getXmlEntry(Messages.getString("Pflanzung.ZeitaufwandBeschaffung"), getZeitBeschaffung_h())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.KostenProPflanze") + " [" + mainWindow.getCurrency() + "]", getKostenProPflanze()));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.ZeitaufwandTransport"), getZeitTransport_h())); //$NON-NLS-1$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.KostenTransportmittel") + " [" + mainWindow.getCurrency() + "]", getKostenTransportmittel()));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		sb.append(getXmlEntry(Messages.getString("Pflanzung.Pflanzleistung"), getZeitPflanzung_PflProH())); //$NON-NLS-1$
		if (pflanztechnik == Pflanztechnik.MASCHINELL) {
			sb.append(getXmlEntry(Messages.getString("Pflanzung.Maschinenlaufzeit"), getAnteilMaschinenlaufzeit_Prz())); //$NON-NLS-1$
			sb.append(getXmlEntry(Messages.getString("Pflanzung.KostensatzBohrgeraet") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]", getKostensatzGeraet_proH())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		sb.append(getXmlEntry(Messages.getString("Pflanzung.ZeitaufwandUnterhalt"), getZeitUnterhalt_Prozent())); //$NON-NLS-1$
		
		sb.append("</section> "); //$NON-NLS-1$
		
		return sb.toString();
	}


	@Override
	public void updateCurrency() {
		lblKostenProPflanze.setText(Messages.getString("Pflanzung.KostenProPflanze") + " [" + mainWindow.getCurrency() + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		lblKostenTransportmittel.setText(Messages.getString("Pflanzung.KostenTransportmittel") + " [" + mainWindow.getCurrency() + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		lblKostensatzGeraet_proH.setText(Messages.getString("Pflanzung.KostensatzBohrgeraet") + " [" + mainWindow.getCurrency() + Messages.getString("Common.ProStunde") + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
}
