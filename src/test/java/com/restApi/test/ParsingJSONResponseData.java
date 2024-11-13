package com.restApi.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {

	@Test(priority = 1)
	void testJsonResponse() {
//Appoach1
		/*
		 * given() .contentType(ContentType.JSON) .when()
		 * .get("http://localhost:3000/store") .then() .statusCode(200)
		 * .header("Content-Type", "application/json; charset=utf-8")
		 * .body("book [3].title", equal To ("The Lord of the Rings"));
		 */
//Approach2
		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");
		Assert.assertEquals(res.getStatusCode(), 200);
		// validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		String bookname = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");

	}

	@Test(priority = 2)
	void testJsonResponseBodyData() {
		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");
//json object class
		JSONObject jo = new JSONObject(res.toString());
//converting response to json object

		boolean status = false;
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if (bookTitle.equals("The Lord of the Rings")) {
				status = true;
				break;
			}
			Assert.assertEquals(status, true);

		}
	}
}
