package com.module.common.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;

public class RandomUtil {
    public RandomUtil() throws RuntimeException {
        throw new RuntimeException("this class is util");
    }

    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};


    /**
     * 生成8位随机字符
     * 测试10W次无重复
     *
     * @return
     */
    public static String encry8() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = IdUtil.fastSimpleUUID();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString().toLowerCase();

    }


    /**
     * 10进制转26进制 适用于时间戳的转换
     *
     * @Auther: WangDong
     * @Date: 2018/4/24 11:19
     */
    public static String to26Jinzhi(long data) {
        String str = "ABCDEFGHIJKLNMOPQRSTUVWXYZ";
        //转化目标进制
        int scale = str.length();
        String s = "";
        if (data == 0) {
            return str.charAt(0) + "";
        }
        while (data > 0) {
            if (data < scale) {
                s = str.charAt(Long.valueOf(data).intValue()) + s;
                data = 0;
            } else {
                int r = Long.valueOf(data % scale).intValue();
                s = str.charAt(r) + s;
                data = (data - r) / scale;
            }
        }
        return s;
    }


    public static String toNumberSystem26(long n) {
        String s = "";
        while (n > 0) {
            int m = Long.valueOf(n % 26).intValue();
            if (m == 0) {
                m = 26;
            }
            s = (char) (m + 64) + s;
            n = (n - m) / 26;
        }
        return s;
    }

    public static Long getNum(String s) {
        Long sum = 0L;
        for (int i = 0; i < s.length(); i++) {
            sum += (long) ((s.charAt(i) - 'a') * Math.pow(26, s.length() - 1 - i));
        }
        return sum;

    }

    public static long FromNumberSystem26(String s) {
        if (StrUtil.isBlank(s)) {
            return 0;
        }
        long n = 0;
        char[] chars = s.toCharArray();
        long j = 1;
        for (int i = chars.length - 1; i >= 0; i--, j *= 26) {
            char c = Character.toUpperCase(chars[i]);
            if (c < 'A' || c > 'Z') {
                return 0;
            }
            n += ((long) c - 64) * j;
        }
        return n;
    }

    /**
     * 生成4位随机验证码
     * @return
     */
    public static String fourVerifyCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            code.append(cn.hutool.core.util.RandomUtil.randomInt(10));
        }
        int repeat=0;
        for (int i = 0; i < 4; i++) {
            if (code.charAt(0)==code.charAt(i)){
                repeat++;
            }
        }
        //验证四个码是否全部一样
        if (repeat==4){
            return fourVerifyCode();
        }
        return code.toString();
    }

}
