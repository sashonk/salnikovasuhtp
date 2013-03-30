package salnikova.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class AjaxController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		System.out.println("in ajax");
		return new ModelAndView("ajax");
	}

}
