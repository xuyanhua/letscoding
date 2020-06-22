package com.yanhua.algorithms.esort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MySort {
    public static void main(String[] args) {
//        testQd1();
//        testQd2();
//        testYt2();
        testWf1();
//        testRz1();
    }

    /**
     * 青岛袋鼠01
     * 系统排序
     * 2020-01-01	20200101青岛市袋鼠01	3316
     * 2020-01-01	20200101青岛市袋鼠01	1645
     * 2020-01-01	20200101青岛市袋鼠01	1791
     * 2020-01-01	20200101青岛市袋鼠01	1873
     * 2020-01-01	20200101青岛市袋鼠01	1657
     * 2020-01-01	20200101青岛市袋鼠01	1829
     * 2020-01-01	20200101青岛市袋鼠01	1690
     * 2020-01-01	20200101青岛市袋鼠01	4788
     * 2020-01-01	20200101青岛市袋鼠01	1662
     * 2020-01-01	20200101青岛市袋鼠01	1688
     * 2020-01-01	20200101青岛市袋鼠01	4804
     * 2020-01-01	20200101青岛市袋鼠01	2273
     * 2020-01-01	20200101青岛市袋鼠01	1646
     * 2020-01-01	20200101青岛市袋鼠01	1670
     * 2020-01-01	20200101青岛市袋鼠01	1656
     * <p>
     * 真实顺序
     * 2020-01-01 	青岛市袋鼠01	 10:47:10	3316
     * 2020-01-01 	青岛市袋鼠01	 11:17:33	1656
     * 2020-01-01 	青岛市袋鼠01	 11:40:37	1670
     * 2020-01-01 	青岛市袋鼠01	 12:01:03	1645
     * 2020-01-01 	青岛市袋鼠01	 12:14:37	1791
     * 2020-01-01 	青岛市袋鼠01	 12:21:23	1873
     * 2020-01-01 	青岛市袋鼠01	 12:26:59	1657
     * 2020-01-01 	青岛市袋鼠01	 12:38:05	1829
     * 2020-01-01 	青岛市袋鼠01	 12:47:30	1690
     * 2020-01-01 	青岛市袋鼠01	 12:58:43	4788
     * 2020-01-01 	青岛市袋鼠01	 13:10:00	1662
     * 2020-01-01 	青岛市袋鼠01	 13:17:08	1688
     * 2020-01-01 	青岛市袋鼠01	 13:27:41	4804
     * 2020-01-01 	青岛市袋鼠01	 13:38:12	2273
     * 2020-01-01 	青岛市袋鼠01	 13:43:10	1646
     * <p>
     */
    //样本1-青岛袋鼠01 100%
    public static void testQd1() {
        System.out.println("青岛袋鼠01");
        //1.抽取数据，日期->有序的店铺
        Map<String, List<Integer>> history = extract("/qdds01");
        //2.学习经验，shopId1在shopId2前面的次数
        Map<String, Integer> experience = learning(history);
        //3.待排序的样本，系统通过距离远近的排序（2020-01-01）
        Integer[] shopIds = new Integer[]{3316, 1645, 1791, 1873, 1657, 1829, 1690, 4788, 1662, 1688, 4804, 2273, 1646, 1670, 1656};
        System.out.println("排序前（根据距离远近排序）：" + Arrays.toString(shopIds));
        //根据经验值进行排序
        sort(shopIds, experience);
        System.out.println("排序后（根据经验远近排序）：" + Arrays.toString(shopIds));
        //司机真实走的路线的样本 （2020-01-01）
        Integer[] real = new Integer[]{3316, 1656, 1670, 1645, 1791, 1873, 1657, 1829, 1690, 4788, 1662, 1688, 4804, 2273, 1646};
        System.out.println("真排序（司机本次决定排序）：" + Arrays.toString(real));
        System.out.println("\n");
    }


    /**
     * 系统排序
     * <p>
     * 2020-01-01	20200101青岛市袋鼠02	2938
     * 2020-01-01	20200101青岛市袋鼠02	1668
     * 2020-01-01	20200101青岛市袋鼠02	3487
     * 2020-01-01	20200101青岛市袋鼠02	1620
     * 2020-01-01	20200101青岛市袋鼠02	5214
     * 2020-01-01	20200101青岛市袋鼠02	5467
     * 2020-01-01	20200101青岛市袋鼠02	1634
     * 2020-01-01	20200101青岛市袋鼠02	1807
     * 2020-01-01	20200101青岛市袋鼠02	1642
     * 2020-01-01	20200101青岛市袋鼠02	1644
     * 2020-01-01	20200101青岛市袋鼠02	1803
     * 2020-01-01	20200101青岛市袋鼠02	1631
     * <p>
     * 真实顺序
     * 2020-01-01 	青岛市袋鼠02	 09:51:19	1642
     * 2020-01-01 	青岛市袋鼠02	 10:04:34	1644
     * 2020-01-01 	青岛市袋鼠02	 10:18:40	1803
     * 2020-01-01 	青岛市袋鼠02	 10:47:30	1631
     * 2020-01-01 	青岛市袋鼠02	 11:18:38	1620
     * 2020-01-01 	青岛市袋鼠02	 11:39:07	1668
     * 2020-01-01 	青岛市袋鼠02	 11:46:24	3487
     * 2020-01-01 	青岛市袋鼠02	 12:03:23	2938
     * 2020-01-01 	青岛市袋鼠02	 12:25:19	1634
     * 2020-01-01 	青岛市袋鼠02	 12:33:47	5467
     * 2020-01-01 	青岛市袋鼠02	 12:41:24	5214
     * 2020-01-01 	青岛市袋鼠02	 12:52:42	1807
     */
    //样本2-青岛袋鼠02 100%
    public static void testQd2() {
        System.out.println("青岛市袋鼠02");
        //1.抽取数据，日期->有序的店铺
        Map<String, List<Integer>> history = extract("/qdds02");
        //2.学习经验，shopId1在shopId2前面的次数
        Map<String, Integer> experience = learning(history);
        //3.待排序的样本，系统通过距离远近的排序（2020-01-01）
        Integer[] shopIds = new Integer[]{2938, 1668, 3487, 1620, 5214, 5467, 1634, 1807, 1642, 1644, 1803, 1631};
        System.out.println("排序前（根据距离远近排序）：" + Arrays.toString(shopIds));
        //根据经验值进行排序
        sort(shopIds, experience);
        System.out.println("排序后（根据经验远近排序）：" + Arrays.toString(shopIds));
        //司机真实走的路线的样本 （2020-01-01）
        Integer[] real = new Integer[]{1642, 1644, 1803, 1631, 1620, 1668, 3487, 2938, 1634, 5467, 5214, 1807};
        System.out.println("真排序（司机本次决定排序）：" + Arrays.toString(real));
        System.out.println("\n");
    }

    /**
     * 烟台绿雀01
     * 系统排序
     * 2020-01-01	20200101烟台市烟台绿雀01	2008
     * 2020-01-01	20200101烟台市烟台绿雀01	2137
     * 2020-01-01	20200101烟台市烟台绿雀01	4926
     * 2020-01-01	20200101烟台市烟台绿雀01	4914
     * 2020-01-01	20200101烟台市烟台绿雀01	2020
     * 2020-01-01	20200101烟台市烟台绿雀01	2017
     * 2020-01-01	20200101烟台市烟台绿雀01	2546
     * 2020-01-01	20200101烟台市烟台绿雀01	5051
     * 2020-01-01	20200101烟台市烟台绿雀01	2147
     * 2020-01-01	20200101烟台市烟台绿雀01	5426
     * 2020-01-01	20200101烟台市烟台绿雀01	2031
     * 2020-01-01	20200101烟台市烟台绿雀01	2282
     * 2020-01-01	20200101烟台市烟台绿雀01	2206
     * <p>
     * 真实排序
     * 2020-01-01 	烟台市烟台绿雀01	 10:48:46	2137
     * 2020-01-01 	烟台市烟台绿雀01	 10:57:00	2008
     * 2020-01-01 	烟台市烟台绿雀01	 11:06:31	4926
     * 2020-01-01 	烟台市烟台绿雀01	 11:17:06	4914
     * 2020-01-01 	烟台市烟台绿雀01	 11:36:37	2282
     * 2020-01-01 	烟台市烟台绿雀01	 12:27:22	2206
     * 2020-01-01 	烟台市烟台绿雀01	 12:54:01	2031
     * 2020-01-01 	烟台市烟台绿雀01	 12:59:01	5426
     * 2020-01-01 	烟台市烟台绿雀01	 13:10:50	2546
     * 2020-01-01 	烟台市烟台绿雀01	 13:15:00	2017
     * 2020-01-01 	烟台市烟台绿雀01	 13:18:29	2020
     * 2020-01-01 	烟台市烟台绿雀01	 13:26:35	5051
     * 2020-01-01 	烟台市烟台绿雀01	 13:34:22	2147
     */
    //样3-烟台市绿雀01 刚改了开始位置 不准
    public static void testYt2() {
        System.out.println("烟台市烟台绿雀01");
        //1.抽取数据，日期->有序的店铺
        Map<String, List<Integer>> history = extract("/ytlq01");
        //2.学习经验，shopId1在shopId2前面的次数
        Map<String, Integer> experience = learning(history);
        //3.待排序的样本，系统通过距离远近的排序（2020-01-01）
        Integer[] shopIds = new Integer[]{2008, 2137, 4926, 4914, 2020, 2017, 2546, 5051, 2147, 5426, 2031, 2282, 2206};
        System.out.println("排序前（根据距离远近排序）：" + Arrays.toString(shopIds));
        //根据经验值进行排序
        sort(shopIds, experience);
        System.out.println("排序后（根据经验远近排序）：" + Arrays.toString(shopIds));
        //司机真实走的路线的样本 （2020-01-01）
        Integer[] real = new Integer[]{2137, 2008, 4926, 4914, 2282, 2206, 2031, 5426, 2546, 2017, 2020, 5051, 2147};
        System.out.println("真排序（司机本次决定排序）：" + Arrays.toString(real));
        System.out.println("\n");
    }


    /**
     * 系统排序
     * 2020-01-01	20200101潍坊市唯捷01	2648
     * 2020-01-01	20200101潍坊市唯捷01	2274
     * 2020-01-01	20200101潍坊市唯捷01	1742
     * 2020-01-01	20200101潍坊市唯捷01	2509
     * 2020-01-01	20200101潍坊市唯捷01	1712
     * 2020-01-01	20200101潍坊市唯捷01	1731
     * 2020-01-01	20200101潍坊市唯捷01	2660
     * 2020-01-01	20200101潍坊市唯捷01	1756
     * 2020-01-01	20200101潍坊市唯捷01	1754
     * 2020-01-01	20200101潍坊市唯捷01	1745
     * 2020-01-01	20200101潍坊市唯捷01	1744
     * 2020-01-01	20200101潍坊市唯捷01	5326
     * 2020-01-01	20200101潍坊市唯捷01	4642
     * 2020-01-01	20200101潍坊市唯捷01	1738
     * 2020-01-01	20200101潍坊市唯捷01	1864
     * 2020-01-01	20200101潍坊市唯捷01	2756
     * 2020-01-01	20200101潍坊市唯捷01	1998
     * 2020-01-01	20200101潍坊市唯捷01	2077
     * <p>
     * 真实排序
     * 2020-01-01 	潍坊市唯捷01	 09:15:20	2648
     * 2020-01-01 	潍坊市唯捷01	 09:28:30	2274
     * 2020-01-01 	潍坊市唯捷01	 09:42:03	1754
     * 2020-01-01 	潍坊市唯捷01	 09:52:36	1756
     * 2020-01-01 	潍坊市唯捷01	 09:58:10	2660
     * 2020-01-01 	潍坊市唯捷01	 10:12:13	1742
     * 2020-01-01 	潍坊市唯捷01	 10:20:40	2509
     * 2020-01-01 	潍坊市唯捷01	 10:26:29	1712
     * 2020-01-01 	潍坊市唯捷01	 10:29:42	1731
     * 2020-01-01 	潍坊市唯捷01	 10:39:18	5326
     * 2020-01-01 	潍坊市唯捷01	 10:56:18	1744
     * 2020-01-01 	潍坊市唯捷01	 11:10:20	1745
     * 2020-01-01 	潍坊市唯捷01	 11:18:28	4642
     * 2020-01-01 	潍坊市唯捷01	 11:23:34	1738
     * 2020-01-01 	潍坊市唯捷01	 11:40:28	1864
     * 2020-01-01 	潍坊市唯捷01	 11:53:34	2756
     * 2020-01-01 	潍坊市唯捷01	 12:04:22	1998
     * 2020-01-01 	潍坊市唯捷01	 12:23:34	2077
     */
    //样4-潍坊市唯捷01
    public static void testWf1() {
        System.out.println("潍坊市唯捷01");
        //1.抽取数据，日期->有序的店铺
        Map<String, List<Integer>> history = extract("/wfwj01");
        //2.学习经验，shopId1在shopId2前面的次数
        Map<String, Integer> experience = learning(history);
        //3.待排序的样本，系统通过距离远近的排序（2020-01-01）
        Integer[] shopIds = new Integer[]{2648, 2274, 1742, 2509, 1712, 1731, 2660, 1756, 1754, 1745, 1744, 5326, 4642, 1738, 1864, 2756, 1998, 2077};
        System.out.println("排序前（根据距离远近排序）：" + Arrays.toString(shopIds));
        //根据经验值进行排序
        sort(shopIds, experience);
        System.out.println("排序后（根据经验远近排序）：" + Arrays.toString(shopIds));
        //司机真实走的路线的样本 （2020-01-01）
        Integer[] real = new Integer[]{2648, 2274, 1754, 1756, 2660, 1742, 2509, 1712, 1731, 5326, 1744, 1745, 4642, 1738, 1864, 2756, 1998, 2077};
        System.out.println("真排序（司机本次决定排序）：" + Arrays.toString(real));
        System.out.println("\n");
    }

    /**
     * 日照市唯捷01
     * 系统排序
     * 2020-01-01	20200101日照市唯捷01	4590
     * 2020-01-01	20200101日照市唯捷01	1495
     * 2020-01-01	20200101日照市唯捷01	5028
     * 2020-01-01	20200101日照市唯捷01	1504
     * 2020-01-01	20200101日照市唯捷01	3199
     * 2020-01-01	20200101日照市唯捷01	3651
     * 2020-01-01	20200101日照市唯捷01	3533
     * 2020-01-01	20200101日照市唯捷01	3670
     * 2020-01-01	20200101日照市唯捷01	3477
     * 2020-01-01	20200101日照市唯捷01	3597
     * 2020-01-01	20200101日照市唯捷01	3596
     * 2020-01-01	20200101日照市唯捷01	3673
     * 2020-01-01	20200101日照市唯捷01	3557
     * 2020-01-01	20200101日照市唯捷01	3644
     * 2020-01-01	20200101日照市唯捷01	3564
     * 2020-01-01	20200101日照市唯捷01	3886
     * 2020-01-01	20200101日照市唯捷01	3652
     * <p>
     * 真实排序
     * 2020-01-01 	日照市唯捷01	 09:43:27	4590
     * 2020-01-01 	日照市唯捷01	 10:29:39	5028
     * 2020-01-01 	日照市唯捷01	 10:29:46	1504
     * 2020-01-01 	日照市唯捷01	 10:30:10	1495
     * 2020-01-01 	日照市唯捷01	 10:51:59	3199
     * 2020-01-01 	日照市唯捷01	 10:52:06	3651
     * 2020-01-01 	日照市唯捷01	 11:26:15	3533
     * 2020-01-01 	日照市唯捷01	 11:26:29	3564
     * 2020-01-01 	日照市唯捷01	 11:35:15	3886
     * 2020-01-01 	日照市唯捷01	 11:44:40	3652
     * 2020-01-01 	日照市唯捷01	 12:14:20	3670
     * 2020-01-01 	日照市唯捷01	 12:14:28	3477
     * 2020-01-01 	日照市唯捷01	 12:24:52	3596
     * 2020-01-01 	日照市唯捷01	 12:52:52	3597
     * 2020-01-01 	日照市唯捷01	 12:53:01	3557
     * 2020-01-01 	日照市唯捷01	 13:02:07	3673
     * 2020-01-01 	日照市唯捷01	 13:09:50	3644
     *
     * @return
     */
    //样本5-日照市唯捷01 日志12-31刚改了配送地址，不准
    public static void testRz1() {
        System.out.println("日照市唯捷01");
        //1.抽取数据，日期->有序的店铺
        Map<String, List<Integer>> history = extract("/rzwj01");
        //2.学习经验，shopId1在shopId2前面的次数
        Map<String, Integer> experience = learning(history);
        //3.待排序的样本，系统通过距离远近的排序（2020-01-01）
        Integer[] shopIds = new Integer[]{4590, 1495, 5028, 1504, 3199, 3651, 3533, 3670, 3477, 3597, 3596, 3673, 3557, 3644, 3564, 3886, 3652};
        System.out.println("排序前（根据距离远近排序）：" + Arrays.toString(shopIds));
        //根据经验值进行排序
        sort(shopIds, experience);
        System.out.println("排序后（根据经验远近排序）：" + Arrays.toString(shopIds));
        //司机真实走的路线的样本 （2020-01-01）
        Integer[] real = new Integer[]{4590, 5028, 1504, 1495, 3199, 3651, 3533, 3564, 3886, 3652, 3670, 3477, 3596, 3597, 3557, 3673, 3644};
        System.out.println("真排序（司机本次决定排序）：" + Arrays.toString(real));
        System.out.println("\n");
    }


    public static Map<String, List<Integer>> extract(String fileName) {
        Map<String, List<Integer>> history = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(MySort.class.getResourceAsStream(fileName)))) {
            do {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String date = line.split("\\s+")[0];
                Integer shopId = Integer.parseInt(line.split("\\s+")[3]);
                if (!history.containsKey(date)) {
                    history.put(date, new ArrayList<>());
                }
                history.get(date).add(shopId);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("历史天数：" + history.size());
        return history;
    }

    /**
     * 从历史中学习出来
     *
     * @param history
     * @return
     */
    public static Map<String, Integer> learning(Map<String, List<Integer>> history) {
        Map<String, Integer> experience = new HashMap<>();
        for (List<Integer> shotIdList : history.values()) {
            for (int i = 0; i < shotIdList.size() - 1; i++) {
                for (int j = i + 1; j < shotIdList.size(); j++) {
                    String key = shotIdList.get(i) + "_" + shotIdList.get(j);
                    if (!experience.containsKey(key)) {
                        experience.put(key, 0);
                    }
                    experience.put(key, experience.get(key) + 1);
                    if (key.equals("3557_1495")) {
                        System.out.println("3557_1495:" + shotIdList);
                    }
                }
            }
        }

        System.out.println("经验值：" + experience);
        return experience;
    }

    public static void sort(Integer[] shopIds, Map<String, Integer> experience) {
        for (int i = 0; i < shopIds.length - 1; i++) {
            for (int j = i + 1; j < shopIds.length; j++) {
                int poss = nearer(shopIds[i], shopIds[j], experience);
                if (poss < 0) {
                    System.out.println("换序了：" + shopIds[i] + "," + shopIds[j]+"("+poss+")");
                    Integer tmp = shopIds[i];
                    shopIds[i] = shopIds[j];
                    shopIds[j] = tmp;
                }
            }
        }
    }

    /**
     * 店铺1是否比店铺2更靠前
     *
     * @param shopId1
     * @param shopId2
     * @param experience
     * @return
     */
    public static int nearer(Integer shopId1, Integer shopId2, Map<String, Integer> experience) {
        String key1 = shopId1 + "_" + shopId2;
        String key2 = shopId2 + "_" + shopId1;
        Integer score1 = experience.get(key1) == null ? 0 : experience.get(key1);
        Integer score2 = experience.get(key2) == null ? 0 : experience.get(key2);
        if (score1 == 0 && score2 == 0) {
            System.err.println("-缺乏经验：" + key1);
//            throw new RuntimeException("缺乏经验："+key1);
        }else if(score1 == score2){
            System.err.println("*经验冲突：" + key1);
        }
        return score1 - score2;
    }
}




