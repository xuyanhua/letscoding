package com.yanhua.javabase.concurrent.local;

/**
 * 线程局部变量
 */
public class ThreadLocalTest {
    static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            final int finalI = i;
            new Thread(() -> {
                Thread.currentThread().setName("T-" + finalI);
                m1();
                m2();
            }).start();
        }
    }

    public static void m1() {
        tl.set(Thread.currentThread().getName());
    }

    public static void m2() {
        String s = tl.get();
        System.out.println(s);
    }

}
