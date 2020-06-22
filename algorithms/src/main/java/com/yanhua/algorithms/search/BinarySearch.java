package com.yanhua.algorithms.search;

import java.util.Arrays;

/**
 * 二分查找 时间复杂度O(logn)
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18};
        int key = 20;
        int index = search(arr, key);
        System.out.printf("find %d from %s ,index=%d\n", key, Arrays.toString(arr), index);
        int index2 = search2(arr, key);
        System.out.printf("find %d from %s ,index=%d\n", key, Arrays.toString(arr), index2);

    }


    /**
     * 折半查找，时间复杂度O(logn)
     *
     * @param arr
     * @param key
     * @return
     */
    public static int search(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        int count = 0;
        do {
            int middle = (high + low) / 2;
            count++;
            if (arr[middle] == key) {
                System.out.println("比较次数：" + count);
                return middle;
            } else if (arr[middle] > key) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        } while (low <= high);
        System.out.println("比较次数：" + count);
        return -1;
    }


    /**
     * key-arr[low]
     * 折半查找的改进，将1/2改成   ----------------- * (high-low)
     * arr[high]-arr[low]
     * 这种方式，越是平均分布，速度越快，如果完全平均，仅需一次查找
     *
     * @param arr
     * @param key
     * @return
     */
    public static int search2(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        int count = 0;
        do {
            int middle = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]);
            if (middle > high) {
                if (arr[high] == key) {
                    return high;
                } else {
                    return -1;
                }
            }
            count++;
            if (arr[middle] == key) {
                System.out.println("比较次数：" + count);
                return middle;
            } else if (arr[middle] > key) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        } while (low <= high);
        System.out.println("比较次数：" + count);
        return -1;
    }


}
