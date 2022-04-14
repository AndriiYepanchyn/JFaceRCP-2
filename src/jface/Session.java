package jface;

import java.util.ArrayList;

public class Session {
    public ArrayList<Entity> unsavedRecords;

    public String name;
    public String group;
    public boolean swtDone;
    public Entity activeRecord;
    public String fileName;

    public Session() {
	this.unsavedRecords = new ArrayList<>();
	this.name = "";
	this.group = "";
	this.swtDone = false;
	this.activeRecord = null;
    }

    public void addEntity(String tmpname, String tmpgroup, boolean swtDone) {
	this.unsavedRecords.add(new Entity(tmpname, tmpgroup, swtDone));
    }

    public void addEntity(Entity e) {
	this.unsavedRecords.add(e);
    }

    public ArrayList<Entity> getAllRecords() {
	return unsavedRecords;
    }

// Others
    public void clear() {
	this.unsavedRecords.clear();
	;
//	this.unsavedRecords.removeAll(unsavedRecords);
	// this.unsavedRecords = new ArrayList<>();
	this.name = "";
	this.group = "";
	this.swtDone = false;
	this.activeRecord = null;
	this.fileName = "";
	// this.fileType = "";
    }

    public void removeCurrentObject() {
	if (activeRecord != null && unsavedRecords.size() > 0) {
	    unsavedRecords.remove(activeRecord);
	    activeRecord = null;
	    name = "";
	    group = "";
	    swtDone = false;
	}
    }

    @Override
    public String toString() {
	StringBuilder answer = new StringBuilder();
	for (Entity s : unsavedRecords) {
	    answer.append("Entity: (" + s + ") Name: " + s.getName() + ", group: " + s.getGroup() + ", SWT Done: "
		    + s.getSwtDone() + "\n");
	}
	return answer.toString();
    }
}
