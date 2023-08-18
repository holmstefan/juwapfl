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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.annotations.XYPolygonAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ch.wsl.fps.juwapfl.Messages;
import ch.wsl.fps.juwapfl.gui.panel.ergebnis.AbstractErgebnisPanel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WildschutzChartPanel extends JPanel {
	
	private final JFreeChart chart;
	private final XYSeries dataLine = new XYSeries("dataLine"); //$NON-NLS-1$
	private final XYSeries dataPoint = new XYSeries("dataPoint"); //$NON-NLS-1$
	private double anzahlPflanzenBeiZaunlaenge500 = 1000;
	private double anzahlPflanzen = 0;
	private double zaunlaenge = 0;

	private static final int ALPHA = 48;
	private static final Color COLOR_A = new Color(255, 255, 0, ALPHA);
	private static final Color COLOR_B = new Color(0, 0, 255, ALPHA);
	
	public WildschutzChartPanel() {
		String title = Messages.getString("Wildschutz.TitelKostenvergleich"); //$NON-NLS-1$
		String categoryAxisLabel = Messages.getString("Wildschutz.Zaunlaenge"); //$NON-NLS-1$
		String valueAxisLabel = Messages.getString("Wildschutz.AnzahlPflanzen"); //$NON-NLS-1$
		XYSeriesCollection dataset = new XYSeriesCollection(dataLine);
		dataset.addSeries(dataPoint);
		
		chart = ChartFactory.createXYLineChart(title, categoryAxisLabel, valueAxisLabel, dataset);
		chart.removeLegend();
//		chart.getTitle().setFont(chart.getTitle().getFont().deriveFont(Font.PLAIN));
		chart.getTitle().setFont(chart.getTitle().getFont().deriveFont(14f));

		chart.getXYPlot().getDomainAxis().setLabelFont(chart.getXYPlot().getDomainAxis().getLabelFont().deriveFont(12f));
		chart.getXYPlot().getRangeAxis().setLabelFont(chart.getXYPlot().getRangeAxis().getLabelFont().deriveFont(12f));

		chart.getXYPlot().getDomainAxis().setRange(0, 500);
		chart.getXYPlot().getRangeAxis().setRange(0, 1000);
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
		renderer.setSeriesLinesVisible(1, false);
//		renderer.setSeriesShapesVisible(1, true);
		renderer.setSeriesPaint(0, Color.BLACK);
		renderer.setSeriesPaint(1, Color.BLACK);
		renderer.setSeriesShape(1, new Ellipse2D.Double(-3, -3, 6, 6));
		chart.getXYPlot().setRenderer(renderer);
		
		
		chart.setBackgroundPaint(AbstractErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(550, 200));
		
//	    this.addComponentListener(new ComponentAdapter() {
//	        @Override
//	        public void componentResized(ComponentEvent e) {
//	        	chartPanel.setMaximumDrawHeight(e.getComponent().getHeight());
//	        	chartPanel.setMaximumDrawWidth(e.getComponent().getWidth());
//	        	chartPanel.setMinimumDrawHeight(e.getComponent().getHeight());
//	        	chartPanel.setMinimumDrawWidth(e.getComponent().getWidth());
//	        }
//	    });

		this.setLayout(new BorderLayout());
		this.add(chartPanel, BorderLayout.CENTER);
		
		resetData();
	}
	
	
	public void setData(double anzahlPflanzenBeiZaunlaenge500, double anzahlPflanzen, double zaunlaenge) {
		this.anzahlPflanzenBeiZaunlaenge500 = anzahlPflanzenBeiZaunlaenge500;
		this.anzahlPflanzen = anzahlPflanzen;
		this.zaunlaenge = zaunlaenge;
		resetData();
	}
	
	
	private void resetData() {
		dataLine.clear();
		dataLine.add(0, 0);
		dataLine.add(10*500, 10*anzahlPflanzenBeiZaunlaenge500);

		dataPoint.clear();
		dataPoint.add(zaunlaenge, anzahlPflanzen);

		chart.getXYPlot().clearAnnotations();
		setPolygons(chart.getXYPlot(), 500, anzahlPflanzenBeiZaunlaenge500);
		setDatapoint(chart.getXYPlot(), zaunlaenge, anzahlPflanzen);
	}
	
	
	private static void setPolygons(XYPlot plot, double x, double y) {
		x *= 10;
		y *= 10;
		
		XYPolygonAnnotation polygon1 = new XYPolygonAnnotation(
				new double[] {0, 0, x, 0, x, y},
				null,
				null,
				COLOR_A);
		plot.addAnnotation(polygon1);
		
		XYPolygonAnnotation polygon2 = new XYPolygonAnnotation(
				new double[] {0, 0, 0, y, x, y},
				null,
				null,
				COLOR_B);
		plot.addAnnotation(polygon2);
	}
	
	
	private static void setDatapoint(XYPlot plot, double x, double y) {
		XYPointerAnnotation datapoint = new XYPointerAnnotation(Messages.getString("Wildschutz.IhreEingabe"), x, y, 0/4d * Math.PI); //$NON-NLS-1$
		datapoint.setFont(datapoint.getFont().deriveFont(14f));
		datapoint.setFont(datapoint.getFont().deriveFont(Font.BOLD));
		datapoint.setBaseRadius(40);
		datapoint.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		final boolean isOnRightHalf = x > plot.getDomainAxis().getUpperBound() / 2;
		final boolean isOnUpperHalf = y > plot.getRangeAxis().getUpperBound() / 2;
		if (isOnRightHalf) {
			datapoint.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
		}
		
		if (isOnRightHalf == false && isOnUpperHalf == true) {
			datapoint.setAngle(1/4d * Math.PI);
		}
		if (isOnRightHalf == true && isOnUpperHalf == true) {
			datapoint.setAngle(3/4d * Math.PI);
		}
		if (isOnRightHalf == true && isOnUpperHalf == false) {
			datapoint.setAngle(5/4d * Math.PI);
		}
		if (isOnRightHalf == false && isOnUpperHalf == false) {
			datapoint.setAngle(7/4d * Math.PI);
		}
		
		plot.addAnnotation(datapoint);
	}

}
