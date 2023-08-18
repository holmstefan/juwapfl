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

import java.text.DecimalFormat;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractRueckegassenErgebnis extends AbstractErgebnis {

	public abstract double getZeitaufwandHauptperson_minProHektar();
	public abstract double getZeitaufwandHauptperson_minGesamtflaeche();

	public abstract double getZeitaufwandHilfskraft_minProHektar();
	public abstract double getZeitaufwandHilfskraft_minGesamtflaeche();

	public abstract double getKostenHauptperson_proHektar();
	public abstract double getKostenHauptperson_Gesamtflaeche();

	public abstract double getKostenHilfskraft_proHektar();
	public abstract double getKostenHilfskraft_Gesamtflaeche();

	public abstract double getKostenMaterial_proHektar();
	public abstract double getKostenMaterial_Gesamtflaeche();

	public abstract double getKostenGesamt_proHektar();
	public abstract double getKostenGesamt_Gesamtflaeche();


	@Override
	public void printErgebnis() {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
		System.out.print( df.format(getZeitaufwandHauptperson_minProHektar()) + TAB);
		System.out.print( df.format(getZeitaufwandHauptperson_minGesamtflaeche()) + TAB);

		System.out.print( df.format(getZeitaufwandHilfskraft_minProHektar()) + TAB);
		System.out.print( df.format(getZeitaufwandHilfskraft_minGesamtflaeche()) + TAB);

		System.out.print( df.format(getKostenHauptperson_proHektar()) + TAB);
		System.out.print( df.format(getKostenHauptperson_Gesamtflaeche()) + TAB);

		System.out.print( df.format(getKostenHilfskraft_proHektar()) + TAB);
		System.out.print( df.format(getKostenHilfskraft_Gesamtflaeche()) + TAB);

		System.out.print( df.format(getKostenMaterial_proHektar()) + TAB);
		System.out.print( df.format(getKostenMaterial_Gesamtflaeche()) + TAB);

		System.out.print( df.format(getKostenGesamt_proHektar()) + TAB);
		System.out.print( df.format(getKostenGesamt_Gesamtflaeche()) + TAB);
		
		System.out.println();
	}

}
