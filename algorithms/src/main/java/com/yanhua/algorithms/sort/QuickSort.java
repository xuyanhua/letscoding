package com.yanhua.algorithms.sort;

import java.util.List;

/**
 * @author xuyanhua
 * @description:
 * @date 2019/4/23 下午2:05
 */
public class QuickSort {

    /**
     * 交换顺序表L中子表的记录，使枢轴记录到位，并返回其所在位置
     * 此时它之前（后）的记录均不大（小）于它
     *
     * @param list
     * @param low
     * @param high
     * @return
     */
    static int partition(List<Integer> list, int low, int high) {
        //用子表的第一个记录作枢轴记录
        int pivotkey = list.get(low);
        while (low < high) {
            while (low < high && list.get(high) >= pivotkey) {
                high--;
            }
            //将此枢轴记录小的记录交换到低端
            SortUtil.swap(list, low, high);
            while (low < high && list.get(low) <= pivotkey) {
                low++;
            }
            SortUtil.swap(list, low, high);
        }
        return low;
    }

    /**
     * 对顺序表list作快速排序
     *
     * @param list
     */
    static void quickSort(List<Integer> list) {
        qSort(list, 0, list.size() - 1);
    }

    /**
     * 对顺序表list中的子序列list[row]...list[hign]作快速排序
     *
     * @param list
     * @param low
     * @param high
     */
    static void qSort(List<Integer> list, int low, int high) {
        int pivot;
        if (low < high) {
            //将list[row]...list[high]一分为二，算>出枢轴值pivot
            pivot = partition(list, low, high);
            //对低子表递归排序
            qSort(list, low, pivot - 1);
            //对高子表递归排序
            qSort(list, pivot + 1, high);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = SortUtil.random(10);
        System.out.println(list);
        quickSort(list);
        System.out.println(list);
    }
}
