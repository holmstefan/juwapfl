/*******************************************************************************
 * Copyright 2024 Stefan Holm
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

import java.time.LocalTime;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.annotations.XYPolygonAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.event.AnnotationChangeEvent;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * 
 * @author Stefan Holm
 *
 */
public class JFreeChartPreloader { //TODO(enhancement): put JFreeChart source as source into the jar, not as jar.

	private static volatile boolean hasFinished = false;
	
	private static final Thread t1 = new Thread(() -> {
//		System.out.println("1_1 " + LocalTime.now());
		XYSeries xySeries = new XYSeries("");
//		System.out.println("1_2 " + LocalTime.now());
		xySeries.add(0, 0);
//		System.out.println("1_3 " + LocalTime.now());
		new StandardXYToolTipGenerator();
//		System.out.println("1_4 " + LocalTime.now());
	});
	
	private static final Thread t2 = new Thread(() -> {
//		System.out.println("2_1 " + LocalTime.now());
		new XYLineAndShapeRenderer(true, false);
//		System.out.println("2_2 " + LocalTime.now());
		JFreeChart.class.getName();
//		System.out.println("2_3 " + LocalTime.now());
	});
	
	private static final Thread t3 = new Thread(() -> {
//		System.out.println("3_1 " + LocalTime.now());
		XYPlot.class.getName();
//		System.out.println("3_2 " + LocalTime.now());
		ChartPanel.class.getName();
//		System.out.println("3_3 " + LocalTime.now());
	});
	
	private static final Thread t4 = new Thread(() -> {
//		System.out.println("4_1 " + LocalTime.now());
		NumberAxis axis = new NumberAxis("");
//		System.out.println("4_2 " + LocalTime.now());
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
//		System.out.println("4_3 " + LocalTime.now());
		XYSeriesCollection dataset = new XYSeriesCollection();
//		System.out.println("4_4 " + LocalTime.now());
		XYPlot plot = new XYPlot(dataset, axis, axis, renderer);
//    	System.out.println("4_5 " + LocalTime.now());
		JFreeChart chart = new JFreeChart(plot);
//		System.out.println("4_6 " + LocalTime.now());
		new ChartPanel(chart);
//		System.out.println("4_7 " + LocalTime.now());
	});
	
	private static final Thread t5 = new Thread(() -> {
//		System.out.println("5_1 " + LocalTime.now());
		new XYPolygonAnnotation(
				new double[] {},
				null,
				null,
				null);
//		System.out.println("5_2 " + LocalTime.now());
		XYPointerAnnotation datapoint = new XYPointerAnnotation("", 0, 0, 0);
//		System.out.println("5_3 " + LocalTime.now());
		new AnnotationChangeEvent(new Object(), datapoint);
//		System.out.println("5_4 " + LocalTime.now());
		ChartFactory.createXYLineChart("title", "xAxisLabel", "yAxisLabel", new XYSeriesCollection());
//		System.out.println("5_5 " + LocalTime.now());
	});
	
	private static final Thread t6 = new Thread(() -> {
//		System.out.println("6_1 " + LocalTime.now());
		ChartFactory.createXYLineChart("title", "xAxisLabel", "yAxisLabel", null);
//		System.out.println("6_2 " + LocalTime.now());
	});
	
	
	/**
	 * Preloads JFreeChart classes needed when loading Wildschutz model.
	 * If run from jar without this method and jar created with "package libraries"
	 * instead of "extract libraries", it will take approx. 25 seconds to load the 
	 * Wildschutz model for the first time.
	 */
	public static void preload() {
		System.out.println(LocalTime.now() + ": starting threads in JFreeChartPreloader..");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		Thread tWait = new Thread(() -> {
			try {
				t1.join();
				t2.join();
				t3.join();
				t4.join();
				t5.join();
				t6.join();
				hasFinished = true;
				System.out.println(LocalTime.now() + ": all threads finished in JFreeChartPreloader!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		tWait.start();
	}
	
	
	public static boolean hasFinished() {
		return hasFinished;
	}

}
