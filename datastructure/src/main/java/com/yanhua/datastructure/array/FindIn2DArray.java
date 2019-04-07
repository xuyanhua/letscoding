package com.yanhua.datastructure.array;

/**
 * 在二维数组中查找指定元素是否存在
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/7 下午8:46
 */
public class FindIn2DArray {
    /**
     * 解法，从右上角开始
     * <ul>
     * <li>a、如果选定的值与被查的值num相等，则返回true，结束查询</li>
     * <li>b、如果选定的值比num大，则肯定在下面，x+1往下走</li>
     * <li>c、如果选定的值比num小，则肯定在左面，y-1往左边</li>
     * <li>d、当遍历完后没有找到返回false</li>
     * </ul>
     *
     * @param arr 被查的二维数组，二维数组横向从左向右递增，纵向从上往下递增
     * @param num 被查的数字
     * @return
     */
    public static boolean exists(int[][] arr, int num) {
        if (arr != null && arr.length == 0 && arr[0].length == 0) {
            return false;
        }
        int width = arr[0].length;
        int height = arr.length;
        for (int row = 0, column = width - 1; row <= height - 1 && column >= 0; ) {
            int tmp = arr[row][column];
            System.out.println("tmp=" + tmp);
            if (tmp == num) {
                return true;
            }
            if (tmp > num) {
                column--;//左
            } else {
                row++;//下
            }
        }
        return false;
    }


    public static void main(String[] args) {
        /**
         * 1 ,2 ,8 ,9
         * 2 ,4 ,9 ,12
         * 4 ,7 ,10 ,13
         * 6 ,8 ,11 ,15
         * 7 ,9 ,12 ,17
         */
        int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15},{7 ,9 ,12 ,17}};
        int num = 14;
        boolean exists = exists(arr, num);
        System.out.println("exists = " + exists);
    }
}
