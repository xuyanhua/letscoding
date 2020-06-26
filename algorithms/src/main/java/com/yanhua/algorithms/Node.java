package com.yanhua.algorithms;

public class Node {
    public int value;
    public Node next;

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
