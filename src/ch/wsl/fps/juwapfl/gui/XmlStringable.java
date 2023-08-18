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
package ch.wsl.fps.juwapfl.gui;

import java.text.DecimalFormat;

/**
 * 
 * @author Stefan Holm
 *
 */
public interface XmlStringable {
	
	public String getAsXmlString();
	
	static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(",##0.00"); //$NON-NLS-1$
	
	default String getXmlEntry(String label, Object value) {
		String s1 = "<entry><label>"; //$NON-NLS-1$
		String s2 = "</label><value>"; //$NON-NLS-1$
		String s3 = "</value></entry> "; //$NON-NLS-1$
		
		String valueAsString = value == null ? null : value.toString().replace("<", "&lt;"); //$NON-NLS-1$ //$NON-NLS-2$
		
		String result = s1 + label + s2 + valueAsString + s3;
		return result;
	}
	
	default String getXmlEntry(String label, Double value) {
		String s1 = "<entry><label>"; //$NON-NLS-1$
		String s2 = "</label><value>"; //$NON-NLS-1$
		String s3 = "</value></entry> "; //$NON-NLS-1$
		
		String result = s1 + label + s2 + DECIMAL_FORMAT.format(value) + s3;
		return result;
	}
}
