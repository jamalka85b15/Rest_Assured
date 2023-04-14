package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P02_HrWithParameters extends HrTestBase {
    @DisplayName("Get Regions /countries/{region_id:2} path with param q")
    @Test
    public void test1(){

        Response response=given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when().get("/countries");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));


    }
}
