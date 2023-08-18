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
public class SchneegleitenModel extends AbstractModel {
	
	private Massnahme massnahme;
	
	private int anzahlBauten;
	private Gelaendeverhaeltnisse gelaendeverhaeltnisse;
	
	private double erstellenPersonalzeit_minProEinheit;
	private double erstellenMaterialkosten_ChfProEinheit;
	private double erstellenMaschinenkosten_ChfProEinheit;
	private double erstellenTransportkosten_ChfProEinheit;

	private double unterhaltPersonalzeit_minProEinheit;
	private double unterhaltFahrzeugkosten_ChfProEinheit;
	private double unterhaltMaterialkosten_ChfProEinheit;

	private double abbauPersonalzeit_minProEinheit;
	private double abbauFahrzeugkosten_ChfProEinheit;
	private double abbauEntsorgungsgebuehren_ChfProEinheit;
	
	private int kostenPersonalProPerson;
	private double faktorWegzeitenUndPausen;
	

	public void setMassnahme(Massnahme massnahme) {
		this.massnahme = massnahme;
	}

	public void setAnzahlBauten(int anzahlBauten) {
		this.anzahlBauten = anzahlBauten;
	}

	public void setGelaendeverhaeltnisse(Gelaendeverhaeltnisse gelaendeverhaeltnisse) {
		this.gelaendeverhaeltnisse = gelaendeverhaeltnisse;
	}


	public void setErstellenPersonalzeit_minProEinheit(double personalzeit_minProEinheit) {
		this.erstellenPersonalzeit_minProEinheit = personalzeit_minProEinheit;
	}

	public void setErstellenMaterialkosten_ChfProEinheit(double materialkosten_ChfProEinheit) {
		this.erstellenMaterialkosten_ChfProEinheit = materialkosten_ChfProEinheit;
	}

	public void setErstellenMaschinenkosten_ChfProEinheit(double maschinenkosten_ChfProEinheit) {
		this.erstellenMaschinenkosten_ChfProEinheit = maschinenkosten_ChfProEinheit;
	}

	public void setErstellenTransportkosten_ChfProEinheit(double transportkosten_ChfProEinheit) {
		this.erstellenTransportkosten_ChfProEinheit = transportkosten_ChfProEinheit;
	}


	public void setUnterhaltPersonalzeit_minProEinheit(double unterhaltPersonalzeit_minProEinheit) {
		this.unterhaltPersonalzeit_minProEinheit = unterhaltPersonalzeit_minProEinheit;
	}

	public void setUnterhaltFahrzeugkosten_ChfProEinheit(double unterhaltFahrzeugkosten_ChfProEinheit) {
		this.unterhaltFahrzeugkosten_ChfProEinheit = unterhaltFahrzeugkosten_ChfProEinheit;
	}

	public void setUnterhaltMaterialkosten_ChfProEinheit(double unterhaltMaterialkosten_ChfProEinheit) {
		this.unterhaltMaterialkosten_ChfProEinheit = unterhaltMaterialkosten_ChfProEinheit;
	}

	
	public void setAbbauPersonalzeit_minProEinheit(double abbauPersonalzeit_minProEinheit) {
		this.abbauPersonalzeit_minProEinheit = abbauPersonalzeit_minProEinheit;
	}

	public void setAbbauFahrzeugkosten_ChfProEinheit(double abbauFahrzeugkosten_ChfProEinheit) {
		this.abbauFahrzeugkosten_ChfProEinheit = abbauFahrzeugkosten_ChfProEinheit;
	}

	public void setAbbauEntsorgungsgebuehren_ChfProEinheit(double abbauEntsorgungsgebuehren_ChfProEinheit) {
		this.abbauEntsorgungsgebuehren_ChfProEinheit = abbauEntsorgungsgebuehren_ChfProEinheit;
	}

	
	public void setKostenPersonalProPerson(int kostenPersonalProPerson) {
		this.kostenPersonalProPerson = kostenPersonalProPerson;
	}

	public void setFaktorWegzeitenUndPausen(double faktorWegzeitenUndPausen) {
		this.faktorWegzeitenUndPausen = faktorWegzeitenUndPausen;
	}
	

	@Override
	public SchneegleitenErgebnis getErgebnis() {
		// Personalzeiten insgesamt
		double zeitaufwandAufbau_min = erstellenPersonalzeit_minProEinheit * anzahlBauten * F_INDIR * faktorWegzeitenUndPausen;
		double zeitaufwandUnterhalt_min = unterhaltPersonalzeit_minProEinheit * anzahlBauten * F_INDIR * faktorWegzeitenUndPausen;
		double zeitaufwandAbbau_min = abbauPersonalzeit_minProEinheit * anzahlBauten * F_INDIR * faktorWegzeitenUndPausen;
			
		// Korrekturfaktor Geländeverhältnisse
		zeitaufwandAufbau_min *= gelaendeverhaeltnisse.getFaktor(massnahme);
		zeitaufwandUnterhalt_min *= gelaendeverhaeltnisse.getFaktor(massnahme);
		zeitaufwandAbbau_min *= gelaendeverhaeltnisse.getFaktor(massnahme);
 
		// Ergebnis-Objekt erstellen
		SchneegleitenErgebnis ergebnis = new SchneegleitenErgebnis();
		ergebnis.setZeitPersonalAufbau_min(zeitaufwandAufbau_min);
		ergebnis.setZeitPersonalUnterhalt_min(zeitaufwandUnterhalt_min);
		ergebnis.setZeitPersonalAbbau_min(zeitaufwandAbbau_min);

		ergebnis.setKostenPersonalAufbau(zeitaufwandAufbau_min * kostenPersonalProPerson / 60);
		ergebnis.setKostenPersonalUnterhalt(zeitaufwandUnterhalt_min * kostenPersonalProPerson / 60);
		ergebnis.setKostenPersonalAbbau(zeitaufwandAbbau_min * kostenPersonalProPerson / 60);

		ergebnis.setKostenFahrtenAufbau(erstellenTransportkosten_ChfProEinheit * anzahlBauten);
		ergebnis.setKostenFahrtenUnterhalt(unterhaltFahrzeugkosten_ChfProEinheit * anzahlBauten);
		ergebnis.setKostenFahrtenAbbau(abbauFahrzeugkosten_ChfProEinheit * anzahlBauten);

		ergebnis.setKostenMaterialAufbau((erstellenMaterialkosten_ChfProEinheit + erstellenMaschinenkosten_ChfProEinheit) * anzahlBauten);
		ergebnis.setKostenMaterialUnterhalt(unterhaltMaterialkosten_ChfProEinheit * anzahlBauten);
		ergebnis.setKostenMaterialAbbau(abbauEntsorgungsgebuehren_ChfProEinheit * anzahlBauten);
		
		return ergebnis;
	}

	
	public enum Massnahme {
		PFAEHLE,
		BERMEN,
		SCHWELLEN,
		DREIBEINBOECKE;
		
		@Override
		public String toString() {
			switch(this) {
			case PFAEHLE:
				return Messages.getString("Schneegleiten.Pfaehle"); //$NON-NLS-1$
				
			case BERMEN:
				return Messages.getString("Schneegleiten.BremenHandarbeit"); //$NON-NLS-1$
				
			case SCHWELLEN:
				return Messages.getString("Schneegleiten.Schwellen"); //$NON-NLS-1$
				
			case DREIBEINBOECKE:
				return Messages.getString("Schneegleiten.Dreibeinboecke"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Massnahme getDefault() {
			return DREIBEINBOECKE;
		}
	}

	
	public enum Gelaendeverhaeltnisse {
		NORMAL(1.0, 1.0),
		SCHWIERIG(1.2, 1.20),
		SEHR_SCHWIERIG(1.5, 1.40);

		private final double faktorStandard;
		private final double faktorDreibeinboecke;
		
		Gelaendeverhaeltnisse(double faktorStandard, double faktorDreibeinboecke) {
			this.faktorStandard = faktorStandard;
			this.faktorDreibeinboecke = faktorDreibeinboecke;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case NORMAL:
				return Messages.getString("Schneegleiten.normal"); //$NON-NLS-1$
				
			case SCHWIERIG:
				return Messages.getString("Schneegleiten.schwierig"); //$NON-NLS-1$
				
			case SEHR_SCHWIERIG:
				return Messages.getString("Schneegleiten.sehrSchwierig"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}
		
		public double getFaktor(Massnahme massnahme) {
			switch(massnahme) {
			case PFAEHLE:
			case BERMEN:
			case SCHWELLEN:
				return faktorStandard;

			case DREIBEINBOECKE:
				return faktorDreibeinboecke;

			default:
				throw new IllegalStateException("Unbekannte Massnahme: " + massnahme);  //$NON-NLS-1$
			}
		}

		public static Gelaendeverhaeltnisse getDefault() {
			return NORMAL;
		}
	}

	
	public static double getDefaultErstellenPersonalzeit_minProEinheit(Massnahme massnahme) {
		switch(massnahme) {
		case PFAEHLE:
			return 0.26 * 60;

		case BERMEN:
			return 0.04 * 60;

		case SCHWELLEN:
			return 2.30 * 60;

		case DREIBEINBOECKE:
			return 2.20 * 60;

		default:
			throw new IllegalStateException("Unbekannte Massnahme: " + massnahme);  //$NON-NLS-1$
		}
	}
	
	public static double getDefaultErstellenMaterialkosten_ChfProEinheit(Massnahme massnahme) {
		switch(massnahme) {
		case PFAEHLE:
			return 5.00;

		case BERMEN:
			return 0.00;

		case SCHWELLEN:
			return 35.00;

		case DREIBEINBOECKE:
			return 75.80;

		default:
			throw new IllegalStateException("Unbekannte Massnahme: " + massnahme);  //$NON-NLS-1$
		}
	}
	
	public static double getDefaultErstellenMaschinenkosten_ChfProEinheit(Massnahme massnahme) {
		switch(massnahme) {
		case PFAEHLE:
			return 0.50;

		case BERMEN:
			return 0.00;

		case SCHWELLEN:
			return 5.00;

		case DREIBEINBOECKE:
			return 7.00;

		default:
			throw new IllegalStateException("Unbekannte Massnahme: " + massnahme);  //$NON-NLS-1$
		}
	}

	public static double getDefaultUnterhaltPersonalzeit_minProEinheit(Massnahme massnahme) {
		switch(massnahme) {
		case PFAEHLE:
			return 0.02 * 60;

		case BERMEN:
			return 0.01 * 60;

		case SCHWELLEN:
			return 0.02 * 60;

		case DREIBEINBOECKE:
			return 0.04 * 60;

		default:
			throw new IllegalStateException("Unbekannte Massnahme: " + massnahme);  //$NON-NLS-1$
		}
	}
}
