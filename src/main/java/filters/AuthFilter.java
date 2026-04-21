package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
public class AuthFilter extends HttpFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4032613782405408071L;

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String requestURI = request.getRequestURI();
		boolean isPublic = requestURI.contains("/login") || requestURI.contains("/logout");
		if (isPublic) {
			chain.doFilter(request, response);
			return;

		}

		HttpSession session = request.getSession(false);
		boolean isLoggedIn = (session != null && session.getAttribute("utilisateur") != null);

		if (isLoggedIn) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}

	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
