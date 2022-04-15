package savers;

import static jface.SessionManager.getInstatnce;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;

import jface.AppWindow;

public class FileSaveManager {
    private static final String[] FILTER_NAMES = { "JSON Files (*.json)", "Plain text (*.txt)", "All Files (*.*)" };

    // These filter extensions are used to filter which files are displayed.
    private static final String[] FILTER_EXTS = { "*.json", "*.txt", "*.*" };

    private static AppWindow window;

    // SaveWithConfirmationOfFileName = true - ask file name in DialogBox, = false
    // use fileName saved in session
    public static void execute(AppWindow inWindow, boolean SaveWithFileSelection) {

	window = inWindow;
	String tempFileName;
	if (SaveWithFileSelection) {
	    tempFileName = chooseFile();
	} else {
	    tempFileName = getInstatnce().fileName;
	    if (!isFileNameCorrect(tempFileName)) {
		FileSaveManager.execute(inWindow, true);
	    }
	}
	if (isFileNameCorrect(tempFileName)) {
	    getInstatnce().fileName = tempFileName;
	    Savable fileData = selectSaver(getInstatnce().fileName);
	    fileData.saveToFile(getInstatnce().unsavedRecords, getInstatnce().fileName);
	} else {
	    // Errorbox
	    MessageBox incorrectFileDialogBox = new MessageBox(window.getShell(), SWT.OK);
	    incorrectFileDialogBox.setMessage("There wasn't correct file name entered");
	    incorrectFileDialogBox.setText("WARNING! File wasn't saved");
	    int answer = incorrectFileDialogBox.open();
	}
    }

    private static String chooseFile() {
	String fileName = "";
	FileDialog dlg = new FileDialog(window.getShell(), SWT.SAVE);
	dlg.setFilterNames(FILTER_NAMES);
	dlg.setFilterExtensions(FILTER_EXTS);
	fileName = dlg.open();
	if (fileName == null) {
	    fileName = "";
	}
	return fileName;
    }

    private static Savable selectSaver(String filename) {
	int start = filename.lastIndexOf(".");
	String extenstion = filename.substring(start + 1).toUpperCase();
	switch (extenstion) {
	case "JSON":
	    return new JsonSaver();
	case "TXT":
	    return new TxtSaver();
	default:
	    System.out.println("Incorrect file type");
	    return null;
	}
    }

    private static boolean isFileNameCorrect(String fileName) {
	if (fileName != null && fileName.length() > 0) {
	    File f = new File(fileName);
	    try {
		if (f.exists() || (f.createNewFile()) && f.canWrite()) {
		    return true;
		} else
		    return false;
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	    }
	} else
	    return false;
    }
}
