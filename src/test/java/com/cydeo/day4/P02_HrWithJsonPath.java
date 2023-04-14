package com.cydeo.day4;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithJsonPath extends HrTestBase {


    @DisplayName("Get all countries")
    @Test
    public void test1() {
        Response response = get("/countries");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getString("items[2].country_name"));
        System.out.println(jsonPath.getString("items[3,4].country_name"));
        System.out.println(jsonPath.getList("items.findAll {it.region_id==2}.country_name"));
    }

    @DisplayName("Get all employees?limit 200 with JsoPath")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams("limit", 200)
                .when().get("/employees");
        //response.prettyPrint();
        assertEquals(response.statusCode(), 200);
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("items.email"));
        System.out.println(jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.email"));
        System.out.println(jsonPath.getList("items.findAll{it.salary>10000}.first_name"));
        System.out.println(jsonPath.getString("items.max{it.salary}.first_name"));
        System.out.println(jsonPath.getString("items.min{it.salary}.first_name"));
    }

    @DisplayName("Get second and last city with JsoPath")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/locations");
        //response.prettyPrint();
        assertEquals(response.contentType(),"application/json");
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getString("items[1].city"));
        System.out.println(jsonPath.getString("items[-1].city"));
        System.out.println(jsonPath.getList("items.country_id"));
        System.out.println(jsonPath.getList("items.findAll{it.country_id=='UK'}.city"));
    }
}
