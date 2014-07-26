package com.modules.Util;

import java.io.UnsupportedEncodingException;

public class Test2 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        char str = '说';
        char string = '飛';
        
        byte[] bytes = String.valueOf(str).getBytes("GB2312");
        byte[] bytese = String.valueOf(str).getBytes("GB2312");
        
        System.out.println();
    }
}
