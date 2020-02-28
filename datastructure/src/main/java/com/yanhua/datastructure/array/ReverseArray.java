package com.yanhua.datastructure.array;

import java.util.Arrays;

/**
 * 数组反转
 */
public class ReverseArray {

    public static void reverseArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;

        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        reverseArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}