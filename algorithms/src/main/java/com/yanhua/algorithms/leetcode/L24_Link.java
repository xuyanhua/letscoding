package com.yanhua.algorithms.leetcode;

/**
 * 将链表的节点两两交换，例如
 * 1,2,3,4 -> 2,1,4,3
 */
public class L24_Link {
    public static void main(String[] args) {
        Node head = toLink(new int[]{1, 2, 3, 4});
        print(head);
        System.out.println("\n反转后：");
        Node head1 = swapPairs(head);
        print(head1);


    }

    /**
     * 递归法
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

    public static class Node {
        int value;
        Node next;
    }

    public static Node toLink(int[] arr) {
        Node head = new Node();
        head.value = arr[0];
        Node p = head;
        for (int i = 1; i < arr.length; i++) {
            Node tmp = new Node();
            tmp.value = arr[i];
            p.next = tmp;
            p = tmp;
        }
        return head;
    }

    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.value + ",");
            head = head.next;
        }
    }

}
