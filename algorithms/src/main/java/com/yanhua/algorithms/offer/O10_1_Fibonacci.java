package com.yanhua.algorithms.offer;

/**
 * 斐波那契数列
 * 写一个函数，输入n，求契波那契数列的第n项
 */
public class O10_1_Fibonacci {
    public static void main(String[] args) {
        int n = 7;
        int ways = fibonacci(n);
        System.out.printf("递归法第%d项是%d\n", n, ways);
        int ways2 = fibonacci2(n);
        System.out.printf("循环法第%d项是%d\n", n, ways2);

    }

    /**
     * 解法1，递归法
     * <li>优点：简单</li>
     * <li>缺点：有大量重复的计算,性能较差;受栈深度影响;</li>
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 2) + fibonacci(n - 1);
    }

    /**
     * 循环法
     * 思路，从前往后算
     * 时间复杂度O(n)
     *
     * @param n
     * @return
     */
    public static int fibonacci2(int n) {
        int[] result = {0, 1};
        if (n <= 1) {
            return result[n];
        }
        for (int i = 2; i <= n; i++) {
            int x = result[1] + result[0];
            result[0] = result[1];
            result[1] = x;
        }
        return result[1];
    }

    /**
     * 解法3：将斐波那契数列转换为矩阵
     * 时间复杂度为O(logn)
     */
}
