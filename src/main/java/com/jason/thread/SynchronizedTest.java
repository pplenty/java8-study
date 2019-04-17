package com.jason.thread;

/**
 * @author : yusik
 * @date : 2019-04-17
 */
public class SynchronizedTest {

    private final static int NUM_OF_LOOP = 100000;
    private int count1 = 0;
    private int count2 = 0;

    public static void main(String[] args) throws InterruptedException {

        SynchronizedTest test = new SynchronizedTest();

        // async
        new Thread(() -> {
            for (int i = 0; i < NUM_OF_LOOP; i++) {
                test.addCount1();
            }
            System.out.println("no1: " + test.count1);
            System.out.println(Thread.currentThread());
        }).start();

        new Thread(() -> {
            for (int i = 0; i < NUM_OF_LOOP; i++) {
                test.addCount1();
            }
            System.out.println("no2: " + test.count1);
            System.out.println(Thread.currentThread());
        }).start();

        // sync
        new Thread(() -> {
            for (int i = 0; i < NUM_OF_LOOP; i++) {
                test.addCount2();
            }
            System.out.println("no3: " + test.count2);
            System.out.println(Thread.currentThread());
        }).start();

        new Thread(() -> {
            for (int i = 0; i < NUM_OF_LOOP; i++) {
                test.addCount2();
            }
            System.out.println("no4: " + test.count2);
            System.out.println(Thread.currentThread());
        }).start();


        Thread.sleep(3000);
        System.out.println(test.count1);
        System.out.println(test.count2);

    }

    public void addCount1() {
        count1++;
    }

    public synchronized void addCount2() {
        count2++;
    }
}
