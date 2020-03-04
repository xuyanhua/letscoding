package com.yanhua.javabase.concurrent.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程交替打印数字，用Condition实现，如：
 * t1 1
 * t2 2
 * t3 3
 * t1 4
 * t2 5
 * ...
 */
public class PrintNumberWithCondition {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        AtomicInteger number = new AtomicInteger();

        ThreadPrinter threadPrinter1 = new ThreadPrinter(number, lock, condition1, condition2);
        ThreadPrinter threadPrinter2 = new ThreadPrinter(number, lock, condition2, condition3);
        ThreadPrinter threadPrinter3 = new ThreadPrinter(number, lock, condition3, condition1);
        new Thread(threadPrinter1).start();
        new Thread(threadPrinter2).start();
        new Thread(threadPrinter3).start();
        Thread.sleep(1);
        lock.lock();
        condition1.signal();
        lock.unlock();

    }

    static class ThreadPrinter implements Runnable {

        private AtomicInteger number;
        private ReentrantLock lock;
        private Condition condition;
        private Condition nextCondition;

        public ThreadPrinter(AtomicInteger number, ReentrantLock lock, Condition condition, Condition nextCondition) {
            this.number = number;
            this.lock = lock;
            this.condition = condition;
            this.nextCondition = nextCondition;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "--" + (char) ('A' + (number.incrementAndGet()-1) % 3));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    nextCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }
}
