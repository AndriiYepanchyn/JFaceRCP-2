package jface;

import java.util.ArrayList;

public class Session {
    ArrayList<Entity> unsavedRecords;

    String name;
    int group;
    boolean swtDone;

    int activeRecord;

    public Session() {
	this.unsavedRecords = new ArrayList<>();
	this.name = "";
	this.group = 0;
	this.swtDone = false;
	this.activeRecord = 0;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setGroup(int group) {
	this.group = group;
    }

    public void setSWTDone(boolean isDone) {
	this.swtDone = isDone;
    }

    public int getActiveRecord() {
	return activeRecord;
    }

    public void setActiveRecord(int activeRecord) {
	this.activeRecord = activeRecord;
    }

    public void addEntity(String name, int group, boolean swtDone) {
	this.unsavedRecords.add(new Entity(name, group, swtDone));
    }

    public void addEntity(Entity e) {
	this.unsavedRecords.add(e);
    }

    public Entity getEntity(int index) {
	if (index >= unsavedRecords.size() || index < 0) {
	    return null;
	} else {
	    return unsavedRecords.get(index);
	}
    }

    public ArrayList<Entity> getAllRecords() {
	return unsavedRecords;
    }

    public void clear() {
	this.unsavedRecords = new ArrayList<>();
	this.name = "";
	this.group = 0;
	this.swtDone = false;
	this.activeRecord = 0;
    }

    public void removeByIndex(int index) {
	if (index >= 0 && index < unsavedRecords.size())
	    unsavedRecords.remove(index);
    }
}
