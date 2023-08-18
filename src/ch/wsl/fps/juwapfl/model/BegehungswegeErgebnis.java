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
public class BegehungswegeErgebnis extends AbstractBegehungswegeErgebnis {
	
	private double zeitPersonal_min;
	private double zeitMaschine_min;
	
	private double kostenPersonal;
	private double kostenMaschine;
	
	
	@Override
	public double getZeitPersonal_min() {
		return zeitPersonal_min;
	}
	
	public void setZeitPersonal_min(double zeitPersonal_min) {
		this.zeitPersonal_min = zeitPersonal_min;
	}
	
	
	@Override
	public double getZeitMaschine_min() {
		return zeitMaschine_min;
	}
	
	public void setZeitMaschine_min(double zeitMaschine_min) {
		this.zeitMaschine_min = zeitMaschine_min;
	}
	
	
	@Override
	public double getKostenPersonal() {
		return kostenPersonal;
	}
	
	public void setKostenPersonal(double kostenPersonal) {
		this.kostenPersonal = kostenPersonal;
	}

	
	@Override
	public double getKostenMaschine() {
		return kostenMaschine;
	}
	
	public void setKostenMaschine(double kostenMaschine) {
		this.kostenMaschine = kostenMaschine;
	}
	
	
	@Override
	public double getKostenGesamt() {
		return kostenPersonal + kostenMaschine;
	}

}
