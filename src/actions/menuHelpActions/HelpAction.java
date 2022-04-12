package actions.menuHelpActions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

public class HelpAction extends Action {
    ApplicationWindow _window;

    public HelpAction(ApplicationWindow window) {
	_window = window;
	setText("Help \tF1");
	setToolTipText("Help");
	// setImageDescriptor(greenImageDesc);
    }

    public void run() {

	String helpString = "Are you really need help to take care about application which contains 4 buttons?";
	MessageBox helpMessageBox = new MessageBox(_window.getShell(), SWT.OK);
	helpMessageBox.setMessage(helpString);
	helpMessageBox.setText("Help dialog");
	int answer = helpMessageBox.open();

    }
}
