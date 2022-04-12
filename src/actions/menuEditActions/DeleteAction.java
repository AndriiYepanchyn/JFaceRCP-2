package actions.menuEditActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;

public class DeleteAction extends Action {
    AppWindow _window;
    // boolean isActive = true;

    public DeleteAction(AppWindow window) {
	_window = window;
	setText("Delete record \tCtrl+D");
	setToolTipText("Delete record");
	// setImageDescriptor(greenImageDesc);
	// isActive = (SessionManager.getInstatnce().activeRecord == null);
    }

    public void run() {
//	if (isActive) {
//	    this.setEnabled(false);
//	} else {
//	    this.setEnabled(true);
	_window.deleteAction();
//	}
    }
}
