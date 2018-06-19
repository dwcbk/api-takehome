package com.dwcbk.dao;

import com.dwcbk.beans.User;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class UserDaoImpl implements UserDao {
	private final Map<Long,User> userMap;
	private final Multimap<Long,Long> friendsMap;

	public UserDaoImpl() {
		this.userMap = new HashMap<>();
		this.friendsMap = HashMultimap.create();
	}

	public void addUser(User user) {
		if (user != null) {
			if (userMap.containsKey(user.getId())) {
				throw new IllegalStateException("User with id " + user.getId() + " already exists");
			}
			userMap.put(user.getId(), user);
		}
	}

	public void addFriend(Long from, Long to) {
		if (from == null || to == null) {
			throw new IllegalArgumentException("Can't add null friend. From=" + from + ", to=" + to);
		}
		if (userMap.containsKey(from) && userMap.containsKey(to)) {
			friendsMap.put(from, to);
		}
	}

	@Override
	public User getUser(Long id) {
		return userMap.get(id);
	}

	@Override
	public Collection<User> getFriends(User user) {
		return friendsMap.get(user.getId()).stream().map(this::getUser).collect(Collectors.toList());
	}
}
