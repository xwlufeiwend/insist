package com.crall.insist.utils;

public class StringUtils {
    /**
     * 截取str最后 > 之后的值
     * @param str
     * @return
     */
    public static String subTagBefore(String str){
        int index = str.lastIndexOf(">");
//        System.out.println(index);
        String result = str.substring(index + 1);
        return result;
    }

    public static void main(String[] args) {
        String str = "<b>小雨</b>12 ~ 16℃";
        System.out.println(subTagBefore(str));
    }
}
