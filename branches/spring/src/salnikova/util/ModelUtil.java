package salnikova.util;

import salnikova.model.Control;
import salnikova.model.Document;
import salnikova.model.Identity;
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

	public boolean equals(final Identity i1, final Identity i2) {
		if (i1 == null || i2 == null) {
			throw new NullPointerException();
		}

		if (i1.getClass() != i2.getClass()) {
			return false;
		}

		return i1.getId().equals(i2.getId());
	}
	 
	public static String controlRef(final Control c){

		return String.format("<a href='control.html&id=%s'>%s</a>", c.getId(),
				c.getName());
	}
	
	public static String documentRef(final Document c) {
		return String.format("<a href='doc.jsp?id=%s'>%s</a>", c.getId(), c.getName());
	}
}
