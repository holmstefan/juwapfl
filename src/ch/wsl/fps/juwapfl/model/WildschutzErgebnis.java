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
public class WildschutzErgebnis extends AbstractWildschutzErgebnis {
	
	private double zeitPersonalAufbau_min;
	private double zeitPersonalUnterhalt_min;
	private double zeitPersonalAbbau_min;

	private double kostenPersonalAufbau;
	private double kostenPersonalUnterhalt;
	private double kostenPersonalAbbau;

	private double kostenFahrtenAufbau;
	private double kostenFahrtenUnterhalt;
	private double kostenFahrtenAbbau;

	private double kostenMaterialAufbau;
	private double kostenMaterialUnterhalt;
	private double kostenMaterialAbbau;
	
	
	@Override
	public double getZeitPersonalAufbau_min() {
		return zeitPersonalAufbau_min;
	}
	
	public void setZeitPersonalAufbau_min(double zeitPersonalAufbau_min) {
		this.zeitPersonalAufbau_min = zeitPersonalAufbau_min;
	}
	
	@Override
	public double getZeitPersonalUnterhalt_min() {
		return zeitPersonalUnterhalt_min;
	}
	
	public void setZeitPersonalUnterhalt_min(double zeitPersonalUnterhalt_min) {
		this.zeitPersonalUnterhalt_min = zeitPersonalUnterhalt_min;
	}
	
	@Override
	public double getZeitPersonalAbbau_min() {
		return zeitPersonalAbbau_min;
	}
	
	public void setZeitPersonalAbbau_min(double zeitPersonalAbbau_min) {
		this.zeitPersonalAbbau_min = zeitPersonalAbbau_min;
	}
	
	@Override
	public double getZeitPersonalGesamt_min() {
		return zeitPersonalAufbau_min + zeitPersonalUnterhalt_min + zeitPersonalAbbau_min;
	}

	
	
	@Override
	public double getKostenPersonalAufbau() {
		return kostenPersonalAufbau;
	}
	
	public void setKostenPersonalAufbau(double kostenPersonalAufbau) {
		this.kostenPersonalAufbau = kostenPersonalAufbau;
	}
	
	@Override
	public double getKostenPersonalUnterhalt() {
		return kostenPersonalUnterhalt;
	}
	
	public void setKostenPersonalUnterhalt(double kostenPersonalUnterhalt) {
		this.kostenPersonalUnterhalt = kostenPersonalUnterhalt;
	}
	
	@Override
	public double getKostenPersonalAbbau() {
		return kostenPersonalAbbau;
	}
	
	public void setKostenPersonalAbbau(double kostenPersonalAbbau) {
		this.kostenPersonalAbbau = kostenPersonalAbbau;
	}
	
	@Override
	public double getKostenPersonalGesamt() {
		return kostenPersonalAufbau + kostenPersonalUnterhalt + kostenPersonalAbbau;
	}


	
	@Override
	public double getKostenFahrtenAufbau() {
		return kostenFahrtenAufbau;
	}
	
	public void setKostenFahrtenAufbau(double kostenFahrtenAufbau) {
		this.kostenFahrtenAufbau = kostenFahrtenAufbau;
	}
	
	@Override
	public double getKostenFahrtenUnterhalt() {
		return kostenFahrtenUnterhalt;
	}
	
	public void setKostenFahrtenUnterhalt(double kostenFahrtenUnterhalt) {
		this.kostenFahrtenUnterhalt = kostenFahrtenUnterhalt;
	}
	
	@Override
	public double getKostenFahrtenAbbau() {
		return kostenFahrtenAbbau;
	}
	
	public void setKostenFahrtenAbbau(double kostenFahrtenAbbau) {
		this.kostenFahrtenAbbau = kostenFahrtenAbbau;
	}
	
	@Override
	public double getKostenFahrtenGesamt() {
		return kostenFahrtenAufbau + kostenFahrtenUnterhalt + kostenFahrtenAbbau;
	}


	
	@Override
	public double getKostenMaterialAufbau() {
		return kostenMaterialAufbau;
	}
	
	public void setKostenMaterialAufbau(double kostenMaterialAufbau) {
		this.kostenMaterialAufbau = kostenMaterialAufbau;
	}
	
	@Override
	public double getKostenMaterialUnterhalt() {
		return kostenMaterialUnterhalt;
	}
	
	public void setKostenMaterialUnterhalt(double kostenMaterialUnterhalt) {
		this.kostenMaterialUnterhalt = kostenMaterialUnterhalt;
	}
	
	@Override
	public double getKostenMaterialAbbau() {
		return kostenMaterialAbbau;
	}
	
	public void setKostenMaterialAbbau(double kostenMaterialAbbau) {
		this.kostenMaterialAbbau = kostenMaterialAbbau;
	}
	
	@Override
	public double getKostenMaterialGesamt() {
		return kostenMaterialAufbau + kostenMaterialUnterhalt + kostenMaterialAbbau;
	}


	
	@Override
	public double getKostenGesamtAufbau() {
		return kostenPersonalAufbau + kostenFahrtenAufbau + kostenMaterialAufbau;
	}
	
	@Override
	public double getKostenGesamtUnterhalt() {
		return kostenPersonalUnterhalt + kostenFahrtenUnterhalt + kostenMaterialUnterhalt;
	}
	
	@Override
	public double getKostenGesamtAbbau() {
		return kostenPersonalAbbau + kostenFahrtenAbbau + kostenMaterialAbbau;
	}
	
	@Override
	public double getKostenGesamtGesamt() {
		return getKostenGesamtAufbau() + getKostenGesamtUnterhalt() + getKostenGesamtAbbau();
	}

}
