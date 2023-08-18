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

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Stefan Holm
 *
 */
@Getter
@Setter
public class PflanzungMockErgebnis extends AbstractPflanzungErgebnis {
	
	private double zeitPersonalBeschaffung_min;
	private double zeitPersonalTransport_min;
	private double zeitPersonalPflanzung_min;
	private double zeitPersonalUnterhalt_min;
	private double zeitPersonalGesamt_min;
	
	private double zeitGeraetePflanzung_min;
	private double zeitGeraeteGesamt_min;
	
	private double kostenPersonalBeschaffung;
	private double kostenPersonalTransport;
	private double kostenPersonalPflanzung;
	private double kostenPersonalUnterhalt;
	private double kostenPersonalGesamt;
	private double kostenTransportmittelTransport;
	private double kostenTransportmittelGesamt;
	private double kostenPflanzenBeschaffung;
	private double kostenGeraetePflanzung;
	private double kostenGeraeteGesamt;
	
	private double kostenGesamtBeschaffung;
	private double kostenGesamtTransport;
	private double kostenGesamtPflanzung;
	private double kostenGesamtUnterhalt;
	private double kostenGesamtGesamt;
	
}
