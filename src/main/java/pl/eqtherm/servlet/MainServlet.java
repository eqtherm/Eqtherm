package pl.eqtherm.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.eqtherm.utils.ServletRequestUtils;

import com.google.inject.Singleton;

@Singleton
public class MainServlet extends AbstractServlet {

	private static final long serialVersionUID = 9215865324907289383L;

	private static final Logger LOG = Logger.getLogger(MainServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showForm(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		LOG.info(ServletRequestUtils.createInfoAbout(request));
	}
}