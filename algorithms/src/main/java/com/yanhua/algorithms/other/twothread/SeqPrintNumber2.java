package com.yanhua.algorithms.other.twothread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程，一个打印1，2，一个打印3,4
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/9 下午6:12
 */
public class SeqPrintNumber2 {
    static ReentrantLock lock = new ReentrantLock(true);
    static Integer num = 1;
    static int thread_num = 7;

    public static void main(String[] args) throws InterruptedException {
        List<Condition> conditionList = new ArrayList<>();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < thread_num; i++) {
            conditionList.add(lock.newCondition());
        }
        for (int i = 0; i < thread_num; i++) {
            Condition c = conditionList.get(i);
            int j = i + 1;
            if (j > thread_num - 1) {
                j = 0;
            }
            Condition cc = conditionList.get(j);
            threadList.add(new Thread(new MyThread(lock, c, cc), "Thread" + (i + 1)));
        }
        for (int i = 0; i < thread_num; i++) {
            threadList.get(i).start();
        }
    }


    static class MyThread implements Runnable {

        private Lock lock;
        private Condition condition;
        private Condition nextCondition;

        public MyThread(Lock lock, Condition condition, Condition nextCondition) {
            this.lock = lock;
            this.condition = condition;
            this.nextCondition = nextCondition;
        }

        @Override
        public void run() {
            while (num <= 100) {
                try {
                    lock.lock();
                    if (num > 100) {
                        condition.signalAll();
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + "-->" + num++);
                    nextCondition.signal();
                    if (num < 100) {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


}