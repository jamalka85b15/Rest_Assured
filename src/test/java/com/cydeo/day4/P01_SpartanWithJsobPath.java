package com.cydeo.day4;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithJsobPath extends SpartanTestBase {

    @DisplayName("Get request to countries with using reponse path")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 145)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();
        System.out.println("name "+ response.path("name").toString());

        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getString("name"));
    }

}
