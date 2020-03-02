package com.yanhua.datastructure.list.lists;

/**
 * 链表结点
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/5 上午7:16
 */
public class Node<T> {
    //数据结点
    T data;
    //后继结点
    Node next;

    public T data() {
        return data;
    }

    public void data(T data) {
        this.data = data;
    }

    public Node next() {
        return next;
    }

    public void next(Node next) {
        this.next = next;
    }

}
