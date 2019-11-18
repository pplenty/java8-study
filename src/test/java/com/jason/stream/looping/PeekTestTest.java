package com.jason.stream.looping;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by kohyusik on 15/11/2019.
 */
public class PeekTestTest {

    @Test
    public void peek() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            users.add(new User());
        }

        String dummyName = "dummy";
        long startTime = System.nanoTime();
        users = users.stream()
                .peek(user -> user.setId(0))
                .filter(user -> user.getId() > 10)
                .peek(user -> user.setName(dummyName))
                .collect(Collectors.toList());
        long endTime = System.nanoTime();
        System.out.println("peek: ");
        System.out.println((endTime - startTime) / 1000);
        System.out.println(users.size());
    }

    @Test
    public void map() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            users.add(new User());
        }

        String dummyName = "dummy";
        long startTime = System.nanoTime();
        users = users.stream()
                .map(user -> user.setIdThis(0))
                .filter(user -> user.getId() > 10)
                .map(user -> user.setNameThis(dummyName))
                .collect(Collectors.toList());
        long endTime = System.nanoTime();
        System.out.println("map: ");
        System.out.println((endTime - startTime) / 1000);
        System.out.println(users.size());
    }

    @Test
    public void parallelStream() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            users.add(new User());
        }

        String dummyName = "dummy";
        long startTime = System.nanoTime();
        users = users.parallelStream()
                .map(user -> user.setIdThis(0))
                .filter(user -> user.getId() > 10)
                .map(user -> user.setNameThis(dummyName))
                .collect(Collectors.toList());
        long endTime = System.nanoTime();
        System.out.println("parallelStream: ");
        System.out.println((endTime - startTime) / 1000);
        System.out.println(users.size());
    }

    @Test
    public void enhancedForLoop() {

        List<User> users = new ArrayList<>();
        List<User> results = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            users.add(new User());
        }

        long startTime = System.nanoTime();
        for (User user : users) {
            user.setId(0);
            if (user.getId() > 10) {
                results.add(user);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("parallelStream: ");
        System.out.println((endTime - startTime) / 1000);
        System.out.println(users.size());
    }

    public static class User {
        private Random random = new Random();
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = random.nextInt(100);
        }

        public User setIdThis(int id) {
            this.id = random.nextInt(100);
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User setNameThis(String name) {
            this.name = name;
            return this;
        }
    }

}