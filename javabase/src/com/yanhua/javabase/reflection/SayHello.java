package com.yanhua.javabase.reflection;

public class SayHello {
    public void sayHello(String name) {
        System.out.println("public hello:" + name);
    }

    private void sayHello2(String name) {
        System.out.println("private hello:" + name);
    }
}
