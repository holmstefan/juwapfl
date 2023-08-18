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
public class ZBaumDurchforstungErgebnis extends AbstractZBaumDurchforstungErgebnis {
	
	private int anzahlZBaeumeProHektar;
	private double flaecheBestand_ha;
	
	private double zeitPersonal_WPSHProHa;
	
	private double kostenPersonal_proHa;
	private double kostenMotorsaege_proHa;
	private double kostenMaterial_proHa;
	private double kostenGesamt_proHa;
	
	
	
	public void setAnzahlZBaeumeProHektar(int value) {
		this.anzahlZBaeumeProHektar = value;
	}

	public void setFlaecheBestand_ha(double value) {
		this.flaecheBestand_ha = value;
	}
	
	
	
	public void setZeitPersonal_WPSHProHa(double value) {
		this.zeitPersonal_WPSHProHa = value;
	}

	@Override
	public double getZeitaufwandPersonal_minProBaum() {
		return (zeitPersonal_WPSHProHa * 60d) / anzahlZBaeumeProHektar;
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
		return kostenPersonal_proHa / anzahlZBaeumeProHektar;
	}

	@Override
	public double getKostenPersonal_proHektar() {
		return kostenPersonal_proHa;
	}

	@Override
	public double getKostenPersonal_proBestand() {
		return kostenPersonal_proHa * flaecheBestand_ha;
	}

	
	
	public void setKostenMotorsaege_proHa(double value) {
		this.kostenMotorsaege_proHa = value;
	}
	
	@Override
	public double getKostenMotorsaege_proBaum() {
		return kostenMotorsaege_proHa / anzahlZBaeumeProHektar;
	}

	@Override
	public double getKostenMotorsaege_proHektar() {
		return kostenMotorsaege_proHa;
	}

	@Override
	public double getKostenMotorsaege_proBestand() {
		return kostenMotorsaege_proHa * flaecheBestand_ha;
	}
	
	
	
	public void setKostenMaterial_proHa(double value) {
		this.kostenMaterial_proHa = value;
	}

	@Override
	public double getKostenMaterial_proBaum() {
		return kostenMaterial_proHa / anzahlZBaeumeProHektar;
	}

	@Override
	public double getKostenMaterial_proHektar() {
		return kostenMaterial_proHa;
	}

	@Override
	public double getKostenMaterial_proBestand() {
		return kostenMaterial_proHa * flaecheBestand_ha;
	}
	
	
	
	public void setKostenGesamt_proHa(double value) {
		this.kostenGesamt_proHa = value;
	}

	@Override
	public double getKostenGesamt_proBaum() {
		return kostenGesamt_proHa / anzahlZBaeumeProHektar;
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
