package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P03_SpartanPUTPATCHDELETE extends SpartanTestBase {

    @DisplayName("PUT Spartan with Map")
    @Test
    public void test1(){
        Map<String, Object> requestBodyMap= new HashMap<>();
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("name", "Markush Hall");
        requestBodyMap.put("phone", 3134445555l);

        int id=1053;
       JsonPath jsonPath= given()
                .pathParam("id", id)
               .contentType(ContentType.JSON)
               .body(requestBodyMap)
                .when()
               .put("/api/spartans/{id}").prettyPeek()
                .then().statusCode(204)
                .extract().jsonPath();
    }

    @DisplayName("PATCH Spartan with Map")
    @Test
    public void test2(){
        Map<String, Object> requestBodyMap= new HashMap<>();
        requestBodyMap.put("phone", 6664445555l);

        int id=1053;
        JsonPath jsonPath= given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(requestBodyMap)
                .when().patch("/api/spartans/{id}").prettyPeek()
                .then().statusCode(204)
                .extract().jsonPath();
    }

    @DisplayName("DELETE Spartan with Map")
    @Test
    public void test3(){
        Map<String, Object> requestBodyMap= new HashMap<>();
        requestBodyMap.put("phone", 6664445555l);

        int id=1053;
        JsonPath jsonPath= given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(requestBodyMap)
                .when().delete("/api/spartans/{id}").prettyPeek()
                .then().statusCode(204)
                .extract().jsonPath();
    }


}
