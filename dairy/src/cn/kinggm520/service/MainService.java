package cn.kinggm520.service;

import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.DiaryType;
import cn.kinggm520.domain.PageBean;
import cn.kinggm520.domain.User;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 10:55
 */
public interface MainService {

//    查询所有日记
    public List<Diary> diaryListService(User user, PageBean pageBean,Diary s_diary);

//    查询总记录数
    public int diaryCountService(User user,Diary s_diary);

//    生成分页
    public String genPagation(int totalNum,int currentPage,int pageSize);

//        查询

    public List<DiaryType> diaryTypeCountListService(User user);

    public List<Diary> diaryCountListService(User user);
}
