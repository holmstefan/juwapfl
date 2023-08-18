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
package ch.wsl.fps.juwapfl.model;

import ch.wsl.fps.juwapfl.Messages;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungModel extends AbstractModel {

	private static final double zeitaufwandPflanzungBuchenbuehlerSchraegpflanzungEinfach_PflProH = 120;
	private static final double zeitaufwandPflanzungBuchenbuehlerSchraegpflanzungMittel_PflProH = 100;
	private static final double zeitaufwandPflanzungBuchenbuehlerSchraegpflanzungSchwierig_PflProH = 80;

	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenKlemmSpaltpflanzungEinfach_PflProH = 81;
	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenKlemmSpaltpflanzungMittel_PflProH = 65;
	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenKlemmSpaltpflanzungSchwierig_PflProH = 49;

	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenLochpflanzungEinfach_PflProH = 65;
	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenLochpflanzungMittel_PflProH = 43;
	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenLochpflanzungSchwierig_PflProH = 27;

	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenTopfpflanzungEinfach_PflProH = 65;
	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenTopfpflanzungMittel_PflProH = 52;
	private static final double zeitaufwandPflanzungRhodenerPflanzverfahrenTopfpflanzungSchwierig_PflProH = 37;

	private static final double zeitaufwandPflanzungHohlspatenverfahrenKlemmSpaltpflanzungEinfach_PflProH = 65;
	private static final double zeitaufwandPflanzungHohlspatenverfahrenKlemmSpaltpflanzungMittel_PflProH = 54;
	private static final double zeitaufwandPflanzungHohlspatenverfahrenKlemmSpaltpflanzungSchwierig_PflProH = 43;

	private static final double zeitaufwandPflanzungHohlspatenverfahrenLochpflanzungEinfach_PflProH = 54;
	private static final double zeitaufwandPflanzungHohlspatenverfahrenLochpflanzungMittel_PflProH = 43;
	private static final double zeitaufwandPflanzungHohlspatenverfahrenLochpflanzungSchwierig_PflProH = 27;

	private static final double zeitaufwandPflanzungHohlspatenverfahrenTopfpflanzungEinfach_PflProH = 60;
	private static final double zeitaufwandPflanzungHohlspatenverfahrenTopfpflanzungMittel_PflProH = 45;
	private static final double zeitaufwandPflanzungHohlspatenverfahrenTopfpflanzungSchwierig_PflProH = 30;

	private static final double zeitaufwandPflanzungWinkelpflanzungNadelholz_PflProH = 100;
	private static final double zeitaufwandPflanzungWinkelpflanzungLaubholz_PflProH = 120;

	private static final double zeitaufwandPflanzungLochpflanzungMitErdbohrerManuellEinfach_PflProH = 27;
	private static final double zeitaufwandPflanzungLochpflanzungMitErdbohrerManuellMittel_PflProH = 14;
	private static final double zeitaufwandPflanzungLochpflanzungMitErdbohrerManuellSchwierig_PflProH = 10;

	private static final double zeitaufwandPflanzungLochpflanzungMitErdbohrerMaschinellEinfach_PflProH = 71;
	private static final double zeitaufwandPflanzungLochpflanzungMitErdbohrerMaschinellMittel_PflProH = 40;
	private static final double zeitaufwandPflanzungLochpflanzungMitErdbohrerMaschinellSchwierig_PflProH = 29;

	private static final double zeitaufwandPflanzungLochpflanzungMitAnbaugeraetEinfach_PflProH = 143;
	private static final double zeitaufwandPflanzungLochpflanzungMitAnbaugeraetMittel_PflProH = 77;
	private static final double zeitaufwandPflanzungLochpflanzungMitAnbaugeraetSchwierig_PflProH = 56;
	

	private int anzahlPflanzen;
	
	@SuppressWarnings("unused") // wird aktuell nur im GUI zur Anzeige der korrekten default-Werte benötigt.
	private Pflanzverfahren pflanzverfahren;
	
	private Pflanztechnik pflanztechnik;
	
	@SuppressWarnings("unused") // wird aktuell nur im GUI zur Anzeige der korrekten default-Werte benötigt.
	private Baumart baumart;
	
	@SuppressWarnings("unused") // wird aktuell nur im GUI zur Anzeige der korrekten default-Werte benötigt.
	private Schwierigkeitsgrad schwierigkeitsgrad;
	
	@SuppressWarnings("unused") // wird aktuell nur im GUI zur Anzeige der korrekten default-Werte benötigt.
	private PflanzungWinkelpflanzungModel winkelpflanzungModel;
	
	private double zeitBeschaffung_h;
	private double kostenProPflanze;
	private double zeitTransport_h;
	private double kostenTransportmittel;
	private double zeitPflanzung_PflProH;
	private double anteilMaschinenlaufzeit_Prz;
	private double kostensatzGeraet_proH;
	private double zeitUnterhalt_Prozent;
	
	private int kostenPersonalProPerson_proH;
	private double faktorWegzeitenUndPausen;


	public int getAnzahlPflanzen() {
		return anzahlPflanzen;
	}

	public void setAnzahlPflanzen(int anzahlPflanzen) {
		this.anzahlPflanzen = anzahlPflanzen;
	}

	public void setPflanzverfahren(Pflanzverfahren pflanzverfahren) {
		this.pflanzverfahren = pflanzverfahren;
	}

	public void setPflanztechnik(Pflanztechnik pflanztechnik) {
		this.pflanztechnik = pflanztechnik;
	}

	public void setBaumart(Baumart baumart) {
		this.baumart = baumart;
	}

	public void setSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad) {
		this.schwierigkeitsgrad = schwierigkeitsgrad;
	}

	public void setWinkelpflanzungModel(PflanzungWinkelpflanzungModel winkelpflanzungModel) {
		this.winkelpflanzungModel = winkelpflanzungModel;
	}

	public void setZeitBeschaffung_h(double zeitBeschaffung_h) {
		this.zeitBeschaffung_h = zeitBeschaffung_h;
	}

	public void setKostenProPflanze(double kostenProPflanze) {
		this.kostenProPflanze = kostenProPflanze;
	}

	public void setZeitTransport_h(double zeitTransport_h) {
		this.zeitTransport_h = zeitTransport_h;
	}

	public void setKostenTransportmittel(double kostenTransportmittel) {
		this.kostenTransportmittel = kostenTransportmittel;
	}

	public void setZeitPflanzung_PflProH(double zeitPflanzung_PflProH) {
		this.zeitPflanzung_PflProH = zeitPflanzung_PflProH;
	}

	public void setAnteilMaschinenlaufzeit_Prz(double anteilMaschinenlaufzeit_Prz) {
		this.anteilMaschinenlaufzeit_Prz = anteilMaschinenlaufzeit_Prz;
	}

	public void setKostensatzGeraet_proH(double kostensatzGeraet_proH) {
		this.kostensatzGeraet_proH = kostensatzGeraet_proH;
	}

	public void setZeitUnterhalt_Prozent(double zeitUnterhalt_Prozent) {
		this.zeitUnterhalt_Prozent = zeitUnterhalt_Prozent;
	}

	public void setKostenPersonalProPerson_proH(int kostenPersonalProPerson_proH) {
		this.kostenPersonalProPerson_proH = kostenPersonalProPerson_proH;
	}

	public void setFaktorWegzeitenUndPausen(double faktorWegzeitenUndPausen) {
		this.faktorWegzeitenUndPausen = faktorWegzeitenUndPausen;
	}


	@Override
	public PflanzungErgebnis getErgebnis() {
		PflanzungErgebnis ergebnis = new PflanzungErgebnis();
		
		int anzahlPersonen = pflanztechnik == Pflanztechnik.MASCHINELL ? 2 : 1;
		
		double zeitUnterhalt_Faktor = zeitUnterhalt_Prozent / 100d;
		
		double zeitPflanzung_h = anzahlPflanzen / zeitPflanzung_PflProH;
		
		ergebnis.setZeitPersonalBeschaffung_min(zeitBeschaffung_h * 60);
		ergebnis.setZeitPersonalTransport_min(zeitTransport_h * 60);
		ergebnis.setZeitPersonalPflanzung_min(zeitPflanzung_h * 60 * faktorWegzeitenUndPausen * F_INDIR);
		ergebnis.setZeitPersonalUnterhalt_min(zeitPflanzung_h * 60 * faktorWegzeitenUndPausen * F_INDIR * zeitUnterhalt_Faktor);

		ergebnis.setZeitGeraetePflanzung_min(zeitPflanzung_h * 60 * (anteilMaschinenlaufzeit_Prz/100d));
		
		
		ergebnis.setKostenPersonalBeschaffung(zeitBeschaffung_h * kostenPersonalProPerson_proH);
		ergebnis.setKostenPersonalTransport(zeitTransport_h * kostenPersonalProPerson_proH);
		ergebnis.setKostenPersonalPflanzung(zeitPflanzung_h * faktorWegzeitenUndPausen * F_INDIR * kostenPersonalProPerson_proH * anzahlPersonen);
		ergebnis.setKostenPersonalUnterhalt(zeitPflanzung_h * faktorWegzeitenUndPausen * F_INDIR * zeitUnterhalt_Faktor * kostenPersonalProPerson_proH);

		ergebnis.setKostenTransportmittelTransport(kostenTransportmittel);
		
		ergebnis.setKostenPflanzenBeschaffung(anzahlPflanzen * kostenProPflanze);

		ergebnis.setKostenGeraetePflanzung(zeitPflanzung_h * (anteilMaschinenlaufzeit_Prz/100d) * kostensatzGeraet_proH);
		
		return ergebnis;
	}

	
	public enum Pflanzverfahren {
		BUCHENBUEHLER_SCHRAEGPFLANZUNG,
		RHODENER_PFLANZVERFAHREN,
		HOHLSPATEN_VERFAHREN,
		WINKELPFLANZUNG,
		LOCHPFLANZUNG_MIT_ERDBOHRER,
		LOCHPFLANZUNG_MIT_ANBAUGERAET;
		
	@Override
		public String toString() {
			switch(this) {
			case BUCHENBUEHLER_SCHRAEGPFLANZUNG:
				return Messages.getString("Pflanzung.Pflanzverfahren.BuchenbuehlerSchraegpflanzung"); //$NON-NLS-1$
				
			case RHODENER_PFLANZVERFAHREN:
				return Messages.getString("Pflanzung.Pflanzverfahren.RhodenerPflanzverfahren"); //$NON-NLS-1$
				
			case HOHLSPATEN_VERFAHREN:
				return Messages.getString("Pflanzung.Pflanzverfahren.HohlspatenVerfahren"); //$NON-NLS-1$
				
			case WINKELPFLANZUNG:
				return Messages.getString("Pflanzung.Pflanzverfahren.Winkelpflanzung"); //$NON-NLS-1$
				
			case LOCHPFLANZUNG_MIT_ERDBOHRER:
				return Messages.getString("Pflanzung.Pflanzverfahren.LochpflanzungErdbohrer"); //$NON-NLS-1$
				
			case LOCHPFLANZUNG_MIT_ANBAUGERAET:
				return Messages.getString("Pflanzung.Pflanzverfahren.LochpflanzungAnbaugeraet"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Pflanzverfahren getDefault() {
			return BUCHENBUEHLER_SCHRAEGPFLANZUNG;
		}
	}
	
	
	public enum Pflanztechnik {
		KLEMM_ODER_SPALTPFLANZUNG,
		LOCHPFLANZUNG,
		TOPFPFLANZUNG,
		MANUELL,
		MASCHINELL;
		
		@Override
		public String toString() {
			switch(this) {
			case KLEMM_ODER_SPALTPFLANZUNG:
				return Messages.getString("Pflanzung.Pflanztechnik.KlemmSpaltpflanzung"); //$NON-NLS-1$
				
			case LOCHPFLANZUNG:
				return Messages.getString("Pflanzung.Pflanztechnik.Lochpflanzung"); //$NON-NLS-1$
				
			case TOPFPFLANZUNG:
				return Messages.getString("Pflanzung.Pflanztechnik.Topfpflanzung"); //$NON-NLS-1$
				
			case MANUELL:
				return Messages.getString("Pflanzung.Pflanztechnik.manuell"); //$NON-NLS-1$
				
			case MASCHINELL:
				return Messages.getString("Pflanzung.Pflanztechnik.maschinell"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Pflanztechnik getDefault() {
			return KLEMM_ODER_SPALTPFLANZUNG;
		}
		
		public static Pflanztechnik[] getPflanztechniken(Pflanzverfahren pflanzverfahren) {
			switch(pflanzverfahren) {
			case BUCHENBUEHLER_SCHRAEGPFLANZUNG:
				return new Pflanztechnik[] {KLEMM_ODER_SPALTPFLANZUNG};
				
			case RHODENER_PFLANZVERFAHREN:
			case HOHLSPATEN_VERFAHREN:
				return new Pflanztechnik[] {KLEMM_ODER_SPALTPFLANZUNG, LOCHPFLANZUNG, TOPFPFLANZUNG};
				
			case WINKELPFLANZUNG:
				return new Pflanztechnik[] {KLEMM_ODER_SPALTPFLANZUNG};
				
			case LOCHPFLANZUNG_MIT_ERDBOHRER:
				return new Pflanztechnik[] {MANUELL, MASCHINELL};
				
			case LOCHPFLANZUNG_MIT_ANBAUGERAET:
				return new Pflanztechnik[] {MASCHINELL};
				
			default:
				throw new RuntimeException(pflanzverfahren.name());
			}
		}
	}

	
	public enum Baumart {
		NADELHOLZ,
		LAUBHOLZ;
		
		@Override
		public String toString() {
			switch(this) {
			case NADELHOLZ:
				return Messages.getString("Pflanzung.Baumart.Nadelholz"); //$NON-NLS-1$
				
			case LAUBHOLZ:
				return Messages.getString("Pflanzung.Baumart.Laubholz"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Baumart getDefault() {
			return NADELHOLZ;
		}
	}

	
	public enum Schwierigkeitsgrad {
		EINFACH,
		MITTEL,
		SCHWIERIG;
		
		@Override
		public String toString() {
			switch(this) {
			case EINFACH:
				return Messages.getString("Pflanzung.Schwierigkeitsgrad.einfach"); //$NON-NLS-1$
				
			case MITTEL:
				return Messages.getString("Pflanzung.Schwierigkeitsgrad.mittel"); //$NON-NLS-1$
				
			case SCHWIERIG:
				return Messages.getString("Pflanzung.Schwierigkeitsgrad.schwierig"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Schwierigkeitsgrad getDefault() {
			return EINFACH;
		}
	}
	



	public static double getDefaultKostenProPflanze(Pflanzverfahren pflanzverfahren, Pflanztechnik pflanztechnik) {
		switch(pflanzverfahren) {
		case BUCHENBUEHLER_SCHRAEGPFLANZUNG:
			return 2.40;
			
		case RHODENER_PFLANZVERFAHREN:
		case HOHLSPATEN_VERFAHREN:
			if (pflanztechnik == Pflanztechnik.KLEMM_ODER_SPALTPFLANZUNG) {
				return 2.40;
			}
			else if (pflanztechnik == Pflanztechnik.LOCHPFLANZUNG) {
				return 3.50;
			}
			else if (pflanztechnik == Pflanztechnik.TOPFPFLANZUNG) {
				return 3.50;
			}
			else {
				throw new RuntimeException(pflanzverfahren.name() + " " + pflanztechnik.name()); //$NON-NLS-1$
			}
			
		case WINKELPFLANZUNG:
			return 2.40;
			
		case LOCHPFLANZUNG_MIT_ERDBOHRER:
		case LOCHPFLANZUNG_MIT_ANBAUGERAET:
			return 3.50;
			
		default:
			throw new RuntimeException(pflanzverfahren.name());
		
		}
	}
	

	public static double getDefaultZeitaufwandPflanzung_PflProH(Pflanzverfahren pflanzverfahren, Pflanztechnik pflanztechnik, Baumart baumart, Schwierigkeitsgrad schwierigkeit, PflanzungWinkelpflanzungModel winkelpflanzungModel) {
		switch(pflanzverfahren) {
		case BUCHENBUEHLER_SCHRAEGPFLANZUNG:
			switch(schwierigkeit) {
			case EINFACH:
				return zeitaufwandPflanzungBuchenbuehlerSchraegpflanzungEinfach_PflProH;
				
			case MITTEL:
				return zeitaufwandPflanzungBuchenbuehlerSchraegpflanzungMittel_PflProH;
				
			case SCHWIERIG:
				return zeitaufwandPflanzungBuchenbuehlerSchraegpflanzungSchwierig_PflProH;
				
			default:
				throw new RuntimeException(schwierigkeit.name());
			}
			
		case RHODENER_PFLANZVERFAHREN:
			if (pflanztechnik == Pflanztechnik.KLEMM_ODER_SPALTPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenKlemmSpaltpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenKlemmSpaltpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenKlemmSpaltpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.LOCHPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenLochpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenLochpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenLochpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.TOPFPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenTopfpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenTopfpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungRhodenerPflanzverfahrenTopfpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else {
				throw new RuntimeException(pflanzverfahren.name() + " " + pflanztechnik.name()); //$NON-NLS-1$
			}
			
		case HOHLSPATEN_VERFAHREN:
			if (pflanztechnik == Pflanztechnik.KLEMM_ODER_SPALTPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungHohlspatenverfahrenKlemmSpaltpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungHohlspatenverfahrenKlemmSpaltpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungHohlspatenverfahrenKlemmSpaltpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.LOCHPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungHohlspatenverfahrenLochpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungHohlspatenverfahrenLochpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungHohlspatenverfahrenLochpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.TOPFPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungHohlspatenverfahrenTopfpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungHohlspatenverfahrenTopfpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungHohlspatenverfahrenTopfpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else {
				throw new RuntimeException(pflanzverfahren.name() + " " + pflanztechnik.name()); //$NON-NLS-1$
			}
			
		case WINKELPFLANZUNG:
			return getDefaultZeitaufwandWinkelpflanzung_PflProH(baumart, winkelpflanzungModel);
			
		case LOCHPFLANZUNG_MIT_ERDBOHRER:
			if (pflanztechnik == Pflanztechnik.MANUELL) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungLochpflanzungMitErdbohrerManuellEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungLochpflanzungMitErdbohrerManuellMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungLochpflanzungMitErdbohrerManuellSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.MASCHINELL) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungLochpflanzungMitErdbohrerMaschinellEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungLochpflanzungMitErdbohrerMaschinellMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungLochpflanzungMitErdbohrerMaschinellSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else {
				throw new RuntimeException(pflanzverfahren.name() + " " + pflanztechnik.name()); //$NON-NLS-1$
			}
			
		case LOCHPFLANZUNG_MIT_ANBAUGERAET:
			switch(schwierigkeit) {
			case EINFACH:
				return zeitaufwandPflanzungLochpflanzungMitAnbaugeraetEinfach_PflProH;
				
			case MITTEL:
				return zeitaufwandPflanzungLochpflanzungMitAnbaugeraetMittel_PflProH;
				
			case SCHWIERIG:
				return zeitaufwandPflanzungLochpflanzungMitAnbaugeraetSchwierig_PflProH;
				
			default:
				throw new RuntimeException(schwierigkeit.name());
			}
			
		default:
			throw new RuntimeException(pflanzverfahren.name());
		
		}
	}
	

	private static double getDefaultZeitaufwandWinkelpflanzung_PflProH(Baumart baumart, PflanzungWinkelpflanzungModel winkelpflanzungModel) {
		final double grundgeschwindigkeit_PflProH;
		if (baumart == Baumart.NADELHOLZ) {
			grundgeschwindigkeit_PflProH = zeitaufwandPflanzungWinkelpflanzungNadelholz_PflProH;
		}
		else if (baumart == Baumart.LAUBHOLZ) {
			grundgeschwindigkeit_PflProH = zeitaufwandPflanzungWinkelpflanzungLaubholz_PflProH;
		}
		else {
			throw new RuntimeException("Baumart: " + baumart); //$NON-NLS-1$
		}
		
		double grundzeit_hPro100Pflanzen = 100d / grundgeschwindigkeit_PflProH;

		// Zuschläge addieren
		grundzeit_hPro100Pflanzen += winkelpflanzungModel.getBodenvegetation().getDefaultZuschlag_hPro100Pflanzen(baumart);
		grundzeit_hPro100Pflanzen += winkelpflanzungModel.getSchlagabraum().getDefaultZuschlag_hPro100Pflanzen(baumart);
		grundzeit_hPro100Pflanzen += winkelpflanzungModel.getHangneigung().getDefaultZuschlag_hPro100Pflanzen(baumart);
		grundzeit_hPro100Pflanzen += winkelpflanzungModel.getPflanzenhoehe().getDefaultZuschlag_hPro100Pflanzen(baumart);
		grundzeit_hPro100Pflanzen += winkelpflanzungModel.getTransportdistanz().getDefaultZuschlag_hPro100Pflanzen(baumart);
		
		// Zeit in Geschwindigkeit zurück-umwandeln
		double geschwindigkeit_PflProH = 100d / grundzeit_hPro100Pflanzen;
		
		// Runden auf 1 Nachkommastelle
		geschwindigkeit_PflProH = Math.round(geschwindigkeit_PflProH * 10d) /10d;
		
		return geschwindigkeit_PflProH;
	}


	public static int getDefaultAnteilMaschinenlaufzeit_Prz(Pflanzverfahren pflanzverfahren, Pflanztechnik pflanztechnik, Schwierigkeitsgrad schwierigkeit) {
		if (pflanztechnik == Pflanztechnik.MASCHINELL) {
			if (pflanzverfahren == Pflanzverfahren.LOCHPFLANZUNG_MIT_ERDBOHRER) {
				switch(schwierigkeit) {
				case EINFACH:
					return 40;
					
				case MITTEL:
					return 25;
					
				case SCHWIERIG:
					return 20;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanzverfahren == Pflanzverfahren.LOCHPFLANZUNG_MIT_ANBAUGERAET) {
				switch(schwierigkeit) {
				case EINFACH:
					return 70;
					
				case MITTEL:
					return 50;
					
				case SCHWIERIG:
					return 40;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else {
				throw new RuntimeException(pflanzverfahren.name());
			}
		}
		
		return 0;
	}


	public static double getDefaultKostensatzGeraet_proH(Pflanzverfahren pflanzverfahren, Pflanztechnik pflanztechnik) {
		if (pflanzverfahren == Pflanzverfahren.LOCHPFLANZUNG_MIT_ERDBOHRER && pflanztechnik == Pflanztechnik.MASCHINELL) {
			return 15;
		}
		if (pflanzverfahren == Pflanzverfahren.LOCHPFLANZUNG_MIT_ANBAUGERAET) {
			return 35;
		}
		
		return 0;
	}

}
