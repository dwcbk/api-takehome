package com.dwcbk.dao;

public class UserLocationDaoFactory {
	private static final UserLocationDao DAO = new UserLocationDaoImpl();

	public static UserLocationDao getInstance() {
		return DAO;
	}
}
