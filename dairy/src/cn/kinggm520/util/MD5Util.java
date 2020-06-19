package cn.kinggm520.util;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 11:01
 * MD5加密
 */
public class MD5Util {

    //MD5加密
    public static String EncoderPassword(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Base64.Encoder encoder = Base64.getEncoder();
            return new String(encoder.encode(md5.digest(str.getBytes("utf-8"))));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
