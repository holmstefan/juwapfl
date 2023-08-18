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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 * 
 * @author Stefan Holm
 *
 */
public class JuWaPflExceptionHandler {
	
	private static final boolean PRINT = true;
	private static volatile boolean DIALOG = false;
	private static volatile boolean STACKTRACE = false;

	public static void handle(Throwable e) {
		handle(e, null);
	}

	public static void handle(Throwable e, String msgPrefix) {
		if (DIALOG && STACKTRACE) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			JOptionPane.showMessageDialog(null, (msgPrefix != null ? msgPrefix : "") + sw.toString()); //$NON-NLS-1$
		}
		else if (DIALOG) {
			JOptionPane.showMessageDialog(null, (msgPrefix != null ? msgPrefix : "") + e); //$NON-NLS-1$
		}
		
		if (PRINT) {
			if (msgPrefix != null) {
				System.err.print(msgPrefix);
			}
			e.printStackTrace();
		}
		
		appendToErrorLogFile(e, msgPrefix);
	}
	
	public static void setLogDialog(boolean flag) {
		DIALOG = flag;
	}
	
	public static void setLogStackTrace(boolean flag) {
		STACKTRACE = flag;
	}
	
	
	private static void appendToErrorLogFile(Throwable e, String msgPrefix) {
		File file = new File("juwapfl-error.log"); //$NON-NLS-1$
		try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
			String timeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()); //$NON-NLS-1$
			printWriter.append(timeStamp + "\n"); //$NON-NLS-1$
			
			String errorMsg = getErrorMsgWithStackTrace(e, msgPrefix);
			printWriter.append(errorMsg + "\n"); //$NON-NLS-1$
			
			printWriter.flush();
			printWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	private static String getErrorMsgWithStackTrace(Throwable e, String msgPrefix) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String errorMsg = (msgPrefix == null ? "" : msgPrefix) + sw.toString(); //$NON-NLS-1$
		return errorMsg;
	}
}
