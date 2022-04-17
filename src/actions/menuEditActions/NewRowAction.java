package actions.menuEditActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;

public class NewRowAction extends Action {
    AppWindow _window;
    boolean isActive;

    public NewRowAction(AppWindow window, boolean status) {
	_window = window;
	setText("New row \tCtrl+R");
	setToolTipText("New row");
	isActive = status;
	this.setEnabled(isActive);
    }

    public void run() {
	_window.newRecordAction();
    }
}
