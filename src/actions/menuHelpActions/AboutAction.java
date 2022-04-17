package actions.menuHelpActions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

public class AboutAction extends Action {
    ApplicationWindow _window;

    public AboutAction(ApplicationWindow window) {
	_window = window;
	setText("About program \tCtrl+F10");
	setToolTipText("About program");
    }

    public void run() {
	String helpString = "This is a simple Jface application intended to store information about the interns which have been passed this task. \n"
		+ "Application is made by _Alien_ \nVersion 1.00. \nDistributed on general GNU license or some similar way  ;-)";
	MessageBox aboutMessageBox = new MessageBox(_window.getShell(), SWT.OK);
	aboutMessageBox.setMessage(helpString);
	aboutMessageBox.setText("About program");
	aboutMessageBox.open();
    }
}
