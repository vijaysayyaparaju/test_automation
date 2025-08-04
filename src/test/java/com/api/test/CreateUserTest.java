package com.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class CreateUserTest {
	
	@Test
	public void CreateUserTesting() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        String token = "08d428901e8afd1cab00ebe8b09cf08a0c1fae488df9c344d79f81b2266cbccd"; 

        given()
            .header("Authorization", "Bearer " + token)
            .contentType("application/json") 
            .body("{\"email\":\"ram.reddy@gamil.com\"}")
        .when()
            .patch("/users/8048787")
        .then()
            .statusCode(200);

	}

}
