package com.yanhua.datastructure.list;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
