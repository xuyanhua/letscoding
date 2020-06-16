package com.yanhua.datastructure.array;

public class MaxArr {
    public static void main(String[] args) {
        //有正有负
        int[] arr = new int[]{-2, -1, 1, -1, 1, 2};
        int sum = maxSum4Arr(arr);
        System.out.println(sum);
        //全正
        arr = new int[]{1, 2, 3};
        sum = maxSum4Arr(arr);
        System.out.println(sum);
        //全负
        arr = new int[]{-1, -2, -3};
        sum = maxSum4Arr(arr);
        System.out.println(sum);
        arr = null;
        sum = maxSum4Arr(arr);
        System.out.println(sum);
    }

    /**
     * 最大子数组之和
     *
     * @param arr
     * @return
     */
    private static int maxSum4Arr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int maxSum = 0;//最大子数组之和
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int tmp = sum + arr[i];
            //如果新加元素后和变大则计算在内
            if (tmp > sum) {
                sum = tmp;
                continue;
            }
            if (sum > maxSum) {
                maxSum = sum;
                sum = 0;
            }
        }
        if (sum > maxSum) {
            maxSum = sum;
        }
        return maxSum;
    }
}