package com.yanhua.datastructure.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 两个队列实现一个栈
 */
public class TwoQueueAsStack {
    private Queue<String> queue1 = new ArrayBlockingQueue<String>(10);
    private Queue<String> queue2 = new ArrayBlockingQueue<String>(10);

    public static void main(String[] args) {
        TwoQueueAsStack stack = new TwoQueueAsStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public void push(String s) {
        queue1.add(s);
    }

    public String pop() {
        while (queue1.size() > 1) {
            queue2.add(queue1.remove());
        }
        String s = queue1.remove();
        while (queue2.size() > 0) {
            queue1.add(queue2.remove());
        }
        return s;
    }
}
