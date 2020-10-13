package com.yanhua.algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class S7_QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 9, 7, 2, 3, 1};
        int[] newArr = quickSort(arr);
        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 快速排序
     * 时间复杂度为O(nlogn)不稳定排序
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    public static int[] quickSort(int[] arr, int low, int high) {
        int pivot;
        if (low < high) {
            pivot = partition(arr, low, high);    /*将L->r[low..gigh]一分为二，算>出枢轴值pivot*/
            quickSort(arr, low, pivot - 1);       /*对低子表递归排序*/
            quickSort(arr, pivot + 1, high);      /*对高子表递归排序*/
        }
        return arr;
    }

    /**
     * 找到分区点
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int partition(int[] arr, int low, int high) {
        int pivotkey = arr[low];             /*用子表的第一个记录作枢轴记录*/
        while (low < high) {
            while (low < high && arr[high] >= pivotkey) {
                high--;
            }
            ArrayUtil.swap(arr, low, high);           /*将此枢轴记录小的记录交换到低端*/
            while (low < high && arr[low] <= pivotkey) {
                low++;
            }
            ArrayUtil.swap(arr, low, high);
        }
        return low;
    }
}
