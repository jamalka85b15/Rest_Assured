package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanWithResponsePath extends SpartanTestBase {
   @DisplayName("Get Spartan with response Path")
    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 138)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.path("name").equals("VictoriAAA"));
       assertTrue(response.path("gender").equals("Male"));
       assertTrue(response.path("phone").equals(1231231231));
    }

    @DisplayName("Get all Spartans ")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        response.prettyPrint();
        int IDFirst=response.path("id[0]");
        System.out.println(IDFirst);
        int firstID=response.path("[0].id");
        System.out.println(firstID);
        String name=response.path("name[1]");
        System.out.println(name);
        String name2=response.path("[1].name");
        System.out.println(name2);
        String nameLast=response.path("name[-1]");
        System.out.println(nameLast);
        List<String> names =response.path("name");
        System.out.println(names);
        for (String each : names) {
            System.out.println(each);
        }
    }


}
