package com.yanhua.datastructure.tree.adt;

public class Tree<T> {
    private Tree leftChild;//左子树
    private Tree rightChild;//右子树
    private T data;//数据域
    private char[] ts;//
    private int i = 0;

    /**
     * 前序遍历
     */
    public void preOrderList(Tree tree) {
        if (tree == null) {
            return;
        }
        System.out.printf("%s,", tree.data);//先访问根节点
        preOrderList(tree.leftChild);//再访问左子树
        preOrderList(tree.rightChild);//再访问右子树
    }

    /**
     * 中序遍历
     */
    public void inOrderList(Tree tree) {
        if (tree == null) {
            return;
        }
        inOrderList(tree.leftChild);//先访问左子树
        System.out.printf("%s,", tree.data);//再访问根节点
        inOrderList(tree.rightChild);//再访问右子树
    }


    /**
     * 后序遍历
     */
    public void postOrderList(Tree tree) {
        if (tree == null) {
            return;
        }
        postOrderList(tree.leftChild);//先访问左子树
        postOrderList(tree.rightChild);//再访问右子树
        System.out.printf("%s,", tree.data);//再访问根节点
    }


    /**
     * 层序遍历
     */
    public void levelOrderList(Tree tree) {
        if (tree == null) {
            return;
        }
        System.out.printf("%s,", tree.data);//先访问根节点
        System.out.printf("%s,", tree.leftChild.data);
        System.out.printf("%s,", tree.rightChild.data);
        levelOrderList(tree.leftChild);
        levelOrderList(tree.rightChild);
    }

    public void setTs(char[] chars) {
        this.ts = chars;
    }

    public static void main(String[] args) {
        char[] chars = {'A', 'B', '#', 'D', '#', '#', 'C', '#', '#'};
        Tree tree = createTree(chars);
        tree.preOrderList(tree);
        System.out.println();
        tree.inOrderList(tree);
        System.out.println();
        tree.postOrderList(tree);
        System.out.println();
//        tree.levelOrderList(tree);
    }

    public static Tree createTree(char[] chars) {
        Tree tree = new Tree();
        tree.setTs(chars);
        tree.createTree0(tree);
        return tree;
    }

    public Tree createTree0(Tree tree) {
        char ch = getNext();
        if (ch == '#') {
            return null;
        } else {
            tree.data = ch;
            tree.leftChild = createTree0(new Tree());
            tree.rightChild = createTree0(new Tree());
        }
        return tree;

    }

    private char getNext() {
        if (i >= ts.length) {
            return ' ';
        }
        return ts[i++];
    }

}
