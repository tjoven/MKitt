package com.example.mkitt;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() {
       String str = "123456.!@#234";
       String[] strs = str.split("12345");
       System.out.println(Arrays.asList(strs));
    }
}