package com.yanhua.javabase.concurrent.tools;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 */
public class ExchangeDemo {
    public static void main(String[] args) {
        new ExchangeDemo().go();
    }

    public void go() {
        Exchanger exchanger = new Exchanger();
        new Thread(new Task(exchanger), "girl").start();
        new Thread(new Task(exchanger), "boy").start();

    }

    class Task implements Runnable {
        private Exchanger exchanger;

        public Task(Exchanger exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                Object another = exchanger.exchange("I am " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + ":" + another);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
