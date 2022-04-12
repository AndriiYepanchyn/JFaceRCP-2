package actions.menuFileActions;

import org.eclipse.jface.action.Action;

import jface.AppWindow;
import savers.FileSaveManager;

public class SaveFileAction extends Action {
    AppWindow _window;

    public SaveFileAction(AppWindow window) {
	_window = window;
	setText("Save file \tCtrl+S");
	setToolTipText("Save file");
    }

    public void run() {
	FileSaveManager.execute(_window);
    }
}
