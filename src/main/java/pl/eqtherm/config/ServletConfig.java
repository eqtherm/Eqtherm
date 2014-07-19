package pl.eqtherm.config;

import java.util.Properties;

import pl.eqtherm.filter.AuthorizationFilter;
import pl.eqtherm.filter.CharacterEncodingFilter;
import pl.eqtherm.filter.GoogleAnalyticsFilter;
import pl.eqtherm.servlet.MainServlet;
import pl.eqtherm.utils.PropertiesUtil;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;

public class ServletConfig extends ServletModule {

	@Override
	protected void configureServlets() {
		Properties props;
		try {
			props = PropertiesUtil.loadProperties();
		} catch (Exception e) {
			throw new IllegalStateException("Could not load properties", e);
		}

		Names.bindProperties(binder(), props);
		PropertiesUtil.logAllProperties(props);
		filter("/*").through(CharacterEncodingFilter.class);
		filter("/*").through(GoogleAnalyticsFilter.class);
		filter("/*").through(AuthorizationFilter.class);

		serve("/").with(MainServlet.class);
	}
}
