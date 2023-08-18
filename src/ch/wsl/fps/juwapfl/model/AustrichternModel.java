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
public class AustrichternModel extends AbstractModel {

	public static final double DEFAULT_KOSTEN_SICHEL_PRO_HA = 0.6;
	
	private double flaeche_ha;
	private MaschinenGeraete maschinenGeraete;
	private int anzahlPflanzenProHa;
	private Verunkrautung verunkrautung;
	private Hangneigung hangneigung;
	private RueckegassenVorhanden rueckegassenVorhanden;
	private Schutzsystem schutzsystem;
	private Temperatur temperatur;
	
	private double kostenPersonal_proH;
	private double kostenMotorsaege_proLiter;
	private double kostenSichel_proHektar;
	private double faktorWegzeitenUndPausen;
	

	public void setFlaeche_ha(double flaeche_ha) {
		this.flaeche_ha = flaeche_ha;
	}

	public void setMaschinenGeraete(MaschinenGeraete maschinenGeraete) {
		this.maschinenGeraete = maschinenGeraete;
	}

	public void setAnzahlPflanzenProHa(int anzahlPflanzenProHa) {
		this.anzahlPflanzenProHa = anzahlPflanzenProHa;
	}

	public void setVerunkrautung(Verunkrautung verunkrautung) {
		this.verunkrautung = verunkrautung;
	}

	public void setHangneigung(Hangneigung hangneigung) {
		this.hangneigung = hangneigung;
	}

	public void setRueckegassenVorhanden(RueckegassenVorhanden rueckegassenVorhanden) {
		this.rueckegassenVorhanden = rueckegassenVorhanden;
	}

	public void setSchutzsystem(Schutzsystem schutzsystem) {
		this.schutzsystem = schutzsystem;
	}

	public void setTemperatur(Temperatur temperatur) {
		this.temperatur = temperatur;
	}


	public void setKostenPersonal_proH(double kostenPersonal_proH) {
		this.kostenPersonal_proH = kostenPersonal_proH;
	}

	public void setKostenMotorsaege_proLiter(double kostenMotorsaege_proLiter) {
		this.kostenMotorsaege_proLiter = kostenMotorsaege_proLiter;
	}
	
	public void setKostenSichel_proHektar(double kostenSichel_proHektar) {
		this.kostenSichel_proHektar = kostenSichel_proHektar;
	}
	
	public void setFaktorWegzeitenUndPausen(double faktorWegzeitenUndPausen) {
		this.faktorWegzeitenUndPausen = faktorWegzeitenUndPausen;
	}

	
	@Override
	public AustrichternErgebnis getErgebnis() {
		AustrichternErgebnis ergebnis = new AustrichternErgebnis();
		
		double psh15_basic = (0.004 * anzahlPflanzenProHa) + 4.0;

		double kf1 = verunkrautung.getKorrekturfaktor();
		double kf2 = hangneigung.getKorrekturfaktor();
		double kf3 = rueckegassenVorhanden.getKorrekturfaktor();
		double kf4 = schutzsystem.getKorrekturfaktor();
		double kf5 = temperatur.getKorrekturfaktor();
		
		double psh15_proHa = psh15_basic * kf1 * kf2 * kf3 * kf4 * kf5;
		
		double wpsh_proHa = psh15_proHa * F_INDIR * faktorWegzeitenUndPausen;
		double kostenPersonalProHa = wpsh_proHa * kostenPersonal_proH;

		double treibstoffverbrauchProHa = (0.4722 * psh15_proHa) + 0.9475;
		double kostenMotorsaegeProHa = treibstoffverbrauchProHa * kostenMotorsaege_proLiter;
		double geraetekostenSichelProHa = kostenSichel_proHektar;
		
		double kostenMaschinenGeraete_proHa = 0;
		if (maschinenGeraete == MaschinenGeraete.FREISCHNEIDER_MOTORSAEGE) {
			kostenMaschinenGeraete_proHa = kostenMotorsaegeProHa;
		}
		else if (maschinenGeraete == MaschinenGeraete.SICHEL) {
			kostenMaschinenGeraete_proHa = geraetekostenSichelProHa;
		}
		
		ergebnis.setFlaecheBestand_ha(flaeche_ha);
		ergebnis.setAnzahlPflanzenProHektar(anzahlPflanzenProHa);
		
		ergebnis.setZeitPersonal_WPSHProHa(wpsh_proHa);
		ergebnis.setKostenPersonal_proHa(kostenPersonalProHa);
		ergebnis.setKostenMaschinenGeraete_proHa(kostenMaschinenGeraete_proHa);
		ergebnis.setKostenGesamt_proHa(kostenPersonalProHa + kostenMaschinenGeraete_proHa);
		
		return ergebnis;
	}
	
	
	public static enum MaschinenGeraete {
		FREISCHNEIDER_MOTORSAEGE,
		SICHEL;
		
		@Override
		public String toString() {
			switch(this) {
			case FREISCHNEIDER_MOTORSAEGE:
				return Messages.getString("Austrichtern.FreischneiderMotorsaege"); //$NON-NLS-1$
				
			case SICHEL:
				return Messages.getString("Austrichtern.Sichel"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static MaschinenGeraete getDefault() {
			return FREISCHNEIDER_MOTORSAEGE;
		}
	}
	
	
	public static enum Verunkrautung {
		SCHWACH,
		MITTEL,
		STARK,
		EXTREM_STARK;
		
		@Override
		public String toString() {
			switch(this) {
			case SCHWACH:
				return Messages.getString("Austrichtern.Verunkrautung.schwach"); //$NON-NLS-1$
				
			case MITTEL:
				return Messages.getString("Austrichtern.Verunkrautung.mittel"); //$NON-NLS-1$
				
			case STARK:
				return Messages.getString("Austrichtern.Verunkrautung.stark"); //$NON-NLS-1$
				
			case EXTREM_STARK:
				return Messages.getString("Austrichtern.Verunkrautung.extremStark"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Verunkrautung getDefault() {
			return MITTEL;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case SCHWACH:
				return 0.80;

			case MITTEL:
				return 1.00;

			case STARK:
				return 1.40;

			case EXTREM_STARK:
				return 2.00;
				
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
	
	
	public static enum RueckegassenVorhanden {
		JA,
		NEIN;
		
		@Override
		public String toString() {
			switch(this) {
			case JA:
				return Messages.getString("Austrichtern.RueckegassenVorhanden.ja"); //$NON-NLS-1$
				
			case NEIN:
				return Messages.getString("Austrichtern.RueckegassenVorhanden.nein"); //$NON-NLS-1$
			
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
				return 1.1;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Schutzsystem {
		EINZELSCHUTZ,
		ZAUN,
		KEIN_WILDSCHUTZ;
		
		@Override
		public String toString() {
			switch(this) {
			case EINZELSCHUTZ:
				return Messages.getString("Austrichtern.Schutzsystem.Einzelschutz"); //$NON-NLS-1$
				
			case ZAUN:
				return Messages.getString("Austrichtern.Schutzsystem.Zaun"); //$NON-NLS-1$
				
			case KEIN_WILDSCHUTZ:
				return Messages.getString("Austrichtern.Schutzsystem.KeinWildschutz"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Schutzsystem getDefault() {
			return EINZELSCHUTZ;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case EINZELSCHUTZ:
				return 1.0;

			case ZAUN:
			case KEIN_WILDSCHUTZ:
				return 1.2;
				
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
				return Messages.getString("Austrichtern.Temperatur.angenehm"); //$NON-NLS-1$
				
			case WARM:
				return Messages.getString("Austrichtern.Temperatur.warm"); //$NON-NLS-1$
				
			case HEISS:
				return Messages.getString("Austrichtern.Temperatur.heiss"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Temperatur getDefault() {
			return WARM;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case ANGENEHM:
				return 0.9;

			case WARM:
				return 1.0;
				
			case HEISS:
				return 1.2;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}

}
