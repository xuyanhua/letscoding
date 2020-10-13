package com.yanhua.algorithms.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class S2_SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 9, 7, 2, 3, 1};
        int[] newArr = selectSort(arr);
        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 选择排序
     * 每次循环，假设第1个是最小值，然后从后面的数值中选择一个比当前小的数值进行比较，找到最小的
     * 数以后进行一次交换
     * 时间复杂度也是O(n^2)[实际是n^2/2]，虽然比较的次数没有减少，但交换的次数少了。
     * 因此还是优于冒泡排序
     *
     * @return
     */
    public static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
        return arr;
    }
}
