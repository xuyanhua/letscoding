package com.yanhua.javabase.classloader;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public int howOldAreYou() {
        System.out.println("我" + name + "多大了");
        int age = 30;
        return age;
    }
}
