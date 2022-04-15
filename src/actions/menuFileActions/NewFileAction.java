package actions.menuFileActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;

public class NewFileAction extends Action {
    AppWindow _window;

    public NewFileAction(AppWindow window) {
	_window = window;
	setText("New File \tCtrl+N");
	setToolTipText("New File");

    }

    public void run() {
	_window.clearSession();
	_window.clearFields();
	_window.reassignTableInput();
	_window.redrawAll();
    }
}
