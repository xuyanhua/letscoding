package com.yanhua.algorithms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 两数之和
 */
public class NumberAdd {
    public static void main(String[] args) {
        String num1 = "123.456";
        String num2 = "456.7891";
        String result = add(num1, num2);
        System.out.println(result);
    }

    /**
     * 两个数值相加
     */
    public static String add(String num1, String num2) {
        assertNumber(num1);
        assertNumber(num2);
        return add0(num1, num2);
    }

    /**
     * 验证数值合法性
     *
     * @param num
     */
    private static void assertNumber(String num) {
        if (num == null || num.length() == 0) {
            throw new RuntimeException("参数为空");
        }
        Pattern numberPattern = Pattern.compile("\\d+[\\.\\d*]*");
        Matcher matcher = numberPattern.matcher(num);
        if (!matcher.matches()) {
            throw new RuntimeException("参数必须是一个数字");
        }
    }

    private static String add0(String num1, String num2) {
        int decimalLen1 = num1.length() - num1.indexOf(".") - 1;//小数位长度
        int decimalLen2 = num2.length() - num2.indexOf(".") - 1;//小数位长度
        //让第一个数小数位更长
        int lenDif = decimalLen1 - decimalLen2;//两个小数位的差值
        if (decimalLen1 < decimalLen2) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
            lenDif = decimalLen2 - decimalLen1;
        }

        StringBuffer buffer = new StringBuffer();
        if (lenDif > 0) {
            buffer.append(num1.substring(num1.length() - lenDif, num1.length()));
        }
        int carryBit = 0;//进位
        int i = num1.length() - lenDif - 1;
        int j = num2.length() - 1;
        for (; i >= 0 & j >= 0; i--, j--) {
            char n1 = num1.charAt(i);
            if (n1 == '.') {
                buffer.insert(0, '.');
                continue;
            }
            char n2 = num2.charAt(j);
            int sum = (n1 - '0') + (n2 - '0') + carryBit;
            carryBit = sum / 10;
            buffer.insert(0, sum % 10);
        }
        //将剩余的数加和
        if (i > 0) {
            for (; i > 0; i--) {
                char n1 = num1.charAt(i);
                int sum = n1 + carryBit;
                carryBit = sum / 10;
                buffer.insert(0, sum % 10);
            }
        }
        if (j > 0) {
            for (; j > 0; j--) {
                char n2 = num2.charAt(i);
                int sum = n2 + carryBit;
                carryBit = sum / 10;
                buffer.insert(0, sum % 10);
            }
        }
        //最后进位
        if (carryBit > 0) {
            buffer.insert(0, carryBit);
        }

        return buffer.toString();

    }
}
