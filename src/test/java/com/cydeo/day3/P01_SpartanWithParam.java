package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;


public class P01_SpartanWithParam extends SpartanTestBase {
    @DisplayName("Get Spartans/api/spratans/{id} path with param 24")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 24)
                .when().get("api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals("application/json", response.contentType());
        String bodyText = (response.body().asString());
        assertTrue(bodyText.contains("Julio"));


    }

    @DisplayName("Get Spartans/api/spratans/{id} with invalid id")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 1000)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 404);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Not Found"));
    }

    @DisplayName("Get Spartans/api/spratans/search with Query param")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "F")
                .and()
                .queryParam("gender", "Female")
                .when().get("/api/spartans/search");

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Freida"));

    }


}
