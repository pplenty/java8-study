package com.jason.spring.cglib;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonServiceTest {

    @Test
    public void sayHello() {

//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(PersonService.class);
//        enhancer.setCallback((FixedValue) () -> "Hello Tom!");
//        PersonService proxy = (PersonService) enhancer.create();
//
//        String res = proxy.sayHello(null);
//
//        assertEquals("Hello Tom!", res);
    }
}