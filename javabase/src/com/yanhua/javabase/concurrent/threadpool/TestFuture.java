package com.yanhua.javabase.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * 测试Future
 */
public class TestFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Test1());
        Future<String> future = executorService.submit(new Test2());
        try {
            String s = future.get();
            System.out.printf("hello:%s\n", s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    static class Test1 implements Runnable {

        @Override
        public void run() {
            System.out.println("run Runnable instance.");
        }
    }

    static class Test2 implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("run Callable instance.");
            return "xuyh";
        }
    }

}
