package actions.menuEditActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;

public class CancelAction extends Action {
    AppWindow _window;

    public CancelAction(AppWindow window) {
	_window = window;
	setText("Cancel \tCtrl+Q");
	setToolTipText("Cancel");
	// setImageDescriptor(greenImageDesc);
    }

    public void run() {
	_window.clearFields();
    }
}
