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

import ch.wsl.fps.juwapfl.model.WertastungModel.Astigkeit;
import ch.wsl.fps.juwapfl.model.WertastungModel.Baumart;
import ch.wsl.fps.juwapfl.model.WertastungModel.Etappe;
import ch.wsl.fps.juwapfl.model.WertastungModel.Hangneigung;
import ch.wsl.fps.juwapfl.model.WertastungModel.MethodeEtappe1;
import ch.wsl.fps.juwapfl.model.WertastungModel.MethodeEtappe2;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WertastungModelTest extends AbstractJuwapflModelTest {

	@Override
	protected String getCsvPath() {
		return TESTCASES_CSV_FOLDER + "Wertastung.csv";
	}

	@Override
	protected WertastungModel parseInputData(String[] testcase) {
		WertastungModel result = new WertastungModel();
		
		result.setBaumart(					Baumart.valueOf(testcase[ 0]) );
		result.setAnzahlAstungsbaeumeProHektar(Integer.valueOf(testcase[ 1]) );
		result.setFlaeche_ha(				Double.valueOf(testcase[ 2]) );
		result.setHangneigung(				Hangneigung.valueOf(testcase[ 3]) );
		result.setEtappe(					Etappe.valueOf(testcase[ 4]) );

		if (Etappe.valueOf(testcase[ 4]) == Etappe.Etappe1) {
			result.setMethode(		MethodeEtappe1.valueOf(testcase[ 5]) );
		}
		else {
			result.setMethode(		MethodeEtappe2.valueOf(testcase[ 5]) );
		}
		result.setHoehe1(			Integer.valueOf(testcase[ 6]) );
		result.setHoehe2(			Integer.valueOf(testcase[ 7]) );
		result.setAstigkeit(		Astigkeit.valueOf(testcase[ 8]) );
		
		result.setKostenPersonalProPerson(	Integer.valueOf(testcase[ 9]) );
		result.setFaktorWegzeitenUndPausen(	Double.valueOf(testcase[10]) );
		result.setKorrekturfaktorMaterialkosten(Double.valueOf(testcase[11]) );

		return result;
	}

	@Override
	protected WertastungMockErgebnis parseExpectedValues(String[] testcase) {
		double[] values = getDoubleValues(testcase, 12);
		WertastungMockErgebnis result = new WertastungMockErgebnis();
		
		int i=0;
		result.setAstungszeit_minProBaum(values[i++]);
		result.setAstungszeit_minProHektar(values[i++]);
		result.setAstungszeit_minProBestand(values[i++]);

		result.setGehzeit_minProBaum(values[i++]);
		result.setGehzeit_minProHektar(values[i++]);
		result.setGehzeit_minProBestand(values[i++]);

		result.setZeitaufwandGesamt_minProBaum(values[i++]);
		result.setZeitaufwandGesamt_minProHektar(values[i++]);
		result.setZeitaufwandGesamt_minProBestand(values[i++]);

		result.setKostenAstungszeit_proBaum(values[i++]);
		result.setKostenAstungszeit_proHektar(values[i++]);
		result.setKostenAstungszeit_proBestand(values[i++]);

		result.setKostenGehzeit_proBaum(values[i++]);
		result.setKostenGehzeit_proHektar(values[i++]);
		result.setKostenGehzeit_proBestand(values[i++]);

		result.setKostenMaterial_proBaum(values[i++]);
		result.setKostenMaterial_proHektar(values[i++]);
		result.setKostenMaterial_proBestand(values[i++]);

		result.setKostenGesamt_proBaum(values[i++]);
		result.setKostenGesamt_proHektar(values[i++]);
		result.setKostenGesamt_proBestand(values[i++]);

		return result;
	}


	@Test(dataProvider="csvData")
	public void testAstungszeit_minProBaum(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getAstungszeit_minProBaum(), expectedOutput.getAstungszeit_minProBaum());
	}

	@Test(dataProvider="csvData")
	public void testAstungszeit_minProHektar(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getAstungszeit_minProHektar(), expectedOutput.getAstungszeit_minProHektar());
	}

	@Test(dataProvider="csvData")
	public void testAstungszeit_minProBestand(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getAstungszeit_minProBestand(), expectedOutput.getAstungszeit_minProBestand());
	}


	@Test(dataProvider="csvData")
	public void testGehzeit_minProBaum(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getGehzeit_minProBaum(), expectedOutput.getGehzeit_minProBaum());
	}

	@Test(dataProvider="csvData")
	public void testGehzeit_minProHektar(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getGehzeit_minProHektar(), expectedOutput.getGehzeit_minProHektar());
	}

	@Test(dataProvider="csvData")
	public void testGehzeit_minProBestand(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getGehzeit_minProBestand(), expectedOutput.getGehzeit_minProBestand());
	}


	@Test(dataProvider="csvData")
	public void testZeitaufwandGesamt_minProBaum(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandGesamt_minProBaum(), expectedOutput.getZeitaufwandGesamt_minProBaum());
	}

	@Test(dataProvider="csvData")
	public void testZeitaufwandGesamt_minProHektar(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandGesamt_minProHektar(), expectedOutput.getZeitaufwandGesamt_minProHektar());
	}

	@Test(dataProvider="csvData")
	public void testZeitaufwandGesamt_minProBestand(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandGesamt_minProBestand(), expectedOutput.getZeitaufwandGesamt_minProBestand());
	}


	@Test(dataProvider="csvData")
	public void testKostenAstungszeit_proBaum(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenAstungszeit_proBaum(), expectedOutput.getKostenAstungszeit_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenAstungszeit_proHektar(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenAstungszeit_proHektar(), expectedOutput.getKostenAstungszeit_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenAstungszeit_proBestand(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenAstungszeit_proBestand(), expectedOutput.getKostenAstungszeit_proBestand());
	}


	@Test(dataProvider="csvData")
	public void testKostenGehzeit_proBaum(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGehzeit_proBaum(), expectedOutput.getKostenGehzeit_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenGehzeit_proHektar(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGehzeit_proHektar(), expectedOutput.getKostenGehzeit_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenGehzeit_proBestand(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGehzeit_proBestand(), expectedOutput.getKostenGehzeit_proBestand());
	}


	@Test(dataProvider="csvData")
	public void testKostenMaterial_proBaum(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterial_proBaum(), expectedOutput.getKostenMaterial_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaterial_proHektar(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterial_proHektar(), expectedOutput.getKostenMaterial_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaterial_proBestand(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterial_proBestand(), expectedOutput.getKostenMaterial_proBestand());
	}


	@Test(dataProvider="csvData")
	public void testKostenGesamt_proBaum(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proBaum(), expectedOutput.getKostenGesamt_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamt_proHektar(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proHektar(), expectedOutput.getKostenGesamt_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamt_proBestand(@SuppressWarnings("unused") String testcaseName, WertastungErgebnis actualOutput, WertastungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proBestand(), expectedOutput.getKostenGesamt_proBestand());
	}

}
