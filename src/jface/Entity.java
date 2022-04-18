package jface;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Entity {
    private String name;
    private String group;
    private boolean swtDone;

    public Entity() {
    }

    public Entity(String name, String group, boolean swtDone) {
	this.name = name;
	this.group = group;
	this.swtDone = swtDone;
    }

    public String getName() {
	if (name == null) {
	    return "";
	} else {
	    return name;
	}
    }

    public void setName(String name) {
	if (name == null) {
	    this.name = "";
	} else {
	    this.name = name;
	}
    }

    public String getGroup() {
	if (group == null) {
	    return "";
	} else {
	    return group;
	}
    }

    public void setGroup(String group) {
	if (group == null) {
	    this.group = "";
	} else {
	    this.group = group;
	}
    }

    public boolean getSwtDone() {
	return swtDone;
    }

    public void setSwtDone(boolean swtDone) {
	this.swtDone = swtDone;
    }

    public static String[] getFieldsNames() {
	Field[] fields = null;
	Entity entity = new Entity();
	String[] fieldNames = null;
	try {
	    fields = entity.getClass().getDeclaredFields();
	    fieldNames = new String[fields.length];
	    for (int i = 0; i < fields.length; i++) {
		fieldNames[i] = fields[i].getName();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return fieldNames;
    }

    public static HashMap<String, Method> getRefferedMethods() {
	HashMap<String, Method> methodAssosiations = new HashMap<String, Method>();
	String[] methodNames = Entity.getFieldsNames();
	int len = methodNames.length;
	Method[] methods = new Method[len];
	@SuppressWarnings("rawtypes")
	Class entityClass;
	try {
	    entityClass = Class.forName(Entity.class.getName());
	    methods = entityClass.getDeclaredMethods();
	    for (int i = 0; i < len; i++) {
		String firString = String.valueOf(methodNames[i].charAt(0));
		String metString = "get" + methodNames[i].replaceFirst(firString, firString.toUpperCase());
		@SuppressWarnings("unchecked")
		Method tmpMethod = entityClass.getDeclaredMethod(metString);
		if (tmpMethod == null) {
		    System.out.println("Error in getting method " + metString);
		} else {
		    methodAssosiations.put(methodNames[i], tmpMethod);
		}
	    }
	} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
	    e.printStackTrace();
	}
	return methodAssosiations;
    }

    @Override
    public String toString() {
	return "[name=" + name + "; group=" + group + "; swtDone=" + swtDone + ";]\n";
    }
}
