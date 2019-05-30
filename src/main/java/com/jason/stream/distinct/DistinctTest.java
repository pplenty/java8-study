package com.jason.stream.distinct;

import com.jason.stream.filter.ListStream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

        final Set<Object> seen = new HashSet<>();

        return t -> seen.add(keyExtractor.apply(t));
    }
}
