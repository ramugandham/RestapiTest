package com.restApi.chaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	@Test
	void tes_createUser(ITestContext context) {

		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "Inactive");
		String bearerToken = "ee182920c78c93b2f2a8ed16c554459fb7d91c1f935cf1c3fac66d681f088882";

		int id = given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json")
				.body(data.toString())

				.when().post("https://gorest.co.in/public/v2/users").jsonPath().getInt("id");

		System.out.println("genarated id is:" + id);
		// context.setAttribute("user_id", id);//only test level execute
		context.getSuite().setAttribute("user_id", id);// suit level execute

		/*
		 * Response response = given() .headers("Authorization", "Bearer " +
		 * bearerToken) .contentType("application/json") .body(data.toString()) .when()
		 * .post("https://gorest.co.in/public/v2/users");
		 * 
		 * System.out.println("Response: " + response.asString());
		 * 
		 * int statusCode = response.getStatusCode(); System.out.println("Status Code: "
		 * + statusCode);
		 * 
		 * if (statusCode == 201) { // Success int id =
		 * response.jsonPath().getInt("id"); System.out.println("Generated id is: " +
		 * id); } else { System.out.println("Error: User not created."); }
		 */

	}

}
