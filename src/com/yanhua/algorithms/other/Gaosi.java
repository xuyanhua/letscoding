package com.yanhua.algorithms.other;

/**
 * 高斯算法
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/4 上午7:41
 */
public class Gaosi {
    public static void main(String[] args) {
        int n = 100;
        int sum = sum(n);
        System.out.printf("1+2+...+%d = %d", n, sum);
    }

    public static int sum(int n) {
        if (n <= 0) {
            return 0;
        }
        return (1 + n) * n / 2;
    }
}
