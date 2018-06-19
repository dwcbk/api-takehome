package com.dwcbk.controller;

import com.dwcbk.exception.FumbleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller interface for handling servlet requests. The purpose of {@link FumbleException} is to catch
 * certain kinds of exceptions and return them in the response as HTTP 400 status codes instead of exceptions (500 error).
 * For example, requests with missing required parameters.
 */
public interface FumbleController {
	void execute(HttpServletRequest req, HttpServletResponse resp) throws FumbleException, IOException;
}
