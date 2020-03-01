package com.yanhua.datastructure.list.lists;

/**
 * 链表
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/5 上午7:17
 */
public class List<T> {
    private Node<T> head;
    private int size;

    public Node<T> head() {
        return head;
    }

    public void head(Node<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获取指定索引的结点
     *
     * @param index
     * @return
     */
    public Node get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        int index0 = 0;
        Node cur = head;
        while (cur != null) {
            if (index0 == index) {
                return cur;
            }
            cur = cur.next;
            index0++;
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node cur = head;
        while (cur != null) {
            sb.append(cur.data()).append(",");
            cur = cur.next;
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }

    /**
     * 把数组转为链表
     *
     * @param arr
     * @return
     */
    public static List<Integer> toList(Integer[] arr) {
        List<Integer> list = new List<>();
        Node<Integer> head = new Node();
        list.head(head);
        Node cur = head;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                head.setData(arr[i]);
                continue;
            }
            Node node = new Node();
            node.setData(arr[i]);
            cur.next(node);
            cur = cur.next();
        }
        list.setSize(arr.length);
        return list;
    }
}
