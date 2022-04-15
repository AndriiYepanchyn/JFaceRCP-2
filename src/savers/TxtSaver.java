package savers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import jface.Entity;

public class TxtSaver implements Savable {

    @Override
    public boolean saveToFile(ArrayList<Entity> unsavedRecords, String fileName) {
	boolean answer = false;
	String outputString = unsavedRecords.toString();
	File myFile = new File(fileName);
	try {
	    @SuppressWarnings("resource")
	    OutputStream fileStream = new FileOutputStream(myFile);
	    @SuppressWarnings("resource")
	    Writer writer = new BufferedWriter(new OutputStreamWriter(fileStream));
	    writer.write(outputString);
	    writer.close();
	    answer = true;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return answer;
    }

    @Override
    public ArrayList<Entity> readFromFile(String fileName) {
	ArrayList<Entity> out = new ArrayList<>();
	try {
	    File file = new File(fileName);
	    FileReader reader = new FileReader(file);
	    Scanner scanner = new Scanner(reader);
	    while (scanner.hasNextLine()) {
		String tmpString = scanner.nextLine();
		Entity tmp = additiveToOut(tmpString);
		if (tmp != null)
		    out.add(tmp);
	    }
	    scanner.close();
	    reader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return out;

    }

    private Entity additiveToOut(String tmp) {
	if (tmp.contains("name=") && tmp.contains("group=") && tmp.contains("swtDone=")) {
	    String name, group;
	    boolean swtDone;
	    Entity record;
	    int startNameIndex, endNameIndex;
	    int startGroupIndex, endGroupIndex;
	    int startSWTIndex, endSWTIndex;
	    startNameIndex = tmp.indexOf("name=");
	    endNameIndex = tmp.indexOf(";");
	    startGroupIndex = tmp.indexOf("group=");
	    endGroupIndex = tmp.indexOf(";", startGroupIndex);
	    startSWTIndex = tmp.indexOf("swtDone=");
	    name = tmp.substring(startNameIndex, endNameIndex).replaceFirst("name=", "");
	    group = tmp.substring(startGroupIndex, endGroupIndex).replaceFirst("group=", "");
	    if (tmp.substring(startSWTIndex).replaceFirst("swtDone=", "").contains("true")) {
		swtDone = true;
	    } else {
		swtDone = false;
	    }
	    record = new Entity(name, group, swtDone);
	    return record;
	} else
	    return null;

    }
}
