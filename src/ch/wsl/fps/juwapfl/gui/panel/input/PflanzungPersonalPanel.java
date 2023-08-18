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
package ch.wsl.fps.juwapfl.gui.panel.input;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.main.AbstractMainWindow;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PflanzungPersonalPanel extends SimplePersonalPanel {

	public PflanzungPersonalPanel(AbstractMainWindow mainWindow) {
		super(mainWindow, true);

		final String msg = Messages.getString("Pflanzung.InfoButtonPersonal1") + "<br><br>" //$NON-NLS-1$ //$NON-NLS-2$
				+ Messages.getString("Pflanzung.InfoButtonPersonal2"); //$NON-NLS-1$
		final int componentIndex = 1;

		// replace panel without info button with panel with info button
		this.remove(componentIndex);
		this.add(Utilities.getPanelWithInfoButton(txtKostenPersonalProPerson, msg), componentIndex);
	}

}
