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
public class WertastungModel extends AbstractModel {

	private static final double KF_ETAPPE_1 = 0.9;
	private static final double KF_ETAPPE_2 = 1.1;
	
	private Baumart baumart;
	private int anzahlAstungsbaeumeProHektar;
	private double flaeche_ha;
	private Hangneigung hangneigung;

	private Etappe etappe;
	private Methode methode;
	private int hoehe1;
	private int hoehe2;
	private Astigkeit astigkeit;
	
	private int kostenPersonalProPerson;
	private double faktorWegzeitenUndPausen;
	
	private double korrekturfaktorMaterialkosten;
	
	
	
	public void setBaumart(Baumart baumart) {
		this.baumart = baumart;
	}


	public void setAnzahlAstungsbaeumeProHektar(int anzahlAstungsbaeumeProHektar) {
		this.anzahlAstungsbaeumeProHektar = anzahlAstungsbaeumeProHektar;
	}


	public void setFlaeche_ha(double flaeche_ha) {
		this.flaeche_ha = flaeche_ha;
	}


	public void setHangneigung(Hangneigung hangneigung) {
		this.hangneigung = hangneigung;
	}
	
	
	
	


	public void setEtappe(Etappe etappe) {
		this.etappe = etappe;
	}


	public void setMethode(Methode methode) {
		this.methode = methode;
	}


	public void setHoehe1(int hoehe1) {
		this.hoehe1 = hoehe1;
	}


	public void setHoehe2(int hoehe2) {
		this.hoehe2 = hoehe2;
	}


	public void setAstigkeit(Astigkeit astigkeit) {
		this.astigkeit = astigkeit;
	}


	public void setKostenPersonalProPerson(int kostenPersonalProPerson) {
		this.kostenPersonalProPerson = kostenPersonalProPerson;
	}


	public void setFaktorWegzeitenUndPausen(double faktorWegzeitenUndPausen) {
		this.faktorWegzeitenUndPausen = faktorWegzeitenUndPausen;
	}


	public void setKorrekturfaktorMaterialkosten(double korrekturfaktorMaterialkosten) {
		this.korrekturfaktorMaterialkosten = korrekturfaktorMaterialkosten;
	}


	@Override
	public WertastungErgebnis getErgebnis() {
		WertastungErgebnis ergebnis = new WertastungErgebnis();
		
		final double astungsstrecke = hoehe2 - hoehe1;
		
		double astungszeit_minProBaum = 0;
		if (etappe == Etappe.Etappe1) {
			double astungszeitEtappe1_minProBaum = 2 * astungsstrecke 
					* (baumart.getKorrekturFaktor() * astigkeit.getKorrekturFaktor() * KF_ETAPPE_1);
			astungszeit_minProBaum += astungszeitEtappe1_minProBaum;
		}
		if (etappe == Etappe.Etappe2) {
			double astungszeitEtappe2_minProBaum = 2 * astungsstrecke 
					* ((baumart.getKorrekturFaktor() * astigkeit.getKorrekturFaktor() * KF_ETAPPE_2)	+ ((MethodeEtappe2)methode).getKorrekturWertAstung());
			astungszeit_minProBaum += astungszeitEtappe2_minProBaum;
		}
		
		double gehzeit_sekundenProBaum = (2.8016 * Math.sqrt(1.05 * 10_000 / anzahlAstungsbaeumeProHektar) + 29.743) * hangneigung.getKorrekturFaktor();
		if (etappe == Etappe.Etappe2) {
			gehzeit_sekundenProBaum *= ((MethodeEtappe2)methode).getKorrekturFaktorGehzeit();
		}
		
		double zeitaufwand_minProBaum = (astungszeit_minProBaum + (gehzeit_sekundenProBaum / 60)) * F_INDIR * faktorWegzeitenUndPausen;
		
		
		double materialkostenProBaum = 0;
		if (etappe == Etappe.Etappe1) {
			materialkostenProBaum += methode.getMaterialkostenProBaum();
		}
		if (etappe == Etappe.Etappe2) {
			materialkostenProBaum += methode.getMaterialkostenProBaum();
		}
		materialkostenProBaum *= korrekturfaktorMaterialkosten;
		
		
		
		ergebnis.setAnzahlAstungsbaeumeProHektar(anzahlAstungsbaeumeProHektar);
		ergebnis.setFlaecheBestand_ha(flaeche_ha);
		
		ergebnis.setAstungszeit_minProBaum(astungszeit_minProBaum * F_INDIR * faktorWegzeitenUndPausen);
		ergebnis.setGehzeit_minProBaum((gehzeit_sekundenProBaum / 60d) * F_INDIR * faktorWegzeitenUndPausen);
		ergebnis.setZeitaufwandGesamt_minProBaum(zeitaufwand_minProBaum);

		ergebnis.setKostenAstungszeit_proBaum(ergebnis.getAstungszeit_minProBaum() / 60d * kostenPersonalProPerson);
		ergebnis.setKostenGehzeit_proBaum(ergebnis.getGehzeit_minProBaum() / 60d * kostenPersonalProPerson);
		ergebnis.setKostenMaterial_proBaum(materialkostenProBaum);
		ergebnis.setKostenGesamt_proBaum((ergebnis.getZeitaufwandGesamt_minProBaum() / 60d * kostenPersonalProPerson) + ergebnis.getKostenMaterial_proBaum());	
		
		return ergebnis;
	}
	
	
	public static enum Etappe {
		Etappe1, Etappe2;
		
		@Override
		public String toString() {
			switch(this) {
			case Etappe1:
				return Messages.getString("Wertastung.Etappe1"); //$NON-NLS-1$
				
			case Etappe2:
				return Messages.getString("Wertastung.Etappe2"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}
	}
	
	
	public interface Methode {
		public double getMaterialkostenProBaum();
	}
	

	public static enum MethodeEtappe1 implements Methode {
		ANSTELLLEITER(1.65),
		STANGENSAEGE(0.9),
		DISTELLEITER(2.1);
		
		private final double materialkostenProBaum;
		
		MethodeEtappe1(double materialkostenProBaum) {
			this.materialkostenProBaum = materialkostenProBaum;
		}
		
		@Override
		public double getMaterialkostenProBaum() {
			return materialkostenProBaum;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case ANSTELLLEITER:
				return Messages.getString("Wertastung.Anstellleiter"); //$NON-NLS-1$
				
			case STANGENSAEGE:
				return Messages.getString("Wertastung.Stangensaege"); //$NON-NLS-1$
				
			case DISTELLEITER:
				return Messages.getString("Wertastung.Distelleiter"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static MethodeEtappe1 getDefault() {
			return ANSTELLLEITER;
		}
	}
	
	
	public static enum MethodeEtappe2 implements Methode {
		DISTELLEITER(2.7),
		ASTSTUMMELMETHODE(1.8);
		
		private final double materialkostenProBaum;
		
		MethodeEtappe2(double materialkostenProBaum) {
			this.materialkostenProBaum = materialkostenProBaum;
		}
		
		@Override
		public double getMaterialkostenProBaum() {
			return materialkostenProBaum;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case DISTELLEITER:
				return Messages.getString("Wertastung.Distelleiter"); //$NON-NLS-1$
				
			case ASTSTUMMELMETHODE:
				return Messages.getString("Wertastung.AststummelMethode"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static MethodeEtappe2 getDefault() {
			return DISTELLEITER;
		}
		
		// Korrektur ist additiv, deshalb nicht getKorrekturFaktor()
		public double getKorrekturWertAstung() {
			switch(this) {
			case DISTELLEITER:
				return 0.1;

			case ASTSTUMMELMETHODE:
				return 0;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}


		public double getKorrekturFaktorGehzeit() {
			switch(this) {
			case DISTELLEITER:
				return 2;

			case ASTSTUMMELMETHODE:
				return 1;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Baumart {
		FICHTE, DOUGLASIE, LAECHE_FOEHRE_LAUBHOLZ;
		
		@Override
		public String toString() {
			switch(this) {
			case FICHTE:
				return Messages.getString("Wertastung.Fichte"); //$NON-NLS-1$
				
			case DOUGLASIE:
				return Messages.getString("Wertastung.Douglasie"); //$NON-NLS-1$
				
			case LAECHE_FOEHRE_LAUBHOLZ:
				return Messages.getString("Wertastung.LaercheFoehreLaubholz"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Baumart getDefault() {
			return DOUGLASIE;
		}
		
		public double getKorrekturFaktor() {
			switch(this) {
			case FICHTE:
				return 1;

			case DOUGLASIE:
				return 1.15;

			case LAECHE_FOEHRE_LAUBHOLZ:
				return 0.85;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Hangneigung {
		HN_0_BIS_24_PROZENT("0 - 24%"), //$NON-NLS-1$
		HN_25_BIS_44_PROZENT("25 - 44%"), //$NON-NLS-1$
		HN_45_BIS_64_PROZENT("45 - 64%"), //$NON-NLS-1$
		HN_65_BIS_84_PROZENT("65 - 84%"), //$NON-NLS-1$
		HN_85_BIS_200_PROZENT(">84%"); //$NON-NLS-1$
		
		private final String text;
		
		Hangneigung(String text) {
			this.text = text;
		}
		
		@Override
		public String toString() {
			return text;
		}

		public static Hangneigung getDefault() {
			return HN_0_BIS_24_PROZENT;
		}
		
		public double getKorrekturFaktor() {
			switch(this) {
			case HN_0_BIS_24_PROZENT:
				return 1;

			case HN_25_BIS_44_PROZENT:
				return 1.1;

			case HN_45_BIS_64_PROZENT:
				return 1.25;

			case HN_65_BIS_84_PROZENT:
				return 1.5;

			case HN_85_BIS_200_PROZENT:
				return 2;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Astigkeit {
		FEIN, MITTEL, GROB;
		
		@Override
		public String toString() {
			switch(this) {
			case FEIN:
				return Messages.getString("Wertastung.fein"); //$NON-NLS-1$
				
			case MITTEL:
				return Messages.getString("Wertastung.mittel"); //$NON-NLS-1$
				
			case GROB:
				return Messages.getString("Wertastung.grob"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Astigkeit getDefault() {
			return MITTEL;
		}
		
		public double getKorrekturFaktor() {
			switch(this) {
			case FEIN:
				return 0.7;

			case MITTEL:
				return 1;

			case GROB:
				return 1.35;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
		
		public static Astigkeit[] values(Etappe etappe) {
			if (etappe == Etappe.Etappe1) {
				return new Astigkeit[]{FEIN, MITTEL};
			}
			else if (etappe == Etappe.Etappe2) {
				return new Astigkeit[]{MITTEL, GROB};
			}
			else {
				return new Astigkeit[]{};
			}
		}
	}
}
