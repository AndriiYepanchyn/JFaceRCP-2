package savers;

import java.util.ArrayList;

import jface.Entity;

public interface Savable {
    boolean saveToFile(ArrayList<Entity> unsavedRecords, String fileName);

    ArrayList<Entity> readFromFile(String fileName);
}
