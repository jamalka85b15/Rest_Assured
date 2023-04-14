package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class BookitUtils {

  /*  public static String getToken(String userType) {
        switch (userType) {
            case "TEAM_LEADER":
                String email = ConfigurationReader.getProperty("teamLeaderEmail");
                String password = ConfigurationReader.getProperty("teamLeaderPassword");
        }

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .param(email)
                .param(password)
                .when().get("/sign")
                .then().statusCode(200)
                .extract().jsonPath();
        String token = jsonPath.getString("accessToken");

    }
                return"Bearer "+token;

}*/

    public static String getToken(String email, String password) {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get("/sign")
                .then().statusCode(200)
                .extract().jsonPath();

        String token = jsonPath.getString("accessToken");

        return "Bearer " + token;
    }
}
