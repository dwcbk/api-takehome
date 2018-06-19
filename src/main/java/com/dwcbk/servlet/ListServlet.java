package com.dwcbk.servlet;

import com.dwcbk.beans.User;
import com.dwcbk.beans.UserLocation;
import com.dwcbk.dao.UserDao;
import com.dwcbk.dao.UserDaoFactory;
import com.dwcbk.dao.UserLocationDao;
import com.dwcbk.dao.UserLocationDaoFactory;
import com.dwcbk.exception.FumbleBadRequestException;
import com.dwcbk.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
		name = "ListServlet",
		urlPatterns = "/list"
)
/**
 * Servlet used for testing purposes. Simply lists user locations.
 */
public class ListServlet extends HttpServlet {

	private static final String USER_ID = "userId";

	private final UserLocationDao userLocationDao;
	private final UserDao userdao;

	public ListServlet() {
		this.userLocationDao = UserLocationDaoFactory.getInstance();
		this.userdao = UserDaoFactory.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userIdVal = req.getParameter(USER_ID);
		if (StringUtils.isEmpty(userIdVal)) {
			throw new FumbleBadRequestException("Missing userId param");
		}
		Long userId = null;
		try {
			userId = Long.parseLong(userIdVal);
		} catch (NumberFormatException e) {
			throw new FumbleBadRequestException("Invalid userId format");
		}


		User user = userdao.getUser(userId);
		List<UserLocation> locations = userLocationDao.list(user);
		resp.setContentType("application/json");
		String output = Mapper.getMapper().writeValueAsString(locations);
		try (PrintWriter out = resp.getWriter()) {
			out.println(output);
		}
	}
}
