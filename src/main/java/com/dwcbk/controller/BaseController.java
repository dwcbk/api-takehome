package com.dwcbk.controller;

import com.dwcbk.exception.FumbleBadRequestException;
import com.dwcbk.exception.FumbleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController implements FumbleController {

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws FumbleException, IOException {
		try {
			handleRequest(req, resp);
		} catch (Exception e) {
			if (e instanceof FumbleBadRequestException) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			else {
				throw e;
			}
		}
		resp.setStatus(HttpServletResponse.SC_OK);
	}

	protected abstract void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws FumbleException, IOException;
}
