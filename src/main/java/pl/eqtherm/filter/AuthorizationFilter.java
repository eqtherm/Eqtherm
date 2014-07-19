package pl.eqtherm.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import pl.eqtherm.utils.StringUtil;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Singleton;

@Singleton
public class AuthorizationFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(AuthorizationFilter.class.getName());

	private final UserService userService;

	@Inject
	public AuthorizationFilter() {
		this.userService = UserServiceFactory.getUserService();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		if (isUserAllowed()) {
			// String userEmail = userService.getCurrentUser().getEmail();
			chain.doFilter(req, resp);
			// LOG.warning("User " + userEmail + " is not granted access");
			// req.setAttribute("email", userEmail);
			// req.getRequestDispatcher("/WEB-INF/noaccess.jsp").forward(req,
			// resp);
		} else {
			((HttpServletResponse) resp).sendRedirect(createLoginPageUrl((HttpServletRequest) req));
		}
	}

	private String createLoginPageUrl(HttpServletRequest req) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(req.getRequestURL().toString());
		String queryString = getParametersAsQueryString(req);
		if (StringUtils.isNotEmpty(queryString)) {
			urlBuilder.append("?").append(queryString);
		}
		String loginUrl = urlBuilder.toString();
		LOG.info("User is not log in. Redirect to login page using URL: " + loginUrl);

		return userService.createLoginURL(loginUrl);
	}

	protected boolean isUserAllowed() {
		return true;
	}

	private String getParametersAsQueryString(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = params.nextElement();
			String value = request.getParameter(name);
			if (builder.length() > 0) {
				builder.append("&");
			}
			builder.append(name + "=" + StringUtil.encode(value));
		}

		return builder.toString();
	}
}
