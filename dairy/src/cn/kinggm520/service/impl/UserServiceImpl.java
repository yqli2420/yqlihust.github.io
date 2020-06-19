package cn.kinggm520.service.impl;

import cn.kinggm520.dao.DiaryDao;
import cn.kinggm520.dao.UserDao;
import cn.kinggm520.dao.impl.DiaryDaoImpl;
import cn.kinggm520.dao.impl.UserDaoImpl;
import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.DiaryService;
import cn.kinggm520.service.UserService;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-07 11:03
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public int userUpdate(User user) {
        return userDao.userUpdate(user);
    }
}
