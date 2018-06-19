package com.dwcbk.dao;

import com.dwcbk.beans.User;
import com.dwcbk.beans.UserLocation;
import com.javadocmd.simplelatlng.LatLng;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DAO for simple storage and retrieval of user locations.
 */
public interface UserLocationDao {
	void add(UserLocation userLocation);

	/**
	 * Return all locations for the user. This method is public mostly for testing/debugging purposes.
	 *
	 * @param user
	 * @return
	 */
	List<UserLocation> list(User user);

	/**
	 * Find all UserLocations for the given date range and near the given location
	 *
	 * @param fromTime
	 * @param toTime
	 * @param location
	 * @return
	 */
	List<UserLocation> findLocations(LocalDateTime fromTime, LocalDateTime toTime, LatLng location);
}
