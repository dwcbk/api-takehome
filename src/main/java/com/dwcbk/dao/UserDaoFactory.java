package com.dwcbk.dao;

import com.dwcbk.beans.Relationship;
import com.dwcbk.beans.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class UserDaoFactory {
	private static final UserDaoImpl USER_DAO = new UserDaoImpl();
	private static final String USER_LIST_FILE = "users.json";
	private static final String FRIENDS_LIST_FILE = "relationships.json";

	static {
		// initialize user and relationships lists from JSON files.
		// in a production scenario, this data would be persistent (already present at startup), which is why we are
		// doing this in a factory -- so this implementation detail can easily be changed later without affecting any
		// classes that use instances of UserDao.
		initUsers();
		initFriends();
	}

	private static void initUsers() {
		String usersJson = readResourceFile(USER_LIST_FILE);

		ObjectMapper objectMapper = new ObjectMapper();
		User[] userData = null;
		try {
			userData = objectMapper.readValue(usersJson, User[].class);
		} catch (IOException e) {
			throw new RuntimeException("Error parsing users.json", e);
		}

		for (User user : userData) {
			USER_DAO.addUser(user);
		}
	}

	private static void initFriends() {
		String friendsJson = readResourceFile(FRIENDS_LIST_FILE);

		ObjectMapper objectMapper = new ObjectMapper();
		Relationship[] relationshipData = null;
		try {
			relationshipData = objectMapper.readValue(friendsJson, Relationship[].class);
		} catch (IOException e) {
			throw new RuntimeException("Error parsing users.json", e);
		}

		for (Relationship relationship : relationshipData) {
			USER_DAO.addFriend(relationship.getFrom(), relationship.getTo());
		}
	}

	private static String readResourceFile(String filename) {
		ClassLoader classLoader = UserDaoFactory.class.getClassLoader();
		try {
			return IOUtils.toString(classLoader.getResourceAsStream(filename));
		} catch (IOException e) {
			throw new RuntimeException("Error initializing users list.");
		}
	}

	public static UserDao getInstance() {
		return USER_DAO;
	}
}
