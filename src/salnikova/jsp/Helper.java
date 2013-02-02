package salnikova.jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class Helper {
	public static void logout(final HttpServletRequest req) throws ServletException{
		req.logout(); 
	}
}
