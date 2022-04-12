package actions.menuFileActions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

public class ExitAction extends Action {
    ApplicationWindow _window;

    public ExitAction(ApplicationWindow window) {
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
	} else if (answer == 128) {// CANCEL
	    System.exit(0);
	} else
	    throw new IllegalArgumentException("Incorrect choise index (ExitAction.answer should be 64 or 128)");
	_window.close();
    }
}
