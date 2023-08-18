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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.lang.reflect.Field;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import ch.wsl.fps.juwapfl.gui.Utilities;

/**
 * This class uses reflection to add an info button to the TitledBorder class.
 * 
 * @author Stefan Holm
 * 
 * @deprecated This class uses reflection and leads to an Exception if used with Java >= 16.
 *
 */
@Deprecated
public class TitledBorderWithInfoButton2 extends TitledBorder {
    
    private final JLabel infoButton;
    private final Rectangle infoButtonBounds = new Rectangle();

    
	public TitledBorderWithInfoButton2(String title, boolean showInfoButton) {
		super(TitledBorderFactory.getBaseBorder(), title);
        super.setTitleFont(super.getTitleFont().deriveFont(Font.BOLD));
        
        if (showInfoButton) {
        	// Here, only the blue icon is initialized without the tooltip text. 
        	// Displaying of the tooltip must be managed by the user of this class,
        	// as AbstractBorder does not extend JComponent and therefore can't show tooltips.
            this.infoButton = Utilities.getInfoButtonBlue(""); //$NON-NLS-1$
        }
        else {
        	this.infoButton = null;
        }
	}
	
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		super.paintBorder(c, g, x, y, width, height);

		if (infoButton != null) {
			JLabel label = getSuperclassLabelWithReflection();

			if (label != null) {
				Dimension size = label.getPreferredSize();
				Insets insets = new Insets(0, 0, 0, 0);

				int labelY = y;
				int labelH = size.height;

				insets.top = EDGE_SPACING - labelH/2;
				if (insets.top >= EDGE_SPACING) {
					labelY += insets.top;
				}

				insets.left += EDGE_SPACING + TEXT_INSET_H;
				insets.right += EDGE_SPACING + TEXT_INSET_H;

				int labelX = x;
				int labelW = width - insets.left - insets.right;
				if (labelW > size.width) {
					labelW = size.width;
				}

				labelX += insets.left;

				paintInfoButton(labelX, labelY, labelW, g);
			}
		}
	}
	
	
	private JLabel getSuperclassLabelWithReflection() {
		JLabel label = null;
		try {
			Field field = TitledBorder.class.getDeclaredField("label"); //$NON-NLS-1$
			field.setAccessible(true); // not allowed with Java 16!
			label = (JLabel) field.get(this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return label;
	}
	
	
	private void paintInfoButton(int labelX, int labelY, int labelW, Graphics g) {
		final int infoButtonX = labelX + labelW + 5;
		final int infoButtonY = labelY;
		final int infoButtonEdgeLength = 16;

		g.translate(infoButtonX, infoButtonY);
		infoButton.setSize(infoButtonEdgeLength, infoButtonEdgeLength);
		infoButton.paint(g);
		g.translate(-infoButtonX, -infoButtonY);

		infoButtonBounds.setBounds(infoButtonX, infoButtonY, infoButtonEdgeLength, infoButtonEdgeLength);
	}
	
    
    public Rectangle getInfoButtonBounds() {
    	return infoButtonBounds;
    }
}
