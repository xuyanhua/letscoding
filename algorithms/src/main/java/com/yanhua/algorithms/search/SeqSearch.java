package com.yanhua.algorithms.search;

/**
 * 顺序查找 O(n)
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 21, 23, 34, 51, 60, 67, 88, 89};

    }

    /**
     * 从头到尾查找数据
     * 时间复杂度是O(n)
     * 缺点是每次都需要比较i是否越界
     *
     * @param arr
     * @param key
     * @return
     */
    public static int search(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

}
