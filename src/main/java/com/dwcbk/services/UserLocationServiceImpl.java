package com.dwcbk.services;

import com.dwcbk.beans.User;
import com.dwcbk.beans.UserLocation;
import com.dwcbk.beans.UserLocationMatch;
import com.dwcbk.dao.UserDao;
import com.dwcbk.dao.UserDaoFactory;
import com.dwcbk.dao.UserLocationDao;
import com.dwcbk.dao.UserLocationDaoFactory;
import com.javadocmd.simplelatlng.LatLng;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Simple implementation of {@link UserLocationService}.
 */
public class UserLocationServiceImpl implements UserLocationService {

	private final UserDao userDao;
	private final UserLocationDao userLocationDao;

	public UserLocationServiceImpl() {
		this.userDao = UserDaoFactory.getInstance();
		this.userLocationDao = UserLocationDaoFactory.getInstance();
	}

	@Override
	public void recordLocation(UserLocation userLocation) {
		userLocationDao.add(userLocation);
	}

	private static final Comparator<ZonedDateTime> ZDT_MINUTES_COMPARATOR = Comparator.comparing(
			zdt -> zdt.truncatedTo(ChronoUnit.MINUTES));

	private static final Comparator<UserLocation> LOCATION_TIMESTAMP_COMPARATOR = new Comparator<UserLocation>() {

		@Override
		public int compare(UserLocation o1, UserLocation o2) {
			return ZDT_MINUTES_COMPARATOR.compare(o1.getTime(), o2.getTime());
		}
	};

	@Override
	public List<UserLocationMatch> findMatches(User user) {
		List<UserLocation> userLocations = userLocationDao.list(user);
		// get friend locations sorted by timestamp
		Set<UserLocation> friendLocations = new TreeSet<>(LOCATION_TIMESTAMP_COMPARATOR);
		for (User friend : userDao.getFriends(user)) {
			friendLocations.addAll(userLocationDao.list(friend));
		}
		List<UserLocationMatch> results = new ArrayList<>();
		for (UserLocation userLoc : userLocations) {
			results.addAll(findMatchingLocationas(friendLocations, userLoc));
		}
		return results;
	}

	private List<UserLocationMatch> findMatchingLocationas(Set<UserLocation> all, UserLocation userLoc) {
		return all.stream()
				.filter(ul -> ZDT_MINUTES_COMPARATOR.compare(ul.getTime(), userLoc.getTime()) == 0) // match timestamp
				.filter(ul -> locationsMatch(ul.getLocation(), userLoc.getLocation()))
				.map(ul -> new UserLocationMatch(ul.getUserId(), userLoc.getUserId(), userLoc.getTime()))
				.collect(Collectors.toList());
	}

	private boolean locationsMatch(LatLng loc1, LatLng loc2) {
		return loc1.equals(loc2);
	}
}
