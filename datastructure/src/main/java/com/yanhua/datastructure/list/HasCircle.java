package com.yanhua.datastructure.list;

import com.yanhua.datastructure.list.lists.List;
import com.yanhua.datastructure.list.lists.Node;

/**
 * 判断单链表是否存在环
 */
public class HasCircle {

    /**
     * 判断是否存在环
     * <p>
     * 题目描述：输入一个单向链表，判断链表是否有环？
     * <p>
     * 分析：通过两个指针，分别从链表的头节点出发，一个每次向后移动一步，另一个移动两步，
     * 两个指针移动速度不一样，如果存在环，那么两个指针一定会在环里相遇。
     *
     * @param head
     * @param circleNode 环内相遇的节点
     * @return
     */
    public static boolean hasCircle(Node head, Node circleNode) {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head;
        //fast/slow同时走，快指针一次两步，慢指针一次一步，直到链表结尾
        while (fast != null && fast.next() != null) {
            slow = slow.next();
            fast = fast.next().next();
            if (slow == fast) {
                //如果存在环，则肯定会在环内相遇
                circleNode.data(fast.data());
                circleNode.next(fast.next());
                return true;
            }
        }
        return false;
    }


    /**
     * 找到环的入口点
     * 题目描述：输入一个单向链表，判断链表是否有环。如果链表存在环，如何找到环的入口点？
     * 解题思路：由上题可知，按照 p2 每次两步，p1 每次一步的方式走，发现 p2 和 p1 重合，确定了单向链表有环路了。接下来，让p2回到链表的头部，重新走，每次步长不是走2了，而是走1，那么当 p1 和 p2 再次相遇的时候，就是环路的入口了。
     * <p>
     * 为什么？：假定起点到环入口点的距离为 a，p1 和 p2 的相交点M与环入口点的距离为b，环路的周长为L，当 p1 和 p2 第一次相遇的时候，假定 p1 走了 n 步。那么有：
     * <p>
     * p1走的路径： a+b ＝ n；
     * p2走的路径： a+b+k*L = 2*n； p2 比 p1 多走了k圈环路，总路程是p1的2倍
     * <p>
     * 根据上述公式可以得到 k*L=a+b=n显然，如果从相遇点M开始，p1 再走 n 步的话，还可以再回到相遇点，同时p2从头开始走的话，经过n步，也会达到相遇点M。
     * <p>
     * 显然在这个步骤当中 p1 和 p2 只有前 a 步走的路径不同，所以当 p1 和 p2 再次重合的时候，必然是在链表的环路入口点上。
     *
     * @param head
     * @param circleNode 环内节点
     * @return
     */
    public static Node findCircle(Node head, Node circleNode) {
        if (head == null) {
            return null;
        }
        Node p1 = circleNode;
        Node p2 = head;
        //fast/slow同时走，快指针一次两步，慢指针一次一步，直到链表结尾
        while (p1 != null && p2 != null) {
            p1 = p1.next();
            p2 = p2.next();
            if (p1 == p2) {
                return p2;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List list = List.toList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        //设置6为环的交叉点
        Node circleNode = list.get(5);
        list.get(10).next(circleNode);
        Node circleNode0 = new Node();
        boolean hasCircle = hasCircle(list.head(), circleNode0);
        System.out.println(hasCircle + "-" + circleNode0.data());
        Node circleNode1 = findCircle(list.head(), circleNode0);
        System.out.println(circleNode1.data());


    }
}
