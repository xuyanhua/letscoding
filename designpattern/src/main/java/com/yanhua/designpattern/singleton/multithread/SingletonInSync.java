package com.yanhua.designpattern.singleton.multithread;

/**
 * 多线程环境下的单例模式(同步模式)，懒汉式
 *
 * @author xuyanhu* @description:
 * @date 2019/4/7 上午11:05
 */
public class SingletonInSync {
    private SingletonInSync() {
    }

    private static SingletonInSync singleton = null;

    public static SingletonInSync getInstance() {
        synchronized (SingletonInSync.class) {
            if (singleton == null) {
                singleton = new SingletonInSync();
            }
        }
        return singleton;
    }
}
