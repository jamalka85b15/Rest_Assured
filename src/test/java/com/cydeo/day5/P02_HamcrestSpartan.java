package com.cydeo.day5;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

public class P02_HamcrestSpartan extends SpartanTestBase {

    @Test
    public void test1() {

                given().
                        accept(ContentType.JSON)
                        .pathParam("id", 15)
                .when()
                        .get("api/spartans/{id}")
                .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .body("id",is(15),
                                "name", is("Severus"),
                                      "gender", equalTo("Female"),
                                       "phone", is(1938695106));


    }

    @Test
    public void test2() {

        given().
                accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("api/spartans/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("id",is(15))
                .and()
                .body("name", is("Severus"))
                .body("gender", equalTo("Female"))
                .body("phone", is(1938695106));

    }

    @Test
    public void test3() {
        JsonPath jsonPath=
                 given()
                         .accept(ContentType.JSON)
                        .pathParam("id", 15)
                //.log().body()
                 .when()
                        .get("api/spartans/{id}").prettyPeek()
                .then()
                         .log().ifValidationFails(LogDetail.BODY)
                        .statusCode(200)
                        .contentType("application/json")
                        .body("id",is(15),
                        "name", is("Severus"),
                        "gender", equalTo("Female"),
                        "phone", is(1938695106))
                         .extract().jsonPath();

        String name=jsonPath.getString("name");
        int id=jsonPath.getInt("id");

        System.out.println(name);


    }





}
