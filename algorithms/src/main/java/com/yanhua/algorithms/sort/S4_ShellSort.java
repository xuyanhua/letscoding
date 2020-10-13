package com.yanhua.algorithms.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class S4_ShellSort {
    public static void main(String[] args) {
        int[] arr = {9, 1, 5, 8, 3, 7, 4, 6, 2};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序是对直接插入排序的优化。
     * 直接插入排序在记录本身基本有序和记录数少时，优势明显
     * 希尔排序通过将序列分割实现创造基本有序的条件，同时分割也使得子序列更少
     * 步骤：
     * <li>1.找到增量</li>
     * <li>2.增量的计算方式incr/3+1</li>
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        if (arr.length <= 0) {
            return;
        }
        //1.初始增量为序列的长度
        int incr = arr.length;
        do {
            incr = incr / 3 + 1;
            System.out.println("incr=" + incr);
            for (int i = incr; i < arr.length; i++) {
                System.out.print(arr[i - incr] + "," + arr[i]+"->");
                if (arr[i] < arr[i - incr]) {
                    //将arr[i]插入有序增量子表,
                    int tmp = arr[i];
                    int j;
                    //跳跃式移动
                    for (j = i - incr; j >= 0 && tmp < arr[j]; j -= incr) {
                        arr[j + incr] = arr[j];
                    }
                    arr[j + incr] = tmp;
                }
                System.out.println(Arrays.toString(arr));
            }
        } while (incr > 1);

    }
}
