package com.restApi.test;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpRequests {
	
	int id;
	@Test(priority = 1)
	void getUser() {
		
		given ()
		.when ()
		.get("https://reqres.in/api/users?page=2")
		.then ()
		.statusCode (200)
		//.body ("id", equalTo (2)) 
		.log().all();
		
	}
	
	@Test(priority = 2)
	void createUser() {
		HashMap data=new HashMap();
		data.put("name", "pavan");
		data.put("job", "trainer");
		
		id=given ()
		.contentType("application/json").body(data)
		.when ()
		.post("https://reqres.in/api/users").jsonPath().getInt("id");
		//.then ().statusCode (201).log().all();
		
	}
	
	@Test(priority = 3,dependsOnMethods = {"createUser"} )
	void UpdateUser() {
		HashMap data=new HashMap();
		data.put("name", "ramu");
		data.put("job", "teacher");
		
		given ()
		.contentType("application/json").body(data)
		.when ()
		.put("https://reqres.in/api/users/"+id)
		
		.then ()
		.statusCode (200)
		.log().all();
		
	}
	
	
	@Test(priority = 4)
	void deleteUser() {
		
		
		given ()
		
		.when ()
		.delete("https://reqres.in/api/users/"+id)
		
		.then ().statusCode (204).log().all();
		
	}

}
