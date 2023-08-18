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
public class BegehungswegeModel extends AbstractModel {

	private static final double zeitaufwandErstellenManuellEinfach_minProM = 13.0;
	private static final double zeitaufwandErstellenManuellSchwierig_minProM = 19.5;
	
	private static final double zeitaufwandErstellenMaschinellEinfach_minProM = 2.5;
	private static final double zeitaufwandErstellenMaschinellSchwierig_minProM = 4;

	private static final double zeitaufwandInstandStellenManuellEinfach_minProM = 6.5;
	private static final double zeitaufwandInstandStellenManuellSchwierig_minProM = 9.5;
	
	private static final double zeitaufwandInstandStellenMaschinellEinfach_minProM = 1.0;
	private static final double zeitaufwandInstandStellenMaschinellSchwierig_minProM = 2.0;

	@SuppressWarnings("unused") // wird aktuell nur im GUI zur Anzeige der korrekten default-Werte benötigt.
	private Begehungsweg begehungsweg;
	
	private Ausfuehrung ausfuehrung;
	
	@SuppressWarnings("unused") // wird aktuell nur im GUI zur Anzeige der korrekten default-Werte benötigt.
	private GelaendeSchwierigkeit gelaendeSchwierigkeit;
	
	private int anzahlLaufmeter;
	
	private double zeitaufwand_minProM;

	private int kostenPersonal;
	private int kostenMaschine;
	private double faktorWegzeitenUndPausen;
	

	public void setBegehungsweg(Begehungsweg begehungsweg) {
		this.begehungsweg = begehungsweg;
	}

	public void setAusfuehrung(Ausfuehrung ausfuehrung) {
		this.ausfuehrung = ausfuehrung;
	}

	public void setGelaendeSchwierigkeit(GelaendeSchwierigkeit gelaendeSchwierigkeit) {
		this.gelaendeSchwierigkeit = gelaendeSchwierigkeit;
	}

	public void setAnzahlLaufmeter(int anzahlLaufmeter) {
		this.anzahlLaufmeter = anzahlLaufmeter;
	}
	

	public void setZeitaufwand_minProM(Double zeitaufwand_minProM) {
		this.zeitaufwand_minProM = zeitaufwand_minProM;
	}
	

	public void setKostenPersonal(int kostenPersonal) {
		this.kostenPersonal = kostenPersonal;
	}

	public void setKostenMaschine(int kostenMaschine) {
		this.kostenMaschine = kostenMaschine;
	}

	public void setFaktorWegzeitenUndPausen(double faktorWegzeitenUndPausen) {
		this.faktorWegzeitenUndPausen = faktorWegzeitenUndPausen;
	}


	@Override
	public BegehungswegeErgebnis getErgebnis() {
		BegehungswegeErgebnis ergebnis = new BegehungswegeErgebnis();
		
		double zeitPersonal_min = zeitaufwand_minProM * anzahlLaufmeter * F_INDIR * faktorWegzeitenUndPausen;
		
		boolean isMaschinell = ausfuehrung == Ausfuehrung.MASCHINELL;
		double zeitMaschineErstellen_min = isMaschinell ? zeitaufwand_minProM * anzahlLaufmeter : 0;
		
		ergebnis.setZeitPersonal_min(zeitPersonal_min);
		ergebnis.setZeitMaschine_min(zeitMaschineErstellen_min);
		
		ergebnis.setKostenPersonal(zeitPersonal_min * kostenPersonal / 60);
		ergebnis.setKostenMaschine(zeitMaschineErstellen_min * kostenMaschine / 60);
		
		return ergebnis;
	}

	
	public enum Begehungsweg {
		NEU_ERSTELLEN,
		INSTAND_STELLEN;
		
		@Override
		public String toString() {
			switch(this) {
			case NEU_ERSTELLEN:
				return Messages.getString("Begehungswege.neuErstellen"); //$NON-NLS-1$
				
			case INSTAND_STELLEN:
				return Messages.getString("Begehungswege.instandStellen"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}
	}

	
	public enum Ausfuehrung {
		MANUELL,
		MASCHINELL;
		
		@Override
		public String toString() {
			switch(this) {
			case MANUELL:
				return Messages.getString("Begehungswege.vonHand"); //$NON-NLS-1$
				
			case MASCHINELL:
				return Messages.getString("Begehungswege.maschinell"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Ausfuehrung getDefault() {
			return MANUELL;
		}
	}

	
	public enum GelaendeSchwierigkeit {
		EINFACH,
		SCHWIERIG;
		
		@Override
		public String toString() {
			switch(this) {
			case EINFACH:
				return Messages.getString("Begehungswege.einfach"); //$NON-NLS-1$
				
			case SCHWIERIG:
				return Messages.getString("Begehungswege.schwierig"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static GelaendeSchwierigkeit getDefault() {
			return EINFACH;
		}
	}


	public static double getDefaultZeitaufwand_minProM(Begehungsweg begehungsweg, Ausfuehrung ausfuehrung, GelaendeSchwierigkeit schwierigkeit) {
		if (begehungsweg == Begehungsweg.NEU_ERSTELLEN) {
			return getDefaultZeitaufwandErstellen_minProM(ausfuehrung, schwierigkeit);
		}
		else if (begehungsweg == Begehungsweg.INSTAND_STELLEN) {
			return getDefaultZeitaufwandInstandStellen_minProM(ausfuehrung, schwierigkeit);
		}
		throw new RuntimeException("begehungsweg is null"); //$NON-NLS-1$
	}


	private static double getDefaultZeitaufwandErstellen_minProM(Ausfuehrung ausfuehrung, GelaendeSchwierigkeit schwierigkeit) {
		if (ausfuehrung == Ausfuehrung.MANUELL) {
			if (schwierigkeit == GelaendeSchwierigkeit.EINFACH) {
				return zeitaufwandErstellenManuellEinfach_minProM;
			}
			else {
				return zeitaufwandErstellenManuellSchwierig_minProM;
			}
		}
		else {
			if (schwierigkeit == GelaendeSchwierigkeit.EINFACH) {
				return zeitaufwandErstellenMaschinellEinfach_minProM;
			}
			else {
				return zeitaufwandErstellenMaschinellSchwierig_minProM;
			}
		}
	}

	private static double getDefaultZeitaufwandInstandStellen_minProM(Ausfuehrung ausfuehrung, GelaendeSchwierigkeit schwierigkeit) {
		if (ausfuehrung == Ausfuehrung.MANUELL) {
			if (schwierigkeit == GelaendeSchwierigkeit.EINFACH) {
				return zeitaufwandInstandStellenManuellEinfach_minProM;
			}
			else {
				return zeitaufwandInstandStellenManuellSchwierig_minProM;
			}
		}
		else {
			if (schwierigkeit == GelaendeSchwierigkeit.EINFACH) {
				return zeitaufwandInstandStellenMaschinellEinfach_minProM;
			}
			else {
				return zeitaufwandInstandStellenMaschinellSchwierig_minProM;
			}
		}
	}
}
