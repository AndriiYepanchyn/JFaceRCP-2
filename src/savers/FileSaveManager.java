package savers;

import static jface.SessionManager.getInstatnce;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;

import jface.AppWindow;

public class FileSaveManager {
    private static final String[] FILTER_NAMES = { "JSON Files (*.json)", "Plain text (*.txt)", "All Files (*.*)" };

    // These filter extensions are used to filter which files are displayed.
    private static final String[] FILTER_EXTS = { "*.json", "*.txt", "*.*" };

    private static AppWindow window;

    public static void execute(AppWindow inWindow) {
	window = inWindow;
	getInstatnce().fileName = chooseFile(); // choose file
	if (getInstatnce().fileName != null && getInstatnce().fileName != "") {
	    Savable fileData = selectSaver(getInstatnce().fileName);
	    // System.out.println("Savable: " + fileData);
	    fileData.saveToFile(getInstatnce().unsavedRecords, getInstatnce().fileName);
	}
    }

    private static String chooseFile() {
	String fileName = "";
	FileDialog dlg = new FileDialog(window.getShell(), SWT.SAVE);
	dlg.setFilterNames(FILTER_NAMES);
	dlg.setFilterExtensions(FILTER_EXTS);
	fileName = dlg.open();
	if (fileName == null) {
	    // Errorbox
	    MessageBox incorrectFileDialogBox = new MessageBox(window.getShell(), SWT.OK);
	    incorrectFileDialogBox.setMessage("There wasn't correct file name entered");
	    incorrectFileDialogBox.setText("Incorrect file name");
	    int answer = incorrectFileDialogBox.open();
	    fileName = "";
	}

	System.out.println("Input filename: " + fileName);
	return fileName;
    }

    private static Savable selectSaver(String filename) {
	int start = filename.lastIndexOf(".");
	String extenstion = filename.substring(start + 1).toUpperCase();
	switch (extenstion) {
	case "JSON":
	    System.out.println("JSON TYPE Selected");
	    return new JsonSaver();
	case "TXT":
	    System.out.println("TXT type selected");
	    return new TxtSaver();
	default:
	    System.out.println("Incorrect file type");
	    return null;
	}

    }

}
