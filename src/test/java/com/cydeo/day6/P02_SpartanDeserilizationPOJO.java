package com.cydeo.day6;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P02_SpartanDeserilizationPOJO extends SpartanTestBase {
    @Test
            public void test1(){
       Response response= given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println("---------------RESPONSE-----------------------");
        Spartan spartan=response.as(Spartan.class);
        System.out.println("Response "+spartan);
        System.out.println(spartan.getGender());





        System.out.println("---------------JasonPath-----------------------");
        JsonPath jsonPath=response.jsonPath();
        Spartan spartanJP= jsonPath.getObject("", Spartan.class);
        System.out.println("JP "+spartanJP);
        spartanJP.getName();
    }


    @DisplayName("Get spartans with search")
    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .when().get("api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Spartan spartan1 = jsonPath.getObject("content[9]", Spartan.class);
        System.out.println(spartan1);
    }
        @DisplayName("Get all spartans with search")
        @Test
        public void test3() {
            Response response = given()
                    .accept(ContentType.JSON)
                    .when().get("api/spartans/search")
                    .then()
                    .statusCode(200)
                    .extract().response();


            Search search = response.as(Search.class);
            System.out.println(search);
            System.out.println(search.getTotalElement());
            System.out.println(search.getContent().get(4));
        }
    @DisplayName("Get all spartans with search to List of Spartans")
    @Test
    public void test4() {
        Response response = given()
                .accept(ContentType.JSON)
                .when().get("api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath=response.jsonPath();
       List<Spartan> allspartan=jsonPath.getList("content", Spartan.class);
        for (Spartan eachSpartan : allspartan) {
            System.out.println(eachSpartan);
        }








    }


}
