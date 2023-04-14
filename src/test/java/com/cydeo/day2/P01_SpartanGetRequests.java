package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequests {
    String url="http://54.237.226.155:8000";

    @Test
    public void getAllSpartans(){
        Response response=RestAssured.given()
                .accept(ContentType.JSON)  //hey api send me json response
                .when()
                .get(url+"/api/spartans");

        response.prettyPrint();
        int statusCode=response.statusCode();
        Assertions.assertEquals(200, statusCode);
        String content=response.getContentType();
        Assertions.assertEquals(content,"application/json");
        System.out.println(response.header("Content-type"));
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Date"));
        System.out.println(response.header("Keep-Alive"));
        boolean isDate=response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);

    }

    @Test
    public void getSpartan(){
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(url+"/api/spartans/138");
        response.prettyPrint();
       int statusCode= response.statusCode();
       Assertions.assertEquals(statusCode,200);
      String content= response.getContentType();
      Assertions.assertEquals("application/json",content);
        boolean isVictoria=response.body().asString().contains("VictoriAAA");
        Assertions.assertTrue(isVictoria);

    }

    @Test
    public void getHello(){
        Response response=RestAssured.get(url+"/api/hello");
        response.prettyPrint();
        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertTrue(response.contentType().equals("text/plain;charset=UTF-8"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assertions.assertEquals(response.header("Content-Length"),"17");
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));
    }
}
