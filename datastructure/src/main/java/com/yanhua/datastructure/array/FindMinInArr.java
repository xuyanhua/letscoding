package com.yanhua.datastructure.array;

/**
 * 数组先升后降，取最小的整数，时间复杂度要求O(logn)
 */
public class FindMinInArr {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 6, 5, 3, 1, 2, 3, 4, 5};
        int minIndex = findMinInArr(arr);
        System.out.println("arr[" + minIndex + "]=" + arr[minIndex]);
    }

    public static int findMinInArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int middleIndex = arr.length / 2;
        int minValue = arr[middleIndex];
        while (middleIndex > 1 && middleIndex < arr.length - 1) {
            if (arr[middleIndex - 1] < minValue) {
                middleIndex = middleIndex - 1;
            } else if (arr[middleIndex + 1] < minValue) {
                middleIndex = middleIndex + 1;
            } else {
                return middleIndex;
            }
            minValue = arr[middleIndex];
        }
        return -1;
    }
}
