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
import ch.wsl.fps.juwapfl.model.PflanzungModel.Baumart;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungWinkelpflanzungModel {
	
	private Bodenvegetation bodenvegetation;
	private Schlagabraum schlagabraum;
	private Hangneigung hangneigung;
	private Pflanzenhoehe pflanzenhoehe;
	private Transportdistanz transportdistanz;
	
	
	public Bodenvegetation getBodenvegetation() {
		return bodenvegetation;
	}

	public void setBodenvegetation(Bodenvegetation bodenvegetation) {
		this.bodenvegetation = bodenvegetation;
	}

	public Schlagabraum getSchlagabraum() {
		return schlagabraum;
	}

	public void setSchlagabraum(Schlagabraum schlagabraum) {
		this.schlagabraum = schlagabraum;
	}

	public Hangneigung getHangneigung() {
		return hangneigung;
	}

	public void setHangneigung(Hangneigung hangneigung) {
		this.hangneigung = hangneigung;
	}

	public Pflanzenhoehe getPflanzenhoehe() {
		return pflanzenhoehe;
	}

	public void setPflanzenhoehe(Pflanzenhoehe pflanzenhoehe) {
		this.pflanzenhoehe = pflanzenhoehe;
	}

	public Transportdistanz getTransportdistanz() {
		return transportdistanz;
	}

	public void setTransportdistanz(Transportdistanz transportdistanz) {
		this.transportdistanz = transportdistanz;
	}
	
	
	public interface NadelLaubZuschlag {
		double getDefaultZuschlag_hPro100Pflanzen(Baumart baumart);
	}

	
	public enum Bodenvegetation implements NadelLaubZuschlag {
		KEINE(0, 0),
		WENIG(0.25, 0.14),
		MITTEL(0.33, 0.19),
		VIEL(0.42, 0.25);
		
		private final double defaultZuschlagNadel;
		private final double defaultZuschlagLaub;
		
		Bodenvegetation(double defaultZuschlagNadel, double defaultZuschlagLaub) {
			this.defaultZuschlagNadel = defaultZuschlagNadel;
			this.defaultZuschlagLaub = defaultZuschlagLaub;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case KEINE:
				return Messages.getString("Pflanzung.Winkelpflanzung.Bodenvegetation.keine"); //$NON-NLS-1$
				
			case WENIG:
				return Messages.getString("Pflanzung.Winkelpflanzung.Bodenvegetation.wenig"); //$NON-NLS-1$
				
			case MITTEL:
				return Messages.getString("Pflanzung.Winkelpflanzung.Bodenvegetation.mittel"); //$NON-NLS-1$
				
			case VIEL:
				return Messages.getString("Pflanzung.Winkelpflanzung.Bodenvegetation.viel"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Bodenvegetation getDefault() {
			return KEINE;
		}
		
		@Override
		public double getDefaultZuschlag_hPro100Pflanzen(Baumart baumart) {
			if (baumart == Baumart.NADELHOLZ) {
				return defaultZuschlagNadel;
			}
			else if (baumart == Baumart.LAUBHOLZ) {
				return defaultZuschlagLaub;
			}
			else {
				throw new RuntimeException("Baumart: " + baumart); //$NON-NLS-1$
			}
		}
	}
	
	public enum Schlagabraum implements NadelLaubZuschlag {
		KEINE(0, 0),
		WENIG(0.17, 0.17),
		VIEL(0.33, 0.33),
		SEHR_VIEL(0.50, 0.50);
		
		private final double defaultZuschlagNadel;
		private final double defaultZuschlagLaub;
		
		Schlagabraum(double defaultZuschlagNadel, double defaultZuschlagLaub) {
			this.defaultZuschlagNadel = defaultZuschlagNadel;
			this.defaultZuschlagLaub = defaultZuschlagLaub;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case KEINE:
				return Messages.getString("Pflanzung.Winkelpflanzung.Schlagabraum.keine"); //$NON-NLS-1$
				
			case WENIG:
				return Messages.getString("Pflanzung.Winkelpflanzung.Schlagabraum.wenig"); //$NON-NLS-1$
				
			case VIEL:
				return Messages.getString("Pflanzung.Winkelpflanzung.Schlagabraum.viel"); //$NON-NLS-1$
				
			case SEHR_VIEL:
				return Messages.getString("Pflanzung.Winkelpflanzung.Schlagabraum.sehrViel"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Schlagabraum getDefault() {
			return KEINE;
		}
		
		@Override
		public double getDefaultZuschlag_hPro100Pflanzen(Baumart baumart) {
			if (baumart == Baumart.NADELHOLZ) {
				return defaultZuschlagNadel;
			}
			else if (baumart == Baumart.LAUBHOLZ) {
				return defaultZuschlagLaub;
			}
			else {
				throw new RuntimeException("Baumart: " + baumart); //$NON-NLS-1$
			}
		}
	}
	
	public enum Hangneigung implements NadelLaubZuschlag {
		WENIGER_ALS_10_PROZENT(0, 0),
		BIS_45_PROZENT(0.12, 0.05),
		UEBER_45_PROZENT(0.25, 0.10);
		
		private final double defaultZuschlagNadel;
		private final double defaultZuschlagLaub;
		
		Hangneigung(double defaultZuschlagNadel, double defaultZuschlagLaub) {
			this.defaultZuschlagNadel = defaultZuschlagNadel;
			this.defaultZuschlagLaub = defaultZuschlagLaub;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case WENIGER_ALS_10_PROZENT:
				return "< 10%"; //$NON-NLS-1$
				
			case BIS_45_PROZENT:
				return "10 - 45%"; //$NON-NLS-1$
				
			case UEBER_45_PROZENT:
				return "> 45%"; //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Hangneigung getDefault() {
			return WENIGER_ALS_10_PROZENT;
		}
		
		@Override
		public double getDefaultZuschlag_hPro100Pflanzen(Baumart baumart) {
			if (baumart == Baumart.NADELHOLZ) {
				return defaultZuschlagNadel;
			}
			else if (baumart == Baumart.LAUBHOLZ) {
				return defaultZuschlagLaub;
			}
			else {
				throw new RuntimeException("Baumart: " + baumart); //$NON-NLS-1$
			}
		}
	}
	
	public enum Pflanzenhoehe implements NadelLaubZuschlag {
		WENIGER_ALS_40_CM(0, 0),
		VON_40_BIS_60_CM(0.07, 0.07),
		VON_60_BIS_100CM(0.23, 0.17),
		UEBER_100_CM(0.30, 0.58);
		
		private final double defaultZuschlagNadel;
		private final double defaultZuschlagLaub;
		
		Pflanzenhoehe(double defaultZuschlagNadel, double defaultZuschlagLaub) {
			this.defaultZuschlagNadel = defaultZuschlagNadel;
			this.defaultZuschlagLaub = defaultZuschlagLaub;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case WENIGER_ALS_40_CM:
				return "< 40 cm"; //$NON-NLS-1$
				
			case VON_40_BIS_60_CM:
				return "40 - 60 cm"; //$NON-NLS-1$

			case VON_60_BIS_100CM:
				return "> 60 - 100 cm"; //$NON-NLS-1$
				
			case UEBER_100_CM:
				return "> 100 cm"; //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Pflanzenhoehe getDefault() {
			return WENIGER_ALS_40_CM;
		}
		
		@Override
		public double getDefaultZuschlag_hPro100Pflanzen(Baumart baumart) {
			if (baumart == Baumart.NADELHOLZ) {
				return defaultZuschlagNadel;
			}
			else if (baumart == Baumart.LAUBHOLZ) {
				return defaultZuschlagLaub;
			}
			else {
				throw new RuntimeException("Baumart: " + baumart); //$NON-NLS-1$
			}
		}
	}
	
	public enum Transportdistanz implements NadelLaubZuschlag {
		WENIGER_ALS_100_METER(0, 0),
		NORMALPFLANZEN(0.20, 0.08),
		GROSSPFLANZEN(0.30, 0.25);
		
		private final double defaultZuschlagNadel;
		private final double defaultZuschlagLaub;
		
		Transportdistanz(double defaultZuschlagNadel, double defaultZuschlagLaub) {
			this.defaultZuschlagNadel = defaultZuschlagNadel;
			this.defaultZuschlagLaub = defaultZuschlagLaub;
		}
		
		@Override
		public String toString() {
			switch(this) {
			case WENIGER_ALS_100_METER:
				return "< 100 m"; //$NON-NLS-1$
				
			case NORMALPFLANZEN:
				return Messages.getString("Pflanzung.Winkelpflanzung.Transportdistanz.Normalpflanzen"); //$NON-NLS-1$
				
			case GROSSPFLANZEN:
				return Messages.getString("Pflanzung.Winkelpflanzung.Transportdistanz.Grosspflanzen"); //$NON-NLS-1$
			
			default:
				throw new RuntimeException(this.name());
			}
		}

		public static Transportdistanz getDefault() {
			return WENIGER_ALS_100_METER;
		}
		
		@Override
		public double getDefaultZuschlag_hPro100Pflanzen(Baumart baumart) {
			if (baumart == Baumart.NADELHOLZ) {
				return defaultZuschlagNadel;
			}
			else if (baumart == Baumart.LAUBHOLZ) {
				return defaultZuschlagLaub;
			}
			else {
				throw new RuntimeException("Baumart: " + baumart); //$NON-NLS-1$
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodenvegetation == null) ? 0 : bodenvegetation.hashCode());
		result = prime * result + ((hangneigung == null) ? 0 : hangneigung.hashCode());
		result = prime * result + ((pflanzenhoehe == null) ? 0 : pflanzenhoehe.hashCode());
		result = prime * result + ((schlagabraum == null) ? 0 : schlagabraum.hashCode());
		result = prime * result + ((transportdistanz == null) ? 0 : transportdistanz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PflanzungWinkelpflanzungModel other = (PflanzungWinkelpflanzungModel) obj;
		if (bodenvegetation != other.bodenvegetation)
			return false;
		if (hangneigung != other.hangneigung)
			return false;
		if (pflanzenhoehe != other.pflanzenhoehe)
			return false;
		if (schlagabraum != other.schlagabraum)
			return false;
		if (transportdistanz != other.transportdistanz)
			return false;
		return true;
	}

}
