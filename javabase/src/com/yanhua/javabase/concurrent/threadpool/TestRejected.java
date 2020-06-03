package com.yanhua.javabase.concurrent.threadpool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试饱和策略
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/14 下午2:54
 */
public class TestRejected {
    final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1,
            0, TimeUnit.NANOSECONDS,
            //有界队列使饱和策略生效
            new ArrayBlockingQueue<>(1)
    );

    public static void main(String[] args) {
        testCallerRuns();
        executorService.shutdown();
    }

    public static void testCallerRuns() {
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//交给主线程执行
//        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());//直接忽略，不执行也不报错
//        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//抛出未检查异常RejectedExecutionException
//        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());//忽略最旧的任务，优先做新任务

        final Thread mainThread = Thread.currentThread();
        System.out.println("主线程名称为：" + mainThread);
        Runnable task = () -> {
            Thread child = Thread.currentThread();
            if (mainThread.equals(child)) {
                System.out.println("交还给主线程运行。");
            } else {
                System.out.println("子线程自己运行。");
            }

            try {
                Random r = new Random();
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 10; i++) {
            executorService.submit(task);
        }
    }

}
