package com.dwcbk.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean for storing a "friend" relationship between two users.
 */
public class Relationship {
	private final Long id;
	private final Long from;
	private final Long to;

	@JsonCreator
	public Relationship(@JsonProperty("id") Long id,
						@JsonProperty("from") Long from,
						@JsonProperty("to") Long to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}

	public Long getId() {
		return id;
	}

	public Long getFrom() {
		return from;
	}

	public Long getTo() {
		return to;
	}
}
