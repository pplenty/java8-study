package com.jason.time;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateTimeBetween {

    public static void main(String[] args) {

        Duration duration = Duration.between(
                LocalDateTime.of(2019, 1, 29, 12, 30, 0),
                LocalDateTime.of(2019, 3, 29, 12, 30, 0));

        System.out.println(duration);

    }
}
