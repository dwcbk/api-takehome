package com.dwcbk.services;

import com.dwcbk.beans.User;
import com.dwcbk.beans.UserLocation;
import com.dwcbk.beans.UserLocationMatch;

import java.util.List;

/**
 * Service for recording User locations and searching for friends with matching locations
 */
public interface UserLocationService {
	/**
	 * Records a User's location at a particular time
	 *
	 * @param userLocation
	 */
	void recordLocation(UserLocation userLocation);

	/**
	 * Find friends of this user who were in the same location at the same time (at any point in the past)
	 *
	 * @param user
	 * @return
	 */
	List<UserLocationMatch> findMatches(User user);
}
