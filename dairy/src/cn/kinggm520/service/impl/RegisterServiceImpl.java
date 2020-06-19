package cn.kinggm520.service.impl;

import cn.kinggm520.dao.UserDao;
import cn.kinggm520.dao.impl.UserDaoImpl;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.RegisterService;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 10:47
 */
public class RegisterServiceImpl implements RegisterService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * @param user 用户对象
     * @return 0->用户名未重复   1->用户名重复了
     */
    @Override
    public int isExitService(User user) {
        return userDao.IsExist(user);

    }


    /**
     * @param user     用户对象
     * @return 返回影响行数
     */
    @Override
    public int registerService(User user) {

        return userDao.register(user);


    }
}
