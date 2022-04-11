package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;

public class SaveAction extends Action {
    ApplicationWindow _window;

    public SaveAction(ApplicationWindow window) {
	_window = window;
	setText("Cancel \tCtrl+S");
	setToolTipText("Cancel editing record");

    }

    public void run() {

    }
}
