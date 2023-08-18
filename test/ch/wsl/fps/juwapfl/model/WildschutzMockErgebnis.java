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
public class WildschutzMockErgebnis extends AbstractWildschutzErgebnis {

	private double zeitPersonalAufbau_min;
	private double zeitPersonalUnterhalt_min;
	private double zeitPersonalAbbau_min;
	private double zeitPersonalGesamt_min;

	private double kostenPersonalAufbau;
	private double kostenPersonalUnterhalt;
	private double kostenPersonalAbbau;
	private double kostenPersonalGesamt;

	private double kostenFahrtenAufbau;
	private double kostenFahrtenUnterhalt;
	private double kostenFahrtenAbbau;
	private double kostenFahrtenGesamt;

	private double kostenMaterialAufbau;
	private double kostenMaterialUnterhalt;
	private double kostenMaterialAbbau;
	private double kostenMaterialGesamt;

	private double kostenGesamtAufbau;
	private double kostenGesamtUnterhalt;
	private double kostenGesamtAbbau;
	private double kostenGesamtGesamt;

}
