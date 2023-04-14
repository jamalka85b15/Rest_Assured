package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {


    private String firstName;
    private int batch;
    private String major;

    private  Contact contact;
    private Company company;
   // private Address address;




}
