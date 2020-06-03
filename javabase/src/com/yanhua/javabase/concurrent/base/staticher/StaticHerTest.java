package com.yanhua.javabase.concurrent.base.staticher;

/**
 * 子类会继承父类的静态属性和方法，但不会重写，而是覆盖
 */
public class StaticHerTest {
    public static void main(String[] args) {
        Child child = new Child();
        System.out.println(Parent.s);
        System.out.println(child.s);
        Parent.hello();
        Child.hello();
        child.hello();
    }
}

class Parent {
    static String s = "12345";

    static void hello() {
        System.out.println("Parent hello");
    }
}

class Child extends Parent {
    static String s = "123456";

    static void hello() {
        System.out.println("Child hello");
    }
}
