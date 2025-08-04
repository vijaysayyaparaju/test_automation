package com.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiFlowTesting {
	String token = "08d428901e8afd1cab00ebe8b09cf08a0c1fae488df9c344d79f81b2266cbccd";
    int userId;

    @Test(priority = 1)
    public void createUser() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        String requestBody = "{\n" +
                "  \"name\": \"Jon Auto\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"email\": \"jon.auto." + System.currentTimeMillis() + "@mail.com\",\n" +
                "  \"status\": \"active\"\n" +
                "}";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .body("name", equalTo("Jon Auto"))
                .extract().response();

        userId = response.jsonPath().getInt("id"); 
        System.out.println("User created with ID: " + userId);
    }

    
    @Test(priority = 2, dependsOnMethods = "createUser")
    public void updateUser() {
        String updateBody = "{ \"status\": \"inactive\" }";

        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(updateBody)
        .when()
                .patch("/users/" + userId)
        .then()
                .statusCode(200)
                .body("status", equalTo("inactive"));
    }

    @Test(priority = 3, dependsOnMethods = "updateUser")
    public void deleteUser() {
        given()
                .header("Authorization", "Bearer " + token)
        .when()
                .delete("/users/" + userId)
        .then()
                .statusCode(204);
    }

}
