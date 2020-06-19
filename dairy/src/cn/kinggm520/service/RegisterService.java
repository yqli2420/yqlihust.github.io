package cn.kinggm520.service;

import cn.kinggm520.domain.User;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 10:55
 */
public interface RegisterService {

    //判断是否存在该用户
    public int isExitService(User user);

    //注册用户
    public int registerService(User user);

}
