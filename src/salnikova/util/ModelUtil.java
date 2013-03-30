package salnikova.util;

import salnikova.model.Control;
import salnikova.model.Document;
import salnikova.model.Identity;
import salnikova.model.Student;

public class ModelUtil {

	private ModelUtil() {
		// no-op
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

	public static String shortName(final HasName model) {
		StringBuilder sb = new StringBuilder();
		sb.append(model.getLastName());
		sb.append(' ');

		String firstName = model.getFirstName();
		if (firstName.length() > 1) {
			firstName = firstName.substring(0, 1).concat(".");
		}
		sb.append(firstName);

		String middleName = model.getMiddleName();
		if (!Utils.isBlank(middleName)) {
			if (middleName.length() > 1) {
				middleName = middleName.substring(0, 1).concat(".");
			}
			sb.append(' ');
			sb.append(middleName);
		}

		return sb.toString();
	}

	public static String fullName(final HasName model) {
		StringBuilder sb = new StringBuilder();
		sb.append(model.getLastName());
		sb.append(' ');
		sb.append(model.getFirstName());
		if (!Utils.isBlank(model.getMiddleName())) {
			sb.append(' ');
			sb.append(model.getMiddleName());
		}
		return sb.toString();
	}
	 
	public static String controlRef(final Control c){

		return String.format("<a href='control.html&id=%s'>%s</a>", c.getId(),
				c.getName());
	}
	
	public static String documentRef(final Document c) {
		return String.format("<a href='doc.jsp?id=%s'>%s</a>", c.getId(), c.getName());
	}


}
