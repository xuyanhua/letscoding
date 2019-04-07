package com.yanhua.designpattern.singleton.singlethread;

/**
 * 单线程环境下的单例模式，懒汉式
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/7 上午11:05
 */
public class SingletonInLazy {
    private SingletonInLazy() {
    }

    private static SingletonInLazy singleton = null;

    public static SingletonInLazy getInstance() {
        if (singleton == null) {
            singleton = new SingletonInLazy();
        }
        return singleton;
    }
}
