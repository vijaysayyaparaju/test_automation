package com.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserTest {

	public static void main(String[] args) {
		Response response = RestAssured.get("https://gorest.co.in/public/v2/users");
		System.out.println("Status Code :" + response.getStatusCode());
		System.out.println("Response Body :" +response.getBody().asString());
	}

}
