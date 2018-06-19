package com.dwcbk.exception;

/**
 * The purpose of FumbleException is to catch certain kinds of exceptions and return them in the response as HTTP 400
 * status codes instead of exceptions (500 error). For example, requests with missing required parameters.
 */
public class FumbleException extends RuntimeException {
	public FumbleException(String msg) {
		super(msg);
	}

	public FumbleException(String msg, Throwable t) {
		super(msg, t);
	}
}
