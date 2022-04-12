package actions.menuEditActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;

public class SaveRowAction extends Action {
    AppWindow _window;

    public SaveRowAction(AppWindow window) {
	_window = window;
	setText("Save record \tCtrl+M");
	setToolTipText("Save record");
	// setImageDescriptor(greenImageDesc);
    }

    public void run() {
	_window.saveRowAction();
    }
}
