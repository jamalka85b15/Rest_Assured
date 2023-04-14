
package com.cydeo.pojo.ready;


import lombok.Data;

@Data
public class Address {

    public Integer addressId;
    public String street;
    public String city;
    public String state;
    public Integer zipCode;

}
