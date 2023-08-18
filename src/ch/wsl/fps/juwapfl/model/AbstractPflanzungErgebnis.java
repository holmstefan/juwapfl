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
public abstract class AbstractPflanzungErgebnis extends AbstractErgebnis {
	
	public abstract double getZeitPersonalBeschaffung_min();
	public abstract double getZeitPersonalTransport_min();
	public abstract double getZeitPersonalPflanzung_min();
	public abstract double getZeitPersonalUnterhalt_min();
	public abstract double getZeitPersonalGesamt_min();
	
	public abstract double getZeitGeraetePflanzung_min();
	public abstract double getZeitGeraeteGesamt_min();
	
	public abstract double getKostenPersonalBeschaffung();
	public abstract double getKostenPersonalTransport();
	public abstract double getKostenPersonalPflanzung();
	public abstract double getKostenPersonalUnterhalt();
	public abstract double getKostenPersonalGesamt();
	public abstract double getKostenTransportmittelTransport();
	public abstract double getKostenTransportmittelGesamt();
	public abstract double getKostenPflanzenBeschaffung();
	public abstract double getKostenGeraetePflanzung();
	public abstract double getKostenGeraeteGesamt();
	
	public abstract double getKostenGesamtBeschaffung();
	public abstract double getKostenGesamtTransport();
	public abstract double getKostenGesamtPflanzung();
	public abstract double getKostenGesamtUnterhalt();
	public abstract double getKostenGesamtGesamt();


	@Override
	public void printErgebnis() {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
		System.out.print( df.format(getZeitPersonalBeschaffung_min()) + TAB);
		System.out.print( df.format(getZeitPersonalTransport_min()) + TAB);
		System.out.print( df.format(getZeitPersonalPflanzung_min()) + TAB);
		System.out.print( df.format(getZeitPersonalUnterhalt_min()) + TAB);
		System.out.print( df.format(getZeitPersonalGesamt_min()) + TAB);
		
		System.out.print( df.format(getZeitGeraetePflanzung_min()) + TAB);
		System.out.print( df.format(getZeitGeraeteGesamt_min()) + TAB);
		
		System.out.print( df.format(getKostenPersonalBeschaffung()) + TAB);
		System.out.print( df.format(getKostenPersonalTransport()) + TAB);
		System.out.print( df.format(getKostenPersonalPflanzung()) + TAB);
		System.out.print( df.format(getKostenPersonalUnterhalt()) + TAB);
		System.out.print( df.format(getKostenPersonalGesamt()) + TAB);
		System.out.print( df.format(getKostenTransportmittelTransport()) + TAB);
		System.out.print( df.format(getKostenTransportmittelGesamt()) + TAB);
		System.out.print( df.format(getKostenPflanzenBeschaffung()) + TAB);
		System.out.print( df.format(getKostenGeraetePflanzung()) + TAB);
		System.out.print( df.format(getKostenGeraeteGesamt()) + TAB);
		
		System.out.print( df.format(getKostenGesamtBeschaffung()) + TAB);
		System.out.print( df.format(getKostenGesamtTransport()) + TAB);
		System.out.print( df.format(getKostenGesamtPflanzung()) + TAB);
		System.out.print( df.format(getKostenGesamtUnterhalt()) + TAB);
		System.out.print( df.format(getKostenGesamtGesamt()) + TAB);
		
		System.out.println();
	}
}
