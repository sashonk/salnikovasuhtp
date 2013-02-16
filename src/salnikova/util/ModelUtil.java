package salnikova.util;

import salnikova.model.Control;
import salnikova.model.Document;
import salnikova.model.Student;

public class ModelUtil {
	public static String shortName(final Student s){
		String secName =  s.getSecondName()==null ? "" : s.getSecondName();
		String firstL = null;
		if (s.getFirstName() != null) {
			if (s.getFirstName().length() > 1) {
				firstL = String.format(" %s.", s.getFirstName().charAt(0));
			}
 else {
				firstL = s.getFirstName();
			}
		} else {
			firstL = "";
		}

		return secName.concat(firstL);
	}
	 
	public static String controlRef(final Control c){
		return String.format("<a href='?page=control&id=%s'>%s</a>", c.getId(), c.getName());
	}
	
	public static String documentRef(final Document c) {
		return String.format("<a href='doc.jsp?id=%s'>%s</a>", c.getId(), c.getName());
	}
}
