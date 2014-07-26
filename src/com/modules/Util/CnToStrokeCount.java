package com.modules.Util;

import j2se.modules.Helper.ChineseHelper;

public class CnToStrokeCount {

    public static void main(String[] args) {

        String cnStr = "測試数据于";
        char[] chars = cnStr.toCharArray();
        System.out.print("<" + cnStr + ">的笔画数分别是：");
        for (int i = 0; i < chars.length; i++) {
            System.out.print("*" + ChineseHelper.getStrokeCount(chars[i]));
        }
        System.out.println("*");

        cnStr = "CSDN - 专家门诊 - Java";
        chars = cnStr.toCharArray();
        System.out.print("<" + cnStr + ">的笔画数分别是：");
        for (int i = 0; i < chars.length; i++) {
            System.out.print("*" + ChineseHelper.getStrokeCount(chars[i]));
        }
        System.out.println("*");

        cnStr = "=====提问技巧,请大家注意!!=====";
        chars = cnStr.toCharArray();
        System.out.print("<" + cnStr + ">的笔画数分别是：");
        for (int i = 0; i < chars.length; i++) {
            System.out.print("*" + ChineseHelper.getStrokeCount(chars[i]));
        }
        System.out.println("*");

    }


}
