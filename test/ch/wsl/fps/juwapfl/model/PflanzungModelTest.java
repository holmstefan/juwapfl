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

import ch.wsl.fps.juwapfl.model.PflanzungModel.Baumart;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Pflanztechnik;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Pflanzverfahren;
import ch.wsl.fps.juwapfl.model.PflanzungModel.Schwierigkeitsgrad;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Bodenvegetation;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Hangneigung;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Pflanzenhoehe;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Schlagabraum;
import ch.wsl.fps.juwapfl.model.PflanzungWinkelpflanzungModel.Transportdistanz;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungModelTest extends AbstractJuwapflModelTest {

	@Override
	protected String getCsvPath() {
		return TESTCASES_CSV_FOLDER + "Pflanzung.csv";
	}

	@Override
	protected PflanzungModel parseInputData(String[] testcase) {
		PflanzungModel result = new PflanzungModel();
		
		result.setAnzahlPflanzen(			Integer.valueOf(testcase[ 0]) );
		result.setPflanzverfahren(			Pflanzverfahren.valueOf(testcase[ 1]) );
		result.setPflanztechnik(			Pflanztechnik.valueOf(testcase[ 2]) );
		result.setBaumart(					Baumart.valueOf(testcase[ 3]) );
		result.setSchwierigkeitsgrad(		Schwierigkeitsgrad.valueOf(testcase[ 4]) );
		
		PflanzungWinkelpflanzungModel winkelpflModel = new PflanzungWinkelpflanzungModel();
		winkelpflModel.setBodenvegetation(	Bodenvegetation.valueOf(testcase[ 5]) );
		winkelpflModel.setSchlagabraum(		Schlagabraum.valueOf(testcase[ 6]) );
		winkelpflModel.setHangneigung(		Hangneigung.valueOf(testcase[ 7]) );
		winkelpflModel.setPflanzenhoehe(	Pflanzenhoehe.valueOf(testcase[ 8]) );
		winkelpflModel.setTransportdistanz(	Transportdistanz.valueOf(testcase[ 9]) );
		result.setWinkelpflanzungModel(winkelpflModel);

		result.setZeitBeschaffung_h(		Double.valueOf(testcase[10]) );
		result.setKostenProPflanze(			Double.valueOf(testcase[11]) );
		result.setZeitTransport_h(			Double.valueOf(testcase[12]) );
		result.setKostenTransportmittel(	Double.valueOf(testcase[13]) );
		result.setZeitPflanzung_PflProH(	Double.valueOf(testcase[14]) );
		result.setAnteilMaschinenlaufzeit_Prz(Double.valueOf(testcase[15]) );
		result.setKostensatzGeraet_proH(	Double.valueOf(testcase[16]) );
		result.setZeitUnterhalt_Prozent(	Double.valueOf(testcase[17]) );
		result.setKostenPersonalProPerson_proH(Integer.valueOf(testcase[18]) );
		result.setFaktorWegzeitenUndPausen(	Double.valueOf(testcase[19]) );

		return result;
	}

	@Override
	protected PflanzungMockErgebnis parseExpectedValues(String[] testcase) {
		double[] values = getDoubleValues(testcase, 20);
		PflanzungMockErgebnis result = new PflanzungMockErgebnis();
		
		int i=0;
		result.setZeitPersonalBeschaffung_min(values[i++]);
		result.setZeitPersonalTransport_min(values[i++]);
		result.setZeitPersonalPflanzung_min(values[i++]);
		result.setZeitPersonalUnterhalt_min(values[i++]);
		result.setZeitPersonalGesamt_min(values[i++]);
		
		result.setZeitGeraetePflanzung_min(values[i++]);
		result.setZeitGeraeteGesamt_min(values[i++]);
		
		result.setKostenPersonalBeschaffung(values[i++]);
		result.setKostenPersonalTransport(values[i++]);
		result.setKostenPersonalPflanzung(values[i++]);
		result.setKostenPersonalUnterhalt(values[i++]);
		result.setKostenPersonalGesamt(values[i++]);
		result.setKostenTransportmittelTransport(values[i++]);
		result.setKostenTransportmittelGesamt(values[i++]);
		result.setKostenPflanzenBeschaffung(values[i++]);
		result.setKostenGeraetePflanzung(values[i++]);
		result.setKostenGeraeteGesamt(values[i++]);
		
		result.setKostenGesamtBeschaffung(values[i++]);
		result.setKostenGesamtTransport(values[i++]);
		result.setKostenGesamtPflanzung(values[i++]);
		result.setKostenGesamtUnterhalt(values[i++]);
		result.setKostenGesamtGesamt(values[i++]);

		return result;
	}

	
	@Test(dataProvider="csvData")
	public void testZeitPersonalBeschaffung_min(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalBeschaffung_min(), expectedOutput.getZeitPersonalBeschaffung_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitPersonalTransport_min(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalTransport_min(), expectedOutput.getZeitPersonalTransport_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitPersonalPflanzung_min(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalPflanzung_min(), expectedOutput.getZeitPersonalPflanzung_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitPersonalUnterhalt_min(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalUnterhalt_min(), expectedOutput.getZeitPersonalUnterhalt_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitPersonalGesamt_min(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonalGesamt_min(), expectedOutput.getZeitPersonalGesamt_min());
	}

	
	@Test(dataProvider="csvData")
	public void testZeitGeraetePflanzung_min(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitGeraetePflanzung_min(), expectedOutput.getZeitGeraetePflanzung_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitGeraeteGesamt_min(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitGeraeteGesamt_min(), expectedOutput.getZeitGeraeteGesamt_min());
	}

	
	@Test(dataProvider="csvData")
	public void testKostenPersonalBeschaffung(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalBeschaffung(), expectedOutput.getKostenPersonalBeschaffung());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonalTransport(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalTransport(), expectedOutput.getKostenPersonalTransport());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonalPflanzung(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalPflanzung(), expectedOutput.getKostenPersonalPflanzung());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonalUnterhalt(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalUnterhalt(), expectedOutput.getKostenPersonalUnterhalt());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonalGesamt(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonalGesamt(), expectedOutput.getKostenPersonalGesamt());
	}

	@Test(dataProvider="csvData")
	public void testKostenTransportmittelTransport(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenTransportmittelTransport(), expectedOutput.getKostenTransportmittelTransport());
	}

	@Test(dataProvider="csvData")
	public void testKostenTransportmittelGesamt(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenTransportmittelGesamt(), expectedOutput.getKostenTransportmittelGesamt());
	}

	@Test(dataProvider="csvData")
	public void testKostenPflanzenBeschaffung(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPflanzenBeschaffung(), expectedOutput.getKostenPflanzenBeschaffung());
	}

	@Test(dataProvider="csvData")
	public void testKostenGeraetePflanzung(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGeraetePflanzung(), expectedOutput.getKostenGeraetePflanzung());
	}

	@Test(dataProvider="csvData")
	public void testKostenGeraeteGesamt(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGeraeteGesamt(), expectedOutput.getKostenGeraeteGesamt());
	}

	
	@Test(dataProvider="csvData")
	public void testKostenGesamtBeschaffung(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtBeschaffung(), expectedOutput.getKostenGesamtBeschaffung());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamtTransport(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtTransport(), expectedOutput.getKostenGesamtTransport());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamtPflanzung(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtPflanzung(), expectedOutput.getKostenGesamtPflanzung());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamtUnterhalt(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtUnterhalt(), expectedOutput.getKostenGesamtUnterhalt());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamtGesamt(@SuppressWarnings("unused") String testcaseName, PflanzungErgebnis actualOutput, PflanzungMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamtGesamt(), expectedOutput.getKostenGesamtGesamt());
	}
	
}
