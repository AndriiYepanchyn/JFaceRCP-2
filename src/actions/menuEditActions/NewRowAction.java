package actions.menuEditActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;

public class NewRowAction extends Action {
    AppWindow _window;

    public NewRowAction(AppWindow window) {
	_window = window;
	setText("New row \tCtrl+R");
	setToolTipText("New row");
	// setImageDescriptor(greenImageDesc);
    }

    public void run() {
	_window.newRecordAction();
    }
}
