package toRemove;

import java.lang.reflect.Field;

import jface.Entity;

public class a1 {

    public static void main(String[] args) {

    }

    public String[] getArrayOfEntityFields() {
	Field[] fieldsArrFields = null;
	Entity entity = new Entity();

	try {
	    fieldsArrFields = entity.getClass().getDeclaredFields();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	String[] fieldNames = new String[fieldsArrFields.length];
	for (int i = 0; i < fieldsArrFields.length; i++) {
	    fieldNames[i] = fieldsArrFields[i].getName();
	}
	return fieldNames;
    }

}
