package com.yanhua.algorithms.sort;

import java.util.Arrays;

/**
 * 直接插入排序
 * 基本操作是将一个记录插入到已经排好序的有序有中，从而得到一个新的，记录数增1的有序表
 */
public class S3_InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 6, 7, 2, 3, 1};
        int[] newArr = sort(arr);
        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 直接插入排序
     * 从后往前找到最后一个比当前值大的数，依次往的移动
     * 时间复杂度：平均比较和移动次数约为n^2 / 4 次。因此得出时间复杂度为O(n^2)。
     * 直接插入比选择和冒泡排序性能要好一些。
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        System.out.println(Arrays.toString(arr));
        //从第2个元素开始
        for (int i = 1; i < arr.length; i++) {
            //作为被插入的元素
            int inserted = arr[i];
            int j = i;
            //往后移动比它大的元素
            while (j >= 1 && inserted < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            //插入
            arr[j] = inserted;
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }
}
