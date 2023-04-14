package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HrWithResponsePath extends HrTestBase {
    @DisplayName("Get request to countries with using reponse path")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");
        response.prettyPrint();
        System.out.println("hasMore: "+response.path("hasMore"));
        System.out.println("limit: "+response.path("limit"));
        System.out.println("Second country name: "+response.path("items[1].country_name"));
        System.out.println("4rth element country name: "+response.path("items[3].country_name"));
        System.out.println("3rd element href: "+response.path("items[2].links[0].href"));
        System.out.println("all country names: "+response.path("items.country_name"));
       List<Integer> regionId=response.path("items.region_id");

        //assertTrue(response.path("items[0].region_id").equals(2));
        for (Integer eachId : regionId) {
            assertTrue(eachId.equals(2));
        }


    }


}
