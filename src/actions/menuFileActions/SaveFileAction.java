package actions.menuFileActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;
import jface.SessionManager;
import savers.FileSaveManager;

public class SaveFileAction extends Action {
    AppWindow _window;

    public SaveFileAction(AppWindow window) {
	_window = window;
	setText("Save file \tCtrl+S");
	setToolTipText("Save File");
    }

    public void run() {
	FileSaveManager.execute(_window, false);
	_window.getShell().setText("JFace application:  " + SessionManager.getSession().fileName);
	_window.reassignTableInput();
	_window.redrawAll();
    }
}
