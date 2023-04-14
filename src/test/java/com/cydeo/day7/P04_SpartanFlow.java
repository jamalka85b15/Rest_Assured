package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P04_SpartanFlow extends SpartanTestBase {
   static int id;
    @DisplayName("POST")
    @Test
    public void test1(){

        Map<String, Object> requestBody=new HashMap<>();
       requestBody.put("gender", "Female");
       requestBody.put("name", "Sopushke");
        requestBody.put("phone", 8889994444l);

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();
        id=jsonPath.getInt("data.id");

    }

    @DisplayName("GET")
    @Test
    public void test2(){
        System.out.println("ID IS "+id);
        Response response=given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath=response.jsonPath();
        Map<String, Object> newSpartan=jsonPath.getMap("");
       assertEquals(newSpartan.get("name"), "Sopushke");
    }

    @DisplayName("PUT")
    @Test
    public void test3(){

        Map<String, Object> requestBody=new HashMap<>();
        requestBody.put("gender", "Female");
        requestBody.put("name", "Sopushke Flow");
        requestBody.put("phone", 8889994444l);
        given()
                .pathParam("id",id)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204)
                .extract().response();

    }

    @DisplayName("GET")
    @Test
    public void test4(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath=response.jsonPath();
        Map<String, Object> newSpartan=jsonPath.getMap("");
        assertEquals(newSpartan.get("name"), "Sopushke Flow");
    }

    @DisplayName("DELETE")
    @Test
    public void test5(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id",id)
                .when().delete("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204)
                .extract().response();

    }


    @DisplayName("GET")
    @Test
    public void test6(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(404)
                .extract().response();


    }
    }




