package com.yanhua.javabase.concurrent.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        new CyclicBarrierDemo().go();
    }

    public void go() {
        CyclicBarrier barrier = new CyclicBarrier(3);
        new Thread(new Task(barrier),"Thread1").start();
        new Thread(new Task(barrier),"Thread2").start();
        new Thread(new Task(barrier),"Thread3").start();
    }

    class Task implements Runnable {
        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "已经到达" + System.currentTimeMillis());
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "开始处理" + System.currentTimeMillis());
        }
    }
}
