package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://54.237.226.155:1000/ords/hr";
    }
}
