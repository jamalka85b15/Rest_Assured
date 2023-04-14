package com.cydeo.day6;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_HrDeserialization extends HrTestBase {
    @Test
    public void getLocation() {
      Response response= given().log().uri()
                .accept(ContentType.JSON)
        .when()
                .get("/locations")
              .then()
              .statusCode(200)
              .contentType("application/json")
              .extract().response();

        JsonPath jsonPath=response.jsonPath();
     Map<String, Object> firstLocation= jsonPath.getMap("items[0]");
     System.out.println("firstLocation = " + firstLocation);
     System.out.println("First location link "+jsonPath.getMap("items[0].links[0]"));
    List<Map<String, Object>> allLocations=jsonPath.getList("items");

        for (Map<String, Object> eachLocation : allLocations) {
            System.out.println(eachLocation);
        }
        //System.out.println(allLocations);
        System.out.println("First Location: "+allLocations.get(0));
        System.out.println("Location ID: "+allLocations.get(0).get("location_id"));
        System.out.println("Country ID: "+allLocations.get(0).get("country_id"));
        System.out.println("First location link: "+allLocations.get(0).get("links"));



    }




}
