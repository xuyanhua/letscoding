package com.yanhua.datastructure.array;

import java.util.Arrays;

/**
 * 找出重复的元素
 * <p>在一个长度为n的数组里的所有数字都在0~n-1的范围内，数组中某些数字是重复的，但不知道有哪几个数字重复
 * 了，也不知道重复了几次。请找出数组中任意一个重复的数字。</p>
 * <ul>
 * <li>解法1、将输入的数组排序，从排序的数组中找出重复数字，只要顺序遍历有相邻相同的即可。排序一个数组需要
 * O(nlogn)的时间复杂度</li>
 * <li>解法2、利用hash表解决，从头到尾遍历，遍历到某个数字时，如果hash表中没有则放入，如果有了O(1)的时间
 * 复杂度可以判断有没有，即可找出重复数字</li>
 * <li>解法3、分析：因为数字都在0~n-1之间，所以如果没有重复的数字的话，那么将数字位置调整以后，可以满足
 * arr[i]=i，即值为i的元素正好在下标为i的位置，否则如果有重复数字，则有些位置就会出现多个数字，同时有些位置
 * 没有数字</li>
 * </ul>
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/7 下午5:29
 */
public class FindDuplicateElement {

    /**
     * 使用解法3，从头到尾遍历数组
     * <ul>
     * <li>a、如果arr[i]=i，则遍历下一个元素</li>
     * <li>b、如果arr[i]!=i（假设值为m）,则看看arr[m]处的值是否为m</li>
     * <li>c、如果arr[m]处的值为m，则找到重复的数字是m</li>
     * <li>d、否则交换arr[i]和arr[m]</li>
     * <li>e、重复上面的过程，直到找到重复元素为止.</li>
     * </ul>
     * 时间复杂度为O(n)，因为每个元素最多交换两次即可找到属于它的位置。空间复杂度为O(1)
     *
     * @param arr 输入数组
     * @return 返回重复的数字
     */
    public static int duplicate(int arr[]) {
        //1.判空
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //2.输入值合法性检查
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 || arr[i] > (arr.length - 1)) {
                return -1;
            }
        }
        //3.查找逻辑
        for (int i = 0; i < arr.length; ) {
            if (arr[i] == i) {
                i++;
                continue;
            }
            int m = arr[i];
            if (arr[m] == m) {
                return arr[i];
            }
            arr[i] = arr[m];
            arr[m] = m;
            System.out.println(Arrays.toString(arr));
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 3, 5, 6};
        System.out.println(Arrays.toString(arr));
        int dup = duplicate(arr);
        System.out.println("dup num = " + dup);
    }
}
