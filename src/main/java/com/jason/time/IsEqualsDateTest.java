package com.jason.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IsEqualsDateTest {

    public static final DateTimeFormatter DATE_FORMATTER_FOR_PARSE =
            new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd")
                    .toFormatter();

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        String todayString = today.format(DATE_FORMATTER_FOR_PARSE);

        List<DayOfWeek> eventDayOfWeek = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
        List<Integer> eventDayOfMonth = Arrays.asList(1, 20);
        List<String> eventDay = Arrays.asList("2019-03-14", "2019");



        LocalDate target = LocalDate.parse("2019-05-03", DATE_FORMATTER_FOR_PARSE);
        System.out.println(target);
        System.out.println(today.isEqual(target));

        A a = new A();
        System.out.println(a.toString());



    }

    public static class A {

        B b;
        Integer page;
        @Override
        public String toString() {

            return Optional.ofNullable(this.b)
//                    .map(a -> a.b)
                    .map(b -> b.page)
                    .orElse(0)
                    .toString();
        }
    }

    public static class B {

        Integer page;
    }
}
