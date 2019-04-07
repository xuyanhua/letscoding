package com.yanhua.designpattern.singleton.singlethread;

/**
 * 单线程环境下的单例模式，饿汉式
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/7 上午11:05
 */
public class SingletonInHungry {
    private SingletonInHungry() {
    }

    private static SingletonInHungry singleton = new SingletonInHungry();

    public static SingletonInHungry getInstance() {
        return singleton;
    }
}
