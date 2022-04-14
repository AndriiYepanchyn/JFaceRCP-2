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
	getInstatnce().fileName = chooseFile();
	Savable fileData = selectSaver(getInstatnce().fileName);

	System.out.println("Instance was");
	System.out.println(getInstatnce().unsavedRecords);

	getInstatnce().unsavedRecords = fileData.readFromFile(getInstatnce().fileName);
	System.out.println("start print new instance ");
	System.out.println(getInstatnce().unsavedRecords);
    }

    private static String chooseFile() {
	String fileName = "";
	FileDialog dlg = new FileDialog(window.getShell(), SWT.OPEN);
	dlg.setFilterNames(FILTER_NAMES);
	dlg.setFilterExtensions(FILTER_EXTS);
	fileName = dlg.open();
	if (fileName != null && !fileName.equals("")) {
	    File inputFile = new File(fileName);
	    if (inputFile.exists() && inputFile.canRead()) {
	    } else {
		fileName = "";
	    }
	}
	// TODO PRocess answer cancel
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
