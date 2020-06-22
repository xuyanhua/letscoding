package com.yanhua.algorithms.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 9, 7, 2, 3, 1};
        int[] newArr = bubbleSort(arr);
        System.out.println(Arrays.toString(newArr));

    }

    /**
     * 冒泡排序，时间复杂度O(logn)
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int swap = 0;
            for (int j = len - 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                    swap++;
                }
            }
            System.out.println(Arrays.toString(arr));
            if (swap == 0) {
                break;
            }
        }

        return arr;
    }
}
