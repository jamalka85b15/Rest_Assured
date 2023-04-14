package com.cydeo.day7;

import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.CydeoTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_CydeoTrainingDeserializationPOJO extends CydeoTestBase {
   @DisplayName("Get one student with deser Pojo")
    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
      assertEquals(response.statusCode(),200);

        JsonPath jsonPath=response.jsonPath();

        Student student=jsonPath.getObject("students[0]", Student.class);
        assertEquals("Mark", student.getFirstName());
        assertEquals(13, student.getBatch());
        assertEquals("math", student.getMajor());
        assertEquals("mark@email.com", student.getContact().getEmailAddress());
        assertEquals("Cydeo", student.getCompany().getCompanyName());
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());
        assertEquals(33222, student.getCompany().getAddress().getZipCode());


    }

    @DisplayName("Get  students with deser Pojo")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                 .pathParam("id", 2)
                .when().get("/student/{id}");
        assertEquals(response.statusCode(),200);

        JsonPath jsonPath=response.jsonPath();
        Students students=jsonPath.getObject("", Students.class);
        Student student1=students.getStudents().get(0);
        System.out.println(student1.getFirstName());

        /*assertEquals("Mark", students.getStudents().get(3).getFirstName());
        assertEquals(13, students.getStudents().get(3).getBatch());
        assertEquals("math", students.getStudents().get(3).getMajor());
        assertEquals("mark@email.com", students.getStudents().get(3).getContact().getEmailAddress());
        assertEquals("Cydeo", students.getStudents().get(3).getCompany().getCompanyName());
        assertEquals("777 5th Ave", students.getStudents().get(3).getCompany().getAddress().getStreet());
        assertEquals(33222, students.getStudents().get(3).getCompany().getAddress().getZipCode());*/


    }

    @DisplayName("Get  students with deser Pojo")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when().get("/student/{id}");
        assertEquals(response.statusCode(),200);

        JsonPath jsonPath=response.jsonPath();
        com.cydeo.pojo.ready.Student student1=jsonPath.getObject("students[0]", com.cydeo.pojo.ready.Student.class);
        System.out.println(student1.joinDate);



    }


}
