package com.yanhua.datastructure.list;

import com.yanhua.datastructure.list.lists.List;
import com.yanhua.datastructure.list.lists.Node;

/**
 * 求链表的中间节点
 * 题目描述：求链表的中间节点，如果链表的长度为偶数，返回中间两个节点的任意一个，
 * 若为奇数，则返回中间节点。
 * 分析：此题的解决思路和第3题「求链表的倒数第 k 个节点」很相似。可以先求链表的长度，
 * 然后计算出中间节点所在链表顺序的位置。但是如果要求只能扫描一遍链表，如何解决呢？
 * 最高效的解法和第3题一样，通过两个指针来完成。用两个指针从链表头节点开始，
 * 一个指针每次向后移动两步，一个每次移动一步，直到快指针移到到尾节点，那么慢指针即是所求。
 */
public class TheMiddleNode {
    public static Node theLastKthNode(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        //fast/slow同时走，快指针一次两步，慢指针一次一步，直到链表结尾
        while (fast != null && fast.next() != null) {
            slow = slow.next();
            fast = fast.next().next();
        }
        return slow;
    }

    public static void main(String[] args) {
        List list = List.toList(new Integer[]{1, 2, 3, 4, 5});
        Node theLastKthNode = theLastKthNode(list.head());
        System.out.println(theLastKthNode.data());
    }
}
