package com.restApi.test;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerilizationDeserilization {

	// PojoSerilize--> JSON Object de-serilize----->POjo

	@Test
	void convertPojo2Json() throws JsonProcessingException {

		// created java object using pojo class
		Student stupojo = new Student(); // pojo
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
		String coursesArr[] = { "C", "C++" };
		stupojo.setCourses(coursesArr);
		// convert java objet ---> json object serilization)

		ObjectMapper objMapper = new ObjectMapper();

		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);

	}

	// JSON Object de-serilize----->POjo
	@Test
	void convertJson2Pojo() throws JsonProcessingException {
		

String jsondata="{\r\n"
+"\"name\":\"Scott\", \r\n"
+"\"location\": \"France\", \r\n" 
+"\"phone\":\"123456\", \r\n"
+"\"courses\":[\"C\", \"C++\" ]\r\n"
+"}";
//convert json data---> Pojo object
ObjectMapper objMapper=new ObjectMapper();

Student stupojo = objMapper.readValue(jsondata, Student.class);
stupojo.getName();
stupojo.getLocation();
stupojo.getPhone();
System.out.println(stupojo.getCourses()[0]);
System.out.println(stupojo.getCourses()[1]);
		
	}

}
