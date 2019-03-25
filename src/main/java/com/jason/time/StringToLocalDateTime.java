package com.jason.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class StringToLocalDateTime {

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String startTimeString = "2019-03-25 12:30:00";
        String endTimeString = "2019-03-25 23:59:59";
        String targetTimeString = "2019-03-25 23:59:59";
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);
        LocalDateTime targetTime = LocalDateTime.parse(targetTimeString, formatter);

        // test data
        List<Map<String, String>> subjectInfoList = new ArrayList<>();

        Map<String, String> data1 = new HashMap<>();
        data1.put("planningStartDate", "2019-01-29 16:30:00");
        data1.put("planningEndDate", "2019-01-31 23:59:59");
        data1.put("planningPriority", "1");
        Map<String, String> data2 = new HashMap<>();
        data2.put("planningStartDate", "2019-01-29 16:30:00");
        data2.put("planningEndDate", "2019-01-31 23:59:59");
        data2.put("planningPriority", "99");
        Map<String, String> data3 = new HashMap<>();
        data3.put("planningStartDate", "2019-01-29 16:30:00");
        data3.put("planningEndDate", "2019-03-24 23:59:59");
        data3.put("planningPriority", "50");

        subjectInfoList.add(data1);
        subjectInfoList.add(data2);
        subjectInfoList.add(data3);

        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(targetTime);

        System.out.println(targetTime.isBefore(endTime) || targetTime.isEqual(endTime));
        System.out.println(LocalDateTime.now());

        List<Map<String, String>> filtered = subjectInfoList.stream()
                .filter((plan) -> {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime t1 = LocalDateTime.parse(plan.get("planningStartDate"), formatter);
                    LocalDateTime t2 = LocalDateTime.parse(plan.get("planningEndDate"), formatter);
                    return (now.isAfter(t1) || now.isEqual(t1)) && (now.isBefore(t2) || now.isEqual(t2));
                })
//                .sorted(Comparator.comparing(m -> m.get("planningPriority")).reversed())
                .sorted(Comparator.comparingInt((Map<String, String> m) -> Integer.parseInt(m.get("planningPriority"))).reversed())
                .collect(Collectors.toList());

        filtered.forEach(map -> System.out.println(map.get("planningPriority")));
        System.out.println(filtered.stream().findFirst().orElse(Collections.emptyMap()));
//        System.out.println(filtered.size());
    }
}
