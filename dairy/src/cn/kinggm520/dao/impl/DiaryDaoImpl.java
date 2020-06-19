package cn.kinggm520.dao.impl;


import cn.kinggm520.dao.DiaryDao;
import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.PageBean;
import cn.kinggm520.domain.User;
import cn.kinggm520.util.JDBCDruidUtil;
import cn.kinggm520.util.TimeUtil;
import com.alibaba.druid.util.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-25 17:41
 */
public class DiaryDaoImpl implements DiaryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCDruidUtil.getDataSource());

    /**
     * @param user session中存储的当前用户对象
     * @return 当前用户所有的日记list集合
     */
    //    查询
    @Override
    public List<Diary> diaryList(User user, PageBean pageBean, Diary s_diary) {
        List<Diary> diaryList = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from t_diary t1 , t_diarytype t2 where t1.typeId=t2.diaryTypeId and t1.userName=t2.userName and t1.userName=?");
//  全局模糊查询
        if (!StringUtils.isEmpty(s_diary.getTitle())) {
            sql.append(" and t1.title like '%" + s_diary.getTitle() + "%' ");

        }

//       判断按类别查询条件
        if (s_diary.getTypeId() != -1) { //因为默认为-1
            sql.append(" and t1.typeId=" + s_diary.getTypeId() + " ");
        }

        if (!StringUtils.isEmpty(s_diary.getReleaseDate())) { //日期分类查询条件不为空
            sql.append(" and DATE_FORMAT(t1.releaseDate,'%Y年%m月')='" + s_diary.getReleaseDate() + "'");
        }


        sql.append(" order by t1.releaseDate desc");
        if (pageBean != null) {
            sql.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize()); //注意空格
        }


        try {
            diaryList = template.query(sql.toString(), new BeanPropertyRowMapper<Diary>(Diary.class), user.getUserName());
        } catch (Exception e) {
            //记录日志
            e.printStackTrace();
        }

        return diaryList;

    }

    @Override
    public int diaryCount(User user, Diary s_diary) {

        int count = 0;
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM t_diary WHERE userName= ");
        sql.append("'" + user.getUserName() + "'");

        //  全局模糊查询
        if (!StringUtils.isEmpty(s_diary.getTitle())) {
            sql.append(" and t_diary.title like '%" + s_diary.getTitle() + "%' ");

        }

        //       判断按类别查询条件
        if (s_diary.getTypeId() != -1) { //因为默认为-1
            sql.append(" and t_diary.typeId=" + s_diary.getTypeId() + " ");
        }

        if (!StringUtils.isEmpty(s_diary.getReleaseDate())) { //日期分类查询条件不为空
            sql.append(" and DATE_FORMAT(t_diary.releaseDate,'%Y年%m月')='" + s_diary.getReleaseDate() + "'");
        }


        try {
            count = template.queryForObject(sql.toString(), Integer.class);
        } catch (Exception e) {
//            写日志
            e.printStackTrace();
        }

        return count;
    }

    //    按时间分组
    @Override
    public List<Diary> diaryCountList(User user) {
        List<Diary> diaryCountList = new ArrayList<>();
        String sql = "SELECT DATE_FORMAT(releaseDate,'%Y年%m月') AS releaseDate,COUNT(*) AS diaryCount FROM t_diary WHERE  userName=?  GROUP BY DATE_FORMAT(releaseDate,'%Y年%m月')  ORDER BY releaseDate DESC; ";
        try {
            diaryCountList = template.query(sql, new BeanPropertyRowMapper<Diary>(Diary.class), user.getUserName());
        } catch (DataAccessException e) {
//            写日志
            e.printStackTrace();
        }

        return diaryCountList;
    }


    //    根据日记id查询详细信息
    @Override
    public Diary diaryShow(User user, String diaryId) {
        Diary diary = null;
        String sql = "select * from t_diary t1, t_diarytype t2 where t1.typeId=t2.diaryTypeId and t1.userName=t2.userName and t1.userName=? and diaryId=?";
        try {
            diary = template.queryForObject(sql, new BeanPropertyRowMapper<Diary>(Diary.class), user.getUserName(), diaryId);
        } catch (DataAccessException e) {
//            记录日志
            e.printStackTrace();
        }

        return diary;
    }

    //    添加日志
    @Override
    public int diaryAdd(Diary diary, User user) {
        int count = 0;
        String time = TimeUtil.getTime(new Date());
        String sql = "insert into t_diary values (null,?,?,?,?,'" + time + "')";
        try {
            count = template.update(sql, user.getUserName(), diary.getTitle(), diary.getContent(), diary.getTypeId());
        } catch (DataAccessException e) {
            e.printStackTrace();
//            记录日志
        }
        return count;
    }

    //    删除日志
    @Override
    public int diaryDelete(String diaryId) {

        int i = 0;

        String sql = "delete from t_diary where diaryId=?";
        try {
            i = template.update(sql, diaryId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return i;
    }


    //    修改日志
    @Override
    public int diaryUpdate(Diary diary) {
        int i = 0;
        String sql = "update t_diary set title=?,content=?,typeId=? where diaryId=?";

        try {
            i = template.update(sql, diary.getTitle(), diary.getContent(), diary.getTypeId(),diary.getDiaryId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return i;
    }


}
