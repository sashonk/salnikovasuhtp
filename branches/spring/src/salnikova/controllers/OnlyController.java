package salnikova.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class OnlyController extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		System.out.println("in only");
		String path = req.getServletPath();
		
		
		ModelAndView view = new ModelAndView("index");



		if (path != null && path.length() > 0 && !path.contains("index")) {
			path = path.replace(".html", "");
			view.addObject("page", path.substring(1));
		}
 else if (path.contains("index")) {
			view.addObject("page", "summary");
		}
		
		return view;
	}

}
