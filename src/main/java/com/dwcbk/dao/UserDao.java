package com.dwcbk.dao;

import com.dwcbk.beans.User;

import java.util.Collection;

/**
 * DAO for looking up users and users' friends.
 */
public interface UserDao {
	Collection<User> getFriends(User user);
	User getUser(Long id);
}
