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
package ch.wsl.fps.juwapfl.gui.controls;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ch.wsl.fps.juwapfl.gui.Utilities;
import ch.wsl.fps.juwapfl.gui.XmlStringable;

/**
 * A panel with a titled border intended for input fields.
 * The border title can have an info button next to it.
 * 
 * @author Stefan Holm
 *
 */
public abstract class TitledInputPanel extends JPanel implements XmlStringable {
	
	/**
	 * Creates a panel WITH info button
	 */
	public TitledInputPanel(String title, String infoButtonText) {
		this.setBorder(new TitledBorderWithInfoButton(title, true));
		setToolTipText(Utilities.normalizeTooltipText(infoButtonText));
	}
	
	/**
	 * Creates a panel WITHOUT info button
	 */
	public TitledInputPanel(String title) {
		this.setBorder(new TitledBorderWithInfoButton(title, false));
	}
	
	
	@Override
	public String getToolTipText(MouseEvent event) {
		TitledBorderWithInfoButton border = (TitledBorderWithInfoButton) getBorder();
		Rectangle bounds = border.getInfoButtonBounds();
		boolean isMouseOverInfoButton = bounds.contains(event.getPoint());
        return isMouseOverInfoButton ? super.getToolTipText() : null;
	}

}
