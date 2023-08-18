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

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import ch.wsl.fps.juwapfl.util.CsvReader;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractJuwapflModelTest {
	
	protected static final String TESTCASES_CSV_FOLDER = "testcases/";

	private static final double DEFAULT_DELTA = 0.0051;
	private static final double DEFAULT_DELTA_MINUTES = 0.51;
	private static final boolean ADAPT_DELTA = true;
	
	private Object[][] testData;
	

	@BeforeClass
	public void initTestData() {
		CsvReader csv = new CsvReader(";","#", true);
		String fileName = getCsvPath();
		List<String[]> testcases = csv.readFile(fileName);
		
		final int nrOfTestcases = testcases.size();
		Object[][] testData = new Object[nrOfTestcases][3];
		
		for (int i=0; i<nrOfTestcases; i++) {
			AbstractModel inputData = parseInputData(testcases.get(i));
			AbstractErgebnis expectedOutput = parseExpectedValues(testcases.get(i));
			
			/*
			 * Falls Ergebnis für einen Testcase nicht berechnet werden kann,
			 * soll dies erst beim Testcase selbst angezeigt werden, nicht schon
			 * beim Initialisieren der Testdaten.
			 */
			AbstractErgebnis actualOutput = null;
			try { 
				actualOutput = inputData.getErgebnis();
				actualOutput.printErgebnis();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			testData[i][0] = "Testcase " + (i + 1);
			testData[i][1] = actualOutput;
			testData[i][2] = expectedOutput;
		}

		this.testData = testData;
	}
	

	/**
	 * @return Path of the CSV-file containing the testcases
	 */
	protected abstract String getCsvPath();

	
	protected abstract AbstractModel parseInputData(String[] testcase);	

	protected abstract AbstractErgebnis parseExpectedValues(String[] testcase);
	
	
	@DataProvider(name="csvData")
	public final Object[][] getTestData() {
		return testData;
	}
	
	
	protected double[] getDoubleValues(String[] testcase, int firstCol) {
		int numberOfFields = testcase.length - firstCol;
		double[] result = new double[numberOfFields];
		
		for (int i=firstCol; i<testcase.length; i++) {
			result[i-firstCol] = Double.valueOf( testcase[i] );
		}
		
		return result;
	}
	
	
	protected void assertEqualsDynamicDelta(double actual, double expected) {
		if (expected < 10000) {
			assertEqualsDynamicAbsoluteDelta(actual, expected);
		}
		else {
			assertEqualsDynamicRelativeDelta(actual, expected);
		}
	}
	
	protected void assertEqualsDynamicDeltaMinutes(double actual, double expected) {
		assertEquals(actual, expected, DEFAULT_DELTA_MINUTES);
	}
	
	private final void assertEqualsDynamicAbsoluteDelta(double actual, double expected) {
		double delta = DEFAULT_DELTA;
		if (ADAPT_DELTA && expected > 1000) {
			delta *= 2;
		}
		
		assertEquals(actual, expected, delta);
	}
	
	private final void assertEqualsDynamicRelativeDelta(double actual, double expected) {
		double relativeDelta = 1.00005;
		double absoluteDelta = expected * (relativeDelta - 1.0);
		
		assertEquals(actual, expected, absoluteDelta);
	}
	
}
