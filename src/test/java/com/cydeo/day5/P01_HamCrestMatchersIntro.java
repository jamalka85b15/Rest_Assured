package com.cydeo.day5;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_HamCrestMatchersIntro {
    @Test
    public void numbers() {
        //junit 5 assert equal method
        assertEquals(9, 6 + 3);

        //Hamcrest Matchers
        //Overloaded methods
        assertThat(6 + 3, is(9));
        assertThat(6 + 3, is(equalTo(9)));
        assertThat(6 + 3, equalTo(9));
        //Negative assertion
        assertThat(6 + 3, not(9));
        assertThat(6 + 3, is(not(9)));
        assertThat(6 + 3, is(not(equalTo(9))));

        assertThat(6 + 3, greaterThan(5));
        assertThat(6 + 3, is(greaterThanOrEqualTo(11)));
        assertThat(6 + 3, lessThan(10));
        assertThat(6 + 3, lessThanOrEqualTo(7));
    }

    @Test
    public void testString() {
        String msg = "API is fun";

        assertThat(msg, equalToIgnoringCase("api is fun"));
        assertThat(msg, equalTo("API is fun"));
        assertThat(msg, is("API is fun"));

        assertThat(msg, startsWith("API"));
        assertThat(msg, startsWithIgnoringCase("api"));

        assertThat(msg, endsWithIgnoringCase("FUN"));
        assertThat(msg, endsWith("fun"));

        assertThat(msg, containsString("is"));
        assertThat(msg, containsStringIgnoringCase("IS"));

        assertThat(msg, not("FUN"));
        assertThat(msg, is(not("FUN")));
    }

        @Test
        public void testCollectiond() {
List<Integer> numberList = Arrays.asList(3,5,4,7,88,9);
//check the size
assertThat(numberList,hasSize(6));
//check if item/s there
assertThat(numberList,hasItem(88));
assertThat(numberList,hasItems(88,4,9));
//loop through and check if they are matching inside every item
assertThat(numberList, everyItem(greaterThan(1)));
//check position correct
assertThat(numberList, containsInRelativeOrder(3,5,4,7,88,9));
assertThat(numberList, containsInAnyOrder(3,5,88,7,4,9));


        }
    }


