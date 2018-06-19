package com.dwcbk.controller;

import com.dwcbk.beans.InputLocation;
import com.dwcbk.beans.UserLocation;
import com.dwcbk.exception.FumbleBadRequestException;
import com.dwcbk.exception.FumbleException;
import com.dwcbk.services.UserLocationService;
import com.dwcbk.services.UserLocationServiceImpl;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Controller for reporting User Locations.
 */
public class AddUserLocationController extends BaseController {

	private final UserLocationService userLocationService;

	public AddUserLocationController() {
		this.userLocationService = new UserLocationServiceImpl();
	}

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws FumbleException, IOException {
		// http POST "http://localhost:3000" userId:=1 lon:=70 lat:=-35
		String requestBody = IOUtils.toString(req.getReader());
		InputLocation inLocation = InputLocation.parseJsonString(requestBody);
		if (inLocation == null) {
			throw new FumbleBadRequestException("Invalid location input");
		}
		UserLocation userLocation = new UserLocation(inLocation.getUserId(), inLocation.getLat(), inLocation.getLon(),
				ZonedDateTime.now(ZoneOffset.UTC));
		userLocationService.recordLocation(userLocation);
	}
}
