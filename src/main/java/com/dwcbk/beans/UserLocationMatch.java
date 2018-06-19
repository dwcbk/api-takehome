package com.dwcbk.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

/**
 * Bean for storing user location that matches with a friend
 */
public class UserLocationMatch {
	private final Long from;
	private final Long to;
	private final ZonedDateTime time;

	public UserLocationMatch(Long from, Long to, ZonedDateTime time) {
		this.from = from;
		this.to = to;
		this.time = time;
	}

	public Long getFrom() {
		return from;
	}

	public Long getTo() {
		return to;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm'Z'")
	public ZonedDateTime getTime() {
		return time;
	}
}
