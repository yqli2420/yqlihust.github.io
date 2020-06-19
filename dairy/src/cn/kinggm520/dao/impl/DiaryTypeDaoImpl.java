package cn.kinggm520.dao.impl;

import cn.kinggm520.dao.DiaryTypeDao;
import cn.kinggm520.domain.DiaryType;
import cn.kinggm520.domain.User;
import cn.kinggm520.util.JDBCDruidUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-02 23:09
 */
public class DiaryTypeDaoImpl implements DiaryTypeDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCDruidUtil.getDataSource());

    //    查询当前用户的所有日志类别和类别下日记数量
    @Override
    public List<DiaryType> diaryTypeCountList(User user) {
        List<DiaryType> list = null;
        String sql = "SELECT diaryTypeId,typeName,COUNT(diaryId) as diaryCount FROM (SELECT * FROM t_diary WHERE userName=?) t1 RIGHT JOIN (SELECT * FROM t_diarytype WHERE userName=?) t2 ON t1.typeId=t2.diaryTypeId GROUP BY typeName";

        try {
            list = template.query(sql, new BeanPropertyRowMapper<DiaryType>(DiaryType.class), user.getUserName(), user.getUserName());
        } catch (DataAccessException e) {
            e.printStackTrace();

        }

        return list;
    }


    //    查询当前用户日志类别详情
    @Override
    public List<DiaryType> diaryTypeList(User user) {
        List<DiaryType> diaryTypeList = null;

        String sql = "select diaryTypeId,typeName from t_diarytype where userName=?";


        try {
            diaryTypeList = template.query(sql, new BeanPropertyRowMapper<DiaryType>(DiaryType.class), user.getUserName());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return diaryTypeList;
    }

    //    添加日记类别
    @Override
    public int diaryTypeAdd(DiaryType diaryType, User user) {
        String sql = "insert into t_diarytype values (null,?,?)";
        int i = 0;
        try {
            i = template.update(sql, user.getUserName(), diaryType.getTypeName());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return i;
    }

    //    修改日志类别
    @Override
    public int diaryTypeUpdate(DiaryType diaryType) {
        String sql = "update t_diarytype set  typeName=? where diaryTypeId=?";
        int i = 0;
        try {
            i = template.update(sql, diaryType.getTypeName(), diaryType.getDiaryTypeId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return i;
    }

    //    查询类别详细信息
    @Override
    public DiaryType diaryTypeShow(String diaryTypeId) {
        DiaryType diaryType = null;

        String sql = "select * from t_diarytype where diaryTypeId=?";
        try {
            diaryType = template.queryForObject(sql, new BeanPropertyRowMapper<DiaryType>(DiaryType.class), diaryTypeId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return diaryType;
    }

    //    删除日记类别
    @Override
    public int diaryTypeDelete(String diaryTypeId) {

        int i = 0;
        String sql = "delete from t_diarytype where diaryTypeId=?";
        try {
            i = template.update(sql, diaryTypeId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return i;
    }

    @Override
    public boolean existDiaryWithTypeId(String diaryTypeId, User user) {
        int count=0;

        String sql="select count(*) from t_diary where userName=? and typeId=?";

        try {
            count=  template.queryForObject(sql,Integer.class,user.getUserName(),diaryTypeId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        if(count!=0){
            return true;
        }

        return false;
    }


}
