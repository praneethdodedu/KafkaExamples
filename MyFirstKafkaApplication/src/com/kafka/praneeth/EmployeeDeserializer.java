package com.kafka.praneeth;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeDeserializer implements Deserializer {

	@Override
	public void close() {

	}

	@Override
	public void configure(Map arg0, boolean arg1) {

	}

	@Override
	public Employee deserialize(String arg0, byte[] arg1) {
		ObjectMapper mapper = new ObjectMapper();
		Employee user = null;
		try {
			user = mapper.readValue(arg1, Employee.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return user;
	}

}