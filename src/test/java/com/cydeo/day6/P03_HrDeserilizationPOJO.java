package com.cydeo.day6;
import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HrTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P03_HrDeserilizationPOJO extends HrTestBase {

    @DisplayName("Get regions with POJO--Lombok")
    @Test
    public void test1() {
       JsonPath jsonPath= get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();
       // System.out.println(jsonPath.getMap("items[0]"));
        Region region=jsonPath.getObject("items[0]", Region.class);
        System.out.println(region);

    }

    @DisplayName("Get employee to deser to POJO with only required fields")
    @Test
    public void test2() {
        JsonPath jsonPath = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath();

        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);
        System.out.println(employee1);

    }

    @DisplayName("Get regions with POJO--Lombok")
    @Test
    public void test3() {
        JsonPath jsonPath = get("/regions?limit=3")
                .then().statusCode(200)
                .statusCode(200)
                .extract().jsonPath();
        System.out.println(jsonPath.getMap(""));
        Regions regions = jsonPath.getObject("", Regions.class);
        System.out.println(regions);
    }

    }

