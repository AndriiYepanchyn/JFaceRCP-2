package actions.menuFileActions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import jface.AppWindow;
import savers.FileSaveManager;

public class ExitAction extends Action {
    AppWindow _window;

    public ExitAction(AppWindow window) {
	_window = window;
	setText("Exit \tCtrl+X");
	setToolTipText("Exit Application");
    }

    public void run() {

	MessageBox saveDialogBox = new MessageBox(_window.getShell(), SWT.YES | SWT.NO | SWT.CANCEL);
	saveDialogBox.setMessage("Save changes to file?");
	saveDialogBox.setText("Save dialog");
	int answer = saveDialogBox.open();
	if (answer == SWT.YES) {// OK
	    FileSaveManager.execute(_window, false);
	    _window.close();
	} else if (answer == SWT.NO) {// CANCEL
	    System.exit(0);
	} else if (answer == SWT.CANCEL) {
	    // Do nothing
	} else
	    throw new IllegalArgumentException("Incorrect choise index (ExitAction.answer should be 64 or 128)");
    }
}
