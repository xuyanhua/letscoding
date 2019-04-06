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
        if (list == null || deletedNode == null) {
            return;
        }
        /**
         * 如果结点时尾结点，则删除要(O(n))
         * <ul>
         *     <li>a.从头结点遍历链表到要删除结点的前驱结点</li>
         *     <li>b.把前驱结点的next指针指向删除结点的下一结点</li>
         *     <li>c.再释放删除结点内存空间（java中会自动回收）</li>
         * </ul>
         */
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
        /**
         * 如果删除结点不是尾结点，则O(1)
         * <ul>
         *     <li>a.把删除结点的后继结点的值覆盖到要删除结点上</li>
         *     <li>b.将删除结点的next指针指向后继结点的后继结点</li>
         *     <li>c.删除后续结点(java中会自动回收)</li>
         * </ul>
         */
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
