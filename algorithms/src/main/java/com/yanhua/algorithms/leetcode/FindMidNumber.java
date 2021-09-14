package com.yanhua.algorithms.leetcode;

import java.util.Arrays;

/**
 * 在两个有序数组中寻找
 *
 * @author xuyanhua02
 * @description
 * @createTime 2021-09-13 19:56
 */
public class FindMidNumber {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 4, 5, 9, 10};
        int[] arr2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
//        find1(arr1, arr2);
        find2(arr1, arr2);
    }

    /**
     * 方法0
     * 两数组直接合并，再排序，再查找
     */

    /**
     * 使用构造新数组的方式
     * 空间复杂度是O(m+n)，构造新数组的时间复杂度是O(m+n)
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static double find1(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return -1;
        }
        //将两个数组合并为一个，然后找到中间的数
        int[] arr = new int[arr1.length + arr2.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < arr.length; k++) {
            if (i < arr1.length && j < arr2.length) {
                int num1 = arr1[i];
                int num2 = arr2[j];
                if (num1 < num2) {
                    arr[k] = num1;
                    i++;
                } else {
                    arr[k] = num2;
                    j++;
                }
                continue;
            }
            if (i < arr1.length) {
                arr[k] = arr1[i++];
            }
            if (j < arr2.length) {
                arr[k] = arr2[j++];
            }
        }
        double mid = -1;
        if (arr.length % 2 == 1) {//奇数
            mid = arr[arr.length / 2];
        } else {//偶数
            mid = (arr[arr.length / 2 - 1] + arr[arr.length / 2]) / 2;
        }
        System.out.println(Arrays.toString(arr) + ":" + mid);
        return mid;
    }

    /**
     * 　不构造新数组，直接查找到中间位置.
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static double find2(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return -1;
        }
        //将两个数组合并为一个，然后找到中间的数
        int totalLen = arr1.length + arr2.length;
        int midLeftIndex = 0;
        int midRightIndex = 0;
        if (totalLen % 2 == 1) {
            midLeftIndex = midRightIndex = totalLen / 2;
        } else {
            midLeftIndex = totalLen / 2 - 1;
            midRightIndex = totalLen / 2;
        }

        int i = 0;
        int j = 0;
        int midLeft = 0, midRight = 0;
        for (int k = 0; k < totalLen; k++) {
            int min = 0;
            if (i < arr1.length && j < arr2.length) {
                int num1 = arr1[i];
                int num2 = arr2[j];
                if (num1 < num2) {
                    min = num1;
                    i++;
                } else {
                    min = num2;
                    j++;
                }
                if (k == midLeftIndex) {
                    midLeft = min;
                }
                if (k == midRightIndex) {
                    midRight = min;
                    break;
                }
                continue;
            }
            if (i < arr1.length) {
                min = arr1[i++];
            } else if (j < arr2.length) {
                min = arr2[j++];
            }
            if (k == midLeftIndex) {
                midLeft = min;
            }
            if (k == midRightIndex) {
                midRight = min;
                break;
            }
        }
        double mid = (midLeft + midRight) / 2;
        System.out.println(":" + mid);
        return mid;
    }

    /**
     * 二分查找法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double find3(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    private static int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
