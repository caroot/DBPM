package de.htw.hundertwasser.errorsupport;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JOptionPane;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/*
 * Class for the ErrorMessage
 */
public class ErrorMessageDialog {

	// Constants
	private static final String PREAMBLE = "We are sorry for the inconvenience, but we've found an error with the following message:\n";
	private static final String ADDENDUM = "\nPlease, be ensure that we would remove this error soon. You can report us this error\n on our vendor e-mail-adress.\n You'll find it on the about dialog.";

	/*
	 * Function that return a Message
	 * 
	 * @return message
	 */
	private static String concatinate(String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(PREAMBLE).append(message).append(ADDENDUM);
		return sb.toString();
	}

	/*
	 * Function to show the Message
	 */
	public static void showMessage(Component parent, String message,
			String title) {
		try {
			JOptionPane.showMessageDialog(parent, concatinate(message), title,
					JOptionPane.ERROR_MESSAGE,
					RessourcenEnummeration.ERROR_ICON.getIcon());
		} catch (HeadlessException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Function to show the Message
	 */
	public static void showMessage(Component parent, String message,
			String title, String stacktrace) {
		try {
			String[] yesNoOption = { "Ok", "Report a bug" };
			int n = JOptionPane
					.showOptionDialog(parent, concatinate(message), title,
							JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE,
							RessourcenEnummeration.ERROR_ICON.getIcon(),
							yesNoOption, 0);
			if (n == JOptionPane.NO_OPTION) {
				StackTraceReport stacktraceReport = new StackTraceReport(null,
						true);
				stacktraceReport.showStackTraceReport(stacktrace);
			}
		} catch (HeadlessException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Function to show the Message
	 */
	public static void showMessage(Component parent, String message) {
		try {
			JOptionPane.showMessageDialog(parent, concatinate(message),
					"Error-Message", JOptionPane.ERROR_MESSAGE,
					RessourcenEnummeration.ERROR_ICON.getIcon());
		} catch (HeadlessException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Function to show the Message
	 */
	public static void show(Component parent) {
		try {
			JOptionPane.showMessageDialog(parent, "test", "Error-Message",
					JOptionPane.ERROR_MESSAGE, RessourcenEnummeration
							.getIcon(RessourcenEnummeration.ERROR_ICON));
		} catch (HeadlessException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
