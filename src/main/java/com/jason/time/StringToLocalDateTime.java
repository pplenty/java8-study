package com.jason.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTime {

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String startTimeString = "2019-03-25 12:30:00";
        String endTimeString = "2019-03-25 23:59:59";
        String targetTimeString = "2019-03-25 23:59:59";
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);
        LocalDateTime targetTime = LocalDateTime.parse(targetTimeString, formatter);

        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(targetTime);

        System.out.println(targetTime.isBefore(endTime) || targetTime.isEqual(endTime));
        System.out.println(targetTime.isEqual(endTime));
    }
}
