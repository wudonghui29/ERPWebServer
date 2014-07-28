package com.modules.Util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Test2 {
    public static void main(String[] args) throws IOException {
        String string = "你说呢";
        
        String EN =  new BASE64Encoder().encode(string.getBytes("UTF-8"));
        
        EN = 
//                "ewogICJPQkpFQ1RTIiA6IFsKICAgIHsKICAgICAgInJlc2lnbiIgOiBmYWxzZSwK" + "\r" +
//                "ICAgICAgImluRHJpdmVzIiA6IGZhbHNlLAogICAgICAiZGVwYXJ0bWVudCIgOiAi" + "\r" +
//                "6LSi5Yqh6YOo6ZeoIiwKICAgICAgIndvcmRNYXNrIiA6ICJuS05FOHBUSitublJP" + "\r" +
//                "SnI3dkRmdmQ4OEhDSlh0azhaM3VVNFQraEVsMW9uMkY2UFdMbGw2N3pSQ3ZpNmNl" + "\r" +
//                "UHZjXHJcbkhNSVFwVlwvdHNGWXYrQVdZM214aXlXWk1WdXBoVWl4OEQzOVROU0dt" + "\r" +
//                "aTAyaklTS0JcL0Q2b1dmS2RqUjVYelpQSVxyXG5HNUllSlc1SlhTMzFyYWIrSFJz" + "\r" +
//                "b2lOTFZLcjFhMFpqaWdoZGRQOGVIUjlZPSIsCiAgICAgICJmb3J3YXJkVXNlciIg" + "\r" +
//                "OiAiQUUwMDAxIiwKICAgICAgImV4Y2VwdGlvbiIgOiBmYWxzZSwKICAgICAgImVt" + "\r" +
//                "cGxveWVlTk8iIDogIjIyMTMyNDIzNSIsCiAgICAgICJvd25BcHByb3ZhbCIgOiBm" + "\r" +
//                "YWxzZSwKICAgICAgImdlbmRlciIgOiBmYWxzZSwKICAgICAgIm5hbWUiIDogIuml" + "\r" +
//                "reahjCDmiYAiLAogICAgICAiU2VuaW9yaXR5X0NvbXBsZXgiIDogIuW3pem+hC4u" + "\r" +
//                "IiwKICAgICAgImpvYkxldmVsIiA6ICIyIgogICAgfQogIF0sCiAgIk1PREVMUyIg" + "\r" +
//                "OiBbCiAgICAiLkVtcGxveWVlIgogIF0KfQ==";
        
        
        
        
        "ewogICJPQkpFQ1RTIiA6IFsKICAgIHsKICAgICAgInJlc2lnbiIgOiBmYWxzZSwK" + "\r" +
        "ICAgICAgImluRHJpdmVzIiA6IGZhbHNlLAogICAgICAid29ya19leHBlcmllbmNl" + "\r" +
        "IiA6ICIyMDE0LTA3LTI4Li4uIiwKICAgICAgIndvcmRNYXNrIiA6ICJmeUhkOFBJ" + "\r" +
        "SzNXQ0l0N2JkaGZPQ1NBNGVjcytmUnlwMnVpV0doZ2pYR0Q3NE1cL0hyOHdETmh6" + "\r" +
        "dFdBSFRiQWwrSlxyXG4rNFR5eFNwZVI5U3ZXYXNQUHZjU3p0eFd3Uk9Ya3JOTVwv" + "\r" +
        "bWd6WjZ6Z2FQOUhFXC9kWnZCcVlCd3ZkTnhUTHVqYURcclxuSDQxVzFsSjN5QU9G" + "\r" +
        "NHlpR3pZK09PaFFScEFueEVTUlR1cFBzR1BGS3NpST0iLAogICAgICAiYWdlIiA6" + "\r" +
        "IDAsCiAgICAgICJmb3J3YXJkVXNlciIgOiAiQUUwMDAxIiwKICAgICAgImRlcGFy" + "\r" +
        "dG1lbnQiIDogIuS4muWKoemDqOmXqCIsCiAgICAgICJleGNlcHRpb24iIDogZmFs" + "\r" +
        "c2UsCiAgICAgICJpZENhcmQiIDogIlVsTlNVMVJTV2xSU1UxcFVVMUpbQ11fWDUw" + "\r" +
        "OUNTUiIsCiAgICAgICJiaXJ0aGRheSIgOiAiMjAxNC0wNy0yOCAwMDowMDowMCIs" + "\r" +
        "CiAgICAgICJlbXBsb3llZU5PIiA6ICIyMzIzMjEzNTY0NjQzIiwKICAgICAgIm93" + "\r" +
        "bkFwcHJvdmFsIiA6IGZhbHNlLAogICAgICAiZ2VuZGVyIiA6IGZhbHNlLAogICAg" + "\r" +
        "ICAibmFtZSIgOiAi6aOO6aOO54Gr54GrIiwKICAgICAgIlNlbmlvcml0eV9Db21w" + "\r" +
        "bGV4IiA6ICLlt6XpvoQuLiIsCiAgICAgICJqb2JMZXZlbCIgOiAiMiIKICAgIH0K" + "\r" +
        "ICBdLAogICJNT0RFTFMiIDogWwogICAgIi5FbXBsb3llZSIKICBdCn0=";
                
        String DE = new String(new BASE64Decoder().decodeBuffer(EN));
        
        System.out.println();
    }
}
