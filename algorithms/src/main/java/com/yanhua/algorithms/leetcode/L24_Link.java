package com.yanhua.algorithms.leetcode;

import com.yanhua.algorithms.Node;

/**
 * 将链表的节点两两交换，例如
 * 1,2,3,4 -> 2,1,4,3
 */
public class L24_Link {
    public static void main(String[] args) {
        Node head1 = Node.toLink(new int[]{1, 2, 3, 4});
        Node.print(head1);
        System.out.println("\n反转后：");
        Node head2 = swapPairs(head1);
        Node.print(head2);

        Node head3 = Node.toLink(new int[]{1, 2, 3, 4, 5});
        System.out.println("\n反转后2：");
        Node head4 = swapPairs2(head3);
        Node.print(head4);


    }

    /**
     * 递归法
     * 时间复杂度O(n)、空间复杂度O(n)主要是递归空间
     *
     * @param head
     * @return
     */
    public static Node swapPairs(Node head) {
        if (head == null || head.next == null) {
            //1.如果当前没有节点或只有一个节点，就不需要交换了
            return head;
        }
        //2.调用单元
        //需要交换的两个节点是head和head.next
        Node firstNode = head;
        Node secondNode = head.next;

        //firstNode连接后面交换
        firstNode.next = swapPairs(secondNode.next);
        //secondNode连接firstNode
        secondNode.next = firstNode;
        //3.返回值：返回交换完成的子链表
        //secondNode 变成头结点
        return secondNode;
    }

    /**
     * 迭代法
     * 时间复杂度
     * 空间复杂度
     *
     * @param head
     * @return
     */
    public static Node swapPairs2(Node head) {
        //临时节点，作为列表的头节点的前置节点，并存储头结节的指针
        Node temp = new Node();
        temp.next = head;
        Node preNode = temp;
        while (head != null && head.next != null) {
            Node firstNode = head;
            Node secondNode = head.next;

            //交换
            preNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            //重置头节点
            preNode = firstNode;
            head = firstNode.next;
        }
        //返回新的头节点
        return temp.next;
    }

}
