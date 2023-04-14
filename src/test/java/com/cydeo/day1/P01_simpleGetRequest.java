package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {

    String url="http://54.237.226.155:8000/api/spartans";

    @Test
    public void simpleGetRequest() {
       Response response= RestAssured.get(url);
        System.out.println(response.statusCode());
        int statusCode=response.statusCode();
        Assertions.assertEquals(200, statusCode);
        response.prettyPrint();




    }}














            






            



