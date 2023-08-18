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
public class RueckegassenMockErgebnis extends AbstractRueckegassenErgebnis {

	private double zeitaufwandHauptperson_minProHektar;
	private double zeitaufwandHauptperson_minGesamtflaeche;
	
	private double zeitaufwandHilfskraft_minProHektar;
	private double zeitaufwandHilfskraft_minGesamtflaeche;
	
	
	private double kostenHauptperson_proHektar;
	private double kostenHauptperson_Gesamtflaeche;
	
	private double kostenHilfskraft_proHektar;
	private double kostenHilfskraft_Gesamtflaeche;
	
	private double kostenMaterial_proHektar;
	private double kostenMaterial_Gesamtflaeche;
	
	private double kostenGesamt_proHektar;
	private double kostenGesamt_Gesamtflaeche;

}
