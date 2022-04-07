package jface;

import java.util.ArrayList;

public class SessionManager {
    private static Session session;

    public static synchronized Session getInstatnce() {
	if (session == null) {
	    session = new Session();
	}
	return session;
    }

    private SessionManager() {
	session = new Session();
    }

    public boolean saveSessionToFile() {
	// TODO UNDONE
	System.out.println("Session saved");
	return true;
    }

    ArrayList<Entity> readFromFile(String fileName) {
	// TODO UNDONE
	return null;
    }
}
