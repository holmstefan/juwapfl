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
public class RueckegassenErgebnis extends AbstractRueckegassenErgebnis {

	private double flaeche_ha;
	private double zeitaufwandHauptperson_minProHektar;
	private double zeitaufwandHilfskraft_minProHektar;
	
	private double kostenHauptperson_proHektar;
	private double kostenHilfskraft_proHektar;
	private double kostenMaterial_proHektar;


	public void setFlaeche_ha(double flaeche_ha) {
		this.flaeche_ha = flaeche_ha;
	}

	public void setZeitaufwandHauptperson_minProHektar(double zeitaufwandHauptperson_minProHektar) {
		this.zeitaufwandHauptperson_minProHektar = zeitaufwandHauptperson_minProHektar;
	}

	public void setZeitaufwandHilfskraft_minProHektar(double zeitaufwandHilfskraft_minProHektar) {
		this.zeitaufwandHilfskraft_minProHektar = zeitaufwandHilfskraft_minProHektar;
	}

	public void setKostenHauptperson_proHektar(double kostenHauptperson_proHektar) {
		this.kostenHauptperson_proHektar = kostenHauptperson_proHektar;
	}

	public void setKostenHilfskraft_proHektar(double kostenHilfskraft_proHektar) {
		this.kostenHilfskraft_proHektar = kostenHilfskraft_proHektar;
	}

	public void setKostenMaterial_proHektar(double kostenMaterial_proHektar) {
		this.kostenMaterial_proHektar = kostenMaterial_proHektar;
	}
	

	@Override
	public double getZeitaufwandHauptperson_minProHektar() {
		return zeitaufwandHauptperson_minProHektar;
	}

	@Override
	public double getZeitaufwandHauptperson_minGesamtflaeche() {
		return getZeitaufwandHauptperson_minProHektar() * flaeche_ha;
	}

	@Override
	public double getZeitaufwandHilfskraft_minProHektar() {
		return zeitaufwandHilfskraft_minProHektar;
	}

	@Override
	public double getZeitaufwandHilfskraft_minGesamtflaeche() {
		return getZeitaufwandHilfskraft_minProHektar() * flaeche_ha;
	}

	@Override
	public double getKostenHauptperson_proHektar() {
		return kostenHauptperson_proHektar;
	}

	@Override
	public double getKostenHauptperson_Gesamtflaeche() {
		return getKostenHauptperson_proHektar() * flaeche_ha;
	}

	@Override
	public double getKostenHilfskraft_proHektar() {
		return kostenHilfskraft_proHektar;
	}

	@Override
	public double getKostenHilfskraft_Gesamtflaeche() {
		return getKostenHilfskraft_proHektar() * flaeche_ha;
	}

	@Override
	public double getKostenMaterial_proHektar() {
		return kostenMaterial_proHektar;
	}

	@Override
	public double getKostenMaterial_Gesamtflaeche() {
		return getKostenMaterial_proHektar() * flaeche_ha;
	}

	@Override
	public double getKostenGesamt_proHektar() {
		return getKostenHauptperson_proHektar() + getKostenHilfskraft_proHektar() + getKostenMaterial_proHektar();
	}

	@Override
	public double getKostenGesamt_Gesamtflaeche() {
		return getKostenGesamt_proHektar() * flaeche_ha;
	}

}
