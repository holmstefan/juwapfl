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

	private static final double zeitaufwandPflanzungBuchenbuehlerEinfach_PflProH = 120;
	private static final double zeitaufwandPflanzungBuchenbuehlerMittel_PflProH = 100;
	private static final double zeitaufwandPflanzungBuchenbuehlerSchwierig_PflProH = 80;

	private static final double zeitaufwandPflanzungRhodenerKlemmSpaltpflanzungEinfach_PflProH = 81;
	private static final double zeitaufwandPflanzungRhodenerKlemmSpaltpflanzungMittel_PflProH = 65;
	private static final double zeitaufwandPflanzungRhodenerKlemmSpaltpflanzungSchwierig_PflProH = 49;

	private static final double zeitaufwandPflanzungRhodenerLochpflanzungEinfach_PflProH = 65;
	private static final double zeitaufwandPflanzungRhodenerLochpflanzungMittel_PflProH = 43;
	private static final double zeitaufwandPflanzungRhodenerLochpflanzungSchwierig_PflProH = 27;

	private static final double zeitaufwandPflanzungRhodenerTopfpflanzungEinfach_PflProH = 65;
	private static final double zeitaufwandPflanzungRhodenerTopfpflanzungMittel_PflProH = 52;
	private static final double zeitaufwandPflanzungRhodenerTopfpflanzungSchwierig_PflProH = 37;

	private static final double zeitaufwandPflanzungHohlspatenKlemmSpaltpflanzungEinfach_PflProH = 65;
	private static final double zeitaufwandPflanzungHohlspatenKlemmSpaltpflanzungMittel_PflProH = 54;
	private static final double zeitaufwandPflanzungHohlspatenKlemmSpaltpflanzungSchwierig_PflProH = 43;

	private static final double zeitaufwandPflanzungHohlspatenLochpflanzungEinfach_PflProH = 54;
	private static final double zeitaufwandPflanzungHohlspatenLochpflanzungMittel_PflProH = 43;
	private static final double zeitaufwandPflanzungHohlspatenLochpflanzungSchwierig_PflProH = 27;

	private static final double zeitaufwandPflanzungHohlspatenTopfpflanzungEinfach_PflProH = 60;
	private static final double zeitaufwandPflanzungHohlspatenTopfpflanzungMittel_PflProH = 45;
	private static final double zeitaufwandPflanzungHohlspatenTopfpflanzungSchwierig_PflProH = 30;

	private static final double zeitaufwandPflanzungWiedehopfhaueWinkelpflanzungNadelholz_PflProH = 100;
	private static final double zeitaufwandPflanzungWiedehopfhaueWinkelpflanzungLaubholz_PflProH = 120;
	private static final double zeitaufwandPflanzungWiedehopfhaueLochpflanzungEinfach_PflProH = 56;
	private static final double zeitaufwandPflanzungWiedehopfhaueLochpflanzungMittel_PflProH = 45;
	private static final double zeitaufwandPflanzungWiedehopfhaueLochpflanzungSchwierig_PflProH = 28;

	private static final double zeitaufwandPflanzungErdbohrerManuellEinfach_PflProH = 27;
	private static final double zeitaufwandPflanzungErdbohrerManuellMittel_PflProH = 14;
	private static final double zeitaufwandPflanzungErdbohrerManuellSchwierig_PflProH = 10;

	private static final double zeitaufwandPflanzungErdbohrerMaschinellEinfach_PflProH = 71;
	private static final double zeitaufwandPflanzungErdbohrerMaschinellMittel_PflProH = 40;
	private static final double zeitaufwandPflanzungErdbohrerMaschinellSchwierig_PflProH = 29;

	private static final double zeitaufwandPflanzungAnbaugeraetEinfach_PflProH = 143;
	private static final double zeitaufwandPflanzungAnbaugeraetMittel_PflProH = 77;
	private static final double zeitaufwandPflanzungAnbaugeraetSchwierig_PflProH = 56;
	

	private int anzahlPflanzen;
	
	@SuppressWarnings("unused") // wird aktuell nur im GUI zur Anzeige der korrekten default-Werte benötigt.
	private Pflanzwerkzeug pflanzwerkzeug;
	
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

	public void setPflanzwerkzeug(Pflanzwerkzeug pflanzwerkzeug) {
		this.pflanzwerkzeug = pflanzwerkzeug;
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

	
	public enum Pflanzwerkzeug {
		BUCHENBUEHLER_PFLANZHAUE,
		RHODENER_PFLANZHAUE,
		HOHLSPATEN,
		WIEDEHOPFHAUE,
		ERDBOHRER,
		ANBAUGERAET;
		
	@Override
		public String toString() {
			switch(this) {
			case BUCHENBUEHLER_PFLANZHAUE:
				return Messages.getString("Pflanzung.Pflanzwerkzeug.BuchenbuehlerPflanzhaue"); //$NON-NLS-1$
				
			case RHODENER_PFLANZHAUE:
				return Messages.getString("Pflanzung.Pflanzwerkzeug.RhodenerPflanzhaue"); //$NON-NLS-1$
				
			case HOHLSPATEN:
				return Messages.getString("Pflanzung.Pflanzwerkzeug.Hohlspaten"); //$NON-NLS-1$
				
			case WIEDEHOPFHAUE:
				return Messages.getString("Pflanzung.Pflanzwerkzeug.Wiedehopfhaue"); //$NON-NLS-1$
				
			case ERDBOHRER:
				return Messages.getString("Pflanzung.Pflanzwerkzeug.Erdbohrer"); //$NON-NLS-1$
				
			case ANBAUGERAET:
				return Messages.getString("Pflanzung.Pflanzwerkzeug.Anbaugeraet"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Pflanzwerkzeug getDefault() {
			return BUCHENBUEHLER_PFLANZHAUE;
		}
	}
	
	
	public enum Pflanztechnik {
		KLEMM_ODER_SPALTPFLANZUNG,
		WINKELPFLANZUNG,
		LOCHPFLANZUNG,
		TOPFPFLANZUNG,
		MANUELL,
		MASCHINELL;
		
		@Override
		public String toString() {
			switch(this) {
			case KLEMM_ODER_SPALTPFLANZUNG:
				return Messages.getString("Pflanzung.Pflanztechnik.KlemmSpaltpflanzung"); //$NON-NLS-1$

			case WINKELPFLANZUNG:
				return Messages.getString("Pflanzung.Pflanztechnik.Winkelpflanzung"); //$NON-NLS-1$
				
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
		
		public static Pflanztechnik[] getPflanztechniken(Pflanzwerkzeug pflanzwerkzeug) {
			switch(pflanzwerkzeug) {
			case BUCHENBUEHLER_PFLANZHAUE:
				return new Pflanztechnik[] {KLEMM_ODER_SPALTPFLANZUNG};
				
			case RHODENER_PFLANZHAUE:
			case HOHLSPATEN:
				return new Pflanztechnik[] {KLEMM_ODER_SPALTPFLANZUNG, LOCHPFLANZUNG, TOPFPFLANZUNG};
				
			case WIEDEHOPFHAUE:
				return new Pflanztechnik[] {WINKELPFLANZUNG, LOCHPFLANZUNG};
				
			case ERDBOHRER:
				return new Pflanztechnik[] {MANUELL, MASCHINELL};
				
			case ANBAUGERAET:
				return new Pflanztechnik[] {MASCHINELL};
				
			default:
				throw new RuntimeException(pflanzwerkzeug.name());
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
	



	public static double getDefaultKostenProPflanze(Pflanzwerkzeug pflanzwerkzeug, Pflanztechnik pflanztechnik) {
		switch(pflanzwerkzeug) {
		case BUCHENBUEHLER_PFLANZHAUE:
			return 2.40;
			
		case RHODENER_PFLANZHAUE:
		case HOHLSPATEN:
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
				throw new RuntimeException(pflanzwerkzeug.name() + " " + pflanztechnik.name()); //$NON-NLS-1$
			}
			
		case WIEDEHOPFHAUE:
			return 2.40;
			
		case ERDBOHRER:
		case ANBAUGERAET:
			return 3.50;
			
		default:
			throw new RuntimeException(pflanzwerkzeug.name());
		
		}
	}
	

	public static double getDefaultZeitaufwandPflanzung_PflProH(Pflanzwerkzeug pflanzwerkzeug, Pflanztechnik pflanztechnik, Baumart baumart, Schwierigkeitsgrad schwierigkeit, PflanzungWinkelpflanzungModel winkelpflanzungModel) {
		switch(pflanzwerkzeug) {
		case BUCHENBUEHLER_PFLANZHAUE:
			switch(schwierigkeit) {
			case EINFACH:
				return zeitaufwandPflanzungBuchenbuehlerEinfach_PflProH;
				
			case MITTEL:
				return zeitaufwandPflanzungBuchenbuehlerMittel_PflProH;
				
			case SCHWIERIG:
				return zeitaufwandPflanzungBuchenbuehlerSchwierig_PflProH;
				
			default:
				throw new RuntimeException(schwierigkeit.name());
			}
			
		case RHODENER_PFLANZHAUE:
			if (pflanztechnik == Pflanztechnik.KLEMM_ODER_SPALTPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungRhodenerKlemmSpaltpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungRhodenerKlemmSpaltpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungRhodenerKlemmSpaltpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.LOCHPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungRhodenerLochpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungRhodenerLochpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungRhodenerLochpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.TOPFPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungRhodenerTopfpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungRhodenerTopfpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungRhodenerTopfpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else {
				throw new RuntimeException(pflanzwerkzeug.name() + " " + pflanztechnik.name()); //$NON-NLS-1$
			}
			
		case HOHLSPATEN:
			if (pflanztechnik == Pflanztechnik.KLEMM_ODER_SPALTPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungHohlspatenKlemmSpaltpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungHohlspatenKlemmSpaltpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungHohlspatenKlemmSpaltpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.LOCHPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungHohlspatenLochpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungHohlspatenLochpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungHohlspatenLochpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.TOPFPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungHohlspatenTopfpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungHohlspatenTopfpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungHohlspatenTopfpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else {
				throw new RuntimeException(pflanzwerkzeug.name() + " " + pflanztechnik.name()); //$NON-NLS-1$
			}
			
		case WIEDEHOPFHAUE:
			if (pflanztechnik == Pflanztechnik.WINKELPFLANZUNG) {
				return getDefaultZeitaufwandWinkelpflanzung_PflProH(baumart, winkelpflanzungModel);
			}
			else if (pflanztechnik == Pflanztechnik.LOCHPFLANZUNG) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungWiedehopfhaueLochpflanzungEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungWiedehopfhaueLochpflanzungMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungWiedehopfhaueLochpflanzungSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else {
				throw new RuntimeException(pflanztechnik.name());
			}
			
		case ERDBOHRER:
			if (pflanztechnik == Pflanztechnik.MANUELL) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungErdbohrerManuellEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungErdbohrerManuellMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungErdbohrerManuellSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else if (pflanztechnik == Pflanztechnik.MASCHINELL) {
				switch(schwierigkeit) {
				case EINFACH:
					return zeitaufwandPflanzungErdbohrerMaschinellEinfach_PflProH;
					
				case MITTEL:
					return zeitaufwandPflanzungErdbohrerMaschinellMittel_PflProH;
					
				case SCHWIERIG:
					return zeitaufwandPflanzungErdbohrerMaschinellSchwierig_PflProH;
					
				default:
					throw new RuntimeException(schwierigkeit.name());
				}
			}
			else {
				throw new RuntimeException(pflanzwerkzeug.name() + " " + pflanztechnik.name()); //$NON-NLS-1$
			}
			
		case ANBAUGERAET:
			switch(schwierigkeit) {
			case EINFACH:
				return zeitaufwandPflanzungAnbaugeraetEinfach_PflProH;
				
			case MITTEL:
				return zeitaufwandPflanzungAnbaugeraetMittel_PflProH;
				
			case SCHWIERIG:
				return zeitaufwandPflanzungAnbaugeraetSchwierig_PflProH;
				
			default:
				throw new RuntimeException(schwierigkeit.name());
			}
			
		default:
			throw new RuntimeException(pflanzwerkzeug.name());
		
		}
	}
	

	private static double getDefaultZeitaufwandWinkelpflanzung_PflProH(Baumart baumart, PflanzungWinkelpflanzungModel winkelpflanzungModel) {
		final double grundgeschwindigkeit_PflProH;
		if (baumart == Baumart.NADELHOLZ) {
			grundgeschwindigkeit_PflProH = zeitaufwandPflanzungWiedehopfhaueWinkelpflanzungNadelholz_PflProH;
		}
		else if (baumart == Baumart.LAUBHOLZ) {
			grundgeschwindigkeit_PflProH = zeitaufwandPflanzungWiedehopfhaueWinkelpflanzungLaubholz_PflProH;
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


	public static int getDefaultAnteilMaschinenlaufzeit_Prz(Pflanzwerkzeug pflanzwerkzeug, Pflanztechnik pflanztechnik, Schwierigkeitsgrad schwierigkeit) {
		if (pflanztechnik == Pflanztechnik.MASCHINELL) {
			if (pflanzwerkzeug == Pflanzwerkzeug.ERDBOHRER) {
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
			else if (pflanzwerkzeug == Pflanzwerkzeug.ANBAUGERAET) {
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
				throw new RuntimeException(pflanzwerkzeug.name());
			}
		}
		
		return 0;
	}


	public static double getDefaultKostensatzGeraet_proH(Pflanzwerkzeug pflanzwerkzeug, Pflanztechnik pflanztechnik) {
		if (pflanzwerkzeug == Pflanzwerkzeug.ERDBOHRER && pflanztechnik == Pflanztechnik.MASCHINELL) {
			return 15;
		}
		if (pflanzwerkzeug == Pflanzwerkzeug.ANBAUGERAET) {
			return 35;
		}
		
		return 0;
	}

}
