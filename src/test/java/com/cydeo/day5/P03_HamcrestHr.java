package com.cydeo.day5;

import com.cydeo.utilities.HrTestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

public class P03_HamcrestHr extends HrTestBase {
   @DisplayName("GEt all IT_Prog and validate with hamcrest")
    @Test
            public void test1(){
        List<String> names= Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");
        given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .contentType("application/json")
                .assertThat().body("items.manager_id", is(notNullValue()))
                .assertThat().body("items.job_id", everyItem(is("IT_PROG")))
                .assertThat().body("items.salary", everyItem(is(greaterThan(3000))))
                .assertThat().body("items.first_name",equalTo(names))
                .assertThat().body("items.email", is(containsInAnyOrder("DAUSTIN", "AHUNOLD", "BERNST", "VPATABAL", "DLORENTZ")));


    }

    @DisplayName("GEt all regions and validate with hamcrest")
    @Test
    public void test2(){

       given()
               .accept(ContentType.JSON)
       .when()
               .get("/regions")
       .then()
               .statusCode(200)
               .contentType("application/json")
               .header("Date", is(notNullValue()))
               .body("items[0].region_name", equalTo("Wooden Region"))
               .body("items[0].region_id", is(211))
               .body("items", hasSize(25))
               .body("items.region_name", is(notNullValue()));


    }

}
