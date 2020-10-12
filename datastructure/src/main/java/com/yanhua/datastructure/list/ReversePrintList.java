package com.yanhua.datastructure.list;

import com.yanhua.datastructure.list.lists.List;
import com.yanhua.datastructure.list.lists.Node;

import java.util.Stack;

/**
 * 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来打印出每个节点的值。
 */
public class ReversePrintList {
    public static void main(String[] args) {
        List<Integer> list = List.toList(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Node<Integer> head = list.head();
        reversePrint(head);
        System.out.println("\n--------------------");
        reversePrint2(head);

    }

    /**
     * 递归的实现
     * 当链表很长时，可能导致函数调用栈很深，从而有可能导致函数调用栈溢出。
     *
     * @param head
     */
    public static void reversePrint(Node<Integer> head) {
        if (head == null) {
            return;
        }
        reversePrint(head.next());
        System.out.printf("%d,", head.data());
    }

    /**
     * 基于栈的调用
     *
     * @param head
     */
    public static void reversePrint2(Node<Integer> head) {
        if (head == null) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.data());
            head = head.next();
        }
        while (!stack.empty()) {
            System.out.printf("%d,", stack.pop());
        }
    }
}
