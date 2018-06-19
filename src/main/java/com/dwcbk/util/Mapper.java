package com.dwcbk.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Utility class for returning an {@link ObjectMapper} instance that is configured for Java 8's time APIs, especially
 * {@link java.time.ZonedDateTime}.
 */
public final class Mapper {
	private Mapper() {}

	public static ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}
}
