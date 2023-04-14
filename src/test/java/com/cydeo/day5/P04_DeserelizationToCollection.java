package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.request;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

public class P04_DeserelizationToCollection extends SpartanTestBase {

    @DisplayName("Deseralization for one spartan")
    @Test
    public void test1() {
        Response response=
         given()
                .accept(ContentType.JSON)
                 .pathParam("id", 13)
         .when()
                 .get("/api/spartans/{id}")
         .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

//Option 1
        Map<String, Object> spartanMap=response.as(Map.class);
        System.out.println(spartanMap);
        int id=(int) spartanMap.get("id");
        System.out.println(id);
        String name=(String) spartanMap.get("name");
        System.out.println(name);

//Option 2
        JsonPath jsonPath=response.jsonPath();
        Map<String, Object> jsonMap=jsonPath.getMap("");
        System.out.println(jsonMap);
        int jpId=(int) jsonMap.get("id");
        System.out.println(jpId);

    }

    @DisplayName("Deseralization for all spartans")
    @Test
    public void test2() {
        Response response=
                given()
                        .accept(ContentType.JSON)
                        .when()
                        .get("/api/spartans")
                        .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .extract().response();

        List<Map<String, Object>> spartanList=response.as(List.class);
        for (Map<String, Object> map : spartanList) {
            System.out.println("Map "+map);


        }
        System.out.println("spartanList = " + spartanList.get(0).get("name"));

        JsonPath jsonPath=response.jsonPath();
        List<Map<String, Object>> listOfSpartan=jsonPath.getList("");
        System.out.println("listOfSpartan = " + listOfSpartan.get(0).get("name"));

    }
}
