package com.yanhua.algorithms.sort;

public class ArrayUtil {
    public static int[] swap(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        if (a < 0 || a >= arr.length) {
            return arr;
        }
        if (b < 0 || b >= arr.length) {
            return arr;
        }
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        return arr;
    }
}
