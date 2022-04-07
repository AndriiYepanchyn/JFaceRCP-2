package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

public class SaveAction extends Action {
    ApplicationWindow _window;
    private static final String[] FILTER_EXTS = { "*.json", "*.txt", "*.xls", "*.csv", "*.*" };

    private static final String[] FILTER_NAMES = { "JavaScript Object Notation Files (*.JSON)", "Text Files *.txt",
	    "Microsoft Excel Spreadsheet Files (*.xls)", "Comma Separated Values Files (*.csv)", "All Files (*.*)" };

    private String fileName;

    public SaveAction(ApplicationWindow window) {
	_window = window;
	setText("Save \tCtrl+S");
	setToolTipText("Save File");

    }

    public void run() {
	FileDialog dlg = new FileDialog(_window.getShell(), SWT.SAVE);
	dlg.setFilterNames(FILTER_NAMES);
	dlg.setFilterExtensions(FILTER_EXTS);
	fileName = dlg.open();
	if (fileName == null) {
	    fileName = "";
	}
	System.out.println("fileName=" + fileName);
    }
}
