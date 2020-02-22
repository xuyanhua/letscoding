package com.yanhua.javabase.concurrent.tools;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        new CountDownLatchDemo().go();
    }

    public void go() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(new Task(latch), "Thread1").start();
        new Thread(new Task(latch), "Thread2").start();
        new Thread(new Task(latch), "Thread3").start();
        latch.await();
        System.out.println("所有线程已到达，主线程开始执行." + System.currentTimeMillis());
    }

    class Task implements Runnable {
        private CountDownLatch latch;

        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "已经到达" + System.currentTimeMillis());
            latch.countDown();
        }
    }
}
