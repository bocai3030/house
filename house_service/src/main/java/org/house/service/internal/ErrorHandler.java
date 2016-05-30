package org.house.service.internal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class ErrorHandler implements ErrorController {
	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(value = "/error")
	public void error(final HttpServletRequest request, final HttpServletResponse response) {
		final RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		final Throwable throwable = this.errorAttributes.getError(requestAttributes);
		throwable.printStackTrace();
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
