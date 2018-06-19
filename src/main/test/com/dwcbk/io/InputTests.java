package com.dwcbk.io;

import com.dwcbk.beans.InputLocation;
import com.dwcbk.beans.User;
import com.dwcbk.beans.UserLocation;
import com.dwcbk.beans.UserLocationMatch;
import com.dwcbk.dao.UserDao;
import com.dwcbk.dao.UserDaoFactory;
import com.dwcbk.services.UserLocationService;
import com.dwcbk.services.UserLocationServiceImpl;
import com.dwcbk.util.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

public class InputTests {

	private UserDao userDao;
	private UserLocationService userLocationService;

	@Before
	public void init() {
		userDao = UserDaoFactory.getInstance();
		userLocationService = new UserLocationServiceImpl();
	}

	@Test
	public void testGetInput() {
		String body = "{\"lat\": -35,\"lon\": 70,\"userId\": 1}";
		System.out.println("input=" + body);
		InputLocation inputLocation = InputLocation.parseJsonString(body);
		System.out.println("input location: " + inputLocation);

		String outputJson = InputLocation.toJsonString(inputLocation);
		System.out.println("output=" + outputJson);
	}

	@Test
	public void testPrintUserLocation() {
		UserLocation userLocation = new UserLocation(1L, 70.0, 35.0, ZonedDateTime.now(ZoneOffset.UTC));
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			System.out.println("user location: " + mapper.writeValueAsString(userLocation));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMatchUserLocation() {
		UserLocation ul1 = new UserLocation(1L, 70.0, 35.0, ZonedDateTime.now(ZoneOffset.UTC));
		UserLocation ul2 = new UserLocation(2L, 70.0, 35.0, ZonedDateTime.now(ZoneOffset.UTC));
		userLocationService.recordLocation(ul1);
		userLocationService.recordLocation(ul2);
		User user = userDao.getUser(1L);
		List<UserLocationMatch> matches = userLocationService.findMatches(user);
		String output = null;
		try {
			output = Mapper.getMapper().writeValueAsString(matches);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("output:\n" + output);
	}
}
