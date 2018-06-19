package com.dwcbk.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private final Long id;
	private final String first_name;
	private final String last_name;

	@JsonCreator
	public User(@JsonProperty("id") Long id,
				@JsonProperty("first_name")String first_name,
				@JsonProperty("last_name")String last_name) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public Long getId() {
		return id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", first_name='" + first_name + '\'' +
				", last_name='" + last_name + '\'' +
				'}';
	}
}
