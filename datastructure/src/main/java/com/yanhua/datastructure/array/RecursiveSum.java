package com.yanhua.datastructure.array;

import java.util.Arrays;

/**
 * 数组递归求和
 */
public class RecursiveSum {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int sum = sum(arr);
        System.out.printf("sum = %d\n", sum);

    }

    public static int sum(int arr[]) {
        if (arr == null || arr.length == 0) {
            return 0;
        } else if (arr.length == 0) {
            return arr[0];
        } else {
            return arr[arr.length - 1] + sum(Arrays.copyOf(arr, arr.length - 1));
        }
    }
}
