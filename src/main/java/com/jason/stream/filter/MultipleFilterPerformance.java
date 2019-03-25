package com.jason.stream.filter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * multiple filters vs complex condition
 */
public class MultipleFilterPerformance {

    public static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_PARSE =
            new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd HH:mm:ss")
                    // prevents parse error by nano-second fraction.
                    .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
                    .toFormatter();

    public static void main(String[] args) {

        // set test data
        List<Domain> domainSet = new ArrayList<>();
        for (int i = 0; i < 124000; i++) {

            Domain data = new Domain();
            data.setStartTime(LocalDateTime
                    .of(2019, 1, 29, 12, 30, 0)
                    .format(DATE_TIME_FORMATTER_FOR_PARSE));
            data.setEndTime(LocalDateTime
                    .of(2019, i % 12 + 1, 28, 23, 59, 59)
                    .format(DATE_TIME_FORMATTER_FOR_PARSE));
            data.setAvailable(Math.random() < 0.5 ? "Y" : "N");
            data.setPriority(i);

            domainSet.add(data);
        }

        // test 1 : complex condition
        long ts_1 = System.currentTimeMillis();
        Optional<Domain> result1 = domainSet.stream()
                .filter(info -> {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime t1 = LocalDateTime.parse(info.getStartTime(), DATE_TIME_FORMATTER_FOR_PARSE);
                    LocalDateTime t2 = LocalDateTime.parse(info.getEndTime(), DATE_TIME_FORMATTER_FOR_PARSE);

                    return ("Y".equalsIgnoreCase(info.getAvailable())
                            && now.isAfter(t1) || now.isEqual(t1))
                            && (now.isBefore(t2) || now.isEqual(t2));
                })
                .findFirst();
        long te_1 = System.currentTimeMillis();
        System.out.println("\n속도 : " + (te_1 - ts_1) / 1000.);
        System.out.println(result1);

        // test 2 : multiple filter
        long ts_2 = System.currentTimeMillis();
        Optional<Domain> result2 = domainSet.stream()
                .filter(info -> "Y".equalsIgnoreCase(info.getAvailable()))
                .filter(info -> {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime t1 = LocalDateTime.parse(info.getStartTime(), DATE_TIME_FORMATTER_FOR_PARSE);
                    LocalDateTime t2 = LocalDateTime.parse(info.getEndTime(), DATE_TIME_FORMATTER_FOR_PARSE);

                    return (now.isAfter(t1) || now.isEqual(t1))
                            && (now.isBefore(t2) || now.isEqual(t2));
                })
                .findFirst();
        long te_2 = System.currentTimeMillis();
        System.out.println("\n속도 : " + (te_2 - ts_2) / 1000.);
        System.out.println(result2);
    }

    public static class Domain {
        String startTime;
        String endTime;
        String available;
        int priority;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Domain{" +
                    "startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", available='" + available + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }
}
