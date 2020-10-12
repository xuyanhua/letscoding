package com.yanhua.algorithms.offer;

/**
 * <u>拼图</u>
 * <pre>
 *  ---   -------------------
 *  | |   | | | | | | | | | |
 *  ---   -------------------
 *  | |   | | | | | | | | | |
 *  ---   -------------------
 * </pre>
 * <p>问题：我们可以用2*1的小矩形橫着放或者竖着放去覆盖更大的矩形。请问用8个2*1的小矩形无重叠地
 * 覆盖一个2*8的大矩形有多少种方法？</p>
 * <p>分析：最左边有两种选择，竖着放或横着放。
 * <li>当竖着放时，右边还剩2*7的区域，标记为f(7)</li>
 * <li>当横着放时，左下角必须横着放一个2*1的小矩形，右边还剩2*6的区域，标记为f(6)</li>
 * <li>因此可以标记为f(8)=f(7)+f(6)</li>
 * <li>当n=1时，只有一种方法就是竖着，标记为f(1)=1</li>
 * <li>当n=2时，只有二种方法就是2个竖着或者2个横着，标记为f(2)=1</li>
 * </p>
 */
public class O10_2_Puzzle {
    public static void main(String[] args) {
        int n = 7;
        int ways = ways(n);
        System.out.printf("%d个小矩形共有%d种覆盖方法\n", n, ways);
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

}
