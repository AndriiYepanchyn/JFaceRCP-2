package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

public class CancelAction extends Action {
    ApplicationWindow _window;

    public CancelAction(ApplicationWindow window) {
	_window = window;
	setText("Exit \tCtrl+X");
	setToolTipText("Exit Application");
	// setImageDescriptor(greenImageDesc);
    }

    public void run() {
	MessageBox saveDialogBox = new MessageBox(_window.getShell(), SWT.YES | SWT.NO);
	saveDialogBox.setMessage("Save changes to file?");
	saveDialogBox.setText("Save dialog");
	int answer = saveDialogBox.open();
	if (answer == 64) {// OK
	    System.out.println("OK is chosen");
	    // TODO

	    // ((AppWindow) _window).getSession().saveSession();
	} else if (answer == 128) {// CANCEL
	    System.out.println("CANCEL is chosen");

	} else
	    throw new IllegalArgumentException("Incorrect choise index (ExitAction.answer should be 64 or 128)");
	_window.close();
    }
}
