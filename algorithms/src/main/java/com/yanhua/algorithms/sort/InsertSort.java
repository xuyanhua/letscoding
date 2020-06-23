package com.yanhua.algorithms.sort;

import java.util.Arrays;

/**
 * 直接插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 9, 7, 2, 3, 1};
        int[] newArr = sort(arr);
        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 直接插入排序
     * 从后往前找到最后一个比当前值大的数，依次往的移动
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        for (int i = 2; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j >= 1 && arr[j - 1] > tmp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
        return arr;
    }
}
