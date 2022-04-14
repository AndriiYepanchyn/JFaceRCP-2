package actions.menuFileActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;
import jface.SessionManager;

public class NewFileAction extends Action {
    AppWindow _window;

    public NewFileAction(AppWindow window) {
	_window = window;
	setText("New File \tCtrl+N");
	setToolTipText("New File");

    }

    public void run() {
	System.out.println("was: " + SessionManager.getInstatnce().unsavedRecords);
	_window.clearSession();
	System.out.println("became: " + SessionManager.getInstatnce().unsavedRecords);
	_window.clearFields();
	_window.reassignTableInput();
	_window.redrawAll();
	System.out.println("New File");
    }
}
