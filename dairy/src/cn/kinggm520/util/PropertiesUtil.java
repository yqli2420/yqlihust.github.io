package cn.kinggm520.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-27 14:40
 */
public class PropertiesUtil {
    static Properties pro = new Properties();

    public static String getValue(String fileName, String key) {
        try {
            pro.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
//            写日志
        }
        return pro.getProperty(key);
    }

}
