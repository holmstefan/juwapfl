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
public class PflanzungErgebnis extends AbstractPflanzungErgebnis {
	
	private double zeitPersonalBeschaffung_min;
	private double zeitPersonalTransport_min;
	private double zeitPersonalPflanzung_min;
	private double zeitPersonalUnterhalt_min;

	private double zeitGeraetePflanzung_min;
	
	
	private double kostenPersonalBeschaffung;
	private double kostenPersonalTransport;
	private double kostenPersonalPflanzung;
	private double kostenPersonalUnterhalt;

	private double kostenTransportmittelTransport;

	private double kostenPflanzenBeschaffung;
	private double kostenGeraetePflanzung;

	
	@Override
	public double getZeitPersonalBeschaffung_min() {
		return zeitPersonalBeschaffung_min;
	}

	public void setZeitPersonalBeschaffung_min(double zeitPersonalBeschaffung_min) {
		this.zeitPersonalBeschaffung_min = zeitPersonalBeschaffung_min;
	}

	@Override
	public double getZeitPersonalTransport_min() {
		return zeitPersonalTransport_min;
	}

	public void setZeitPersonalTransport_min(double zeitPersonalTransport_min) {
		this.zeitPersonalTransport_min = zeitPersonalTransport_min;
	}

	@Override
	public double getZeitPersonalPflanzung_min() {
		return zeitPersonalPflanzung_min;
	}

	public void setZeitPersonalPflanzung_min(double zeitPersonalPflanzung_min) {
		this.zeitPersonalPflanzung_min = zeitPersonalPflanzung_min;
	}

	@Override
	public double getZeitPersonalUnterhalt_min() {
		return zeitPersonalUnterhalt_min;
	}

	public void setZeitPersonalUnterhalt_min(double zeitPersonalUnterhalt_min) {
		this.zeitPersonalUnterhalt_min = zeitPersonalUnterhalt_min;
	}

	@Override
	public double getZeitPersonalGesamt_min() {
		return getZeitPersonalBeschaffung_min() + getZeitPersonalTransport_min() + getZeitPersonalPflanzung_min() + getZeitPersonalUnterhalt_min();
	}

	
	@Override
	public double getZeitGeraetePflanzung_min() {
		return zeitGeraetePflanzung_min;
	}

	public void setZeitGeraetePflanzung_min(double zeitGeraetePflanzung_min) {
		this.zeitGeraetePflanzung_min = zeitGeraetePflanzung_min;
	}

	
	@Override
	public double getZeitGeraeteGesamt_min() {
		return getZeitGeraetePflanzung_min();
	}

	
	@Override
	public double getKostenPersonalBeschaffung() {
		return kostenPersonalBeschaffung;
	}

	public void setKostenPersonalBeschaffung(double kostenPersonalBeschaffung) {
		this.kostenPersonalBeschaffung = kostenPersonalBeschaffung;
	}

	@Override
	public double getKostenPersonalTransport() {
		return kostenPersonalTransport;
	}

	public void setKostenPersonalTransport(double kostenPersonalTransport) {
		this.kostenPersonalTransport = kostenPersonalTransport;
	}

	@Override
	public double getKostenPersonalPflanzung() {
		return kostenPersonalPflanzung;
	}

	public void setKostenPersonalPflanzung(double kostenPersonalPflanzung) {
		this.kostenPersonalPflanzung = kostenPersonalPflanzung;
	}

	@Override
	public double getKostenPersonalUnterhalt() {
		return kostenPersonalUnterhalt;
	}

	public void setKostenPersonalUnterhalt(double kostenPersonalUnterhalt) {
		this.kostenPersonalUnterhalt = kostenPersonalUnterhalt;
	}

	@Override
	public double getKostenPersonalGesamt() {
		return getKostenPersonalBeschaffung() + getKostenPersonalTransport() + getKostenPersonalPflanzung() + getKostenPersonalUnterhalt();
	}

	@Override
	public double getKostenTransportmittelTransport() {
		return kostenTransportmittelTransport;
	}

	public void setKostenTransportmittelTransport(double kostenTransportmittelTransport) {
		this.kostenTransportmittelTransport = kostenTransportmittelTransport;
	}

	@Override
	public double getKostenTransportmittelGesamt() {
		return getKostenTransportmittelTransport();
	}

	@Override
	public double getKostenPflanzenBeschaffung() {
		return kostenPflanzenBeschaffung;
	}

	public void setKostenPflanzenBeschaffung(double kostenPflanzenBeschaffung) {
		this.kostenPflanzenBeschaffung = kostenPflanzenBeschaffung;
	}

	@Override
	public double getKostenGeraetePflanzung() {
		return kostenGeraetePflanzung;
	}

	public void setKostenGeraetePflanzung(double kostenGeraetePflanzung) {
		this.kostenGeraetePflanzung = kostenGeraetePflanzung;
	}

	@Override
	public double getKostenGeraeteGesamt() {
		return getKostenPflanzenBeschaffung() + getKostenGeraetePflanzung();
	}

	
	@Override
	public double getKostenGesamtBeschaffung() {
		return getKostenPersonalBeschaffung() + getKostenPflanzenBeschaffung();
	}

	@Override
	public double getKostenGesamtTransport() {
		return getKostenPersonalTransport() + getKostenTransportmittelTransport();
	}

	@Override
	public double getKostenGesamtPflanzung() {
		return getKostenPersonalPflanzung() + getKostenGeraetePflanzung();
	}

	@Override
	public double getKostenGesamtUnterhalt() {
		return getKostenPersonalUnterhalt();
	}

	@Override
	public double getKostenGesamtGesamt() {
		return getKostenPersonalGesamt() + getKostenTransportmittelGesamt() + getKostenGeraeteGesamt();
	}

}
