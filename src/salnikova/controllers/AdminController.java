package salnikova.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class AdminController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		System.out.println("in admin");
		String path = req.getServletPath();

		ModelAndView view = new ModelAndView("home");

		if (path != null && path.length() > 0 && !path.contains("home")) {
			path = path.replace("admin/", "").replace(".html", "");
			view.addObject("page", path.substring(1));
		} else if (path.contains("index")) {
			view.addObject("page", "management");
		}

		return view;
	}

}
