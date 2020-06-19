package cn.kinggm520.dao;

import cn.kinggm520.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 10:40
 */
public interface UserDao {

//    登录验证
    public User login(User user);

//    注册
    public int register(User user);


//    查重
    public int IsExist(User user);

//    修改
    public int userUpdate(User user);

}
