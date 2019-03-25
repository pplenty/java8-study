package com.jason.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

public class StringToLocalDateTime {

    public static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_PARSE =
            new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd HH:mm:ss")
                    // prevents parse error by nano-second fraction.
                    .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
                    .toFormatter();

    public static void main(String[] args) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        DateTimeFormatter DATE_TIME_FORMATTER_FOR_PARSE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//        String startTimeString = "2019-03-25 12:30:00.123";
//        String endTimeString = "2019-03-25 23:59:59";
//        String targetTimeString = "2019-03-25 23:59:59";
//        LocalDateTime startTime = LocalDateTime.parse(startTimeString, DATE_TIME_FORMATTER_FOR_PARSE);
//        LocalDateTime endTime = LocalDateTime.parse(endTimeString, DATE_TIME_FORMATTER_FOR_PARSE);
//        LocalDateTime targetTime = LocalDateTime.parse(targetTimeString, DATE_TIME_FORMATTER_FOR_PARSE);
//
//        System.out.println(startTime);
//        System.out.println(endTime);
//        System.out.println(targetTime);
//
//        System.out.println(targetTime.isBefore(endTime) || targetTime.isEqual(endTime));
//        System.out.println(LocalDateTime.now());

        // test data
        List<Map<String, String>> subjectInfoList = new ArrayList<>();

        Map<String, String> data1 = new HashMap<>();
        data1.put("planningStartDate", "2019-01-29 16:30:00.999");
        data1.put("planningEndDate", "2019-02-31 23:59:59.112311");
        data1.put("planningPriority", "1");
        Map<String, String> data2 = new HashMap<>();
        data2.put("planningStartDate", "2019-01-29 16:30:00");
        data2.put("planningEndDate", "2019-02-31 23:59:59");
        data2.put("planningPriority", "99");
        Map<String, String> data3 = new HashMap<>();
        data3.put("planningStartDate", "2019-01-29 16:30:00");
        data3.put("planningEndDate", "2019-02-25 23:59:59");
        data3.put("planningPriority", "24");

        subjectInfoList.add(data1);
        subjectInfoList.add(data2);
        subjectInfoList.add(data3);

        List<Map<String, String>> filtered = subjectInfoList.stream()
                .filter((plan) -> {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime t1 = LocalDateTime.parse(plan.get("planningStartDate"), DATE_TIME_FORMATTER_FOR_PARSE);
                    LocalDateTime t2 = LocalDateTime.parse(plan.get("planningEndDate"), DATE_TIME_FORMATTER_FOR_PARSE);

                    return (now.isAfter(t1) || now.isEqual(t1)) && (now.isBefore(t2) || now.isEqual(t2));
                })
//                .sorted(Comparator.comparing(m -> m.get("planningPriority")).reversed())
                .sorted(Comparator.comparingInt((Map<String, String> m) -> Integer.parseInt(m.get("planningPriority"))).reversed())
                .collect(Collectors.toList());

        filtered.forEach(map -> System.out.println(map.get("planningPriority")));
        System.out.println(filtered.stream().findFirst().orElseGet(HashMap::new).get("planningPriority"));
//        System.out.println(filtered.size());
    }
}
