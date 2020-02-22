package com.yanhua.javabase.concurrent.tools;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        new SemaphoreDemo().go();
    }

    public void go() {
        Semaphore semaphore = new Semaphore(6);
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(semaphore), "Thread" + i).start();
        }
    }

    class Task implements Runnable {
        private Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("线程" + Thread.currentThread().getName() + "访问资源" + System.currentTimeMillis());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
