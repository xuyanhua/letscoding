package com.yanhua.datastructure.array;

/**
 * @author xuyanhua
 * @description:
 * @date 2019/4/9 下午5:29
 */
public class Combination {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};

        for (int i = 1; i < arr.length; i++) {
            System.out.print(arr[0] + "," + arr[i]);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                if (j != i && j != 0) {
                    sb.append(arr[j]).append(",");
                }
            }
            System.out.println("---" + sb.deleteCharAt(sb.length() - 1).toString());
            System.out.println();
        }

    }
}
