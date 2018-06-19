package com.dwcbk.exception;

public class FumbleBadRequestException extends FumbleException {
	public FumbleBadRequestException(String msg) {
		super(msg);
	}

	public FumbleBadRequestException(String msg, Throwable t) {
		super(msg, t);
	}
}
