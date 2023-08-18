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

import ch.wsl.fps.juwapfl.model.BegehungswegeModel.Ausfuehrung;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel.Begehungsweg;
import ch.wsl.fps.juwapfl.model.BegehungswegeModel.GelaendeSchwierigkeit;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BegehungswegeModelTest extends AbstractJuwapflModelTest {

	@Override
	protected String getCsvPath() {
		return TESTCASES_CSV_FOLDER + "Begehungswege.csv";
	}

	@Override
	protected BegehungswegeModel parseInputData(String[] testcase) {
		BegehungswegeModel result = new BegehungswegeModel();
		
		result.setBegehungsweg(				Begehungsweg.valueOf(testcase[ 0]) );
		result.setAusfuehrung(				Ausfuehrung.valueOf(testcase[ 1]) );
		result.setAnzahlLaufmeter(			Integer.valueOf(testcase[ 2]));
		result.setGelaendeSchwierigkeit(	GelaendeSchwierigkeit.valueOf(testcase[ 3]) );
		result.setZeitaufwand_minProM(		Double.valueOf(testcase[ 4]) );
		result.setKostenPersonal(			Integer.valueOf(testcase[ 5]) );
		result.setKostenMaschine(			Integer.valueOf(testcase[ 6]) );
		result.setFaktorWegzeitenUndPausen(	Double.valueOf(testcase[ 7]) );

		return result;
	}

	@Override
	protected BegehungswegeMockErgebnis parseExpectedValues(String[] testcase) {
		double[] values = getDoubleValues(testcase, 8);
		BegehungswegeMockErgebnis result = new BegehungswegeMockErgebnis();
		
		int i=0;
		result.setZeitPersonal_min(values[i++]);
		result.setZeitMaschine_min(values[i++]);
		result.setKostenPersonal(values[i++]);
		result.setKostenMaschine(values[i++]);
		result.setKostenGesamt(values[i++]);

		return result;
	}

	
	@Test(dataProvider="csvData")
	public void testZeitPersonal_min(@SuppressWarnings("unused") String testcaseName, BegehungswegeErgebnis actualOutput, BegehungswegeMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitPersonal_min(), expectedOutput.getZeitPersonal_min());
	}

	@Test(dataProvider="csvData")
	public void testZeitMaschine_min(@SuppressWarnings("unused") String testcaseName, BegehungswegeErgebnis actualOutput, BegehungswegeMockErgebnis expectedOutput) {
		assertEqualsDynamicDeltaMinutes(actualOutput.getZeitMaschine_min(), expectedOutput.getZeitMaschine_min());
	}

	@Test(dataProvider="csvData")
	public void testKostenPersonal(@SuppressWarnings("unused") String testcaseName, BegehungswegeErgebnis actualOutput, BegehungswegeMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenPersonal(), expectedOutput.getKostenPersonal());
	}

	@Test(dataProvider="csvData")
	public void testKostenMaschine(@SuppressWarnings("unused") String testcaseName, BegehungswegeErgebnis actualOutput, BegehungswegeMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenMaschine(), expectedOutput.getKostenMaschine());
	}

	@Test(dataProvider="csvData")
	public void testKostenGesamt(@SuppressWarnings("unused") String testcaseName, BegehungswegeErgebnis actualOutput, BegehungswegeMockErgebnis expectedOutput) {
		assertEqualsDynamicDelta(actualOutput.getKostenGesamt(), expectedOutput.getKostenGesamt());
	}

}
