package cn.kinggm520.service;

import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.User;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-07 11:03
 */
public interface DiaryService {

//    日记展示
    public Diary diaryShow(User user,String diaryId);

//    添加日记
    public int diaryAdd(Diary diary,User user);

//    删除日记
    public int diaryDelete(String diaryId);

//    修改日记
    public int diaryUpdate(Diary diary);
}
