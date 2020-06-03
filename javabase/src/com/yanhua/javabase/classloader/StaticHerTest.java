package com.yanhua.javabase.classloader;

/**
 * 子类会继承父类的静态属性和方法，但不会重写，而是覆盖
 */
public class StaticHerTest {
    public static void main(String[] args) {
        StaticChild child = new StaticChild();
        System.out.println(StaticParent.s);
        System.out.println(child.s);
        StaticParent.hello();
        StaticChild.hello();
        child.hello();
    }
}

class StaticParent {
    static String s = "12345";

    static void hello() {
        System.out.println("Parent hello");
    }
}

class StaticChild extends StaticParent {
    static String s = "123456";

    static void hello() {
        System.out.println("Child hello");
    }
}
