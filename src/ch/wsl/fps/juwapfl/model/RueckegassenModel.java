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
public class RueckegassenModel extends AbstractModel {

	private double flaeche_ha;
	private Verhaeltnisse verhaeltnisse;
	private int abstandRueckegassen_m;
	private Hilfskraft hilfskraft;
	private Ortskenntnisse ortskenntnisse;
	private UebungPlanungsverfahren uebungPlanungsverfahren;
	
	private double kostenHauptperson_proH;
	private double kostenHilfskraft_proH;
	private double kostenGeraete_proH;
	private double faktorWegzeitenUndPausen;
	

	public void setFlaeche_ha(double flaeche_ha) {
		this.flaeche_ha = flaeche_ha;
	}

	public void setVerhaeltnisse(Verhaeltnisse verhaeltnisse) {
		this.verhaeltnisse = verhaeltnisse;
	}

	public void setAbstandRueckegassen_m(int abstandRueckegassen_m) {
		this.abstandRueckegassen_m = abstandRueckegassen_m;
	}

	public void setHilfskraft(Hilfskraft hilfskraft) {
		this.hilfskraft = hilfskraft;
	}

	public void setOrtskenntnisse(Ortskenntnisse ortskenntnisse) {
		this.ortskenntnisse = ortskenntnisse;
	}

	public void setUebungPlanungsverfahren(UebungPlanungsverfahren uebungPlanungsverfahren) {
		this.uebungPlanungsverfahren = uebungPlanungsverfahren;
	}


	public void setKostenHauptperson_proH(double kostenHauptperson_proH) {
		this.kostenHauptperson_proH = kostenHauptperson_proH;
	}

	public void setKostenHilfskraft_proH(double kostenHilfskraft_proH) {
		this.kostenHilfskraft_proH = kostenHilfskraft_proH;
	}

	public void setKostenGeraete_proH(double kostenGeraete_proH) {
		this.kostenGeraete_proH = kostenGeraete_proH;
	}

	public void setFaktorWegzeitenUndPausen(double faktorWegzeitenUndPausen) {
		this.faktorWegzeitenUndPausen = faktorWegzeitenUndPausen;
	}


	@Override
	public RueckegassenErgebnis getErgebnis() {
		RueckegassenErgebnis ergebnis = new RueckegassenErgebnis();
		
		double zeitaufwandHauptplaner_pph15ProHa = 
				(verhaeltnisse.getGrundaufwandHauptperson_hProHa() - 2.4883 * Math.log(abstandRueckegassen_m))
				* (1 + ortskenntnisse.getKorrekturfaktor() + uebungPlanungsverfahren.getKorrekturfaktor());
		
		double zeitaufwandHilfskraft_pph15ProHa = hilfskraft == Hilfskraft.JA ?
				(verhaeltnisse.getGrundaufwandHilfskraft_hProHa() - 0.5364 * Math.log(abstandRueckegassen_m))
				* (1 + ortskenntnisse.getKorrekturfaktor() + uebungPlanungsverfahren.getKorrekturfaktor())
				: 0;

		double zeitaufwandHauptplaner_wpshProHa = zeitaufwandHauptplaner_pph15ProHa * faktorWegzeitenUndPausen /** F_INDIR*/;
		double zeitaufwandHilfskraft_wpshProHa = zeitaufwandHilfskraft_pph15ProHa * faktorWegzeitenUndPausen /** F_INDIR*/;

		double personalkostenHauptplaner_proHa = zeitaufwandHauptplaner_wpshProHa * kostenHauptperson_proH;
		double personalkostenHilfskraft_proHa = zeitaufwandHilfskraft_wpshProHa * kostenHilfskraft_proH;

		
		ergebnis.setFlaeche_ha(flaeche_ha);
		ergebnis.setZeitaufwandHauptperson_minProHektar(zeitaufwandHauptplaner_wpshProHa * 60);
		ergebnis.setZeitaufwandHilfskraft_minProHektar(zeitaufwandHilfskraft_wpshProHa * 60);
		ergebnis.setKostenHauptperson_proHektar(personalkostenHauptplaner_proHa);
		ergebnis.setKostenHilfskraft_proHektar(personalkostenHilfskraft_proHa);
		ergebnis.setKostenMaterial_proHektar(kostenGeraete_proH);

		return ergebnis;
	}

	
	public static enum Verhaeltnisse {
		LEICHT,
		MITTEL,
		SCHWIERIG;
		
		@Override
		public String toString() {
			switch(this) {
			case LEICHT:
				return Messages.getString("Rueckegassen.Verhaeltnisse.leicht"); //$NON-NLS-1$
				
			case MITTEL:
				return Messages.getString("Rueckegassen.Verhaeltnisse.mittel"); //$NON-NLS-1$
				
			case SCHWIERIG:
				return Messages.getString("Rueckegassen.Verhaeltnisse.schwierig"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Verhaeltnisse getDefault() {
			return MITTEL;
		}
		
		public double getGrundaufwandHauptperson_hProHa() {
			switch(this) {
			case LEICHT:
				return 10.6299;

			case MITTEL:
				return 11.7906;

			case SCHWIERIG:
				return 13.0965;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
		
		public double getGrundaufwandHilfskraft_hProHa() {
			switch(this) {
			case LEICHT:
				return 3.1175;

			case MITTEL:
				return 3.9313;

			case SCHWIERIG:
				return 4.8710;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}
	
	
	public static enum Hilfskraft {
		JA,
		NEIN;
		
		@Override
		public String toString() {
			switch(this) {
			case JA:
				return Messages.getString("Rueckegassen.Hilfskraft.ja"); //$NON-NLS-1$
				
			case NEIN:
				return Messages.getString("Rueckegassen.Hilfskraft.nein"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Hilfskraft getDefault() {
			return NEIN;
		}
	}
	
	
	public static enum Ortskenntnisse {
		JA,
		NEIN;
		
		@Override
		public String toString() {
			switch(this) {
			case JA:
				return Messages.getString("Rueckegassen.Ortskenntnisse.ja"); //$NON-NLS-1$
				
			case NEIN:
				return Messages.getString("Rueckegassen.Ortskenntnisse.nein"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Ortskenntnisse getDefault() {
			return JA;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case JA:
				return 0.0;

			case NEIN:
				return 0.5;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}

	
	public static enum UebungPlanungsverfahren {
		JA,
		NEIN;
		
		@Override
		public String toString() {
			switch(this) {
			case JA:
				return Messages.getString("Rueckegassen.UebungPlanungsverfahren.ja"); //$NON-NLS-1$
				
			case NEIN:
				return Messages.getString("Rueckegassen.UebungPlanungsverfahren.nein"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static UebungPlanungsverfahren getDefault() {
			return JA;
		}
		
		public double getKorrekturfaktor() {
			switch(this) {
			case JA:
				return 0.0;

			case NEIN:
				return 0.5;
				
			default:
				throw new IllegalStateException(this.name());
			}
		}
	}

}
