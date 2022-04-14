package savers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import jface.Entity;

public class JsonSaver implements Savable {

    @Override
    public boolean saveToFile(ArrayList<Entity> unsavedRecords, String fileName) {
	boolean answer = false;
	Gson toGson = new GsonBuilder().setPrettyPrinting().create();
	String outputString = toGson.toJson(unsavedRecords);
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
	// Get string from file
	StringBuilder out = new StringBuilder();
	try {
	    File file = new File(fileName);
	    FileReader reader = new FileReader(file);
	    Scanner scanner = new Scanner(reader);
	    while (scanner.hasNextLine()) {
		out.append(scanner.nextLine());
	    }
	    reader.close();
	    scanner.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	System.out.println("String got from file is\n" + out);
	// Start convert JSON
	ArrayList<Entity> answer = new ArrayList<>();
	if (out != null && !out.equals("")) {
	    Gson gson = new Gson();
	    Type type = new TypeToken<ArrayList<Entity>>() {
	    }.getType();
	    answer = gson.fromJson(out.toString(), type);
	} else {
	    answer = null;
	}

	System.out.println("Answer is\n" + answer);
	return answer;
    }

}
