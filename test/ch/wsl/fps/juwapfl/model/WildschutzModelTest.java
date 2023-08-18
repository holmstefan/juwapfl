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

import org.testng.annotations.Test;

import ch.wsl.fps.juwapfl.model.WildschutzModel.Schutztyp;
import ch.wsl.fps.juwapfl.model.WildschutzModel.Subtyp;
import ch.wsl.fps.juwapfl.model.WildschutzModel.Wuchshuellentyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WildschutzModelTest extends AbstractJuwapflModelTest {

	@Override
	protected String getCsvPath() {
		return TESTCASES_CSV_FOLDER + "Wildschutz.csv";
	}

	@Override
	protected WildschutzModel parseInputData(String[] testcase) {
		WildschutzModel result = new WildschutzModel();
		
		result.setSchutztyp(					Schutztyp.valueOf(testcase[ 0]) );
		result.setSubtyp(						Subtyp.valueOf(testcase[ 1]) );
		result.setWuchshuellentyp(				Wuchshuellentyp.valueOf(testcase[ 2]) );
		result.setAnzahlPflanzen(				Integer.valueOf(testcase[ 3]) );
		result.setZaunlaenge_m(					Integer.valueOf(testcase[ 4]) );
		result.setKostenPersonalProPerson(			Integer.valueOf(testcase[ 5]) );
		result.setFaktorWegzeitenUndPausen(			Double.valueOf(testcase[ 6]) );
		result.setFahrzeugkostenAufbauPauschal(		Double.valueOf(testcase[ 7]) );
		result.setFahrzeugkostenUnterhaltPauschal(	Double.valueOf(testcase[ 8]) );
		result.setFahrzeugkostenAbbauPauschal(		Double.valueOf(testcase[ 9]) );
		result.setKorrekturfaktorZeitaufwand(		Double.valueOf(testcase[10]) );
		result.setKorrekturfaktorMaterialkosten(	Double.valueOf(testcase[11]) );

		return result;
	}

	@Override
	protected WildschutzMockErgebnis parseExpectedValues(String[] testcase) {
		double[] values = getDoubleValues(testcase, 12);
		WildschutzMockErgebnis result = new WildschutzMockErgebnis();
		
		int i=0;
		result.setZeitPersonalAufbau_min(values[i++]);
		result.setZeitPersonalUnterhalt_min(values[i++]);
		result.setZeitPersonalAbbau_min(values[i++]);
		result.setZeitPersonalGesamt_min(values[i++]);

		result.setKostenPersonalAufbau(values[i++]);
		result.setKostenPersonalUnterhalt(values[i++]);
		result.setKostenPersonalAbbau(values[i++]);
		result.setKostenPersonalGesamt(values[i++]);

		result.setKostenFahrtenAufbau(values[i++]);
		result.setKostenFahrtenUnterhalt(values[i++]);
		result.setKostenFahrtenAbbau(values[i++]);
		result.setKostenFahrtenGesamt(values[i++]);

		result.setKostenMaterialAufbau(values[i++]);
		result.setKostenMaterialUnterhalt(values[i++]);
		result.setKostenMaterialAbbau(values[i++]);
		result.setKostenMaterialGesamt(values[i++]);

		result.setKostenGesamtAufbau(values[i++]);
		result.setKostenGesamtUnterhalt(values[i++]);
		result.setKostenGesamtAbbau(values[i++]);
		result.setKostenGesamtGesamt(values[i++]);

		return result;
	}
	

	@Test(dataProvider="csvData")
	public void testZeitPersonalAufbau_min(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalAufbau_min(), expectedOutput.getZeitPersonalAufbau_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitPersonalUnterhalt_min(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalUnterhalt_min(), expectedOutput.getZeitPersonalUnterhalt_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitPersonalAbbau_min(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalAbbau_min(), expectedOutput.getZeitPersonalAbbau_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitPersonalGesamt_min(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalGesamt_min(), expectedOutput.getZeitPersonalGesamt_min());
	}


	@Test(dataProvider="csvData")
	public void testKostenPersonalAufbau(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalAufbau(), expectedOutput.getKostenPersonalAufbau());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonalUnterhalt(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalUnterhalt(), expectedOutput.getKostenPersonalUnterhalt());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonalAbbau(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalAbbau(), expectedOutput.getKostenPersonalAbbau());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonalGesamt(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalGesamt(), expectedOutput.getKostenPersonalGesamt());
	}


	@Test(dataProvider="csvData")
	public void testKostenFahrtenAufbau(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenFahrtenAufbau(), expectedOutput.getKostenFahrtenAufbau());
	}

	@Test(dataProvider="csvData")
	public void testKostenFahrtenUnterhalt(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenFahrtenUnterhalt(), expectedOutput.getKostenFahrtenUnterhalt());
	}

	@Test(dataProvider="csvData")
	public void testKostenFahrtenAbbau(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenFahrtenAbbau(), expectedOutput.getKostenFahrtenAbbau());
	}

	@Test(dataProvider="csvData")
	public void testKostenFahrtenGesamt(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenFahrtenGesamt(), expectedOutput.getKostenFahrtenGesamt());
	}


	@Test(dataProvider="csvData")
	public void testKostenMaterialAufbau(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterialAufbau(), expectedOutput.getKostenMaterialAufbau());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaterialUnterhalt(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterialUnterhalt(), expectedOutput.getKostenMaterialUnterhalt());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaterialAbbau(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterialAbbau(), expectedOutput.getKostenMaterialAbbau());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaterialGesamt(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterialGesamt(), expectedOutput.getKostenMaterialGesamt());
	}


	@Test(dataProvider="csvData")
	public void testKostenGesamtAufbau(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtAufbau(), expectedOutput.getKostenGesamtAufbau());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamtUnterhalt(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtUnterhalt(), expectedOutput.getKostenGesamtUnterhalt());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamtAbbau(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtAbbau(), expectedOutput.getKostenGesamtAbbau());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamtGesamt(@SuppressWarnings("unused") String testcaseName, WildschutzErgebnis actualOutput, WildschutzMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtGesamt(), expectedOutput.getKostenGesamtGesamt());
	}

}
