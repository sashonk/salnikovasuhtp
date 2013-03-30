package salnikova.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Pattern;

public class Utils {
	private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("([0-9A-Za-z-_]+\\.)*[0-9A-Za-z-_]+@((([0-9A-Za-z-_]+\\.)+[A-Za-z]+)|(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}))");

	public static String toString(final Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public static boolean isBlank(final String str) {
		return str == null || str.equals("");
	}

	public static boolean isValidEmailAddress(final String str) {
		if (isBlank(str)) {
			return false;
		}
		return EMAIL_ADDRESS_PATTERN.matcher(str).matches();
	}

	public static void main(String[] a) {
		System.out.println(getSha("tutor"));
	}


	public static String getSha(final String text) {
		try {
			MessageDigest d = MessageDigest.getInstance("sha1");
			d.update(text.getBytes());
			BigInteger value = new BigInteger(1, d.digest());
			return value.toString(16);

		} catch (Exception ex) {
			System.err.println(ex);
		}

		return null;
	}
}
