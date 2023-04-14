package com.cydeo.day8;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanAsAdmin extends SpartanAuthTestBase {

    static int id;
    @DisplayName("POST/api/spartans as admin-->Success")
    @Test
    public void test1() {

        String requestBody="{\n" +
                "    \"name\":\"Katya\",\n" +
                "    \"gender\":\"Female\",\n" +
                "    \"phone\":2223334444\n" +
                "}";


        JsonPath jsonPath=given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().auth().basic("admin", "admin")
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .extract().jsonPath();
        id=jsonPath.getInt("data.id");

    }

    @DisplayName("GET/api/spartans as admin-->Success")
    @Test
    public void test2() {

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when().auth().basic("admin", "admin")
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json");


    }


    @DisplayName("PUT/api/spartans as admin-->Success")
    @Test
    public void test3() {

        String requestBody=" {\n" +
                "        \"name\": \"Katya Lya\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 2223334444\n" +
                "    }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .pathParam("id", id)
                .when().auth().basic("admin", "admin")
                .put("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204);
    }

    @DisplayName("PATCH/api/spartans as admin-->Success")
    @Test
    public void test4() {

      String requestBody="{\n" +
              "\n" +
              "    \"gender\":\"Male\"\n" +
              "\n" +
              "}";


        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .pathParam("id", id)
                .when().auth().basic("admin", "admin")
                .patch("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204);
    }
    @DisplayName("Delete/api/spartans as admin-->Success")
    @Test
    public void test5() {

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when().auth().basic("admin", "admin")
                .delete("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204);
    }


}
