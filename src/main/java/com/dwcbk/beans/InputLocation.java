package com.dwcbk.beans;

import com.dwcbk.exception.FumbleBadRequestException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import java.io.IOException;

/**
 * Bean used only for processing user location inputs.
 */
public class InputLocation {
	private final Long userId;
	private final Double lat;
	private final Double lon;

	@JsonCreator
	public InputLocation(@JsonProperty(value="userId") Long userId,
						 @JsonProperty(value="lat") Double lat,
						 @JsonProperty(value="lon") Double lon) {
		if (userId == null || userId < 0) {
			throw new IllegalArgumentException("User ID is invalid.");
		}
		if (lat == null || lat < -90.0 || lat > 90.0) {
			throw new IllegalArgumentException("Latitude is invalid.");
		}
		if (lon == null || lon < -180.0 || lon > 180.0) {
			throw new IllegalArgumentException("Longitude is invalid.");
		}
		this.userId = userId;
		this.lat = lat;
		this.lon = lon;
	}

	public Long getUserId() {
		return userId;
	}

	public Double getLat() {
		return lat;
	}

	public Double getLon() {
		return lon;
	}

	@Override
	public String toString() {
		return "InputLocation{" +
				"userId='" + userId + '\'' +
				", lat='" + lat + '\'' +
				", lon='" + lon + '\'' +
				'}';
	}

	public static InputLocation parseJsonString(String body) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			InputLocation inputLocation = mapper.readValue(body, InputLocation.class);
			return inputLocation;
		} catch (IllegalArgumentException|InvalidDefinitionException e) {
			throw new FumbleBadRequestException("Error creating InputLocation", e);
		}
		catch (IOException e) {
			throw new RuntimeException("Error reading InputLocation in string: " + body, e);
		}
	}

	public static String toJsonString(InputLocation inputLocation) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(inputLocation);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error converting InputLocation to JSON String: " + inputLocation, e);
		}

	}
}
