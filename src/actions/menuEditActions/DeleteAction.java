package actions.menuEditActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;

public class DeleteAction extends Action {
    AppWindow _window;
    boolean isActive = true;

    public DeleteAction(AppWindow window, boolean status) {
	_window = window;
	setText("Delete record \tCtrl+D");
	setToolTipText("Delete record");
	isActive = status;
	this.setEnabled(isActive);
    }

    public void run() {
	_window.deleteAction();
    }
}
