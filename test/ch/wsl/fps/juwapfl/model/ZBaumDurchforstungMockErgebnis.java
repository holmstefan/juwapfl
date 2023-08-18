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
public class ZBaumDurchforstungMockErgebnis extends AbstractZBaumDurchforstungErgebnis {

	private double zeitaufwandPersonal_minProBaum;
	private double zeitaufwandPersonal_minProHektar;
	private double zeitaufwandPersonal_minProBestand;

	private double kostenPersonal_proBaum;
	private double kostenPersonal_proHektar;
	private double kostenPersonal_proBestand;

	private double kostenMotorsaege_proBaum;
	private double kostenMotorsaege_proHektar;
	private double kostenMotorsaege_proBestand;

	private double kostenMaterial_proBaum;
	private double kostenMaterial_proHektar;
	private double kostenMaterial_proBestand;

	private double kostenGesamt_proBaum;
	private double kostenGesamt_proHektar;
	private double kostenGesamt_proBestand;

}
