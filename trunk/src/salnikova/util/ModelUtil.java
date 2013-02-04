package salnikova.util;

import salnikova.model.Control;
import salnikova.model.Document;
import salnikova.model.Student;

public class ModelUtil {
	public static String shortName(final Student s){
		return String.format("%s %s.", s.getSecondName(), s.getFirstName().charAt(0));
	}
	 
	public static String controlRef(final Control c){
		return String.format("<a href='?page=control&id=%s'>%s</a>", c.getId(), c.getName());
	}
	
	public static String documentRef(final Document c) {
		return String.format("<a href='doc.jsp?id=%s'>%s</a>", c.getId(), c.getName());
	}
}
