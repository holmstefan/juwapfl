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

import ch.wsl.fps.juwapfl.model.AustrichternModel.Hangneigung;
import ch.wsl.fps.juwapfl.model.AustrichternModel.MaschinenGeraete;
import ch.wsl.fps.juwapfl.model.AustrichternModel.RueckegassenVorhanden;
import ch.wsl.fps.juwapfl.model.AustrichternModel.Schutzsystem;
import ch.wsl.fps.juwapfl.model.AustrichternModel.Temperatur;
import ch.wsl.fps.juwapfl.model.AustrichternModel.Verunkrautung;

/**
 * 
 * @author Stefan Holm
 *
 */
public class AustrichternModelTest extends AbstractJuwapflModelTest {

	@Override
	protected String getCsvPath() {
		return TESTCASES_CSV_FOLDER + "Austrichtern.csv";
	}

	@Override
	protected AustrichternModel parseInputData(String[] testcase) {
		AustrichternModel result = new AustrichternModel();
		
		result.setFlaeche_ha(				Double.valueOf(testcase[ 0]) );
		result.setMaschinenGeraete(			MaschinenGeraete.valueOf(testcase[ 1]) );
		result.setAnzahlPflanzenProHa(		Integer.valueOf(testcase[ 2]) );
		result.setVerunkrautung(			Verunkrautung.valueOf(testcase[ 3]) );
		result.setHangneigung(				Hangneigung.valueOf(testcase[ 4]) );
		result.setRueckegassenVorhanden(	RueckegassenVorhanden.valueOf(testcase[ 5]) );
		result.setSchutzsystem(				Schutzsystem.valueOf(testcase[ 6]) );
		result.setTemperatur(				Temperatur.valueOf(testcase[ 7]) );
		result.setKostenPersonal_proH(		Double.valueOf(testcase[ 8]) );
		result.setKostenMotorsaege_proLiter(Double.valueOf(testcase[ 9]) );
		result.setKostenSichel_proHektar(	Double.valueOf(testcase[10]) );
		result.setFaktorWegzeitenUndPausen(	Double.valueOf(testcase[11]) );

		return result;
	}

	@Override
	protected AustrichternMockErgebnis parseExpectedValues(String[] testcase) {
		double[] values = getDoubleValues(testcase, 12);
		AustrichternMockErgebnis result = new AustrichternMockErgebnis();
		
		int i=0;
		result.setZeitaufwandPersonal_minProBaum(values[i++]);
		result.setZeitaufwandPersonal_minProHektar(values[i++]);
		result.setZeitaufwandPersonal_minProBestand(values[i++]);
		
		result.setKostenPersonal_proBaum(values[i++]);
		result.setKostenPersonal_proHektar(values[i++]);
		result.setKostenPersonal_proBestand(values[i++]);
		
		result.setKostenMaschinenGeraete_proBaum(values[i++]);
		result.setKostenMaschinenGeraete_proHektar(values[i++]);
		result.setKostenMaschinenGeraete_proBestand(values[i++]);
		
		result.setKostenGesamt_proBaum(values[i++]);
		result.setKostenGesamt_proHektar(values[i++]);
		result.setKostenGesamt_proBestand(values[i++]);

		return result;
	}
	
	
	@Test(dataProvider="csvData")
	public void testZeitaufwandPersonal_minProBaum(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandPersonal_minProBaum(), expectedOutput.getZeitaufwandPersonal_minProBaum());
	}

	@Test(dataProvider="csvData")
	public void testZeitaufwandPersonal_minProHektar(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandPersonal_minProHektar(), expectedOutput.getZeitaufwandPersonal_minProHektar());
	}

	@Test(dataProvider="csvData")
	public void testZeitaufwandPersonal_minProBestand(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandPersonal_minProBestand(), expectedOutput.getZeitaufwandPersonal_minProBestand());
	}

	
	@Test(dataProvider="csvData")
	public void testKostenPersonal_proBaum(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonal_proBaum(), expectedOutput.getKostenPersonal_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonal_proHektar(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonal_proHektar(), expectedOutput.getKostenPersonal_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonal_proBestand(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonal_proBestand(), expectedOutput.getKostenPersonal_proBestand());
	}

	
	@Test(dataProvider="csvData")
	public void testKostenMaschinenGeraete_proBaum(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaschinenGeraete_proBaum(), expectedOutput.getKostenMaschinenGeraete_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaschinenGeraete_proHektar(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaschinenGeraete_proHektar(), expectedOutput.getKostenMaschinenGeraete_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaschinenGeraete_proBestand(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaschinenGeraete_proBestand(), expectedOutput.getKostenMaschinenGeraete_proBestand());
	}

	
	@Test(dataProvider="csvData")
	public void testKostenGesamt_proBaum(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proBaum(), expectedOutput.getKostenGesamt_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamt_proHektar(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proHektar(), expectedOutput.getKostenGesamt_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamt_proBestand(@SuppressWarnings("unused") String testcaseName, AustrichternErgebnis actualOutput, AustrichternMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proBestand(), expectedOutput.getKostenGesamt_proBestand());
	}

}
