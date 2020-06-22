package com.yanhua.algorithms.search;

import java.util.Arrays;

/**
 * 二分查找 时间复杂度O(logn)
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 21, 23, 34, 51, 60, 67, 88, 89};
        int key = 21;
        int index = search(arr, key);
        System.out.printf("find %d from %s ,index=%d\n", key, Arrays.toString(arr), index);

    }

    public static int search(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        do {
            int middle = (high + low) / 2;
            if (arr[middle] == key) {
                return middle;
            } else if (arr[middle] > key) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        } while (low <= high);
        return -1;
    }
}
