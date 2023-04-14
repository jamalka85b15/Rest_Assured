package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class P01_SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("GET/api/spartans as guest-->Except-->401")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .get("/api/spartans")
                .then().statusCode(401)
                .body("error", is("Unauthorized"));

    }

    @DisplayName("GET/api/spartans as user-->Except-->200")
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .when().auth().basic("admin", "admin")
                .get("/api/spartans")
                .then().statusCode(200)
                .log().all();


    }

    @DisplayName("DELETE/api/spartans/{id} as editor-->Except-->403")
    @Test
    public void test3(){
        given().accept(ContentType.JSON)
                .pathParam("id", 305)
                .when().auth().basic("editor", "editor")
                .delete("/api/spartans/{id}")
                .then().statusCode(403)
                .log().all();

    }

    @DisplayName("DELETE/api/spartans/{id} as admin-->Except-->2xx")
    @Test
    public void test4(){
        given().accept(ContentType.JSON)
                .pathParam("id", 305)
                .when().auth().basic("admin", "admin")
                .delete("/api/spartans/{id}")
                .then().statusCode(204)
                .log().all();


    }
}
