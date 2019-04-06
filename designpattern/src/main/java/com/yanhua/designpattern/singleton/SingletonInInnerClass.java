package com.yanhua.designpattern.singleton;

/**
 * @author xuyanhua
 * @description:
 * @date 2019/4/6 上午9:33
 */
public class SingletonInInnerClass {
    private SingletonInInnerClass() {
    }

    private static class InnerClass{
        private static SingletonInInnerClass singleton = new SingletonInInnerClass();
    }

    public static SingletonInInnerClass getInstance(){
        return InnerClass.singleton;
    }


}
