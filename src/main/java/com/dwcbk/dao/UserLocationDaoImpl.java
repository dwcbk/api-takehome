package com.dwcbk.dao;

import com.dwcbk.beans.User;
import com.dwcbk.beans.UserLocation;
import com.javadocmd.simplelatlng.LatLng;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class UserLocationDaoImpl implements UserLocationDao {
	private final List<UserLocation> cache;

	public UserLocationDaoImpl() {
		this.cache = new ArrayList<>();
	}

	@Override
	public void add(UserLocation userLocation) {
		if (userLocation != null) {
			cache.add(userLocation);
		}
	}

	@Override
	public List<UserLocation> list(User user) {
		return cache.stream().filter(ul -> ul.getUserId().equals(user.getId())).collect(Collectors.toList());
	}

	@Override
	public List<UserLocation> findLocations(LocalDateTime fromTime, LocalDateTime toTime, LatLng location) {
		return null; // TODO: implement
	}
}
