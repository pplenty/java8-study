package com.jason.stream.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * multiple filters vs complex condition
 */
public class ListStream {

    public static void main(String[] args) {

        // set test data
        List<RankingInfo> rankList = new ArrayList<>();
        for (int i = 0; i < 124; i++) {
            RankingInfo rankingInfo = new RankingInfo();
            rankingInfo.setPayload("data" + i);
            rankingInfo.setAvailable(Math.random() < 0.5);
            rankingInfo.setPriority(i);

            rankList.add(rankingInfo);
        }

        // test 1 : complex condition
        long ts_1 = System.currentTimeMillis();
        Optional<RankingInfo> result1 = rankList
                .stream()
                .filter(RankingInfo::isAvailable)
                .findFirst();
        long te_1 = System.currentTimeMillis();
        System.out.println("\n속도 : " + (te_1 - ts_1) / 1000.);

        // test 2 : complex condition
        long ts_2 = System.currentTimeMillis();
        Optional<RankingInfo> result2 = rankList
                .parallelStream()
                .filter(RankingInfo::isAvailable)
                .findFirst();
        long te_2 = System.currentTimeMillis();
        System.out.println("\n속도 : " + (te_2 - ts_2) / 1000.);

    }

    public static class RankingInfo {
        String payload;
        boolean available;
        int priority;

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
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
            return "priority=" + priority;
        }
    }
}
