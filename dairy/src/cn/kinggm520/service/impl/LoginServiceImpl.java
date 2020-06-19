package cn.kinggm520.service.impl;

import cn.kinggm520.dao.UserDao;
import cn.kinggm520.dao.impl.UserDaoImpl;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.LoginService;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 10:47
 */
public class LoginServiceImpl implements LoginService {

    private UserDao userDao = new UserDaoImpl();


    /**
     * @param user 用户输入的user对象
     * @return  数据库中查询到的user对象
     */
    @Override
    public User loginService(User user) {
        return userDao.login(user);
    }
}
