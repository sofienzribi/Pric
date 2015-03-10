package bh.reinsurance.trust.sysfacWeb.Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bh.reinsurance.trust.sysfacWeb.beans.LoginBean;

@WebFilter("/pages/admin/*")
public class FilterAdmin implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		LoginBean loginBean = (LoginBean) request2.getSession().getAttribute(
				"login");
		boolean letgo = false;
		if ((loginBean != null) && (loginBean.isConnected())
				&& loginBean.getUser().getDepartment().equals("admin")) {
			letgo = true;
		}
		if (letgo == true) {
			chain.doFilter(request, response);
		} else {
			response2.sendRedirect(request2.getContextPath() + "/login.jsf");
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
