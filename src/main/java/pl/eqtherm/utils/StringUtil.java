package pl.eqtherm.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringUtil {

	public static final String AT_SIGN = "@";

	public static final int NOT_FOUND = -1;

	private StringUtil() {
	}

	public static final String UTF_8 = "UTF-8";
	public static final String EMPTY = "";

	public static String encode(String value) {
		if (value == null || value.isEmpty()) {
			return EMPTY;
		}

		try {
			return URLEncoder.encode(value, UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("UTF-8 encoding does not exist!");
		}
	}

	public static String getBaseDomainFromEmail(String email) {
		String domain = getDomainFromEmail(email);
		if (StringUtils.isEmpty(domain)) {
			return StringUtils.EMPTY;
		}

		int lastIndex = domain.lastIndexOf(".");
		int baseDomainIndex = domain.substring(0, lastIndex).lastIndexOf(".");
		if (baseDomainIndex < 0) {
			return domain;
		}

		return domain.substring(baseDomainIndex + 1);
	}

	public static String getDomainFromEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return null;
		}

		int index = email.indexOf(AT_SIGN);
		if (index == NOT_FOUND) {
			return null;
		}

		return email.substring(index + 1);
	}
}
