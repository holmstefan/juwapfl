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

import ch.wsl.fps.juwapfl.model.RueckegassenModel.Hilfskraft;
import ch.wsl.fps.juwapfl.model.RueckegassenModel.Ortskenntnisse;
import ch.wsl.fps.juwapfl.model.RueckegassenModel.UebungPlanungsverfahren;
import ch.wsl.fps.juwapfl.model.RueckegassenModel.Verhaeltnisse;

/**
 * 
 * @author Stefan Holm
 *
 */
public class RueckegassenModelTest extends AbstractJuwapflModelTest {

	@Override
	protected String getCsvPath() {
		return TESTCASES_CSV_FOLDER + "Rueckegassen.csv";
	}

	@Override
	protected RueckegassenModel parseInputData(String[] testcase) {
		RueckegassenModel result = new RueckegassenModel();
		
		result.setFlaeche_ha(				Double.valueOf(testcase[ 0]) );
		result.setVerhaeltnisse(			Verhaeltnisse.valueOf(testcase[ 1]) );
		result.setAbstandRueckegassen_m(	Integer.valueOf(testcase[ 2]));
		result.setHilfskraft(				Hilfskraft.valueOf(testcase[ 3]) );
		result.setOrtskenntnisse(			Ortskenntnisse.valueOf(testcase[ 4]) );
		result.setUebungPlanungsverfahren(	UebungPlanungsverfahren.valueOf(testcase[ 5]) );
		result.setKostenHauptperson_proH(	Double.valueOf(testcase[ 6]) );
		result.setKostenHilfskraft_proH(	Double.valueOf(testcase[ 7]) );
		result.setFaktorWegzeitenUndPausen(	Double.valueOf(testcase[ 8]) );
		result.setKostenGeraete_proH(		Double.valueOf(testcase[ 9]) );

		return result;
	}

	@Override
	protected RueckegassenMockErgebnis parseExpectedValues(String[] testcase) {
		double[] values = getDoubleValues(testcase, 10);
		RueckegassenMockErgebnis result = new RueckegassenMockErgebnis();
		
		int i=0;
		result.setZeitaufwandHauptperson_minProHektar(values[i++] * 60);
		result.setZeitaufwandHauptperson_minGesamtflaeche(values[i++] * 60);
		result.setZeitaufwandHilfskraft_minProHektar(values[i++] * 60);
		result.setZeitaufwandHilfskraft_minGesamtflaeche(values[i++] * 60);
		
		result.setKostenHauptperson_proHektar(values[i++]);
		result.setKostenHauptperson_Gesamtflaeche(values[i++]);
		result.setKostenHilfskraft_proHektar(values[i++]);
		result.setKostenHilfskraft_Gesamtflaeche(values[i++]);
		result.setKostenMaterial_proHektar(values[i++]);
		result.setKostenMaterial_Gesamtflaeche(values[i++]);
		result.setKostenGesamt_proHektar(values[i++]);
		result.setKostenGesamt_Gesamtflaeche(values[i++]);

		return result;
	}

	
	@Test(dataProvider="csvData")
	public void testZeitaufwandHauptperson_minProHektar(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandHauptperson_minProHektar(), expectedOutput.getZeitaufwandHauptperson_minProHektar());
	}
	
	@Test(dataProvider="csvData")
	public void testZeitaufwandHauptperson_minGesamtflaeche(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandHauptperson_minGesamtflaeche(), expectedOutput.getZeitaufwandHauptperson_minGesamtflaeche());
	}
	
	@Test(dataProvider="csvData")
	public void testZeitaufwandHilfskraft_minProHektar(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandHilfskraft_minProHektar(), expectedOutput.getZeitaufwandHilfskraft_minProHektar());
	}
	
	@Test(dataProvider="csvData")
	public void testZeitaufwandHilfskraft_minGesamtflaeche(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandHilfskraft_minGesamtflaeche(), expectedOutput.getZeitaufwandHilfskraft_minGesamtflaeche());
	}

	
	@Test(dataProvider="csvData")
	public void testKostenHauptperson_proHektar(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getKostenHauptperson_proHektar(), expectedOutput.getKostenHauptperson_proHektar());
	}
	
	@Test(dataProvider="csvData")
	public void testKostenHauptperson_Gesamtflaeche(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getKostenHauptperson_Gesamtflaeche(), expectedOutput.getKostenHauptperson_Gesamtflaeche());
	}
	
	@Test(dataProvider="csvData")
	public void testKostenHilfskraft_proHektar(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getKostenHilfskraft_proHektar(), expectedOutput.getKostenHilfskraft_proHektar());
	}
	
	@Test(dataProvider="csvData")
	public void testKostenHilfskraft_Gesamtflaeche(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getKostenHilfskraft_Gesamtflaeche(), expectedOutput.getKostenHilfskraft_Gesamtflaeche());
	}
	
	@Test(dataProvider="csvData")
	public void testKostenMaterial_proHektar(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getKostenMaterial_proHektar(), expectedOutput.getKostenMaterial_proHektar());
	}
	
	@Test(dataProvider="csvData")
	public void testKostenMaterial_Gesamtflaeche(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getKostenMaterial_Gesamtflaeche(), expectedOutput.getKostenMaterial_Gesamtflaeche());
	}
	
	@Test(dataProvider="csvData")
	public void testKostenGesamt_proHektar(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getKostenGesamt_proHektar(), expectedOutput.getKostenGesamt_proHektar());
	}
	
	@Test(dataProvider="csvData")
	public void testKostenGesamt_Gesamtflaeche(@SuppressWarnings("unused") String testcaseName, RueckegassenErgebnis actualOutput, RueckegassenMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getKostenGesamt_Gesamtflaeche(), expectedOutput.getKostenGesamt_Gesamtflaeche());
	}

}
