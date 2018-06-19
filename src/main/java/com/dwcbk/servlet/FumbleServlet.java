package com.dwcbk.servlet;

import com.dwcbk.controller.AddUserLocationController;
import com.dwcbk.controller.FumbleController;
import com.dwcbk.controller.GetLocationMatchesController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
		name = "FumbleServlet",
		urlPatterns = "/"
)
/**
 * Main entry point for this application. Handles both adding user locations and searching for friends with matching
 * locations.
 */
public class FumbleServlet extends HttpServlet {

	private final FumbleController addUserLocationController;
	private final FumbleController getLocationMatchesController;

	public FumbleServlet() {
		this.addUserLocationController = new AddUserLocationController();
		this.getLocationMatchesController = new GetLocationMatchesController();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getLocationMatchesController.execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addUserLocationController.execute(req, resp);
	}
}
