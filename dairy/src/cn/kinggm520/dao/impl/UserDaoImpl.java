package cn.kinggm520.dao.impl;

import cn.kinggm520.dao.UserDao;
import cn.kinggm520.domain.User;
import cn.kinggm520.util.JDBCDruidUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static cn.kinggm520.util.MD5Util.EncoderPassword;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-07 21:12
 */

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCDruidUtil.getDataSource());

    /**
     * @param user 用户对象
     * @return 查询到的用户对象如果未查询到结果 则返回null
     */
    //登录验证
    @Override
    public User login(User user) {
        User resultUser = null;
        String sql = "select * from t_user where userName=? AND password=?";

//        使用 BeanRowMapper
        try {
            resultUser = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserName(), EncoderPassword(user.getPassword()));
        } catch (DataAccessException e) {
//            这里以后记录日志
        }
        return resultUser;
    }


    /**
     * @param user 用户对象
     * @return 返回影响行数
     */
    //    注册
    @Override
    public int register(User user) {
        String sql = "insert into t_user (userName,password,nickName,mood,joinDate) VALUES (?,?,?,?,?)";
        int update = 0;
        try {
            update = template.update(sql, user.getUserName(), EncoderPassword(user.getPassword()), "苦逼程序猿", "生活是一种态度", user.getJoinDate());

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return update;
    }


    /**
     * @param user 用户对象
     * @return 0->用户名未重复   1->用户名重复了
     */
    //    查重
    public int IsExist(User user) {
        String sql = "select * from t_user where userName=?";
        User currentUser = null;
        try {
            currentUser = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserName());
        } catch (DataAccessException e) {
            //记录日志
        }

        if (currentUser == null) {
            return 0;   //用户名未重复
        } else {
            return 1;   //用户名重复了
        }

    }

    //    个人信息修改
    @Override
    public int userUpdate(User user) {
        int i = 0;

        String sql = "update t_user set nickName=? , mood=? , imageName=? where userId=?";
        try {
            i = template.update(sql, user.getNickName(), user.getMood(), user.getImageName(), user.getUserId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return i;
    }


}
