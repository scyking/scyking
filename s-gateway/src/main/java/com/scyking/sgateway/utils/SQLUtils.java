package com.scyking.sgateway.utils;

/**
 * sql处理工具类
 *
 * @author scyking
 **/
public class SQLUtils {

    //非法字符(sql关键字)
    private static final String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};
    //敏感字符(sql注入常用特殊字符)
    private static final char[] characters = {'\'', '\"', ';', '\\'};

    /**
     * SQL注入判断
     *
     * @param str 待验证的字符串
     */
    public static boolean sqlInject(String str) {
        //转换成小写
        str = str.toLowerCase();
        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.contains(keyword)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 替换敏感字符
     *
     * @param str 待处理字符串
     */
    public static String sqlInjectReplace(String str) {
        //去掉敏感字符
        for (char character : characters) {
            str = str.replace(character, ' ');
        }
        return str;
    }
}
