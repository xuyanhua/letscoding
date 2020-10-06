package com.yanhua.javabase.concurrent.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试利用线程池渲染页面
 *
 * @author xuyanhua
 * @description:
 * @date 2019/3/13 下午7:12
 */
public class TestPageRenderer {
    static final AtomicLong times = new AtomicLong();

    /**
     * 渲染页面的任务
     */
    static class MyTask implements Callable<String> {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            long time1 = System.currentTimeMillis();
            Thread.sleep(1000);
            String result = name + "数据是->" + Thread.currentThread();
            long time2 = System.currentTimeMillis();
            times.addAndGet((time2 - time1));
            System.out.println(result + "-运行时间-" + (time2 - time1));
            return result;

        }
    }

    public String pageRender(int nThreads) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        List<Callable<String>> tasks = new ArrayList<>();
        //收集任务
        for (int i = 0; i < 20; i++) {
            tasks.add(new MyTask("name-" + i));
        }
        //批量提交任务
        List<Future<String>> futures = executor.invokeAll(tasks);
        StringBuilder sb = new StringBuilder();
        //按顺序获取
        for (Future<String> future : futures) {
            try {
                String s = future.get();
                System.out.println(s);
                sb.append(s);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 使用CompletionService 实现
     * @param nThreads
     * @return
     * @throws InterruptedException
     */
    public String pageRender2(int nThreads) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        CompletionService<String> service = new ExecutorCompletionService<>(executor);
        //批量提交任务

        for (int i = 0; i < 20; i++) {
            service.submit(new MyTask("name-" + i));
        }

        StringBuilder sb = new StringBuilder();
        //按顺序获取
        for(int i = 0; i < 20; i++) {
            Future<String> future = service.take();
            try {
                String s = future.get();
                System.out.println(s);
                sb.append(s);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        long time1 = System.currentTimeMillis();
        new TestPageRenderer().pageRender2(processors + 1);
        long time2 = System.currentTimeMillis();
        System.out.println("并发执行时间:" + (time2 - time1));
        System.out.println("串行时执行时间:" + times);
    }
}
