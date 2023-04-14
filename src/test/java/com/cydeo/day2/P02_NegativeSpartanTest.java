package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P02_NegativeSpartanTest {
@BeforeAll
        public static void init(){
    RestAssured.baseURI ="http://54.237.226.155:8000";
}


@Test
    public void getAllSpartans(){
    Response response=given().accept(ContentType.JSON)
            .when().get("/api/spartans");
    assertEquals(200, response.statusCode());
    response.prettyPrint();

}

@Test
    public void getError(){
    Response response= given().accept(ContentType.XML)
            .when().get("/api/spartans/10");

    assertEquals(406, response.statusCode());
    assertTrue(response.contentType().equals("application/xml;charset=UTF-8"));


}



}
