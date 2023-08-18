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
public class WertastungErgebnis extends AbstractWertastungErgebnis {
	
	private int anzahlAstungsbaeumeProHektar = 30;
	private double flaecheBestand_ha = 1;

	private double astungszeit_minProBaum;
	private double gehzeit_minProBaum;
	private double zeitaufwandGesamt_minProBaum;

	private double kostenAstungszeit_proBaum;
	private double kostenGehzeit_proBaum;
	private double kostenMaterial_proBaum;
	private double kostenGesamt_proBaum;
	
	
	
	public void setAnzahlAstungsbaeumeProHektar(int anzahlAstungsbaeumeProHektar) {
		this.anzahlAstungsbaeumeProHektar = anzahlAstungsbaeumeProHektar;
	}

	public void setFlaecheBestand_ha(double flaecheBestand_ha) {
		this.flaecheBestand_ha = flaecheBestand_ha;
	}

	
	
	public void setAstungszeit_minProBaum(double astungszeit_minProBaum) {
		this.astungszeit_minProBaum = astungszeit_minProBaum;
	}
	
	@Override
	public double getAstungszeit_minProBaum() {
		return astungszeit_minProBaum;
	}
	
	@Override
	public double getAstungszeit_minProHektar() {
		return astungszeit_minProBaum * anzahlAstungsbaeumeProHektar;
	}
	
	@Override
	public double getAstungszeit_minProBestand() {
		return astungszeit_minProBaum * anzahlAstungsbaeumeProHektar * flaecheBestand_ha;
	}

	
	
	public void setGehzeit_minProBaum(double gehzeit_minProBaum) {
		this.gehzeit_minProBaum = gehzeit_minProBaum;
	}
	
	@Override
	public double getGehzeit_minProBaum() {
		return gehzeit_minProBaum;
	}
	
	@Override
	public double getGehzeit_minProHektar() {
		return gehzeit_minProBaum * anzahlAstungsbaeumeProHektar;
	}
	
	@Override
	public double getGehzeit_minProBestand() {
		return gehzeit_minProBaum * anzahlAstungsbaeumeProHektar * flaecheBestand_ha;
	}

	
	
	public void setZeitaufwandGesamt_minProBaum(double zeitaufwandGesamt_minProBaum) {
		this.zeitaufwandGesamt_minProBaum = zeitaufwandGesamt_minProBaum;
	}
	
	@Override
	public double getZeitaufwandGesamt_minProBaum() {
		return zeitaufwandGesamt_minProBaum;
	}
	
	@Override
	public double getZeitaufwandGesamt_minProHektar() {
		return zeitaufwandGesamt_minProBaum * anzahlAstungsbaeumeProHektar;
	}
	
	@Override
	public double getZeitaufwandGesamt_minProBestand() {
		return zeitaufwandGesamt_minProBaum * anzahlAstungsbaeumeProHektar * flaecheBestand_ha;
	}
	
	
	
	public void setKostenAstungszeit_proBaum(double kostenAstungszeit_proBaum) {
		this.kostenAstungszeit_proBaum = kostenAstungszeit_proBaum;
	}
	
	@Override
	public double getKostenAstungszeit_proBaum() {
		return kostenAstungszeit_proBaum;
	}
	
	@Override
	public double getKostenAstungszeit_proHektar() {
		return kostenAstungszeit_proBaum * anzahlAstungsbaeumeProHektar;
	}
	
	@Override
	public double getKostenAstungszeit_proBestand() {
		return kostenAstungszeit_proBaum * anzahlAstungsbaeumeProHektar * flaecheBestand_ha;
	}
	
	
	
	public void setKostenGehzeit_proBaum(double kostenGehzeit_proBaum) {
		this.kostenGehzeit_proBaum = kostenGehzeit_proBaum;
	}
	
	@Override
	public double getKostenGehzeit_proBaum() {
		return kostenGehzeit_proBaum;
	}
	
	@Override
	public double getKostenGehzeit_proHektar() {
		return kostenGehzeit_proBaum * anzahlAstungsbaeumeProHektar;
	}
	
	@Override
	public double getKostenGehzeit_proBestand() {
		return kostenGehzeit_proBaum * anzahlAstungsbaeumeProHektar * flaecheBestand_ha;
	}
	
	
	
	public void setKostenMaterial_proBaum(double kostenMaterial_proBaum) {
		this.kostenMaterial_proBaum = kostenMaterial_proBaum;
	}
	
	@Override
	public double getKostenMaterial_proBaum() {
		return kostenMaterial_proBaum;
	}
	
	@Override
	public double getKostenMaterial_proHektar() {
		return kostenMaterial_proBaum * anzahlAstungsbaeumeProHektar;
	}
	
	@Override
	public double getKostenMaterial_proBestand() {
		return kostenMaterial_proBaum * anzahlAstungsbaeumeProHektar * flaecheBestand_ha;
	}
	
	
	
	public void setKostenGesamt_proBaum(double kostenGesamt_proBaum) {
		this.kostenGesamt_proBaum = kostenGesamt_proBaum;
	}
	
	@Override
	public double getKostenGesamt_proBaum() {
		return kostenGesamt_proBaum;
	}
	
	@Override
	public double getKostenGesamt_proHektar() {
		return kostenGesamt_proBaum * anzahlAstungsbaeumeProHektar;
	}
	
	@Override
	public double getKostenGesamt_proBestand() {
		return kostenGesamt_proBaum * anzahlAstungsbaeumeProHektar * flaecheBestand_ha;
	}

	
}
