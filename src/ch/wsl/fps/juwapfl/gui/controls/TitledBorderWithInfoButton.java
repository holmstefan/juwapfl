/*
 * Copyright (c) 1997, 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package ch.wsl.fps.juwapfl.gui.controls;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Path2D;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicHTML;

import ch.wsl.fps.juwapfl.gui.Utilities;

/**
 * This class is inspired by the OpenJDK class javax.swing.border.TitledBorder.java.
 * 
 * The class has, compared to the original class, reduced tweaking possibilites
 * and an additional possibility to show the icon of an info button.
 * 
 * 
 * @author Stefan Holm
 *
 */
public class TitledBorderWithInfoButton extends AbstractBorder {
	
	private final String title;
	
	private Border border;

    private final JLabel label;
    
    private final JLabel infoButton;

    // Space between the border and the component's edge
    private static final int EDGE_SPACING = 2;

    // Space between the border and text
    private static final int TEXT_SPACING = 2;

    // Horizontal inset of text that is left or right justified
    private static final int TEXT_INSET_H = 5;
    
    private final Rectangle infoButtonBounds = new Rectangle();

    /**
     * Creates a TitledBorder instance with the specified title and info button text.
     *
     * @param title  the title the border should display
     * @param showInfoButton  if an info button should be displayed
     */
    public TitledBorderWithInfoButton(String title, boolean showInfoButton) {
        this.title = title;
        this.border = TitledBorderFactory.getBaseBorder();

        this.label = new JLabel();
        this.label.setOpaque(false);
        this.label.putClientProperty(BasicHTML.propertyKey, null);
        
        if (showInfoButton) {
        	// Here, only the blue icon is initialized without the tooltip text. 
        	// Displaying of the tooltip must be managed by the user of this class,
        	// as AbstractBorder does not extend JComponent and therefore can't show tooltips.
            this.infoButton = Utilities.getInfoButtonBlue("");  //$NON-NLS-1$
        }
        else {
        	this.infoButton = null;
        }
    }

    /**
     * Creates a TitledBorder instance with the specified title without an info button.
     *
     * @param title  the title the border should display
     */
    public TitledBorderWithInfoButton(String title) {
        this(title, false);
    }

    @Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    	Border border = getBorder();
        if ((title != null) && !title.isEmpty()) {
            JLabel label = getLabel(c);
            Dimension size = label.getPreferredSize();
            Insets insets = new Insets(0, 0, 0, 0);

            int borderX = x + EDGE_SPACING;
            int borderY = y + EDGE_SPACING;
            int borderW = width - EDGE_SPACING - EDGE_SPACING;
            int borderH = height - EDGE_SPACING - EDGE_SPACING;

            int labelY = y;
            int labelH = size.height;

            insets.top = EDGE_SPACING - labelH/2;
            if (insets.top < EDGE_SPACING) {
            	borderY -= insets.top;
            	borderH += insets.top;
            }
            else {
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

            if (border != null) {
            	Graphics g2 = g.create();
            	if (g2 instanceof Graphics2D) {
            		Graphics2D g2d = (Graphics2D) g2;
            		Path2D path = new Path2D.Float();
            		path.append(new Rectangle(borderX, borderY, borderW, labelY - borderY), false);
            		path.append(new Rectangle(borderX, labelY, labelX - borderX - TEXT_SPACING, labelH), false);
            		path.append(new Rectangle(labelX + labelW + TEXT_SPACING, labelY, borderX - labelX + borderW - labelW - TEXT_SPACING, labelH), false);
            		path.append(new Rectangle(borderX, labelY + labelH, borderW, borderY - labelY + borderH - labelH), false);
            		g2d.clip(path);
            	}
            	border.paintBorder(c, g2, borderX, borderY, borderW, borderH);
            	g2.dispose();

            }
            g.translate(labelX, labelY);
            label.setSize(labelW, labelH);
            label.paint(g);
            g.translate(-labelX, -labelY);

            if (infoButton != null) {
            	final int infoButtonX = labelX + labelW + 5;
            	final int infoButtonY = labelY;
            	final int infoButtonEdgeLength = 16;

            	g.translate(infoButtonX, infoButtonY);
            	infoButton.setSize(infoButtonEdgeLength, infoButtonEdgeLength);
            	infoButton.paint(g);
            	g.translate(-infoButtonX, -infoButtonY);

            	infoButtonBounds.setBounds(infoButtonX, infoButtonY, infoButtonEdgeLength, infoButtonEdgeLength);
            }
        }
        else if (border != null) {
            border.paintBorder(c, g, x, y, width, height);
        }
    }

    /**
     * Reinitialize the insets parameter with this Border's current Insets.
     * @param c the component for which this border insets value applies
     * @param insets the object to be reinitialized
     */
    @Override
	public Insets getBorderInsets(Component c, Insets insets) {
    	AbstractBorder border = (AbstractBorder) getBorder();
        insets = border.getBorderInsets(c, insets);

        if ((title != null) && !title.isEmpty()) {
            JLabel label = getLabel(c);
            Dimension size = label.getPreferredSize();

            if (insets.top < size.height) {
            	insets.top = size.height - EDGE_SPACING;
            }

            insets.top += EDGE_SPACING + TEXT_SPACING;
            insets.left += EDGE_SPACING + TEXT_SPACING;
            insets.right += EDGE_SPACING + TEXT_SPACING;
            insets.bottom += EDGE_SPACING + TEXT_SPACING;
        }
        return insets;
    }
    
    public Border getBorder() {
        return border != null
                ? border
                : UIManager.getBorder("TitledBorder.border"); //$NON-NLS-1$
    }

    @Override
	public int getBaseline(Component c, int width, int height) {
        if (c == null) {
            throw new NullPointerException("Must supply non-null component"); //$NON-NLS-1$
        }
        if (width < 0) {
            throw new IllegalArgumentException("Width must be >= 0"); //$NON-NLS-1$
        }
        if (height < 0) {
            throw new IllegalArgumentException("Height must be >= 0"); //$NON-NLS-1$
        }
        Border border = getBorder();
        if ((title != null) && !title.isEmpty()) {
            int edge = (border instanceof TitledBorder) ? 0 : EDGE_SPACING;
            JLabel label = getLabel(c);
            Dimension size = label.getPreferredSize();

            int baseline = label.getBaseline(size.width, size.height);

            int topInset = edge + (0 - size.height) / 2;
            return (topInset < edge)
            		? baseline
            				: baseline + topInset;
        }
        return -1;
    }

    @Override
	public Component.BaselineResizeBehavior getBaselineResizeBehavior(Component c) {
        super.getBaselineResizeBehavior(c);
        return Component.BaselineResizeBehavior.CONSTANT_ASCENT;
    }

    private JLabel getLabel(Component c) {
        this.label.setText(title);
        this.label.setFont(UIManager.getFont("TitledBorder.font").deriveFont(Font.BOLD)); //$NON-NLS-1$
        this.label.setForeground(UIManager.getColor("TitledBorder.titleColor")); //$NON-NLS-1$
        this.label.setComponentOrientation(c.getComponentOrientation());
        this.label.setEnabled(c.isEnabled());
        return this.label;
    }
    
    public Rectangle getInfoButtonBounds() {
    	return infoButtonBounds;
    }
}
