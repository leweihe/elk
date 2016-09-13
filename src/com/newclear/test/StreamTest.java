package com.newclear.test;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {

    @Test
    public void testPrintStreamReadArray() {
        Integer[][] array = { { 1, 2, 3 }, { 1, 2, 3 } };
        Stream.of(array).forEach(System.out::print);
        Stream.of(array).forEach(i -> Stream.of(i).forEach(n -> System.out.println(n.intValue())));
    }

    @Test
    public void testListStreamReadArray() {
        Integer[][] array = { { 1, 2, 3 }, { 1, 2, 3 } };
    }
}
