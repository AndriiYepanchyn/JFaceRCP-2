package toRemove;
//Send questions, comments, bug reports, etc. to the authors:

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * This class demonstrates FileDialog
 */
public class ShowFileDialog {
    // These filter names are displayed to the user in the file dialog. Note that
    // the inclusion of the actual extension in parentheses is optional, and
    // doesn't have any effect on which files are displayed.
    private static final String[] FILTER_NAMES = { "OpenOffice.org Spreadsheet Files (*.sxc)",
	    "Microsoft Excel Spreadsheet Files (*.xls)", "Comma Separated Values Files (*.csv)", "All Files (*.*)" };

    // These filter extensions are used to filter which files are displayed.
    private static final String[] FILTER_EXTS = { "*.sxc", "*.xls", "*.csv", "*.*" };

    /**
     * Runs the application
     */
    public void run() {
	Display display = new Display();
	Shell shell = new Shell(display);
	shell.setText("File Dialog");
	createContents(shell);
	shell.pack();
	shell.open();
	while (!shell.isDisposed()) {
	    if (!display.readAndDispatch()) {
		display.sleep();
	    }
	}
	display.dispose();
    }

    /**
     * Creates the contents for the window
     * 
     * @param shell the parent shell
     */
    public void createContents(final Shell shell) {
	shell.setLayout(new GridLayout(5, true));

	final String fileNameString;

	FileDialog dlg = new FileDialog(shell, SWT.SAVE);
	dlg.setFilterNames(FILTER_NAMES);
	dlg.setFilterExtensions(FILTER_EXTS);
	String fn = dlg.open();
	if (fn != null) {
	    fileNameString = fn;
	}
    }

    /**
     * The application entry point
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	new ShowFileDialog().run();
    }
}
