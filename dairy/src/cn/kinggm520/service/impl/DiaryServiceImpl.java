package cn.kinggm520.service.impl;

import cn.kinggm520.dao.DiaryDao;
import cn.kinggm520.dao.impl.DiaryDaoImpl;
import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.DiaryService;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-07 11:03
 */
public class DiaryServiceImpl implements DiaryService {

    private DiaryDao diaryDao=new DiaryDaoImpl();

    @Override
    public Diary diaryShow(User user,String diaryId){
       return diaryDao.diaryShow(user, diaryId);
    }

    @Override
    public int diaryAdd(Diary diary,User user ) {
        return diaryDao.diaryAdd(diary,user);
    }

    @Override
    public int diaryDelete(String diaryId) {
        return diaryDao.diaryDelete(diaryId);
    }

    @Override
    public int diaryUpdate(Diary diary) {
        return diaryDao.diaryUpdate(diary);
    }
}
