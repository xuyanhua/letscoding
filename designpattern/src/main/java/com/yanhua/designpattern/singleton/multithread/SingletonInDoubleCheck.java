package com.yanhua.designpattern.singleton.multithread;

/**
 * 多线程环境下的单例模式(双检查模式)，懒汉式 </br>
 * 注：volatile不能缺失，否则也会导致多个对象创建
 *
 * @author xuyanhu* @description:
 * @date 2019/4/7 上午11:05
 */
public class SingletonInDoubleCheck {
    private SingletonInDoubleCheck() {
    }

    private static volatile SingletonInDoubleCheck singleton = null;

    public static SingletonInDoubleCheck getInstance() {
        if (singleton == null) {
            synchronized (SingletonInDoubleCheck.class) {
                if (singleton == null) {
                    singleton = new SingletonInDoubleCheck();
                }
            }
        }
        return singleton;
    }
}
