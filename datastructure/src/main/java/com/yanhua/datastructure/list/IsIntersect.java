package com.yanhua.datastructure.list;

import com.yanhua.datastructure.list.lists.List;
import com.yanhua.datastructure.list.lists.Node;

/**
 * 判断两个链表是否相交(无环)
 * 进一步考虑“如果两个没有环的链表相交于某一节点，那么在这个节点之后的所有节点都是两个链表
 * 共有的”这个特点，我们可以知道，如果它们相交，则最后一个节点一定是共有的。而我们很容易能
 * 得到链表的最后一个节点，所以这成了我们简化解法的一个主要突破口。那么，我们只要判断两个链
 * 表的尾指针是否相等。相等，则链表相交；否则，链表不相交。
 */
public class IsIntersect {

    //这个方法时间复杂度为线性O(N)，空间复杂度为O(1)
    public static boolean isIntersect(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return false;
        }
        //获取两个链表的尾节点
        Node lastNode1 = head1;
        while (lastNode1.next() != null) {
            lastNode1 = lastNode1.next();
        }
        Node lastNode2 = head2;
        while (lastNode2.next() != null) {
            lastNode2 = lastNode2.next();
        }
        return lastNode1 == lastNode2; //尾节点是否相同
    }

    public static Node findIntersect(Node head1, Node head2) {
        int length1 = List.length(head1);
        int length2 = List.length(head2);
        Node h1 = head1;
        Node h2 = head2;
        //对齐两个链表
        if (length1 > length2) {
            for (int i = 0; i < (length1 - length2); i++) {
                h1 = h1.next();
            }
        } else {
            for (int i = 0; i < (length2 - length1); i++) {
                h2 = h2.next();
            }
        }
        //对齐后，两个指针同时往后走，相交点即为第一个公共节点
        while (h1 != null) {
            if (h1 == h2) {
                return h1;
            }
            h1 = h1.next();
            h2 = h2.next();
        }
        return null;

    }

    public static void main(String[] args) {
        //假设第一个链表的6是交叉点
        List list1 = List.toList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        List list2 = List.toList(new Integer[]{1, 2, 3});
        list2.get(2).next(list1.get(5));
        boolean isIntersect = isIntersect(list1.head(), list2.head());
        System.out.println(isIntersect);
        Node intersect = findIntersect(list1.head(), list2.head());
        System.out.println(intersect.data());
    }
}
