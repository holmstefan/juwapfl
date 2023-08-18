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

/**
 * 
 * @author Stefan Holm
 *
 */
public class AustrichternErgebnis extends AbstractAustrichternErgebnis {
	
	private int anzahlPflanzenProHektar;
	private double flaecheBestand_ha;
	
	private double zeitPersonal_WPSHProHa;
	
	private double kostenPersonal_proHa;
	private double kostenMaschinenGeraete_proHa;
	private double kostenGesamt_proHa;
	
	
	public void setAnzahlPflanzenProHektar(int value) {
		this.anzahlPflanzenProHektar = value;
	}

	public void setFlaecheBestand_ha(double value) {
		this.flaecheBestand_ha = value;
	}
	
	
	
	public void setZeitPersonal_WPSHProHa(double value) {
		this.zeitPersonal_WPSHProHa = value;
	}

	@Override
	public double getZeitaufwandPersonal_minProBaum() {
		return (zeitPersonal_WPSHProHa * 60d) / anzahlPflanzenProHektar;
	}

	@Override
	public double getZeitaufwandPersonal_minProHektar() {
		return (zeitPersonal_WPSHProHa * 60d);
	}

	@Override
	public double getZeitaufwandPersonal_minProBestand() {
		return (zeitPersonal_WPSHProHa * 60d) * flaecheBestand_ha;
	}

	
	
	public void setKostenPersonal_proHa(double value) {
		this.kostenPersonal_proHa = value;
	}
	
	@Override
	public double getKostenPersonal_proBaum() {
		return kostenPersonal_proHa / anzahlPflanzenProHektar;
	}

	@Override
	public double getKostenPersonal_proHektar() {
		return kostenPersonal_proHa;
	}

	@Override
	public double getKostenPersonal_proBestand() {
		return kostenPersonal_proHa * flaecheBestand_ha;
	}

	
	
	public void setKostenMaschinenGeraete_proHa(double value) {
		this.kostenMaschinenGeraete_proHa = value;
	}
	
	@Override
	public double getKostenMaschinenGeraete_proBaum() {
		return kostenMaschinenGeraete_proHa / anzahlPflanzenProHektar;
	}

	@Override
	public double getKostenMaschinenGeraete_proHektar() {
		return kostenMaschinenGeraete_proHa;
	}

	@Override
	public double getKostenMaschinenGeraete_proBestand() {
		return kostenMaschinenGeraete_proHa * flaecheBestand_ha;
	}
	
	
	
	public void setKostenGesamt_proHa(double value) {
		this.kostenGesamt_proHa = value;
	}

	@Override
	public double getKostenGesamt_proBaum() {
		return kostenGesamt_proHa / anzahlPflanzenProHektar;
	}

	@Override
	public double getKostenGesamt_proHektar() {
		return kostenGesamt_proHa;
	}

	@Override
	public double getKostenGesamt_proBestand() {
		return kostenGesamt_proHa * flaecheBestand_ha;
	}

}
