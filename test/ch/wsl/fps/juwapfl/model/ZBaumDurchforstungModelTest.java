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

import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.AusfuehrungshoeheKronenschnitt;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Baendeln;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Belaubung;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Entwicklungsstufe;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Hangneigung;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.RueckegassenVorhanden;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Temperatur;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Verunkrautung;
import ch.wsl.fps.juwapfl.model.ZBaumDurchforstungModel.Zersaegen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ZBaumDurchforstungModelTest extends AbstractJuwapflModelTest {

	@Override
	protected String getCsvPath() {
		return TESTCASES_CSV_FOLDER + "ZBaumDurchforstung.csv";
	}

	@Override
	protected ZBaumDurchforstungModel parseInputData(String[] testcase) {
		ZBaumDurchforstungModel result = new ZBaumDurchforstungModel();
		
		result.setFlaeche_ha(			Double.valueOf(testcase[ 0]) );
		result.setEntwicklungsstufe(	Entwicklungsstufe.valueOf(testcase[ 1]) );
		result.setAnzahlZBaeumeProHa(	Integer.valueOf(testcase[ 2]) );
		result.setAnzahlKonkurrentenProZBaum(Double.valueOf(testcase[ 3]) );
		result.setBaendeln(				Baendeln.valueOf(testcase[ 4]) );
		result.setHangneigung(			Hangneigung.valueOf(testcase[ 5]) );
		result.setBelaubung(			Belaubung.valueOf(testcase[ 6]) );
		result.setVerunkrautung(		Verunkrautung.valueOf(testcase[ 7]) );
		result.setRueckegassenVorhanden(RueckegassenVorhanden.valueOf(testcase[ 8]) );
		result.setTemperatur(			Temperatur.valueOf(testcase[ 9]) );
		result.setZersaegen(			Zersaegen.valueOf(testcase[10]) );
		result.setAnzahlZBaeumeMitKronenschnittProHektare(Integer.valueOf(testcase[11]) );
		result.setAusfuehrungshoeheKronenschnitt(	AusfuehrungshoeheKronenschnitt.valueOf(testcase[12]) );
		result.setKostenPersonal_proH(				Double.valueOf(testcase[13]) );
		result.setKostenMotorsaege_proLiter(		Double.valueOf(testcase[14]) );
		result.setKostenKronenschnittUnten_proBaum(	Double.valueOf(testcase[15]) );
		result.setKostenKronenschnittOben_proBaum(	Double.valueOf(testcase[16]) );
		result.setFaktorWegzeitenUndPausen(			Double.valueOf(testcase[17]) );

		return result;
	}

	@Override
	protected ZBaumDurchforstungMockErgebnis parseExpectedValues(String[] testcase) {
		double[] values = getDoubleValues(testcase, 18);
		ZBaumDurchforstungMockErgebnis result = new ZBaumDurchforstungMockErgebnis();
		
		int i=0;
		result.setZeitaufwandPersonal_minProBaum(values[i++]);
		result.setZeitaufwandPersonal_minProHektar(values[i++]);
		result.setZeitaufwandPersonal_minProBestand(values[i++]);

		result.setKostenPersonal_proBaum(values[i++]);
		result.setKostenPersonal_proHektar(values[i++]);
		result.setKostenPersonal_proBestand(values[i++]);

		result.setKostenMotorsaege_proBaum(values[i++]);
		result.setKostenMotorsaege_proHektar(values[i++]);
		result.setKostenMotorsaege_proBestand(values[i++]);

		result.setKostenMaterial_proBaum(values[i++]);
		result.setKostenMaterial_proHektar(values[i++]);
		result.setKostenMaterial_proBestand(values[i++]);

		result.setKostenGesamt_proBaum(values[i++]);
		result.setKostenGesamt_proHektar(values[i++]);
		result.setKostenGesamt_proBestand(values[i++]);

		return result;
	}


	@Test(dataProvider="csvData")
	public void testZeitaufwandPersonal_minProBaum(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandPersonal_minProBaum(), expectedOutput.getZeitaufwandPersonal_minProBaum());
	}

	@Test(dataProvider="csvData")
	public void testZeitaufwandPersonal_minProHektar(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandPersonal_minProHektar(), expectedOutput.getZeitaufwandPersonal_minProHektar());
	}

	@Test(dataProvider="csvData")
	public void testZeitaufwandPersonal_minProBestand(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitaufwandPersonal_minProBestand(), expectedOutput.getZeitaufwandPersonal_minProBestand());
	}


	@Test(dataProvider="csvData")
	public void testKostenPersonal_proBaum(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonal_proBaum(), expectedOutput.getKostenPersonal_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonal_proHektar(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonal_proHektar(), expectedOutput.getKostenPersonal_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonal_proBestand(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonal_proBestand(), expectedOutput.getKostenPersonal_proBestand());
	}


	@Test(dataProvider="csvData")
	public void testKostenMotorsaege_proBaum(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMotorsaege_proBaum(), expectedOutput.getKostenMotorsaege_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenMotorsaege_proHektar(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMotorsaege_proHektar(), expectedOutput.getKostenMotorsaege_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenMotorsaege_proBestand(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMotorsaege_proBestand(), expectedOutput.getKostenMotorsaege_proBestand());
	}


	@Test(dataProvider="csvData")
	public void testKostenMaterial_proBaum(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterial_proBaum(), expectedOutput.getKostenMaterial_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaterial_proHektar(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterial_proHektar(), expectedOutput.getKostenMaterial_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaterial_proBestand(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaterial_proBestand(), expectedOutput.getKostenMaterial_proBestand());
	}


	@Test(dataProvider="csvData")
	public void testKostenGesamt_proBaum(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proBaum(), expectedOutput.getKostenGesamt_proBaum());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamt_proHektar(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proHektar(), expectedOutput.getKostenGesamt_proHektar());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamt_proBestand(@SuppressWarnings("unused") String testcaseName, ZBaumDurchforstungErgebnis actualOutput, ZBaumDurchforstungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt_proBestand(), expectedOutput.getKostenGesamt_proBestand());
	}
	
}
