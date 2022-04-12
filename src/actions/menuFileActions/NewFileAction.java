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
	SessionManager.getInstatnce().clear();
	System.out.println("New File");

    }
}
