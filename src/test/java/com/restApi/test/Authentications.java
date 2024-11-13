package com.restApi.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {

	// @Test(priority = 1)
	void testBasicAuthentication() {

		given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

	// @Test(priority = 2)
	void testDigestAuthentication() {

		given().auth().digest("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

	// @Test(priority = 3)
	void testPreemptiveAuthentication() {

		given().auth().preemptive().basic("postman", "password").when().get("https://postman-echo.com/basic-auth")
				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

	// @Test(priority = 4)
	void testBearerTokenAuthentication() {

		String BearerToken = "ba29ba1f5583015978a535302a3f0408bd128e7d";

		given().headers("Authorization", "Bearer " + BearerToken).when()
				.get("https://github.com/ramugandham?tab=repositories").then().statusCode(200).log().all();

	}

	// @Test(priority = 5)
	void testOAuth1Authentication() {

		given().auth().oauth("consumerKey", "consumer Secrat", "accessToken", "tokenSecrate")// this Oauth1
				.when().get("url").then().statusCode(200).log().all();

	}

	//@Test(priority = 6)
	void testOAuth2Authentication() {

		given().auth().oauth2("ba29ba1f5583015978a535302a3f0408bd128e7d")// this Oauth2
				.when().get("https://github.com/ramugandham?tab=repositories").then().statusCode(200).log().all();

	}

	@Test(priority = 7)
	void testAPIKeyAuthentication() {
		//method1
		given().queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c") // appid is APIKey
				.when().get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7").then()
				.statusCode(200).log().all();
		
		//Method2  
		given()  
		    .queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")  
		    .pathParam("mypath", "data/2.5/forecast/daily")  
		    .queryParam("q", "Delhi")  
		    .queryParam("units", "metric")  
		    .queryParam("cnt", "7")  
		.when()  
		    .get("https://api.openweathermap.org/{mypath}")  
		.then() 
		.statusCode(200).log().all();
	}
}
