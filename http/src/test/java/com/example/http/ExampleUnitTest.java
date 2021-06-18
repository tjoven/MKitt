package com.example.http;

import androidx.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @org.junit.Test
    public void addition_isCorrect() {

        ArrayList<Test> date = new ArrayList<>() ;
        for(int i =0 ;i < 5;i++){
            Test test = new Test(i);
            date.add(test);
        }
        System.out.println("date before: "+date);

        ArrayList<Test> list = new ArrayList<>();
        list.add(date.get(0));
        list.add(date.get(1));

        list.get(0).i = 1000;
//        list.add(new Test(0));
        System.out.println("list remove: "+list);

        date.removeAll(list);

        System.out.println("date after: "+date);
    }

    static class Test{
        int i = 0;
        public Test(int i){
            this.i = i;
        }

        @NonNull
        @Override
        public String toString() {
            return i+"";
        }
    }
}