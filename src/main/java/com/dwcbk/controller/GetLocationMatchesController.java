package com.dwcbk.controller;

import com.dwcbk.beans.User;
import com.dwcbk.beans.UserLocationMatch;
import com.dwcbk.dao.UserDao;
import com.dwcbk.dao.UserDaoFactory;
import com.dwcbk.exception.FumbleBadRequestException;
import com.dwcbk.exception.FumbleException;
import com.dwcbk.services.UserLocationService;
import com.dwcbk.services.UserLocationServiceImpl;
import com.dwcbk.util.Mapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Controller for searching for user's friends' matching locations.
 */
public class GetLocationMatchesController extends BaseController {
	private static final String USER_ID = "userId";

	private final UserLocationService userLocationService;
	private final UserDao userdao;

	public GetLocationMatchesController() {
		this.userLocationService = new UserLocationServiceImpl();
		this.userdao = UserDaoFactory.getInstance();
	}

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws FumbleException, IOException {
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
		if (user == null) {
			throw new FumbleBadRequestException("User not found for id: " + userId);
		}
		List<UserLocationMatch> matches = userLocationService.findMatches(user);
		resp.setContentType("application/json");
		String output = Mapper.getMapper().writeValueAsString(matches);
		try (PrintWriter out = resp.getWriter()) {
			out.println(output);
		}
	}
}
