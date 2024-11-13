package com.restApi.chaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	void test_DeleteUser(ITestContext context) {
		String bearerToken = "ee182920c78c93b2f2a8ed16c554459fb7d91c1f935cf1c3fac66d681f088882";
		//int id=(int) context.getAttribute("user_id"); //test level execute
		int id = (int) context.getSuite().getAttribute("user_id"); // suit level execute

		given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").pathParam("id", id)
				.when().delete("https://gorest.co.in/public/v2/users/{id}").then().statusCode(204).log().all();

	}

}
