package com.yanhua.datastructure.array;

import java.util.Arrays;

/**
 * 合并两个排好序的数组
 * <p>
 * 有两个排序的数组A1和A2，内存在A1的末尾有足够多的空余空间容纳A2.
 * 请实现一个函数，把A2中的所有数组插入到A1中，并且所有的数字是排序的。
 *
 * @author xuyanhua
 * @description:
 * @date 2020/10/12 17:14
 */
public class O05_2_MergeArray {

    /**
     * 合并两个有序的数组
     * 解法：从后往前遍历，谁大放到后一个位置。
     * 如果从前遍历，需要往后移动数字，比较耗时。
     * @param arr1
     * @param len1
     * @param arr2
     * @return
     */
    public static int[] merge(int[] arr1, int len1, int arr2[]) {
        //判空
        if (arr1 == null || arr2 == null) {
            return null;
        }
        int k = arr1.length - 1;
        for (int i = len1 - 1, j = arr2.length - 1; i > 0 || j > 0; ) {
            if (arr1[i] >= arr2[j]) {
                arr1[k--] = arr1[i--];
            } else {
                arr1[k--] = arr2[j--];
            }
        }

        return arr1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 8, 9, 0, 0, 0, 0};
        int[] arr2 = {2, 4, 9, 12};
        int[] arr = merge(arr1, 4, arr2);
        System.out.println("arr = " + Arrays.toString(arr));
    }


}
