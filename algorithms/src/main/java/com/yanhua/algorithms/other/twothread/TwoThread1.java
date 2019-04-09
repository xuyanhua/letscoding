package com.yanhua.algorithms.other.twothread;

import java.util.concurrent.TimeUnit;

/**
 * 两个线程，一个打印1，2，一个打印3,4
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/9 下午6:12
 */
public class TwoThread1 {

    final static Object lock = new Object();
    static Integer num = new Integer(0);

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread1 = new MyThread(100);
        MyThread myThread2 = new MyThread(100);
        Thread t1 = new Thread(myThread1, "Thread1");
        Thread t2 = new Thread(myThread2, "Thread2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        synchronized (lock) {
            lock.notify();
        }
        t1.join();
        t2.join();
    }

    private static class MyThread implements Runnable {
        private final int max;

        public MyThread(int max) {
            this.max = max;
        }

        @Override
        public void run() {
            while (num < max) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num = num + 1;
                    System.out.println(Thread.currentThread().getName() + "-->" + num);
                    lock.notify();
                }
            }
        }
    }
}