package com.yanhua.datastructure.array;

import java.util.Arrays;

/**
 * 给出一个数组，两两组合不能重复
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/9 下午5:29
 */
public class Combination {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        twoComb(arr);
    }

    /**
     * 给出一个数组，两两组合不能重复
     */
    public static void twoComb(int[] arr) {
        System.out.printf("数组:%s两两组合\n", Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (j == i) {
                    continue;
                }
                System.out.printf("[%d,%d],", arr[i], arr[j]);
            }
        }
    }


}
