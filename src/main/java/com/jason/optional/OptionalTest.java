package com.jason.optional;

import java.util.ArrayList;
import java.util.List;

public class OptionalTest {

    public static void main(String[] args) {

        List<Integer> numberList1 = getTestData(2);
        List<Integer> numberList2 = getTestData(3);
        List<Integer> numberList3 = getTestData(6);

        List<List<Integer>> list = new ArrayList<>();
        list.add(numberList1);
        list.add(numberList2);
        list.add(numberList3);
        list.add(null);
        System.out.println(list);

        list.stream().map(integers -> integers.get(2));

    }

    public static List<Integer> getTestData(int base) {
        // set test data
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            if (i % base == 0) {
                numberList.add(null);
            } else {
                numberList.add(i);
            }
        }

        return numberList;
    }
}
