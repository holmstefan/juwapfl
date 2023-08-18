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
public abstract class AbstractAustrichternErgebnis extends AbstractErgebnis {

	public abstract double getZeitaufwandPersonal_minProBaum();
	public abstract double getZeitaufwandPersonal_minProHektar();
	public abstract double getZeitaufwandPersonal_minProBestand();
	
	public abstract double getKostenPersonal_proBaum();
	public abstract double getKostenPersonal_proHektar();
	public abstract double getKostenPersonal_proBestand();
	
	public abstract double getKostenMaschinenGeraete_proBaum();
	public abstract double getKostenMaschinenGeraete_proHektar();
	public abstract double getKostenMaschinenGeraete_proBestand();
	
	public abstract double getKostenGesamt_proBaum();
	public abstract double getKostenGesamt_proHektar();
	public abstract double getKostenGesamt_proBestand();
	
	
	@Override
	public void printErgebnis() {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
		System.out.print( df.format(getZeitaufwandPersonal_minProBaum()) + TAB);
		System.out.print( df.format(getZeitaufwandPersonal_minProHektar()) + TAB);
		System.out.print( df.format(getZeitaufwandPersonal_minProBestand()) + TAB);
		
		System.out.print( df.format(getKostenPersonal_proBaum()) + TAB);
		System.out.print( df.format(getKostenPersonal_proHektar()) + TAB);
		System.out.print( df.format(getKostenPersonal_proBestand()) + TAB);
		
		System.out.print( df.format(getKostenMaschinenGeraete_proBaum()) + TAB);
		System.out.print( df.format(getKostenMaschinenGeraete_proHektar()) + TAB);
		System.out.print( df.format(getKostenMaschinenGeraete_proBestand()) + TAB);
		
		System.out.print( df.format(getKostenGesamt_proBaum()) + TAB);
		System.out.print( df.format(getKostenGesamt_proHektar()) + TAB);
		System.out.print( df.format(getKostenGesamt_proBestand()) + TAB);
		
		System.out.println();
	}
}
