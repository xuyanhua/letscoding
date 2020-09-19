package com.yanhua.algorithms.leetcode;

import com.yanhua.algorithms.Node;

/**
 * <h1>《力扣题库》2.两数相加</h1>
 * <p>给出两个非空的链表用来表示两个非负的整数，其中它们各自的位数是按钮逆序的方式来存储的，并且
 * 它们的每个节点只能存储一位数字</p>
 * <p>如果我们将两个整数加起来，则会返回一个新的链表表示它们的和</p>
 * <p>可以假设除了数字0之外，这两个数都不能以0开头</p>
 * <b>示例：</b>
 * <pre>
 * 输入 (2 -> 4 -> 3) + (5 -> 6-> 4)
 * 输出 7->0->8
 * 因为342+465=807
 * </pre>
 */
public class L002_AddTwoNumber {
    public static void main(String[] args) {
        Node num1Link = Node.toLink(new int[]{2, 4, 3});
        Node.print(num1Link, "->");
        System.out.print(" + ");
        Node num2Link = Node.toLink(new int[]{5, 6, 4});
        Node.print(num2Link, "->");
        System.out.println();
        Node sum = addTwoNumber(num1Link, num2Link);
        Node.print(sum, "->");
    }

    /**
     * 将两个链表构成的整数相加
     *
     * @param num1Link
     * @param num2Link
     * @return
     */
    private static Node addTwoNumber(Node num1Link, Node num2Link) {
        if (num1Link == null || num2Link == null) {
            return null;
        }
        Node p1 = num1Link;
        Node p2 = num2Link;
        //头节点
        Node head = null;
        //辅助节点
        Node pre = null;
        //进位
        int carry = 0;
        boolean num1Exists,num2Exists;
        while ((num1Exists = p1 != null) | (num2Exists = p2 != null)) {
            int num = carry;//1.先加进位值
            //2.再加两个加数（如果存在）
            if (num1Exists) {
                num += p1.value;
            }
            if (num2Exists) {
                num += p2.value;
            }
            carry = 0;//3.重置进位
            //构造节点
            Node node = new Node();
            if (num >= 10) {
                node.value = num % 10;
                carry = num / 10;
            } else {
                node.value = num;
            }
            //初始化头节点
            if (head == null) {
                head = node;
            }
            //节点连接
            if (pre != null) {
                pre.next = node;
            }
            //初始化前一节点
            pre = node;
            if (num1Exists) {
                p1 = p1.next;
            }
            if (num2Exists) {
                p2 = p2.next;
            }
        }

        return head;
    }


}
