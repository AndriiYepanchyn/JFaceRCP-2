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

import com.google.gson.Gson;

import jface.Entity;

public class TxtSaver implements Savable {

    @Override
    public boolean saveToFile(ArrayList<Entity> unsavedRecords, String fileName) {
	boolean answer = false;
	String outputString = unsavedRecords.toString();
	System.out.println("output string: \n" + outputString);
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
	Gson gson = new Gson();
	// ArrayList<Entity> answer = new ArrayList<>();
	// TODO UNDONE
//	{
//	Type type = new TypeToken<Map<String, Integer>>(){}.getType();
//	answer=gson.fromJson(out.toString(), ArrayList<Entity>.getClass());
//	}
	return null;

    }

}
