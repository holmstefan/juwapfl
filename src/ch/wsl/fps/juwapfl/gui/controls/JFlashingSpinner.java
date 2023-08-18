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

import java.awt.Color;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Stefan Holm
 *
 */
public class JFlashingSpinner extends JSpinner {

	private static final Color NORMAL_COLOR = new Color(255, 255, 255);
	private static final Color HIGHLIGHT_COLOR = new Color(128, 192, 128);
	private static final int PAUSE_LENGTH_INIITAL = 4000;
	private static final int PAUSE_LENGTH = 80;
	private static final int ROUNDS = 22;
	
	private volatile boolean isFlashing = false;
	private volatile boolean reset = false;
	
	private final Lock lock = new ReentrantLock();
	
	public JFlashingSpinner(SpinnerNumberModel spinnerNumberModel) {
		super(spinnerNumberModel);
	}

	public void flash() {
		lock.lock();
		try {
			if (isFlashing) {
				reset = true;
				return;
			}
			isFlashing = true;
		} finally {
			lock.unlock();
		}

		Thread t = new Thread(() -> {
			SwingUtilities.invokeLater(() -> getTf().setBackground(HIGHLIGHT_COLOR));
			safeSleepInitial();
			lock.lock();
			for (int i=1; i<=ROUNDS || reset; i++) {
				lock.unlock();
				safeSleep();
				if (reset == true) {
					reset = false;
					i = 0;
					SwingUtilities.invokeLater(() -> getTf().setBackground(HIGHLIGHT_COLOR));
					safeSleepInitial();
				}

				// red
				int highlightR = HIGHLIGHT_COLOR.getRed();
				int targetR = NORMAL_COLOR.getRed();
				int newR = highlightR + ((targetR - highlightR) * i/ROUNDS);

				// green
				int highlightG = HIGHLIGHT_COLOR.getGreen();
				int targetG = NORMAL_COLOR.getGreen();
				int newG = highlightG + ((targetG - highlightG) * i/ROUNDS);

				// blue
				int highlightB = HIGHLIGHT_COLOR.getBlue();
				int targetB = NORMAL_COLOR.getBlue();
				int newB = highlightB + ((targetB - highlightB) * i/ROUNDS);

				Color newColor = new Color(newR, newG, newB);
				SwingUtilities.invokeLater(() -> getTf().setBackground(newColor));
				lock.lock();
			}
			isFlashing = false;
			lock.unlock();

		});
		t.start();

	}
	
	private JTextField getTf() {
		return ((JSpinner.NumberEditor) getEditor()).getTextField();
	}
	
	private void safeSleepInitial() {
		try {
			final int STEPS = 10;
			for (int i=0; i<STEPS; i++) {
				if (reset == true) {
					return;
				}
				Thread.sleep(PAUSE_LENGTH_INIITAL / STEPS);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void safeSleep() {
		try {
			final int STEPS = 5;
			for (int i=0; i<STEPS; i++) {
				if (reset == true) {
					return;
				}
				Thread.sleep(PAUSE_LENGTH / STEPS);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
