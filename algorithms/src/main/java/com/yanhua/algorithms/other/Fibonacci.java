package com.yanhua.algorithms.other;

import java.util.Arrays;

/**
 * 斐波那契数列
 */
public class Fibonacci {
    public static void main(String[] args) {
        //递归法
        for (int i = 1; i < 10; i++) {
            int v = fib(i);
            System.out.print(v + ",");
        }
        System.out.println();
        //循环法
        int[] fibArr = fib2(10);
        System.out.println(Arrays.toString(fibArr));
        //找到小于max的最大序列
        fibArr = fib2(10, 10);
        System.out.println(Arrays.toString(fibArr));
    }

    /**
     * 递归法有大量的重复计算，不推荐
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 循环法计算量小。另外注意n过大时可能会超过Int的最大值。
     *
     * @param n
     * @param max
     * @return
     */
    public static int[] fib2(int n, int max) {
        if (n <= 0) {
            return new int[]{0};
        }
        if (n == 1) {
            return new int[]{1};
        }
        if (n == 2) {
            return new int[]{1, 1};
        }
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        int subLen = 0;
        for (int i = 2; i < n; i++) {
            int tmp = arr[i - 1] + arr[i - 2];
            if (tmp > max) {
                break;
            }
            arr[i] = tmp;
            subLen = i + 1;
        }
        arr = Arrays.copyOf(arr, subLen);
        return arr;
    }


    public static int[] fib2(int n) {
        return fib2(n, Integer.MAX_VALUE);
    }

}
