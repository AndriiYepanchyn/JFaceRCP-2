package actions.menuFileActions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;

public class SaveFileAction extends Action {
    ApplicationWindow _window;

    public SaveFileAction(ApplicationWindow window) {
	_window = window;
	setText("Cancel \tCtrl+S");
	setToolTipText("Cancel editing record");

    }

    public void run() {

    }
}
