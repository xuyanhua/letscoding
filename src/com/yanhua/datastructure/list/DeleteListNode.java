package com.yanhua.datastructure.list;

/**
 * 在O(1)时间复杂度内删除结点
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
        if (deletedNode == null) {
            return;
        }
        //尾结点(O(n))
        if (deletedNode.getNext() == null) {
            Node cur = list.head;
            Node next = null;
            while ((next = cur.getNext()) != null) {
                if (next.equals(deletedNode)) {
                    cur.setNext(next.getNext());
                    return;
                }
                cur = next;
            }
        }
        //O(1)
        Node next = deletedNode.getNext();
        deletedNode.setData(next.getData());
        deletedNode.setNext(next.getNext());
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
