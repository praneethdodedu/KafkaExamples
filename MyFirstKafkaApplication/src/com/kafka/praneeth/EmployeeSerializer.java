package com.kafka.praneeth;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeSerializer implements Serializer {

	@Override
	public void configure(Map map, boolean b) {

	}

	@Override
	public byte[] serialize(String arg0, Object arg1) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString((Employee) arg1).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public void close() {

	}

}
