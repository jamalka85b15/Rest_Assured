package com.cydeo.day4;

import com.cydeo.utilities.CydeoTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {
@Test
    public void test1(){
    Response response=RestAssured.given().accept(ContentType.JSON)
            .and()
            .pathParam("id", 2)
            .when().get("/student/{id}");
    response.prettyPrint();
    Assertions.assertEquals(response.statusCode(),200);
    Assertions.assertEquals(response.contentType(),"application/json;charset=UTF-8");
    Assertions.assertTrue(response.headers().hasHeaderWithName("date"));
    Assertions.assertTrue(response.header("server").equals("envoy"));
    JsonPath jsonPath=response.jsonPath();
    Assertions.assertEquals(jsonPath.getString("students[0].firstName"),"Mark");
    Assertions.assertEquals(jsonPath.getInt("students[0].batch"),13);
    Assertions.assertEquals(jsonPath.getString("students[0].major"),"math");
    Assertions.assertEquals(jsonPath.getString("students[0].contact.emailAddress"),"mark@email.com");
    Assertions.assertEquals(jsonPath.getString("students[0].company.companyName"),"Cydeo");
    Assertions.assertEquals(jsonPath.getString("students[0].company.address.street"),"777 5th Ave");
    Assertions.assertEquals(jsonPath.getInt("students[0].company.address.zipCode"),33222);






}

}
