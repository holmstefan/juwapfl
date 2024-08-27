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

import java.text.DecimalFormat;

import ch.wsl.fps.juwapfl.Messages;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WildschutzModel extends AbstractModel {

	private Schutztyp schutztyp;
	private Subtyp subtyp;
	private Wuchshuellentyp wuchshuellentyp;
	private int anzahlPflanzen;
	private int zaunlaenge_m;
	
	private int kostenPersonalProPerson;
	private double faktorWegzeitenUndPausen;
	
	private double korrekturfaktorZeitaufwand;
	private double korrekturfaktorMaterialkosten;
	
	private double fahrzeugkostenAufbauPauschal;
	private double fahrzeugkostenUnterhaltPauschal;
	private double fahrzeugkostenAbbauPauschal;


	public void setSchutztyp(Schutztyp schutztyp) {
		this.schutztyp = schutztyp;
	}

	public void setSubtyp(Subtyp subtyp) {
		this.subtyp = subtyp;
	}

	public void setWuchshuellentyp(Wuchshuellentyp wuchshuellentyp) {
		this.wuchshuellentyp = wuchshuellentyp;
	}

	public void setAnzahlPflanzen(int anzahlPflanzen) {
		this.anzahlPflanzen = anzahlPflanzen;
	}

	public void setZaunlaenge_m(int zaunlaenge_m) {
		this.zaunlaenge_m = zaunlaenge_m;
	}


	public void setKostenPersonalProPerson(int kostenPersonalProPerson) {
		this.kostenPersonalProPerson = kostenPersonalProPerson;
	}

	public void setFaktorWegzeitenUndPausen(double faktorWegzeitenUndPausen) {
		this.faktorWegzeitenUndPausen = faktorWegzeitenUndPausen;
	}

	public void setKorrekturfaktorZeitaufwand(double korrekturfaktorZeitaufwand) {
		this.korrekturfaktorZeitaufwand = korrekturfaktorZeitaufwand;
	}

	public void setKorrekturfaktorMaterialkosten(double korrekturfaktorMaterialkosten) {
		this.korrekturfaktorMaterialkosten = korrekturfaktorMaterialkosten;
	}

	public void setFahrzeugkostenAufbauPauschal(double fahrzeugkostenAufbauPauschal) {
		this.fahrzeugkostenAufbauPauschal = fahrzeugkostenAufbauPauschal;
	}

	public void setFahrzeugkostenUnterhaltPauschal(double fahrzeugkostenUnterhaltPauschal) {
		this.fahrzeugkostenUnterhaltPauschal = fahrzeugkostenUnterhaltPauschal;
	}

	public void setFahrzeugkostenAbbauPauschal(double fahrzeugkostenAbbauPauschal) {
		this.fahrzeugkostenAbbauPauschal = fahrzeugkostenAbbauPauschal;
	}
	

	@Override
	public WildschutzErgebnis getErgebnis() {
		WildschutzErgebnis ergebnis = new WildschutzErgebnis();
		
		double zeitaufwandAufbau_min;
		double zeitaufwandUnterhalt_min;
		double zeitaufwandAbbau_min;

		double kostenMaterialAufbau;
		double kostenMaterialUnterhalt;
		double kostenMaterialAbbau;
		
		
		if (schutztyp == Schutztyp.WUCHSHUELLE) {
			double zeitaufwandAufbau_minProBaum = 2;
			double zeitaufwandUnterhalt_minProBaum = 8;
			double zeitaufwandAbbau_minProBaum = 3;
			double kostenMaterialAufbau_proBaum = 0;
			double kostenMaterialAbbau_proBaum = 0.035;
			
			if (subtyp == Subtyp.REHWILDSICHER_ABBAUBAR || subtyp == Subtyp.REHWILDSICHER_NICHT_ABBAUBAR) {
				kostenMaterialAufbau_proBaum = wuchshuellentyp.getKosten() + 0.85 + (2 * 0.03);
			}
			else if (subtyp == Subtyp.ROTWILDSICHER_ABBAUBAR || subtyp == Subtyp.ROTWILDSICHER_NICHT_ABBAUBAR) {
				kostenMaterialAufbau_proBaum = wuchshuellentyp.getKosten() + 1.95 + (2 * 0.03);
			}
			else {
				throw new IllegalStateException("unknown subtyp: " + subtyp); //$NON-NLS-1$
			}
			
			zeitaufwandAufbau_min = zeitaufwandAufbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proBaum * anzahlPflanzen;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = kostenMaterialAbbau_proBaum * anzahlPflanzen;
		}
		else if (schutztyp == Schutztyp.KUNSTSTOFFKORB) {
			double zeitaufwandAufbau_minProBaum = 3;
			double zeitaufwandUnterhalt_minProBaum = 2;
			double zeitaufwandAbbau_minProBaum = 3;
			double kostenMaterialAufbau_proBaum = 0;
			double kostenMaterialAbbau_proBaum = 0.2;
			
			if (subtyp == Subtyp.REHWILDSICHER) {
				kostenMaterialAufbau_proBaum = 6.80 + (2 * 0.85) + (2 * 0.03);
			}
			else {
				throw new IllegalStateException("unknown subtyp: " + subtyp); //$NON-NLS-1$
			}
			
			zeitaufwandAufbau_min = zeitaufwandAufbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proBaum * anzahlPflanzen;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = kostenMaterialAbbau_proBaum * anzahlPflanzen;
		}
		else if (schutztyp == Schutztyp.DRAHTKORB_LEICHT) {
			double zeitaufwandAufbau_minProBaum = 5;
			double zeitaufwandUnterhalt_minProBaum = 2;
			double zeitaufwandAbbau_minProBaum = 3;
			double kostenMaterialAufbau_proBaum = 0;
			double kostenMaterialAbbau_proBaum = 0.2;
			
			if (subtyp == Subtyp.REHWILDSICHER) {
				kostenMaterialAufbau_proBaum = 2.50 + (3 * 0.85);
			}
			else if (subtyp == Subtyp.ROTWILDSICHER) {
				kostenMaterialAufbau_proBaum = 4.50 + (3 * 1.95);
			}
			else {
				throw new IllegalStateException("unknown subtyp: " + subtyp); //$NON-NLS-1$
			}
			
			zeitaufwandAufbau_min = zeitaufwandAufbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proBaum * anzahlPflanzen;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = kostenMaterialAbbau_proBaum * anzahlPflanzen;
		}
		else if (schutztyp == Schutztyp.DRAHTKORB_MASSIV) {
			double zeitaufwandAufbau_minProBaum = 44;
			double zeitaufwandUnterhalt_minProBaum = 53;
			double zeitaufwandAbbau_minProBaum = 9;
			double kostenMaterialAufbau_proBaum = 0;
			
			if (subtyp == Subtyp.REHWILDSICHER) {
				kostenMaterialAufbau_proBaum = 2.50 + (3 * 1.50) + (3 * 1);
			}
			else if (subtyp == Subtyp.ROTWILDSICHER) {
				kostenMaterialAufbau_proBaum = 5.60 + (3 * 2.90) + (3 * 1);
			}
			else {
				throw new IllegalStateException("unknown subtyp: " + subtyp); //$NON-NLS-1$
			}
			
			zeitaufwandAufbau_min = zeitaufwandAufbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proBaum * anzahlPflanzen;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = 0;
		}
		else if (schutztyp == Schutztyp.EINZELSCHUTZ_HOLZ) {
			double zeitaufwandAufbau_minProBaum = 3;
			double zeitaufwandUnterhalt_minProBaum = 2;
			double zeitaufwandAbbau_minProBaum = 0;
			double kostenMaterialAufbau_proBaum = 0;
			
			if (subtyp == Subtyp.REHWILDSICHER) {
				kostenMaterialAufbau_proBaum = 6.50 + (1 * 0.85);
			}
			else {
				throw new IllegalStateException("unknown subtyp: " + subtyp); //$NON-NLS-1$
			}
			
			zeitaufwandAufbau_min = zeitaufwandAufbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proBaum * anzahlPflanzen;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = 0;
		}
		else if (schutztyp == Schutztyp.TRIEBSCHUTZMANSCHETTE) {
			double zeitaufwandAufbau_minProBaum = 0.5;
			double zeitaufwandUnterhalt_minProBaum = 0.2;
			double zeitaufwandAbbau_minProBaum = 0.2;
			double kostenMaterialAufbau_proBaum = 0.2;
			double kostenMaterialAbbau_proBaum = 0.01;
			
			zeitaufwandAufbau_min = zeitaufwandAufbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proBaum * anzahlPflanzen;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = kostenMaterialAbbau_proBaum * anzahlPflanzen;
		}
		else if (schutztyp == Schutztyp.CHEMISCHER_SCHUTZ) {
			double zeitaufwandAufbau_minProBaum = 5;
			double zeitaufwandUnterhalt_minProBaum = 0;
			double zeitaufwandAbbau_minProBaum = 0;
			double kostenMaterialAufbau_proBaum = 0.2;
			
			zeitaufwandAufbau_min = zeitaufwandAufbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proBaum * anzahlPflanzen;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = 0;
		}
		else if (schutztyp == Schutztyp.SCHAELSCHUTZ) {
			double zeitaufwandAufbau_minProBaum = 7.1 + 4.4;
			double zeitaufwandUnterhalt_minProBaum = 8.9;
			double zeitaufwandAbbau_minProBaum = 2.7;
			double kostenMaterialAufbau_proBaum = 4;
			
			zeitaufwandAufbau_min = zeitaufwandAufbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProBaum * anzahlPflanzen * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proBaum * anzahlPflanzen;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = 0;
		}
		else if (schutztyp == Schutztyp.FLAECHENSCHUTZ) {
			double zeitaufwandAufbau_minProLfm = 0;
			double zeitaufwandUnterhalt_minProLfm = 0;
			double zeitaufwandAbbau_minProLfm = 5.5;
			double kostenMaterialAufbau_proLfm = 0;
			
			switch(subtyp) {
			case REHWILDSICHER_HOLZPFAHL:
				zeitaufwandAufbau_minProLfm = 8.9;
				zeitaufwandUnterhalt_minProLfm = 27;
				kostenMaterialAufbau_proLfm = 3.9875;
				break;

			case ROTWILDSICHER_HOLZPFAHL:
				zeitaufwandAufbau_minProLfm = 22.2;
				zeitaufwandUnterhalt_minProLfm = 36;
				kostenMaterialAufbau_proLfm = 7.65;
				break;

			case REHWILDSICHER_EISENPFAHL:
				zeitaufwandAufbau_minProLfm = 6.9;
				zeitaufwandUnterhalt_minProLfm = 27;
				kostenMaterialAufbau_proLfm = 5;
				break;

			case ROTWILDSICHER_EISENPFAHL:
				zeitaufwandAufbau_minProLfm = 17.2;
				zeitaufwandUnterhalt_minProLfm = 36;
				kostenMaterialAufbau_proLfm = 8.71;
				break;

				//$CASES-OMITTED$
			default:
				throw new RuntimeException("Unzuläasiger Subtyp: " + subtyp); //$NON-NLS-1$
			}

			zeitaufwandAufbau_min = zeitaufwandAufbau_minProLfm * zaunlaenge_m * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandUnterhalt_min = zeitaufwandUnterhalt_minProLfm * zaunlaenge_m * F_INDIR * faktorWegzeitenUndPausen;
			zeitaufwandAbbau_min = zeitaufwandAbbau_minProLfm * zaunlaenge_m * F_INDIR * faktorWegzeitenUndPausen;

			kostenMaterialAufbau = kostenMaterialAufbau_proLfm * zaunlaenge_m;
			kostenMaterialUnterhalt = 0;
			kostenMaterialAbbau = 0;
		}
		else {
			throw new IllegalStateException("unknown schutztyp: " + schutztyp); //$NON-NLS-1$
		}
		
		//Korrekturfaktor Zeitaufwand
		zeitaufwandAufbau_min *= korrekturfaktorZeitaufwand;
		zeitaufwandUnterhalt_min *= korrekturfaktorZeitaufwand;
		zeitaufwandAbbau_min *= korrekturfaktorZeitaufwand;
		
		// Korrekturfaktor Materialkosten
		kostenMaterialAufbau *= korrekturfaktorMaterialkosten;
		kostenMaterialUnterhalt *= korrekturfaktorMaterialkosten;
		kostenMaterialAbbau *= korrekturfaktorMaterialkosten;
		
		
		ergebnis.setZeitPersonalAufbau_min(zeitaufwandAufbau_min);
		ergebnis.setZeitPersonalUnterhalt_min(zeitaufwandUnterhalt_min);
		ergebnis.setZeitPersonalAbbau_min(zeitaufwandAbbau_min);

		ergebnis.setKostenPersonalAufbau(zeitaufwandAufbau_min * kostenPersonalProPerson / 60);
		ergebnis.setKostenPersonalUnterhalt(zeitaufwandUnterhalt_min * kostenPersonalProPerson / 60);
		ergebnis.setKostenPersonalAbbau(zeitaufwandAbbau_min * kostenPersonalProPerson / 60);

		ergebnis.setKostenFahrtenAufbau(fahrzeugkostenAufbauPauschal);
		ergebnis.setKostenFahrtenUnterhalt(fahrzeugkostenUnterhaltPauschal);
		ergebnis.setKostenFahrtenAbbau(fahrzeugkostenAbbauPauschal);

		ergebnis.setKostenMaterialAufbau(kostenMaterialAufbau);
		ergebnis.setKostenMaterialUnterhalt(kostenMaterialUnterhalt);
		ergebnis.setKostenMaterialAbbau(kostenMaterialAbbau);
		
		return ergebnis;
	}
	
	
	public enum Schutztyp {
		WUCHSHUELLE,
		KUNSTSTOFFKORB,
		DRAHTKORB_LEICHT,
		DRAHTKORB_MASSIV,
		EINZELSCHUTZ_HOLZ,
		TRIEBSCHUTZMANSCHETTE,
		CHEMISCHER_SCHUTZ,
		SCHAELSCHUTZ,
		FLAECHENSCHUTZ;
		
		@Override
		public String toString() {
			switch(this) {
			case WUCHSHUELLE:
				return Messages.getString("Wildschutz.Schutztyp.Wuchshuelle"); //$NON-NLS-1$
				
			case KUNSTSTOFFKORB:
				return Messages.getString("Wildschutz.Schutztyp.Kunststoffkorb"); //$NON-NLS-1$
				
			case DRAHTKORB_LEICHT:
				return Messages.getString("Wildschutz.Schutztyp.DrahtkorbLeicht"); //$NON-NLS-1$
				
			case DRAHTKORB_MASSIV:
				return Messages.getString("Wildschutz.Schutztyp.DrahtkorbMassiv"); //$NON-NLS-1$
				
			case EINZELSCHUTZ_HOLZ:
				return Messages.getString("Wildschutz.Schutztyp.EinzelschutzHolz"); //$NON-NLS-1$
				
			case TRIEBSCHUTZMANSCHETTE:
				return Messages.getString("Wildschutz.Schutztyp.Triebschutzmanschette"); //$NON-NLS-1$
				
			case CHEMISCHER_SCHUTZ:
				return Messages.getString("Wildschutz.Schutztyp.ChemischerSchutz"); //$NON-NLS-1$
				
			case SCHAELSCHUTZ:
				return Messages.getString("Wildschutz.Schutztyp.Schaelschutz"); //$NON-NLS-1$
				
			case FLAECHENSCHUTZ:
				return Messages.getString("Wildschutz.Schutztyp.Flaechenschutz"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Schutztyp getDefault() {
			return WUCHSHUELLE;
		}
	}
	
	
	public enum Subtyp {
		REHWILDSICHER_ABBAUBAR,
		REHWILDSICHER_NICHT_ABBAUBAR,
		ROTWILDSICHER_ABBAUBAR,
		ROTWILDSICHER_NICHT_ABBAUBAR,
		REHWILDSICHER,
		ROTWILDSICHER,
		REHWILDSICHER_HOLZPFAHL,
		ROTWILDSICHER_HOLZPFAHL,
		REHWILDSICHER_EISENPFAHL,
		ROTWILDSICHER_EISENPFAHL;
		
		@Override
		public String toString() {
			switch(this) {
			case REHWILDSICHER_ABBAUBAR:
				return Messages.getString("Wildschutz.Subtyp.rehwildsicherAbbaubar"); //$NON-NLS-1$
				
			case REHWILDSICHER_NICHT_ABBAUBAR:
				return Messages.getString("Wildschutz.Subtyp.rehwildsicherNichtAbbaubar"); //$NON-NLS-1$

			case ROTWILDSICHER_ABBAUBAR:
				return Messages.getString("Wildschutz.Subtyp.rotwildsicherAbbaubar"); //$NON-NLS-1$
				
			case ROTWILDSICHER_NICHT_ABBAUBAR:
				return Messages.getString("Wildschutz.Subtyp.rotwildsicherNichtAbbaubar"); //$NON-NLS-1$

			case REHWILDSICHER:
				return Messages.getString("Wildschutz.Subtyp.rehwildsicher"); //$NON-NLS-1$
				
			case ROTWILDSICHER:
				return Messages.getString("Wildschutz.Subtyp.rotwildsicher"); //$NON-NLS-1$

			case REHWILDSICHER_HOLZPFAHL:
				return Messages.getString("Wildschutz.Subtyp.rehwildsicherHolzpfahl"); //$NON-NLS-1$
				
			case ROTWILDSICHER_HOLZPFAHL:
				return Messages.getString("Wildschutz.Subtyp.rotwildsicherHolzpfahl"); //$NON-NLS-1$

			case REHWILDSICHER_EISENPFAHL:
				return Messages.getString("Wildschutz.Subtyp.rehwildsicherEisenpfahl"); //$NON-NLS-1$
				
			case ROTWILDSICHER_EISENPFAHL:
				return Messages.getString("Wildschutz.Subtyp.rotwildsicherEisenpfahl"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}
		
		public static Subtyp[] getValues(Schutztyp schutztyp) {
			if (schutztyp == null) {
				return new Subtyp[]{};
			}
			
			switch(schutztyp) {
			case WUCHSHUELLE:
				return new Subtyp[]{
						REHWILDSICHER_ABBAUBAR,
						REHWILDSICHER_NICHT_ABBAUBAR,
						ROTWILDSICHER_ABBAUBAR,
						ROTWILDSICHER_NICHT_ABBAUBAR};
			
			case EINZELSCHUTZ_HOLZ:
			case KUNSTSTOFFKORB:
				return new Subtyp[]{
						REHWILDSICHER};
				
			case DRAHTKORB_LEICHT:
			case DRAHTKORB_MASSIV:
				return new Subtyp[]{REHWILDSICHER, ROTWILDSICHER};
				
			case CHEMISCHER_SCHUTZ:
			case SCHAELSCHUTZ:
			case TRIEBSCHUTZMANSCHETTE:
				return new Subtyp[]{};
				
			case FLAECHENSCHUTZ:
				return new Subtyp[]{
						REHWILDSICHER_HOLZPFAHL,
						ROTWILDSICHER_HOLZPFAHL,
						REHWILDSICHER_EISENPFAHL,
						ROTWILDSICHER_EISENPFAHL};
				
			default:
				throw new RuntimeException("unknown Schutztyp: " + schutztyp.name()); //$NON-NLS-1$
			
			}
		}
	}
	
	public enum Wuchshuellentyp {
		TUBEX_VENTEX_90(2.2),
		TUBEX_VENTEX_120(2.4),
		TUBEX_VENTEX_150(3.0),
		PLANT_SAVER_90(1.2),
		PLANT_SAVER_120(1.6),
		PLANT_SAVER_150(1.9),
		TUBEX_VENTEX_12D(3.2),
		FORTE_TUBE_120(1.8),
		FORTE_TUBE_150(2.6),
		
		TUBEX_VENTEX_180(3.8),
		FORTE_TUBE_180(3.5),
		PLANT_SAVER_180(2.1);
		
		private final double kosten;
		private final DecimalFormat format = new DecimalFormat("0.00"); //$NON-NLS-1$
		
		Wuchshuellentyp(double kosten) {
			this.kosten = kosten;
		}
		
		@Override
		public String toString() {
			final String suffix = " (" + format.format(kosten) +  " " + Messages.getString("Wildschutz.Wuchshuellentyp.CHFproStueck") + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			
			switch(this) {
			case TUBEX_VENTEX_90:
				return "Tubex Ventex 90 cm" + suffix; //$NON-NLS-1$
				
			case TUBEX_VENTEX_120:
				return "Tubex Ventex 120 cm" + suffix; //$NON-NLS-1$

			case TUBEX_VENTEX_150:
				return "Tubex Ventex 150 cm" + suffix; //$NON-NLS-1$
				
			case PLANT_SAVER_90:
				return "Plant Saver \"Microvent VarioWING\" 90 cm" + suffix; //$NON-NLS-1$

			case PLANT_SAVER_120:
				return "Plant Saver \"Microvent VarioWING\" 120 cm" + suffix; //$NON-NLS-1$
				
			case PLANT_SAVER_150:
				return "Plant Saver \"Microvent VarioWING\" 150 cm" + suffix; //$NON-NLS-1$

			case TUBEX_VENTEX_12D:
				return "Tubex Ventex 120 cm " + Messages.getString("Wildschutz.Wuchshuellentyp.SuffixAbbaubar") + suffix; //$NON-NLS-1$ //$NON-NLS-2$
				
			case FORTE_TUBE_120:
				return "Forte Tube Makrovent 120 cm " + Messages.getString("Wildschutz.Wuchshuellentyp.SuffixAbbaubar") + suffix; //$NON-NLS-1$ //$NON-NLS-2$

			case FORTE_TUBE_150:
				return "Forte Tube Makrovent 150 cm " + Messages.getString("Wildschutz.Wuchshuellentyp.SuffixAbbaubar") + suffix; //$NON-NLS-1$ //$NON-NLS-2$
				
			case TUBEX_VENTEX_180:
				return "Tubex Ventex 180 cm" + suffix; //$NON-NLS-1$

			case FORTE_TUBE_180:
				return "Forte Tube Makrovent 180 cm " + Messages.getString("Wildschutz.Wuchshuellentyp.SuffixAbbaubar") + suffix; //$NON-NLS-1$ //$NON-NLS-2$
				
			case PLANT_SAVER_180:
				return "Plant Saver \"Microvent VarioWING\" 180 cm" + suffix; //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}
		
		public double getKosten() {
			return kosten;
		}
		
		public static Wuchshuellentyp[] getValues(Subtyp subtyp) {
			if (subtyp == null) {
				return new Wuchshuellentyp[]{};
			}
			
			switch(subtyp) {
			case REHWILDSICHER_ABBAUBAR:
				return new Wuchshuellentyp[]{
						TUBEX_VENTEX_12D,
						FORTE_TUBE_120,
						FORTE_TUBE_150};
				
			case REHWILDSICHER_NICHT_ABBAUBAR:
				return new Wuchshuellentyp[]{
						TUBEX_VENTEX_90, 
						TUBEX_VENTEX_120, 
						TUBEX_VENTEX_150, 
						PLANT_SAVER_90, 
						PLANT_SAVER_120, 
						PLANT_SAVER_150};

			case ROTWILDSICHER_ABBAUBAR:
				return new Wuchshuellentyp[]{
						FORTE_TUBE_180};
				
			case ROTWILDSICHER_NICHT_ABBAUBAR:
				return new Wuchshuellentyp[]{
						TUBEX_VENTEX_180,
						PLANT_SAVER_180};
				
				//$CASES-OMITTED$
			default:
				throw new RuntimeException("unknown subtyp: " + subtyp.name()); //$NON-NLS-1$
			
			}
		}
		
		public static Wuchshuellentyp getDefault(Subtyp subtyp) {
			if (subtyp == null) {
				return null;
			}
			
			switch(subtyp) {
			case REHWILDSICHER_ABBAUBAR:
				return TUBEX_VENTEX_12D;
				
			case REHWILDSICHER_NICHT_ABBAUBAR:
				return TUBEX_VENTEX_120;

			case ROTWILDSICHER_ABBAUBAR:
				return FORTE_TUBE_180;
				
			case ROTWILDSICHER_NICHT_ABBAUBAR:
				return TUBEX_VENTEX_180;
				
				//$CASES-OMITTED$
			default:
				throw new RuntimeException("unknown subtyp: " + subtyp.name()); //$NON-NLS-1$
			
			}
		}
	}

}
