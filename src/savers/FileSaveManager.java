package savers;

import static jface.SessionManager.getSession;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;

import jface.AppWindow;
import jface.Session;
import jface.SessionManager;

public class FileSaveManager {
    private static final String[] FILTER_NAMES = { "JSON Files (*.json)", "Plain text (*.txt)", "All Files (*.*)" };
    private static final String[] FILTER_EXTS = { "*.json", "*.txt", "*.*" };
    private static AppWindow window;

    public static void execute(AppWindow inWindow, boolean isSaveAs) {

	window = inWindow;
	String tempFileName;
	Session session = SessionManager.getSession();
	if (isSaveAs || session.isNewFile) {
	    tempFileName = chooseFile();
	} else {
	    tempFileName = session.fileName;
	}

	if (isFileNameCorrect(tempFileName)) {
	    Savable fileData = selectSaver(tempFileName);
	    fileData.saveToFile(getSession().unsavedRecords, tempFileName);
	    getSession().fileName = tempFileName;
	    getSession().isNewFile = false;
	} else {
	    // Errorbox
	    MessageBox incorrectFileDialogBox = new MessageBox(window.getShell(), SWT.OK);
	    incorrectFileDialogBox.setMessage("There wasn't correct file name entered");
	    incorrectFileDialogBox.setText("WARNING! File wasn't saved");
	    incorrectFileDialogBox.open();
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
		return (f.exists() || (f.createNewFile()) && f.canWrite());
	    } catch (IOException e) {
		e.printStackTrace();
		return false;
	    }
	} else {
	    return false;
	}
    }
}
