package jface;

public class SessionManager {
    private static Session session;

    public static synchronized Session getSession() {
	if (session == null) {
	    session = new Session();
	}
	return session;
    }

    private SessionManager() {
	session = new Session();
    }
}
