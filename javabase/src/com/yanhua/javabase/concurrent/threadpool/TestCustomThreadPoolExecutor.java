package com.yanhua.javabase.concurrent.threadpool;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试对ThreadPoolExecutor的定制
 * 可以给线程池加上统计信息
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/14 下午3:18
 */
public class TestCustomThreadPoolExecutor extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final AtomicLong totlalTime = new AtomicLong();
    private final AtomicLong numTasks = new AtomicLong();

    public TestCustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        Date now = new Date();
        startTime.set(now.getTime());
        System.out.println("线程：" + t + ",开始任务 " + r + ", on " + now);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Date now = new Date();
        long timestart = startTime.get();
        long timeend = now.getTime();
        totlalTime.addAndGet(timeend - timestart);
        numTasks.incrementAndGet();
        System.out.println("完成任务 " + r + ", on " + now);
    }

    @Override
    protected void terminated() {
        System.out.println("线程结束，平均时间：" + (totlalTime.get() / numTasks.get()));
    }

    public static void main(String[] args) {
        TestCustomThreadPoolExecutor threadPoolExecutor = new TestCustomThreadPoolExecutor(1, 1, 0, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<Runnable>());
        Runnable task = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        };
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(task);
        }
        threadPoolExecutor.shutdown();
    }
}
