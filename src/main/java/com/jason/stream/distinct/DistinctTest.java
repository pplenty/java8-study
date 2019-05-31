package com.jason.stream.distinct;

import com.jason.stream.filter.ListStream;

import java.time.DayOfWeek;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DistinctTest {

    public static void main(String[] args) {

        // set test data
        List<ListStream.RankingInfo> rankList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ListStream.RankingInfo rankingInfo = new ListStream.RankingInfo();
            rankingInfo.setPayload("data" + i);
            rankingInfo.setAvailable(Math.random() < 0.5);
            rankingInfo.setPriority((int) (Math.random() * 20));

            rankList.add(rankingInfo);
        }

        // test 1 : complex condition
        System.out.println(rankList.size());
        long ts_1 = System.currentTimeMillis();

        rankList = rankList.stream()
                .filter(distinctByKey(ListStream.RankingInfo::getPriority)).collect(Collectors.toList());
        long te_1 = System.currentTimeMillis();
        System.out.println("\n속도 : " + (te_1 - ts_1) / 1000.);
        System.out.println(rankList.size());

        String a = Optional.of("naver")
//                .filter(StringUtils::isNotBlank)
                .map(utmSource -> "AP".equals(utmSource) ? "external" : null)
                .orElse("search");
        System.out.println(a);
        System.out.println(isBlank("     "));


        System.out.println(DayOfWeek.FRIDAY);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

        final Set<Object> seen = new HashSet<>();

        return t -> seen.add(keyExtractor.apply(t));
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
