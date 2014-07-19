package pl.eqtherm.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

public final class PropertiesUtil {

	private static final Logger LOG = Logger.getLogger(PropertiesUtil.class.getName());

	private static final String APP_PROPERTIES = "app.properties";

	public static Properties loadProperties() throws Exception {
		Properties properties = new Properties();
		ClassLoader loader = PropertiesUtil.class.getClassLoader();
		URL url = loader.getResource(APP_PROPERTIES);
		InputStream inputStream = null;
		try {
			inputStream = url.openStream();
			properties.load(inputStream);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return properties;
	}

	public static void logAllProperties(Properties properties) {
		Enumeration<?> propertyNames = properties.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String key = propertyNames.nextElement().toString();
			if (key.startsWith("google")) {
				LOG.info(key + "=" + StringUtils.abbreviate(properties.getProperty(key), 7));
			} else {
				LOG.info(key + "=" + properties.getProperty(key));
			}
		}
	}
}
