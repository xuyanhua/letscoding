package com.yanhua.javabase.concurrent.tools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 测试TestFutureTask，另外一种闭锁。<br/>
 * FutureTask是一种抽象的可生成结果的计算，它通过Callable来实现。<br/>
 * future.get()方法的结果，取决于线程运行的状态，如果线程运行完成，则立即返回结果，<br/>
 * 反之，则进入阻塞状态。<br/>
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/13 下午2:03
 */
public class TestFutureTask {
    private final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
        public String call() throws Exception {
            System.out.println("正在初始化数据..");
            Thread.sleep(2 * 1000);
            System.out.println("正在初始化数据完成");
            return "ok";
        }
    });
    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public String get() throws ExecutionException, InterruptedException {
        return futureTask.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestFutureTask testFutureTask = new TestFutureTask();
        testFutureTask.start();
        String info = testFutureTask.get();
        System.out.println("获取到初始化信息:" + info);

    }
}
