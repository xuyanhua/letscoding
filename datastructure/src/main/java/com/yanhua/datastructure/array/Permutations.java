package com.yanhua.datastructure.array;

import java.util.Arrays;

/**
 * 给定一组数字，返回所有的排列组合
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/9 下午11:34
 */
public class Permutations {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        permutations(arr);
        System.out.printf("\n数组:%s的全排序\n", Arrays.toString(arr));
        permutations2(arr, 0, arr.length);
//        System.out.println();
//        int[] arr2 = {1, 1, 2};
//        System.out.printf("数组:%s的全排序\n", Arrays.toString(arr2));
//        permutationsDup(arr2, 0, arr.length);
    }

    /**
     * Given a collection of numbers, return all possible permutations.
     * <p>
     * For example,
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    public static void permutations(int[] arr) {
        System.out.printf("数组:%s的全排序\n", Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < arr.length; k++) {
                    if (k == j || k == i) {
                        continue;
                    }
                    System.out.printf("[%d, %d, %d],", arr[i], arr[j], arr[k]);
                }
            }
        }
    }

    /**
     * Given a collection of numbers, return all possible permutations.
     * <p>
     * For example,
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     * https://images0.cnblogs.com/blog/587576/201411/251159363407394.png
     * 解法：先固定第一个位置，将剩余的位置进行全排序
     * 之后将第二个位起与第一个位置的数字交换一次，形成一种排序
     */
    public static void permutations2(int[] arr, int start, int end) {
        if (start == end) {
            System.out.printf(Arrays.toString(arr) + ",");
            return;
        }
        for (int i = start; i < arr.length; i++) {
            //每次先确定第一个位置的元素,即i
            //然后交换位置
            swap(arr, start, i);
            permutations2(arr, start + 1, end);
            swap(arr, start, i);
        }
        //递归步骤
        //1,2,3
        //  2,3
        //    3 --> 1,2,3
        //  3,2
        //    2 --> 1,3,2
        //2,1,3
        //  1,3
        //    3 --> 2,1,3
        //  3,1
        //    1 --> 2,3,1
        //3,2,1
        //  2,1
        //    1 --> 3,2,1
        //  1,2
        //    2 --> 3,1,2
    }

    private static void swap(int[] arr, int a, int b) {
        if (a == b) {
            return;
        }
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    public static void permutationsDup(int[] arr, int start, int end) {
        if (start == end) {
            System.out.printf(Arrays.toString(arr) + ",");
            return;
        }
        for (int i = start; i < arr.length; i++) {
            //每次先确定第一个位置的元素,即i
            //然后交换位置
            //交换时，判断一下两个位置的元素是否相等
            boolean hasSame = false;
            for (int j = 0; j < start; j++) {
                if (arr[j] == arr[start]) {
                    hasSame = true;
                    break;
                }
            }
            if (!hasSame) {
                swap(arr, start, i);
                permutationsDup(arr, start + 1, end);
                swap(arr, start, i);
            }

        }
    }

}
