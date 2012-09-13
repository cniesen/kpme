package org.kuali.hr.time.mobile.service;

import org.kuali.hr.time.util.TKContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TkMobileFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hsRequest = (HttpServletRequest) request;
        TKContext.clear();
        TKContext.setHttpServletRequest(hsRequest);
		chain.doFilter(hsRequest, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
