package com.smartweibo.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MyStringUtil {

    private static String randomSet = "abcdefghijkmnopqrstuvwxyzZBCDEFGHIJKLMNPQRSTUVWXYZ0123456789";
    /**
     * 随机出一个指定长度的字符串
     * @return String
     */
    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int a = (int) (Math.random()*randomSet.length());
            sb.append(randomSet.charAt(a));
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否为空
     */
    public static  boolean isNotNull(String string){
        if (string == null || "".equals(string)) {
            return Boolean.FALSE;
        }else {
            return Boolean.TRUE;
        }
    }

    /**
     * 以字符串的形式获取当前系统时间
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 将字符串转化为utf-8编码
     * @param str
     * @return
     */
    public static String toUTF8(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
