package savers;

import static jface.SessionManager.getInstatnce;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import jface.AppWindow;
import jface.Entity;

public class FileReadManager {
    ArrayList<Entity> recordsToLoadArrayList = new ArrayList<>();
    StringBuilder inputString = new StringBuilder();
    private static final String[] FILTER_NAMES = { "JSON Files (*.json)", "Plain text (*.txt)", "All Files (*.*)" };
    // These filter extensions are used to filter which files are displayed.
    private static final String[] FILTER_EXTS = { "*.json", "*.txt", "*.*" };

    private static AppWindow window;

    public static void execute(AppWindow inWindow) {
	window = inWindow;
	String tempFileName = chooseFile();
	if (tempFileName != null && !tempFileName.equals("")) {
	    getInstatnce().fileName = tempFileName;
	    Savable fileData = selectSaver(getInstatnce().fileName);
	    getInstatnce().unsavedRecords = fileData.readFromFile(getInstatnce().fileName);
	}
    }

    private static String chooseFile() {
	String fileName = "";
	FileDialog dlg = new FileDialog(window.getShell(), SWT.OPEN);
	dlg.setFilterNames(FILTER_NAMES);
	dlg.setFilterExtensions(FILTER_EXTS);
	fileName = dlg.open();
	if (fileName != null && !fileName.equals("")) {
	    File inputFile = new File(fileName);
	    if (!inputFile.exists() || !inputFile.canRead()) {
		fileName = "";
	    }
	}
	return fileName;
    }

    private static Savable selectSaver(String filename) {
	// System.out.println("file" + filename);
	int start = filename.lastIndexOf(".");
	String extenstion = filename.substring(start + 1).toUpperCase();
	switch (extenstion) {
	case "JSON":
	    // System.out.println("JSON TYPE Selected");
	    return new JsonSaver();
	case "TXT":
	    // System.out.println("TXT type selected");
	    return new TxtSaver();
	default:
	    System.out.println("Incorrect file type");
	    return null;
	}
    }

}
