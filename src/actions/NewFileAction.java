package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;

import jface.AppWindow;

public class NewFileAction extends Action {
    ApplicationWindow _window;

    public NewFileAction(ApplicationWindow window) {
	_window = window;
	setText("New File \tCtrl+N");
	setToolTipText("New File");

    }

    public void run() {
	((AppWindow) _window).getSession().clear();
	System.out.println("New File");
    }
}
