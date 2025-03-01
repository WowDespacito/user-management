package com.wowdespacito.user_management.utils;

import java.util.Random;

public class RandomStringGeneratorUtil {
    //定义字符集
    private static final String characters = "0123456789abcdefghijklmnopqrstuvwxyz";

    //获取随机用户名
    //不指定用户名长度时，默认为8
    public static String getRandomUsername(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    /**
     * 定义：邻近字符串
     * 1. 两个字符串，不同字符只会出现一个
     * 2. 规定字符串左侧为字符串低位，右侧为高位。计算邻近字符串时，优先替换低位字符，超出索引范围则考虑下一位或上一位字符
     * @param str
     * @return
     */
    public static String neighborString(String str, int step) {
        //如果距离为0，则返回字符串本身
        if (step == 0) {
            return str;
        }

        //距离非零，开始计算
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            int index = characters.indexOf(str.charAt(i));
            if (index + step < characters.length() && index + step >= 0) {
                sb.setCharAt(index, characters.charAt(index + step));
                break;
            }
        }
        return sb.toString();
    }
}
