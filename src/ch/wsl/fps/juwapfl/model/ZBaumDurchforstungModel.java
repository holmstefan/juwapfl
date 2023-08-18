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
public class ZBaumDurchforstungModel extends AbstractModel {

	public static final double DEFAULT_KOSTEN_KRONENSCHNITT_PRO_BAUM_UNTEN = 0.1;
	public static final double DEFAULT_KOSTEN_KRONENSCHNITT_PRO_BAUM_OBEN = 0.25;
	
	private double flaeche_ha;
	private Entwicklungsstufe entwicklungsstufe;
	private int anzahlZBaeumeProHa;
	private double anzahlKonkurrentenProZBaum;
	private Hangneigung hangneigung;
	private Belaubung belaubung;
	private Verunkrautung verunkrautung;
	private RueckegassenVorhanden rueckegassenVorhanden;
	private Temperatur temperatur;
	private int anzahlZBaeumeMitKronenschnittProHektare;
	
	private Baendeln baendeln;
	private Zersaegen zersaegen;
	private AusfuehrungshoeheKronenschnitt ausfuehrungshoeheKronenschnitt;
	
	private double kostenPersonal_proH;
	private double kostenMotorsaege_proLiter;
	private double kostenKronenschnittUnten_proBaum;
	private double kostenKronenschnittOben_proBaum;
	private double faktorWegzeitenUndPausen;
	
	

	public void setFlaeche_ha(double flaeche_ha) {
		this.flaeche_ha = flaeche_ha;
	}

	public void setEntwicklungsstufe(Entwicklungsstufe entwicklungsstufe) {
		this.entwicklungsstufe = entwicklungsstufe;
	}

	public void setAnzahlZBaeumeProHa(int anzahlZBaeumeProHa) {
		this.anzahlZBaeumeProHa = anzahlZBaeumeProHa;
	}

	public void setAnzahlKonkurrentenProZBaum(double anzahlKonkurrentenProZBaum) {
		this.anzahlKonkurrentenProZBaum = anzahlKonkurrentenProZBaum;
	}

	public void setHangneigung(Hangneigung hangneigung) {
		this.hangneigung = hangneigung;
	}

	public void setBelaubung(Belaubung belaubung) {
		this.belaubung = belaubung;
	}

	public void setVerunkrautung(Verunkrautung verunkrautung) {
		this.verunkrautung = verunkrautung;
	}

	public void setRueckegassenVorhanden(RueckegassenVorhanden rueckegassenVorhanden) {
		this.rueckegassenVorhanden = rueckegassenVorhanden;
	}

	public void setTemperatur(Temperatur temperatur) {
		this.temperatur = temperatur;
	}

	public void setAnzahlZBaeumeMitKronenschnittProHektare(int anzahlZBaeumeMitKronenschnittProHektare) {
		this.anzahlZBaeumeMitKronenschnittProHektare = anzahlZBaeumeMitKronenschnittProHektare;
	}

	

	public void setBaendeln(Baendeln baendeln) {
		this.baendeln = baendeln;
	}

	public void setZersaegen(Zersaegen zersaegen) {
		this.zersaegen = zersaegen;
	}

	public void setAusfuehrungshoeheKronenschnitt(AusfuehrungshoeheKronenschnitt ausfuehrungshoeheKronenschnitt) {
		this.ausfuehrungshoeheKronenschnitt = ausfuehrungshoeheKronenschnitt;
	}

	

	public void setKostenPersonal_proH(double kostenPersonal_proH) {
		this.kostenPersonal_proH = kostenPersonal_proH;
	}

	public void setKostenMotorsaege_proLiter(double kostenMotorsaege_proLiter) {
		this.kostenMotorsaege_proLiter = kostenMotorsaege_proLiter;
	}
	
	public void setKostenKronenschnittUnten_proBaum(double kostenKronenschnittUnten_proBaum) {
		this.kostenKronenschnittUnten_proBaum = kostenKronenschnittUnten_proBaum;
	}

	public void setKostenKronenschnittOben_proBaum(double kostenKronenschnittOben_proBaum) {
		this.kostenKronenschnittOben_proBaum = kostenKronenschnittOben_proBaum;
	}
	
	public void setFaktorWegzeitenUndPausen(double faktorWegzeitenUndPausen) {
		this.faktorWegzeitenUndPausen = faktorWegzeitenUndPausen;
	}


	
	@Override
	public ZBaumDurchforstungErgebnis getErgebnis() {
		ZBaumDurchforstungErgebnis ergebnis = new ZBaumDurchforstungErgebnis();
		
		
		double psh15_basic = entwicklungsstufe.getPsh15(anzahlZBaeumeProHa);

		double kf1 = 0.9 + (anzahlKonkurrentenProZBaum / 20);
		double kf2 = baendeln.getKorrekturfaktor(anzahlZBaeumeProHa);
		double kf3 = hangneigung.getKorrekturfaktor();
		double kf4 = belaubung.getKorrekturfaktor(entwicklungsstufe);
		double kf5 = verunkrautung.getKorrekturfaktor(entwicklungsstufe);
		double kf6 = rueckegassenVorhanden.getKorrekturfaktor();
		double kf7 = zersaegen.getKorrekturfaktor();
		double kf8 = temperatur.getKorrekturfaktor();
		double kf9 = ausfuehrungshoeheKronenschnitt.getKorrekturfaktor(entwicklungsstufe, anzahlZBaeumeMitKronenschnittProHektare);
		
		
		double pph15_proHa = ((psh15_basic * kf1 * kf7 * kf8) + kf2 + kf9) * kf3 * kf4 * kf5 * kf6;
		
		double wpsh_proHa = pph15_proHa * F_INDIR * faktorWegzeitenUndPausen;
		
		double anzahlKonkurrentenProHa = anzahlZBaeumeProHa * anzahlKonkurrentenProZBaum;
		
		double treibstoffverbrauchProHa = entwicklungsstufe.getTreibstoffverbrauchProHa(anzahlKonkurrentenProHa, zersaegen);
		
		double geraetekostenKronenschnittProHa = ausfuehrungshoeheKronenschnitt.getGeraetekostenProHa(this);
		
		double kostenPersonalProHa = wpsh_proHa * kostenPersonal_proH;
		double kostenMotorsaegeProHa = treibstoffverbrauchProHa * kostenMotorsaege_proLiter;
		
		ergebnis.setAnzahlZBaeumeProHektar(anzahlZBaeumeProHa);
		ergebnis.setFlaecheBestand_ha(flaeche_ha);
		ergebnis.setZeitPersonal_WPSHProHa(wpsh_proHa);
		ergebnis.setKostenPersonal_proHa(kostenPersonalProHa);
		ergebnis.setKostenMotorsaege_proHa(kostenMotorsaegeProHa);
		ergebnis.setKostenMaterial_proHa(geraetekostenKronenschnittProHa);
		ergebnis.setKostenGesamt_proHa(kostenPersonalProHa + kostenMotorsaegeProHa + geraetekostenKronenschnittProHa);
		
		
		return ergebnis;
	}
	
	
	public static enum Entwicklungsstufe {
		DICKUNG,
		STANGENHOLZ_1;
		
		@Override
		public String toString() {
			switch(this) {
			case DICKUNG:
				return Messages.getString("ZBaumDurchforstung.Dickung"); //$NON-NLS-1$
				
			case STANGENHOLZ_1:
				return Messages.getString("ZBaumDurchforstung.Stangenholz1"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Entwicklungsstufe getDefault() {
			return STANGENHOLZ_1;
		}
		
		public double getPsh15(int anzahlZBaeume_proHa) {
			switch(this) {
			case DICKUNG:
				return (0.0224 * anzahlZBaeume_proHa) + 1.6226;
				
			case STANGENHOLZ_1:
				return (0.0230 * anzahlZBaeume_proHa) + 2.4317;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
		
		public double getTreibstoffverbrauchProHa(double anzahlKonkurrenten_proHa, Zersaegen zersaegen) {
			switch(this) {
			case DICKUNG:
				return ((0.005 * anzahlKonkurrenten_proHa) - 0.099) * zersaegen.getKorrekturfaktor();
				
			case STANGENHOLZ_1:
				return ((0.0076 * anzahlKonkurrenten_proHa) + 0.8371) * zersaegen.getKorrekturfaktor();
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Baendeln {
		NEIN,
		JA;
		
		@Override
		public String toString() {
			switch(this) {
			case NEIN:
				return Messages.getString("ZBaumDurchforstung.Baendeln.nein"); //$NON-NLS-1$
				
			case JA:
				return Messages.getString("ZBaumDurchforstung.Baendeln.ja"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Baendeln getDefault() {
			return NEIN;
		}
		
		public double getKorrekturfaktor(int anzahlZBaeume_proHa) {
			switch(this) {
			case NEIN:
				return 0;
				
			case JA:
				return ((0.5 * anzahlZBaeume_proHa) + 30) / 60;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Hangneigung {
		HN_0_BIS_24_PROZENT,
		HN_25_BIS_44_PROZENT,
		HN_45_BIS_64_PROZENT,
		HN_65_BIS_84_PROZENT,
		HN_85_BIS_200_PROZENT;
		
		@Override
		public String toString() {
			switch(this) {
			case HN_0_BIS_24_PROZENT:
				return "0 - 24%"; //$NON-NLS-1$
				
			case HN_25_BIS_44_PROZENT:
				return "25 - 44%"; //$NON-NLS-1$

			case HN_45_BIS_64_PROZENT:
				return "45 - 64%"; //$NON-NLS-1$
				
			case HN_65_BIS_84_PROZENT:
				return "65 - 84%"; //$NON-NLS-1$
				
			case HN_85_BIS_200_PROZENT:
				return ">= 85%"; //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Hangneigung getDefault() {
			return HN_0_BIS_24_PROZENT;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case HN_0_BIS_24_PROZENT:
				return 1.00;

			case HN_25_BIS_44_PROZENT:
				return 1.10;

			case HN_45_BIS_64_PROZENT:
				return 1.25;

			case HN_65_BIS_84_PROZENT:
				return 1.50;

			case HN_85_BIS_200_PROZENT:
				return 2.00;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Belaubung {
		UNBELAUBT,
		BELAUBT;
		
		@Override
		public String toString() {
			switch(this) {
			case UNBELAUBT:
				return Messages.getString("ZBaumDurchforstung.Belaubung.unbelaubt"); //$NON-NLS-1$
				
			case BELAUBT:
				return Messages.getString("ZBaumDurchforstung.Belaubung.belaubt"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Belaubung getDefault() {
			return UNBELAUBT;
		}
		
		public double getKorrekturfaktor(Entwicklungsstufe entwicklungsstufe) {
			switch(this) {
			case UNBELAUBT:
				return 1.0;
				
			case BELAUBT:
				if (entwicklungsstufe == Entwicklungsstufe.DICKUNG) {
					return 1.3;
				}
				else if (entwicklungsstufe == Entwicklungsstufe.STANGENHOLZ_1) {
					return 1.2;
				}
				else {
					throw new IllegalStateException(entwicklungsstufe.name());
				}
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Verunkrautung {
		KEINE,
		MITTEL,
		STARK,
		EXTREM_STARK;
		
		@Override
		public String toString() {
			switch(this) {
			case KEINE:
				return Messages.getString("ZBaumDurchforstung.Verunkrautung.keine"); //$NON-NLS-1$
				
			case MITTEL:
				return Messages.getString("ZBaumDurchforstung.Verunkrautung.mittel"); //$NON-NLS-1$

			case STARK:
				return Messages.getString("ZBaumDurchforstung.Verunkrautung.stark"); //$NON-NLS-1$
				
			case EXTREM_STARK:
				return Messages.getString("ZBaumDurchforstung.Verunkrautung.extremStark"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Verunkrautung getDefault() {
			return KEINE;
		}
		
		public double getKorrekturfaktor(Entwicklungsstufe entwicklungsstufe) {
			switch(this) {
			case KEINE:
				return 1.0;

			case MITTEL:
				return 1.1;

			case STARK:
				return 1.2;
				
			case EXTREM_STARK:
				if (entwicklungsstufe == Entwicklungsstufe.DICKUNG) {
					return 1.4;
				}
				else if (entwicklungsstufe == Entwicklungsstufe.STANGENHOLZ_1) {
					throw new IllegalStateException("Unzulässige Kombination"); //$NON-NLS-1$
				}
				else {
					throw new IllegalStateException(entwicklungsstufe.name());
				}
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum RueckegassenVorhanden {
		JA,
		NEIN;
		
		@Override
		public String toString() {
			switch(this) {
			case JA:
				return Messages.getString("ZBaumDurchforstung.RueckegassenVorhanden.ja"); //$NON-NLS-1$
				
			case NEIN:
				return Messages.getString("ZBaumDurchforstung.RueckegassenVorhanden.nein"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static RueckegassenVorhanden getDefault() {
			return JA;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case JA:
				return 1.0;

			case NEIN:
				return 1.3;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Zersaegen {
		MINIMAL,
		MITTEL,
		HOCH,
		EXTREM;
		
		@Override
		public String toString() {
			switch(this) {
			case MINIMAL:
				return Messages.getString("ZBaumDurchforstung.Zersaegen.minimal"); //$NON-NLS-1$
				
			case MITTEL:
				return Messages.getString("ZBaumDurchforstung.Zersaegen.mittel"); //$NON-NLS-1$
				
			case HOCH:
				return Messages.getString("ZBaumDurchforstung.Zersaegen.hoch"); //$NON-NLS-1$
				
			case EXTREM:
				return Messages.getString("ZBaumDurchforstung.Zersaegen.extrem"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Zersaegen getDefault() {
			return MINIMAL;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case MINIMAL:
				return 1.0;

			case MITTEL:
				return 1.2;
				
			case HOCH:
				return 1.5;

			case EXTREM:
				return 2.0;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Temperatur {
		ANGENEHM,
		WARM,
		HEISS;
		
		@Override
		public String toString() {
			switch(this) {
			case ANGENEHM:
				return Messages.getString("ZBaumDurchforstung.Temperatur.angenehm"); //$NON-NLS-1$
				
			case WARM:
				return Messages.getString("ZBaumDurchforstung.Temperatur.warm"); //$NON-NLS-1$
				
			case HEISS:
				return Messages.getString("ZBaumDurchforstung.Temperatur.heiss"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Temperatur getDefault() {
			return ANGENEHM;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case ANGENEHM:
				return 1.0;

			case WARM:
				return 1.1;
				
			case HEISS:
				return 1.3;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum AusfuehrungshoeheKronenschnitt {
		NICHT_VERFUEGBAR,
		BIS_2_EINHALB_METER,
		AB_2_EINHALB_METER_BIS_6_METER;
		
		@Override
		public String toString() {
			switch(this) {
			case NICHT_VERFUEGBAR:
				return "-"; //$NON-NLS-1$
				
			case BIS_2_EINHALB_METER:
				return Messages.getString("ZBaumDurchforstung.AusfuehrungshoeheKronenschnittTief"); //$NON-NLS-1$
				
			case AB_2_EINHALB_METER_BIS_6_METER:
				return Messages.getString("ZBaumDurchforstung.AusfuehrungshoeheKronenschnittHoch"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static AusfuehrungshoeheKronenschnitt getDefault() {
			return BIS_2_EINHALB_METER;
		}
		
		public double getKorrekturfaktor(Entwicklungsstufe entwicklungsstufe, int anzahlZBaeumeMitKronenschnitt) {
			if (entwicklungsstufe != Entwicklungsstufe.DICKUNG) {
				return 0;
			}
			
			switch(this) {
			case BIS_2_EINHALB_METER:
				return (0d + anzahlZBaeumeMitKronenschnitt) / 180;

			case AB_2_EINHALB_METER_BIS_6_METER:
				return (0d + anzahlZBaeumeMitKronenschnitt) / 15;
				
			case NICHT_VERFUEGBAR:
			default:
				throw new IllegalStateException(this.name());
			}
		}
		
		public double getGeraetekostenProHa(ZBaumDurchforstungModel model) {
			if (model.entwicklungsstufe != Entwicklungsstufe.DICKUNG) {
				return 0;
			}
			
			switch(this) {
			case NICHT_VERFUEGBAR:
				return 0;
				
			case BIS_2_EINHALB_METER:
				return model.anzahlZBaeumeMitKronenschnittProHektare * model.kostenKronenschnittUnten_proBaum;

			case AB_2_EINHALB_METER_BIS_6_METER:
				return model.anzahlZBaeumeMitKronenschnittProHektare * model.kostenKronenschnittOben_proBaum;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
		
		public static AusfuehrungshoeheKronenschnitt[] values(Entwicklungsstufe entwicklungsstufe) {
			if (entwicklungsstufe == Entwicklungsstufe.DICKUNG) {
				return new AusfuehrungshoeheKronenschnitt[]{BIS_2_EINHALB_METER, AB_2_EINHALB_METER_BIS_6_METER};
			}
			return new AusfuehrungshoeheKronenschnitt[]{NICHT_VERFUEGBAR};
		}
	}

}
