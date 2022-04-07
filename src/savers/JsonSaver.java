package savers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jface.Entity;

public class JsonSaver implements Savable {

    @Override
    public boolean saveToFile(ArrayList<Entity> unsavedRecords, String fileName) {
	boolean answer = false;
	Gson toGson = new GsonBuilder().setPrettyPrinting().create();
	toGson.toJson(unsavedRecords);
	File myFile = new File(fileName);
	try {
	    FileWriter fileWriter = new FileWriter(myFile);
	    fileWriter.write(toGson.toString());
	    fileWriter.close();
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
