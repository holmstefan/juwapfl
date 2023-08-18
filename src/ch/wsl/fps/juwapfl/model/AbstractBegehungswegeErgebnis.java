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
public abstract class AbstractBegehungswegeErgebnis extends AbstractErgebnis {

	public abstract double getZeitPersonal_min();
	public abstract double getZeitMaschine_min();
	public abstract double getKostenPersonal();
	public abstract double getKostenMaschine();
	public abstract double getKostenGesamt();

	
	@Override
	public void printErgebnis() {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
		System.out.print( df.format(getZeitPersonal_min()) + TAB);
		System.out.print( df.format(getZeitMaschine_min()) + TAB);
		System.out.print( df.format(getKostenPersonal()) + TAB);
		System.out.print( df.format(getKostenMaschine()) + TAB);
		System.out.print( df.format(getKostenGesamt()) + TAB);
		
		System.out.println();
	}
}
