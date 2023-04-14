package com.cydeo.day8;

import com.cydeo.utilities.BookitTestBase;
import com.cydeo.utilities.BookitUtils;
import com.cydeo.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P02_BookitTest extends BookitTestBase {

    String token=BookitUtils.getToken("lfinnisz@yolasite.com", "lissiefinnis");
    String email="lfinnisz@yolasite.com";
    String password="lissiefinnis";

    @DisplayName("GET/api/campuses")
    @Test
    public void test1(){
        System.out.println(token);
        given().accept(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);

}

    @DisplayName("GET/api/users/me")
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .header("Authorization", BookitUtils.getToken(email, password))
                .when()
                .get("/api/users/me").prettyPeek()
                .then()
                .statusCode(200);
    }

}
