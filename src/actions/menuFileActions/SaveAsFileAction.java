package actions.menuFileActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;
import savers.FileSaveManager;

public class SaveAsFileAction extends Action {
    AppWindow _window;

    public SaveAsFileAction(AppWindow window) {
	_window = window;
	setText("File save as \tCtrl+F");
	setToolTipText("File save as");
    }

    public void run() {
	FileSaveManager.execute(_window, true);
	_window.reassignTableInput();
	_window.redrawAll();
    }
}
