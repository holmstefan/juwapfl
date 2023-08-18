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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Utilities {
	
	private static final int TOOLTIP_CHARS_PER_LINE = 75;
	
	private static ImageIcon imageIconBlue = null;
	
	
	public static ImageIcon getWslLogo() {
		return getImageIcon("data/WSL64.png"); //$NON-NLS-1$
	}
	
	
	public static JPanel getPanelWithInfoButton(JComponent inputField, String infoButtonText) {
		JPanel pnlInputFieldAndInfoButton = new JPanel(new BorderLayout(5, 0)) {
			@Override
			public void setEnabled(boolean enabled) {
				super.setEnabled(enabled);
				inputField.setEnabled(enabled);
			}
		};
		
		pnlInputFieldAndInfoButton.setOpaque(false);
		pnlInputFieldAndInfoButton.add(inputField, BorderLayout.CENTER);
		pnlInputFieldAndInfoButton.add(Utilities.getInfoButtonBlue(infoButtonText), BorderLayout.EAST);
		return pnlInputFieldAndInfoButton;
	}
	
	
	public static JPanel getPanelWithoutInfoButton(JComponent inputField) {
		JPanel pnlInputFieldAndInfoButton = new JPanel(new BorderLayout(5, 0)) {
			@Override
			public void setEnabled(boolean enabled) {
				super.setEnabled(enabled);
				inputField.setEnabled(enabled);
			}
		};
		
		pnlInputFieldAndInfoButton.add(inputField, BorderLayout.CENTER);
		
		JPanel infoButtonPlaceholder = new JPanel();
		infoButtonPlaceholder.setPreferredSize(new Dimension(16, 16));
		pnlInputFieldAndInfoButton.add(infoButtonPlaceholder, BorderLayout.EAST);
		
		return pnlInputFieldAndInfoButton;
	}
	
	
	public static JLabel getInfoButtonBlue(String text) {
		JLabel label = new JLabel(getInfoIconBlue());
		label.setToolTipText(normalizeTooltipText(text));
		return label;
	}
	
	
	private static ImageIcon getInfoIconBlue() {
		if (imageIconBlue != null) {
			return imageIconBlue; //Datei wird nur beim ersten Zugriff gelesen
		}
		
		imageIconBlue = getImageIcon("data/info.png"); //$NON-NLS-1$
		return imageIconBlue;
	}
	
	
	private static ImageIcon getImageIcon(String filePath) {
		//try to open from jar
		URL imgURL = Utilities.class.getClassLoader().getResource(filePath);	
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		
		//otherwise, try to open from file system
		return new ImageIcon(filePath);
	}
	
	
	public static String normalizeTooltipText(String tooltipText) {
		return wrap(tooltipText, TOOLTIP_CHARS_PER_LINE);
	}
	
	
	private static String wrap(String text, final int charsPerLine) {
		if (text.startsWith("<html>") || text.length() < charsPerLine) { //$NON-NLS-1$
			return text;
		}
		else {
			final String BREAK = "<br>"; //$NON-NLS-1$
			int startPos = 0;
			int latestCutPosition = startPos + charsPerLine;
			
			//handle possible extra breaks
			while (text.lastIndexOf(BREAK, latestCutPosition) > startPos) {
				startPos = text.lastIndexOf(BREAK, latestCutPosition) + BREAK.length();
				latestCutPosition = startPos + charsPerLine;
			}
			
			while (latestCutPosition < text.length()) {
				int indexOfLastSpace = text.lastIndexOf(" ", latestCutPosition); //$NON-NLS-1$
				if (indexOfLastSpace == -1) {
					text = text.substring(0, latestCutPosition) + "-" + BREAK + text.substring(latestCutPosition); //$NON-NLS-1$
					startPos = latestCutPosition + 1 + BREAK.length();
					latestCutPosition = startPos + charsPerLine;
				}
				else {
					text = text.substring(0, indexOfLastSpace) + BREAK + text.substring(indexOfLastSpace + 1);
					startPos = indexOfLastSpace + BREAK.length();
					latestCutPosition = startPos + charsPerLine;
				}
				
				//handle possible extra breaks
				while (text.lastIndexOf(BREAK, latestCutPosition) > startPos) {
					startPos = text.lastIndexOf(BREAK, latestCutPosition) + BREAK.length();
					latestCutPosition = startPos + charsPerLine;
				}
			}
			text = "<html>" + text + "</html>"; //$NON-NLS-1$ //$NON-NLS-2$
			return text;
		}
	}
}
