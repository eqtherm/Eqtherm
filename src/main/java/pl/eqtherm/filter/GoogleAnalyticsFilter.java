package pl.eqtherm.filter;

import java.io.IOException;

import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class GoogleAnalyticsFilter implements Filter {

	@Inject
	@Named("appengineid")
	private String appEngineId;

	@Inject
	@Named("google.analytics.id")
	private String googleAnalyticsId;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("globalAppEngineId", appEngineId);
		request.setAttribute("globalGoogleAnalyticsId", googleAnalyticsId);
		chain.doFilter(request, response);
	}
}
