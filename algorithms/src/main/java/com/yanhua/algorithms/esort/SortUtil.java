package com.yanhua.algorithms.esort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xuyanhua
 * @description:
 * @date 2019/4/23 下午2:07
 */
public class SortUtil {
    public static void swap(List<Integer> list, int i, int j) {
        Integer a = list.get(i);
        Integer b = list.get(j);
        list.set(i, b);
        list.set(j, a);
    }

    public static List<Integer> random(int size) {
        Random r = new Random(System.currentTimeMillis());
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(r.nextInt(100));
        }
        return list;

    }
}
