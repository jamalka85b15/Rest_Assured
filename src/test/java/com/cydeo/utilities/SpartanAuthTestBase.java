package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanAuthTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://54.237.226.155:7000";
    }
}


