package com.yanhua.datastructure.list;

import com.yanhua.datastructure.list.lists.List;
import com.yanhua.datastructure.list.lists.Node;

/**
 * 求链表倒数第k个节点
 * 题目描述：输入一个单向链表，输出该链表中倒数第k个节点，链表的倒数第0个节点为链表的尾指针。
 * 分析：设置两个指针 p1、p2，首先 p1 和 p2 都指向 head，然后 p2 向前走 k 步，这样 p1 和 p2 之间就间隔 k 个节点，最后 p1 和 p2 同时向前移动，直至 p2 走到链表末尾。
 */
public class LastKthNode {

    public static Node theLastKthNode(Node head, int k) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        //首先fast指针往前移k次
        int i = k;
        for (; i > 0 && fast != null; i--) {
            fast = fast.next();
        }
        if (i > 0) {
            return null;//考虑k大于链表长度的场景
        }
        //fast/slow同时走，直到链表结尾
        while (fast != null) {
            slow = slow.next();
            fast = fast.next();
        }
        return slow;
    }

    public static void main(String[] args) {
        List list = List.toList(new Integer[]{1, 2, 3, 4, 5, 6});
        Node theLastKthNode = theLastKthNode(list.head(), 2);
        System.out.println(theLastKthNode.data());
    }
}
