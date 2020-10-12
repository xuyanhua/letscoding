package com.yanhua.algorithms.offer;

/**
 * <u>青蛙跳</u>
 * <p>一只青蛙可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个n级台阶总共有多少种跳法。</p>
 * <p>分析：
 * <li>只有1级台阶，只有1种跳法</li>
 * <li>只有2级台阶，只有2种跳法，1次跳2阶和分别两次1级的</li>
 * <li>当n大于2时，最后1次跳，可以跳两阶，即f(n-2)，也可以跳1级即f(n-1)；即f(n)=f(n-1)+f(n-2)</li>
 * </p>
 */
public class O10_2_FrogSkip {
    public static void main(String[] args) {
        int n = 7;
        int ways = ways(n);
        System.out.printf("%d级台阶共有%d种跳法\n", n, ways);
    }

    /**
     * 递归解法
     * <li>优点：简单</li>
     * <li>缺点：有大量重复的计算,性能较差;受栈深度影响;</li>
     *
     * @param n
     * @return
     */
    public static int ways(int n) {
        int[] result = {1, 2};
        if (n <= 2) {
            return result[n - 1];
        }
        for (int i = 1; i < n-1; i++) {
            int x = result[0] + result[1];
            result[0] = result[1];
            result[1] = x;
        }
        return result[1];
    }

    /**
     * 如果每次可以跳1级,2级…也可以跳n级，通过归纳法是2^(n-1)种方式
     *
     * @param n
     * @return
     */
    public static int waysn(int n) {
        return 2 ^ (n - 1);
    }
}
