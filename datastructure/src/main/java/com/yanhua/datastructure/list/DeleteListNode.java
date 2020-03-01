package com.yanhua.datastructure.list;

import com.yanhua.datastructure.list.lists.List;
import com.yanhua.datastructure.list.lists.Node;

/**
 * 在O(1)时间复杂度内删除结点
 * 题目描述：给定链表的头指针和一个节点指针，在O(1)时间删除该节点。[Google面试题]
 * 分析：本题与《编程之美》上的「从无头单链表中删除节点」类似。主要思想都是「狸猫换太子」，即用下一个节点数据覆盖要删除的节点，然后删除下一个节点。但是如果节点是尾节点时，该方法就行不通了。
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/4 上午8:23
 */
public class DeleteListNode {


    /**
     * 在O(1)时间复杂度内删除结点
     *
     * @param list
     * @param deletedNode
     */
    public static void deleteOneNode(List list, Node deletedNode) {
        if (list == null || deletedNode == null) {
            return;
        }
        /**
         * 如果结点是尾结点，则删除要(O(n))
         * <ul>
         *     <li>a.从头结点遍历链表到要删除结点的前驱结点</li>
         *     <li>b.把前驱结点的next指针指向删除结点的下一结点</li>
         *     <li>c.再释放删除结点内存空间（java中会自动回收）</li>
         * </ul>
         */
        if (deletedNode.next() == null) {
            Node cur = list.head();
            Node next = null;
            while ((next = cur.next()) != null) {
                if (next.equals(deletedNode)) {
                    cur.next(next.next());
                    return;
                }
                cur = next;
            }
        }
        /**
         * 如果删除结点不是尾结点，则O(1)
         * <ul>
         *     <li>a.把删除结点的后继结点的值覆盖到要删除结点上</li>
         *     <li>b.将删除结点的next指针指向后继结点的后继结点</li>
         *     <li>c.删除后续结点(java中会自动回收)</li>
         * </ul>
         */
        Node next = deletedNode.next();
        deletedNode.data(next.data());
        deletedNode.next(next.next());
    }

    public static void main(String[] args) {
        Integer arr[] = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> list = List.toList(arr);
        Node deletedNode = list.get(3);
        System.out.println(list.toString());
        deleteOneNode(list, deletedNode);
        System.out.println(list.toString());
    }
}
