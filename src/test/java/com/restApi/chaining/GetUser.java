package com.restApi.chaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {

	@Test
	void test_getUser(ITestContext context) {
		// int id=(int) context.getAttribute("user_id"); //test level execute
		int id = (int) context.getSuite().getAttribute("user_id"); // suit level execute // this should come from
																	// createuser request
		String bearerToken = "ee182920c78c93b2f2a8ed16c554459fb7d91c1f935cf1c3fac66d681f088882";
		given().headers("Authorization", "Bearer " + bearerToken).pathParam("id", id).when()
				.get("https://gorest.co.in/public/v2/users/{id}").then().statusCode(200).log().all();

	}

}
