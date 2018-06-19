package com.dwcbk.beans;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javadocmd.simplelatlng.LatLng;

import java.time.ZonedDateTime;

/**
 * Bean for storing a User's Location (lat/long and timestamp)
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.ANY, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class UserLocation {
	private final Long userId;
	private final LatLng location;
	private final ZonedDateTime dateTime;

	public UserLocation(Long userId, Double lat, Double lng, ZonedDateTime dateTime) {
		this.userId = userId;
		this.location = new LatLng(lat, lng);
		this.dateTime = dateTime;
	}

	public Long getUserId() {
		return userId;
	}

	@JsonIgnore
	public LatLng getLocation() {
		return location;
	}

	public Double getLat() {
		return location.getLatitude();
	}

	public Double getLon() {
		return location.getLongitude();
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss'Z'")
	public ZonedDateTime getTime() {
		return dateTime;
	}
}
