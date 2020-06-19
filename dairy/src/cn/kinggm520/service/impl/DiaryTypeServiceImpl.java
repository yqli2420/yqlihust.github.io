package cn.kinggm520.service.impl;

import cn.kinggm520.dao.DiaryTypeDao;
import cn.kinggm520.dao.impl.DiaryTypeDaoImpl;
import cn.kinggm520.domain.DiaryType;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.DiaryTypeService;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-07 11:03
 */
public class DiaryTypeServiceImpl implements DiaryTypeService {

    private DiaryTypeDao diaryTypeDao = new DiaryTypeDaoImpl();

    //    查询当前用户日志类别详情
    @Override
    public List<DiaryType> diaryTypeList(User user) {
        return diaryTypeDao.diaryTypeList(user);
    }

    //    添加日记类别
    @Override
    public int diaryTypeAdd(DiaryType diaryType, User user) {
        return diaryTypeDao.diaryTypeAdd(diaryType, user);
    }

    //    修改日志类别
    @Override
    public int diaryTypeUpdate(DiaryType diaryType) {
        return diaryTypeDao.diaryTypeUpdate(diaryType);
    }

    //    查询类别详细信息
    @Override
    public DiaryType diaryTypeShow(String diaryTypeId) {
        return diaryTypeDao.diaryTypeShow(diaryTypeId);
    }

    @Override
    public int diaryTypeDelete(String diaryTypeId) {
        return diaryTypeDao.diaryTypeDelete(diaryTypeId);
    }

    @Override
    public boolean existDiaryWithTypeId(String diaryTypeId, User user) {
        return diaryTypeDao.existDiaryWithTypeId(diaryTypeId,user);
    }
}
