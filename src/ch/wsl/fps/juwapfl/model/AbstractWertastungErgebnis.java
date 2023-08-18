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
public abstract class AbstractWertastungErgebnis extends AbstractErgebnis {

	public abstract double getAstungszeit_minProBaum();
	public abstract double getAstungszeit_minProHektar();
	public abstract double getAstungszeit_minProBestand();

	public abstract double getGehzeit_minProBaum();
	public abstract double getGehzeit_minProHektar();
	public abstract double getGehzeit_minProBestand();

	public abstract double getZeitaufwandGesamt_minProBaum();
	public abstract double getZeitaufwandGesamt_minProHektar();
	public abstract double getZeitaufwandGesamt_minProBestand();

	public abstract double getKostenAstungszeit_proBaum();
	public abstract double getKostenAstungszeit_proHektar();
	public abstract double getKostenAstungszeit_proBestand();

	public abstract double getKostenGehzeit_proBaum();
	public abstract double getKostenGehzeit_proHektar();
	public abstract double getKostenGehzeit_proBestand();

	public abstract double getKostenMaterial_proBaum();
	public abstract double getKostenMaterial_proHektar();
	public abstract double getKostenMaterial_proBestand();

	public abstract double getKostenGesamt_proBaum();
	public abstract double getKostenGesamt_proHektar();
	public abstract double getKostenGesamt_proBestand();


	@Override
	public void printErgebnis() {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
		System.out.print( df.format(getAstungszeit_minProBaum()) + TAB);
		System.out.print( df.format(getAstungszeit_minProHektar()) + TAB);
		System.out.print( df.format(getAstungszeit_minProBestand()) + TAB);

		System.out.print( df.format(getGehzeit_minProBaum()) + TAB);
		System.out.print( df.format(getGehzeit_minProHektar()) + TAB);
		System.out.print( df.format(getGehzeit_minProBestand()) + TAB);

		System.out.print( df.format(getZeitaufwandGesamt_minProBaum()) + TAB);
		System.out.print( df.format(getZeitaufwandGesamt_minProHektar()) + TAB);
		System.out.print( df.format(getZeitaufwandGesamt_minProBestand()) + TAB);

		System.out.print( df.format(getKostenAstungszeit_proBaum()) + TAB);
		System.out.print( df.format(getKostenAstungszeit_proHektar()) + TAB);
		System.out.print( df.format(getKostenAstungszeit_proBestand()) + TAB);

		System.out.print( df.format(getKostenGehzeit_proBaum()) + TAB);
		System.out.print( df.format(getKostenGehzeit_proHektar()) + TAB);
		System.out.print( df.format(getKostenGehzeit_proBestand()) + TAB);

		System.out.print( df.format(getKostenMaterial_proBaum()) + TAB);
		System.out.print( df.format(getKostenMaterial_proHektar()) + TAB);
		System.out.print( df.format(getKostenMaterial_proBestand()) + TAB);

		System.out.print( df.format(getKostenGesamt_proBaum()) + TAB);
		System.out.print( df.format(getKostenGesamt_proHektar()) + TAB);
		System.out.print( df.format(getKostenGesamt_proBestand()) + TAB);
		
		System.out.println();
	}
}
