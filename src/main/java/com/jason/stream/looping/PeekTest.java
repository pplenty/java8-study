package com.jason.stream.looping;

import java.util.Arrays;

public class PeekTest {

    public static void main(String[] args) {
        int[] intArr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        System.out.println("peek()를 마지막에 호출");
        Arrays.stream(intArr)
                .filter(a -> a > 50)
                .peek(System.out::println);   //동작 하지 않음.

        System.out.println("최종 처리 메소드를 마지막에 호출");
        int total = Arrays.stream(intArr)
                .filter(a -> a > 50)
                .peek(System.out::println)   //동작
                .sum();
        System.out.println("총합: " + total);

        System.out.println("forEach()를 마지막에 호출");
        Arrays.stream(intArr)
                .filter(a -> a > 50)
                .forEach(System.out::println); //동작

    }

}
