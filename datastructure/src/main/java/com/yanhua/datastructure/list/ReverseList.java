package com.yanhua.datastructure.list;

import com.yanhua.datastructure.list.lists.List;
import com.yanhua.datastructure.list.lists.Node;

/**
 * 单链表反转
 */
public class ReverseList {

    /**
     * 循环法进行单链表的反转
     *
     * @param head
     */
    public static Node reverseListInLoop(Node head) {
        if (head == null) {
            return head;
        }
        /*
            1.用来保存遍历节点的前一个节点（只有保存这个节点才不会断开）
            2.首次,pre节点会指向空，因为反转后最后一个节点的next就是空
         */
        Node pre = null;
        Node next = null;//用户保存遍历节点后一个节点
        while (head != null) {
            //head是当前节点，则next指向head的下一个节点
            next = head.next();
            //执行反转，则head的next调转方向指向pre
            head.next(pre);
            //执行完上面的操作后，将pre,head往后移，即
            //pre再指向head
            pre = head;
            //head再指向next
            head = next;
        }
        return pre;
    }

    /**
     * 递归法进行单链表的反转
     *
     * @param head node
     */
    public static Node reverseListInRecursion(Node head) {
        if (head == null || head.next() == null) {
            return head;
        }
        //将剩余的子表返回新的头节点
        Node newHead = reverseListInRecursion(head.next());
        //将头节点的下一个节点的后继调转为当前的头节点
        head.next().next(head);
        //因为头节点被反转为尾，所以后继变为空
        head.next(null);
        return newHead;
    }

    public static void main(String[] args) {
        List list = List.toList(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(list.toString());
        Node newHead1 = reverseListInLoop(list.head());
        list.head(newHead1);
        System.out.println(list.toString());
        Node newHead2 = reverseListInRecursion(list.head());
        list.head(newHead2);
        System.out.println(list.toString());
    }
}
