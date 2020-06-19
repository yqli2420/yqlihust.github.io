package cn.kinggm520.dao;

import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.PageBean;
import cn.kinggm520.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 10:40
 */
public interface DiaryDao {

    //    查询
    public List<Diary> diaryList(User user, PageBean pageBean,Diary s_diary);

//    查询总记录数
    public int diaryCount(User user,Diary s_diary);

//    查询按时间分组记录
    public List<Diary> diaryCountList(User user);


    //    根据日记id查询详细信息
    public Diary diaryShow(User user,String diaryId);

//    添加日记
    public int diaryAdd(Diary diary,User user);

//    删除日志
    public int diaryDelete(String diaryId);

//    修改日记
    public int diaryUpdate(Diary diary);


}
