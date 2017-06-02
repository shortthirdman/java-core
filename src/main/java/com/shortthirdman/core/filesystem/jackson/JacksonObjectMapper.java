package com.shortthirdman.core.filesystem.jackson;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.shortthirdman.core.filesystem.jackson.bean.Address;
import com.shortthirdman.core.filesystem.jackson.bean.User;

public class JacksonObjectMapper {

	public static void main(String[] args) throws IOException {
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("user.json"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		User user = objectMapper.readValue(jsonData, User.class);
		
		System.out.println("User Object\n" + user);
		
		//convert Object to json string
		User user1 = createUser();
		
		//configure Object mapper for pretty print
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		//writing to console, can write to any output stream such as file
		StringWriter stringUser = new StringWriter();
		objectMapper.writeValue(stringEmp, user1);
		System.out.println("Employee JSON is\n" + stringUser);
	}
	
	public static User createUser() {
		User emp = new User();
		emp.setId(100);
		emp.setName("David");
		emp.setPermanent(false);
		emp.setPhoneNumbers(new long[] { 123456, 987654 });
		emp.setRole("Manager");

		Address add = new Address();
		add.setCity("Bangalore");
		add.setStreet("BTM 1st Stage");
		add.setZipcode(560100);
		emp.setAddress(add);

		List<String> cities = new ArrayList<String>();
		cities.add("Los Angeles");
		cities.add("New York");
		emp.setCities(cities);

		Map<String, String> props = new HashMap<String, String>();
		props.put("salary", "₹ 1000");
		props.put("age", "28 years");
		emp.setProperties(props);

		return emp;
	}
}