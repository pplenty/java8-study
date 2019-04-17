package com.jason.thread;

import java.util.*;

/**
 * @author : yusik
 * @date : 2019-04-17
 */
public class SynchronizedObjectTest {

    final static int NUM_OF_LOOP = 100000;
    private int count1 = 0;
    private int count2 = 0;

    Object sync1 = new Object();
    Object sync2 = new Object();
    Map<String, Integer> sync3 = new HashMap<>();

    public static void main(String... args) throws InterruptedException {

        SynchronizedObjectTest test = new SynchronizedObjectTest();

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
                test.subCount1();
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
                test.subCount2();
            }
            System.out.println("no4: " + test.count2);
            System.out.println(Thread.currentThread());
        }).start();


        Thread.sleep(3000);
        System.out.println(test.count1);
        System.out.println(test.count2);

        /**
         * Contructor line 451
         */
/*
        if (candidateList.size() == 1 && explicitArgs == null && !mbd.hasConstructorArgumentValues()) {
            Method uniqueCandidate = candidateList.get(0);
            if (uniqueCandidate.getParameterCount() == 0) {
                mbd.factoryMethodToIntrospect = uniqueCandidate;

                ////////////////////////////////////////////////////////////////////////////
                synchronized (mbd.constructorArgumentLock) {
                    mbd.resolvedConstructorOrFactoryMethod = uniqueCandidate;
                    mbd.constructorArgumentsResolved = true;
                    mbd.resolvedConstructorArguments = EMPTY_ARGS;
                }
                ////////////////////////////////////////////////////////////////////////////

                bw.setBeanInstance(instantiate(beanName, mbd, factoryBean, uniqueCandidate, EMPTY_ARGS));
                return bw;
            }
        }
*/

    }

    public void addCount1() {
        synchronized (sync1) {
            count1++;
        }
    }

    public void subCount1() {
        synchronized (sync1) {
            count1--;
        }
    }

    public void addCount2() {
        synchronized (sync3) {
            count2++;
        }
    }

    public void subCount2() {
        synchronized (sync3) {
            count2--;
        }
    }
}
