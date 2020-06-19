package cn.kinggm520.service.impl;

import cn.kinggm520.service.CheckCodeService;
import cn.kinggm520.util.IdentifyCode;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 10:47
 */
public class CheckCodeServiceImpl implements CheckCodeService {
    /**
     * @param outputStream 字节输出流
     * @return 验证码字符串
     */
    @Override
    public String checkCodeService(OutputStream outputStream) {
        IdentifyCode identifyCode = new IdentifyCode();   //通过工具类生成验证码图片
        try {
            identifyCode.write(outputStream);   //传response获取的字节输出流
        } catch (IOException e) {
            //写日志
        }

        return identifyCode.getCode();
    }
}
