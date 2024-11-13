package com.restApi.chaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	void test_UpdateUser(ITestContext context) {
		Faker faker = new Faker();

		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		String bearerToken = "ee182920c78c93b2f2a8ed16c554459fb7d91c1f935cf1c3fac66d681f088882";
//int id=(int) context.getAttribute("user_id"); //test level execute 
		int id = (int) context.getSuite().getAttribute("user_id"); // suit level execute
		given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(data.toString())
				.pathParam("id", id).when().put("https://gorest.co.in/public/v2/users/{id}").then().statusCode(200)
				.log().all();

	}

}
