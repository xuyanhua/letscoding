package com.yanhua.datastructure.array;

import java.util.Arrays;

/**
 * 不修改数组找出重复的元素
 * <p>在一个长度为n+1的数组里的所有数字范围都在1-n之间，所以数组中至少有一个重复的数字，请找出这个重复的
 * 数字，但不能修改输入的数组。</p>
 * <ul>
 * <li>解法1、时间优先。申请一个n+1长度的辅助数组，顺序遍历数组，并将arr[i]放到i的位置，如果arr[i]上已
 * 经有数字了，说明重复了。此时的时间复杂度为O(n)，空间复杂度为O(n)</li>
 * <li>解法2、空间优先。模仿二分查找的思路，取1~n数字中间的m，如果1~m中的数字个数多于m个，则1~m间肯定有
 * 至少有一个是重复的，否则在m+1~n之间有一个是重复的，依次类推，空间复杂度为O(1)，时间复杂度为O(nlgn)</li>
 * </ul>
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/7 下午5:29
 */
public class O03_2_FindDuplicateElementWithNoModify {

    /**
     * 解法1，从头到尾遍历数组
     * <ul>
     * <li>a、申请一个n大小的辅助数组</li>
     * <li>b、假设arr[i]=m，则将arr[i]放置辅助数组的m位置</li>
     * <li>c、如果m位置已经有数字了，则m为重复的数字</li>
     * </ul>
     * 时间复杂度为O(n)，因为每个元素最多交换两次即可找到属于它的位置。空间复杂度为O(1)
     *
     * @param arr 输入数组
     * @return 返回重复的数字
     */
    public static int duplicate1(int arr[]) {
        //1.判空
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //2.输入值合法性检查
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] < 1 || arr[i] > n) {
                return -1;
            }
        }
        int[] arrTmp = new int[n];
        //3.查找逻辑
        for (int i = 0; i < arr.length; i++) {
            int m = arr[i];
            if (arrTmp[m] == m) {
                return m;
            }
            arrTmp[m] = m;
            System.out.println(Arrays.toString(arrTmp));
        }
        return -1;
    }

    /**
     * 解法2：由于数组有n+1个元素组成，范围在1~n之间，因此
     * <ul>
     * <li>a、取1~n的中间数middle，如果start~middle之间的数字计数count大于middle-start+1，则start~middle
     * 之间肯定存在重复的数，否则middle+1~end之间存在重复的数</li>
     * <li>b、重复上面的步骤，直到start==end时，count如果大于1则说明start（或end）就是重复的数字</li>
     * <p>
     * </ul>
     *
     * @param arr
     * @return
     */
    public static int duplicate2(int[] arr) {
        //1.判空
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //2.输入值合法性检查
        int len = arr.length;
        int n = len - 1;
        for (int i = 0; i < len; i++) {
            if (arr[i] < 1 || arr[i] > n) {
                return -1;
            }
        }
        //3.查找逻辑
        int start = 1;
        int end = n;

        while (end >= start) {
            int middle = (start + end) / 2;
            int count = countRange(arr, start, middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > (middle - start + 1)) {
                end = middle;
            } else {
                //fixme 注：该算法这有问题，如果count数和数字相同，就跳过了，但有可能这里也
                //有重复的数字，例如2,3,3子数组中，在1～3范围内有三个数字，就检测不出来
                start = middle + 1;
            }
        }
        return -1;
    }

    private static int countRange(int[] arr, int start, int end) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= start && arr[i] <= end) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 2, 3, 5, 6};
        int[] arr = {2, 3, 5, 4, 2, 6};
//        int[] arr = {1,1,2};
//        int dup = duplicate1(arr);
//        System.out.println("dup num = " + dup);
        int dup2 = duplicate2(arr);
        System.out.println("dup num = " + dup2);
    }
}
