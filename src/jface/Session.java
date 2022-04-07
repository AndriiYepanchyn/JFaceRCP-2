package jface;

import java.util.ArrayList;

public class Session {
    private ArrayList<Entity> unsavedRecords;

    private String name;
    private int group;
    private boolean swtDone;
    private int activeRecord;
    private String fileName;
    private String fileType;

    public Session() {
	this.unsavedRecords = new ArrayList<>();
	this.name = "";
	this.group = 0;
	this.swtDone = false;
	this.activeRecord = 0;
    }

//Setters
    public void setName(String name) {
	this.name = name;
    }

    public void setGroup(int group) {
	this.group = group;
    }

    public void setSWTDone(boolean isDone) {
	this.swtDone = isDone;
    }

    public void setActiveRecord(int activeRecord) {
	this.activeRecord = activeRecord;
    }

    public void setfileName(String fileName) {
	this.fileName = fileName;
    }

    public void setFileType(String fileType) {
	this.fileType = fileType;
    }

    public int getActiveRecord() {
	return activeRecord;
    }

    public void addEntity(String name, int group, boolean swtDone) {
	this.unsavedRecords.add(new Entity(name, group, swtDone));
    }

    public void addEntity(Entity e) {
	this.unsavedRecords.add(e);
    }

// Getters
    public Entity getEntity(int index) {
	if (index >= unsavedRecords.size() || index < 0) {
	    return null;
	} else {
	    return unsavedRecords.get(index);
	}
    }

    public Entity[] getAllRecords() {
	int size = unsavedRecords.size();
	Entity[] answerEntities = unsavedRecords.toArray(new Entity[size]);
	return answerEntities;
    }

    public String getFileName() {
	return this.fileName;
    }

    public String getFileType() {
	return this.fileType;
    }

// Others
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

    @Override
    public String toString() {
	StringBuilder answer = new StringBuilder();
	for (Entity s : unsavedRecords) {
	    answer.append("Name: " + s.getName() + ", group: " + s.getGroup() + ", SWT Done: " + s.isSwtDone() + "\n");
	}
	return answer.toString();
    }
}
