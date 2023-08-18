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
public abstract class AbstractSchneegleitenErgebnis extends AbstractErgebnis {

	public abstract double getZeitPersonalAufbau_min();
	public abstract double getZeitPersonalUnterhalt_min();
	public abstract double getZeitPersonalAbbau_min();
	public abstract double getZeitPersonalGesamt_min();

	public abstract double getKostenPersonalAufbau();
	public abstract double getKostenPersonalUnterhalt();
	public abstract double getKostenPersonalAbbau();
	public abstract double getKostenPersonalGesamt();

	public abstract double getKostenFahrtenAufbau();
	public abstract double getKostenFahrtenUnterhalt();
	public abstract double getKostenFahrtenAbbau();
	public abstract double getKostenFahrtenGesamt();

	public abstract double getKostenMaterialAufbau();
	public abstract double getKostenMaterialUnterhalt();
	public abstract double getKostenMaterialAbbau();
	public abstract double getKostenMaterialGesamt();

	public abstract double getKostenGesamtAufbau();
	public abstract double getKostenGesamtUnterhalt();
	public abstract double getKostenGesamtAbbau();
	public abstract double getKostenGesamtGesamt();


	@Override
	public void printErgebnis() {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
		System.out.print( df.format(getZeitPersonalAufbau_min()) + TAB);
		System.out.print( df.format(getZeitPersonalUnterhalt_min()) + TAB);
		System.out.print( df.format(getZeitPersonalAbbau_min()) + TAB);
		System.out.print( df.format(getZeitPersonalGesamt_min()) + TAB);

		System.out.print( df.format(getKostenPersonalAufbau()) + TAB);
		System.out.print( df.format(getKostenPersonalUnterhalt()) + TAB);
		System.out.print( df.format(getKostenPersonalAbbau()) + TAB);
		System.out.print( df.format(getKostenPersonalGesamt()) + TAB);

		System.out.print( df.format(getKostenFahrtenAufbau()) + TAB);
		System.out.print( df.format(getKostenFahrtenUnterhalt()) + TAB);
		System.out.print( df.format(getKostenFahrtenAbbau()) + TAB);
		System.out.print( df.format(getKostenFahrtenGesamt()) + TAB);

		System.out.print( df.format(getKostenMaterialAufbau()) + TAB);
		System.out.print( df.format(getKostenMaterialUnterhalt()) + TAB);
		System.out.print( df.format(getKostenMaterialAbbau()) + TAB);
		System.out.print( df.format(getKostenMaterialGesamt()) + TAB);

		System.out.print( df.format(getKostenGesamtAufbau()) + TAB);
		System.out.print( df.format(getKostenGesamtUnterhalt()) + TAB);
		System.out.print( df.format(getKostenGesamtAbbau()) + TAB);
		System.out.print( df.format(getKostenGesamtGesamt()) + TAB);
		
		System.out.println();
	}
}
