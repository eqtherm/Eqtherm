package pl.eqtherm.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ServletContextListener extends GuiceServletContextListener {

	private final ServletConfig servletConfig = new ServletConfig();

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(servletConfig);
	}
}
