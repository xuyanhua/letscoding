package com.yanhua.datastructure.stack;


import java.util.Stack;

/**
 * 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail和deleteHead，分别完成在
 * 队列尾部插入节点和在队列头部删除节点的功能
 */
public class O09_1_TwoStackAsQueue {
    private Stack<String> stack1 = new Stack<>();
    private Stack<String> stack2 = new Stack<>();

    public static void main(String[] args) {
        O09_1_TwoStackAsQueue queue = new O09_1_TwoStackAsQueue();
        queue.appendTail("a");
        queue.appendTail("b");
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        queue.appendTail("c");
        queue.appendTail("d");
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }

    public void appendTail(String s) {
        stack1.push(s);
    }

    /**
     * 栈的特性是先进后出，队列的特性是先进先出。
     * 将数据第1次压栈是反序出来，再压1次栈就会成正序。
     * 因此出队时只要stack2中没有，就从stack1中拿导出来，如果有直接出队
     *
     * @return
     */
    public String deleteHead() {
        //从第二个栈中弹出来
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        //如果栈2中没有，看看栈1有没有，有的话就导到栈2中去
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        return null;

    }


}
