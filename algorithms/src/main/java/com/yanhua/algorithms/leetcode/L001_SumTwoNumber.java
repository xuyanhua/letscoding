package com.yanhua.algorithms.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>《力扣题库》1.两数之和</h1>
 * <p>给定一个整数数组nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回
 * 他们的数组下标。</p>
 * <p>你可以假设每种输入只会对应一个答案。但是数组中同一个元素不能使用两遍。</p>
 * <b>示例:</b>
 * <pre>
 * 给定 nums = [2,7,11,15],target=9
 * 因为nums[0] + nums[1] = 9
 * 所以返回[0,1]
 * </pre>
 */
public class L001_SumTwoNumber {
    public static void main(String[] args) {
        //正确示例
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 22;
        int[] twoIndex = twoNumSum(nums, target);
        System.out.println(Arrays.toString(twoIndex));

        //错误示例
        nums = null;
        target = 0;
        twoIndex = twoNumSum(nums, target);
        System.out.println(twoIndex);
    }

    /**
     * 两数之和算法
     *
     * @param nums   数组
     * @param target 目标值
     * @return
     */
    private static int[] twoNumSum(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return null;
        //方法1，两层循环，时间复杂度为O(n^2)
//        for (int i = 0; i < nums.length; i++) {
//            int num1 = nums[i];
//            for (int k = i + 1; k < nums.length; k++) {
//                int num2 = nums[k];
//                if (num1 + num2 == target) {
//                    return new int[]{i, k};
//                }
//            }
//        }
        //方法2，辅助空间法，时间复杂度为O(n)，空间复杂度为O(n)
//        Map<Integer, Integer> tempMap = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            tempMap.put(nums[i], i);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            int num1 = nums[i];
//            int num2 = target - num1;
//            if(tempMap.containsKey(num2)){
//                int index2 = tempMap.get(num2);
//                return new int[]{i,index2};
//            }
//        }
        //方法3，只遍历一次数组，同样借助辅助空间，时间复杂度O(n)，空间复杂度O(n)
        Map<Integer, Integer> tempMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            if (tempMap.containsKey(target - num1)) {
                return new int[]{i, tempMap.get(target - num1)};
            }
            tempMap.put(num1, i);
        }

        throw new IllegalArgumentException("no two num sum is " + target);
    }
}
