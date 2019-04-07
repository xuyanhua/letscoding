package com.yanhua.designpattern.singleton;

/**
 * 使用静态内部类实现的单例模式（最优模式）
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/6 上午9:33
 */
public class SingletonInInnerClass {
    private SingletonInInnerClass() {
    }

    private static class InnerClass {
        private static SingletonInInnerClass singleton = new SingletonInInnerClass();
    }

    public static SingletonInInnerClass getInstance() {
        return InnerClass.singleton;
    }

    public static void main(String[] args) {
        SingletonInInnerClass singleton = SingletonInInnerClass.getInstance();
        System.out.println(singleton);
    }

}
