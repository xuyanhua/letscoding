package com.yanhua.datastructure.string;

/**
 * 替换字符串中的空格
 *
 * @author xuyanhua
 * @description:
 * @date 2019/4/7 下午10:19
 */
public class ReplaceBlank {

    /**
     * 要求将字符串中的空格替换为%20。时间复杂度为O(n)
     * <ul>
     * <li>1、假设字符数组的长度足够(用expand方法模拟)</li>
     * <li>2、模拟方法，因为每替换一次，字符数组长度就加2，因此找到空格的数目，乘以2，加上原字符长度即为新字符数组长度</li>
     * <li>3、用两个索引位置，一个指向原字符串最后一个字符的位置，另一个指向现在字符串最后一个字符的位置</li>
     * <li>4、从后往前复制字符串</li>
     * <li>5、如果遇到空格，则替换为%20</li>
     * <li>6、如果不是空格，直接复制</li>
     * <p>
     * </ul>
     *
     * @param str 被替换的字符串
     * @return
     */
    public static String replaceBlank(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] chars = str.toCharArray();
        int blankCount = 0;
        for (char c : chars) {
            if (c == ' ') {
                blankCount++;
            }
        }
        //原字符串的尾字符位置
        int tailIndex = chars.length - 1;
        int newLength = chars.length + blankCount * 2;
        chars = expand(chars, newLength);
        //新字符串的尾字符位置
        int newTailIndex = chars.length - 1;
        for (; tailIndex >= 0; tailIndex--) {
            if (chars[tailIndex] == ' ') {
                chars[newTailIndex--] = '0';
                chars[newTailIndex--] = '2';
                chars[newTailIndex--] = '%';
            } else {
                chars[newTailIndex--] = chars[tailIndex];
            }
        }
        return new String(chars);
    }

    /**
     * 模拟一个足够长的字符数组
     *
     * @param chars
     * @param newLength
     * @return
     */
    private static char[] expand(char[] chars, int newLength) {
        char[] newChars = new char[newLength];
        for (int i = 0; i < chars.length; i++) {
            newChars[i] = chars[i];
        }
        return newChars;
    }

    public static void main(String[] args) {
        String str = "We are happy.";
        String newStr = replaceBlank(str);
        System.out.println(newStr);
    }
}
