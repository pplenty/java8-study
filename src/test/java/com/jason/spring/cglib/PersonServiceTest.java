package com.jason.spring.cglib;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonServiceTest {

    @Test
    public void test1() {

//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(PersonService.class);
//        enhancer.setCallback((FixedValue) () -> "Hello Tom!");
//        PersonService proxy = (PersonService) enhancer.create();
//
//        String res = proxy.sayHello(null);
//
//        assertEquals("Hello Tom!", res);
    }

    @Test
    public void test2() {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(PersonService.class);
//        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
//            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
//                return "Hello Tom!";
//            } else {
//                return proxy.invokeSuper(obj, args);
//            }
//        });
//
//        PersonService proxy = (PersonService) enhancer.create();
//
//        assertEquals("Hello Tom!", proxy.sayHello(null));
//        int lengthOfName = proxy.lengthOfName("Mary");
//
//        assertEquals(4, lengthOfName);
    }

    @Test
    public void test3() {
//        public interface Interface1 {
//            String first();
//        }
//
//        public interface Interface2 {
//            String second();
//        }
//
//        public class Class1 implements Interface1 {
//            @Override
//            public String first() {
//                return "first behaviour";
//            }
//        }
//
//        public class Class2 implements Interface2 {
//            @Override
//            public String second() {
//                return "second behaviour";
//            }
//        }
    }
}