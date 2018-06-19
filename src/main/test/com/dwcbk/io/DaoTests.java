package com.dwcbk.io;

import com.dwcbk.beans.User;
import com.dwcbk.dao.UserDao;
import com.dwcbk.dao.UserDaoFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class DaoTests {

	private UserDao userDao;

	@Before
	public void init() {
		userDao = UserDaoFactory.getInstance();
	}

	@Test
	public void testUserLookup() {
		User user = userDao.getUser(1L);
		System.out.println("User 1: " + user);
		Collection<User> friends = userDao.getFriends(user);
		System.out.println("FRIENDS:");
		for (User friend : friends) {
			System.out.println(friend);
		}
	}
}
