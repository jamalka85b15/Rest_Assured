package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P02_SpartanPost extends SpartanTestBase {
@DisplayName("POST spartan with String ")
    @Test
    public void test1(){
       /* Map<String, Object> newSpartan= new HashMap<>();
        newSpartan.put("gender", "Male");
        newSpartan.put("name", "Markush");
        newSpartan.put("phone", 3134445555l);*/

    String requestBody = "   {\n" +
            "     \"gender\":\"Male\",\n" +
            "     \"name\":\"Markush\",\n" +
            "     \"phone\":3134445555\n" +
            "     }";
   JsonPath jsonPath= given()
            .accept(ContentType.JSON)
            .and()
            .contentType(ContentType.JSON)
            .body(requestBody)
    .when()
            .post("/api/spartans").prettyPeek()
    .then()
           .statusCode(201)
            .contentType("application/json")
            .body("success", is("A Spartan is Born!"))
            .extract().jsonPath();

  assertEquals("Markush",jsonPath.getString("data.name"));
  assertEquals("Male", jsonPath.getString("data.gender"));
  assertEquals(3134445555l, jsonPath.getLong("data.phone"));




    }


    @DisplayName("POST spartan with Map ")
    @Test
    public void test2(){
       Map<String, Object> requestBodyMap= new HashMap<>();
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("name", "Markush");
        requestBodyMap.put("phone", 3134445555l);


        JsonPath jsonPath= given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestBodyMap)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("Markush",jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(3134445555l, jsonPath.getLong("data.phone"));




    }



    @DisplayName("POST spartan with POJO ")
    @Test
    public void test3(){

Spartan spartan=new Spartan();
spartan.setName("Malina");
spartan.setGender("Female");
spartan.setPhone(6667778888l);
        System.out.println(spartan);

        JsonPath jsonPath= given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("Malina",jsonPath.getString("data.name"));
        assertEquals("Female", jsonPath.getString("data.gender"));
        assertEquals(6667778888l, jsonPath.getLong("data.phone"));
        System.out.println(jsonPath.getInt("data.id"));




    }

}
