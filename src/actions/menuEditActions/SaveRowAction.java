package actions.menuEditActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;

public class SaveRowAction extends Action {
    AppWindow _window;
    boolean isActive;

    public SaveRowAction(AppWindow window, boolean status) {
	this._window = window;
	this.setText("Save record \tCtrl+M");
	this.setToolTipText("Save record");
	this.isActive = status;
	this.setEnabled(isActive);
    }

    public void run() {
	_window.saveRowAction();
    }
}
