package cn.kinggm520.service.impl;

import cn.kinggm520.dao.DiaryDao;
import cn.kinggm520.dao.DiaryTypeDao;
import cn.kinggm520.dao.impl.DiaryDaoImpl;
import cn.kinggm520.dao.impl.DiaryTypeDaoImpl;
import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.DiaryType;
import cn.kinggm520.domain.PageBean;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.MainService;

import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-26 10:47
 */
public class MainServiceImpl implements MainService {

    private DiaryDao diaryDao = new DiaryDaoImpl();
    private DiaryTypeDao diaryTypeDao = new DiaryTypeDaoImpl();

    /**
     * @param user 当前用户对象
     * @return 查询到的当前用户所有的日记信息
     */
    @Override
    public List<Diary> diaryListService(User user, PageBean pageBean,Diary s_diary) {
        return diaryDao.diaryList(user, pageBean,s_diary);
    }

    @Override
    public int diaryCountService(User user,Diary s_diary) {
        return diaryDao.diaryCount(user,s_diary);
    }


    /**
     * 显示分页
     *
     * @param totalNum
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public String genPagation(int totalNum, int currentPage, int pageSize) {

//        计算总页数
        int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
//       拼接pageCode


//        跳转到首页
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<li><a href='mainServlet?page=1'>首页</a></li>");

//第一页
        if (currentPage == 1) {
            pageCode.append("<li class='disabled'><span><span aria-hidden='true'>&laquo;</span></span></li>");
        } else {
            pageCode.append("<li> <a href='mainServlet?page=" + (currentPage - 1) + "' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
        }

//循环获取显示页数
        for (int i = currentPage - 2; i <= currentPage + 2; i++) {
            if (i < 1 || i > totalPage) {
                continue;
            }

            if (i == currentPage) {
                pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
            } else {
                pageCode.append("<li> <a href='mainServlet?page=" + i + "'><span aria-hidden='true'>" + i + "</span></a></li>");
            }
        }


//最后一页
        if (currentPage == totalPage) {
            pageCode.append("<li class='disabled'><span><span aria-hidden='true'>&raquo;</span></span></li>");
        } else {
            pageCode.append("<li> <a href='mainServlet?page=" + (currentPage + 1) + "' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");
        }

//        跳转到尾页
        pageCode.append("<li><a href='mainServlet?page=" + totalPage + "'>尾页</a></li>");

        return pageCode.toString();
    }

    @Override
    public List<DiaryType> diaryTypeCountListService(User user) {
        return diaryTypeDao.diaryTypeCountList(user);
    }

    @Override
    public List<Diary> diaryCountListService(User user) {
        return diaryDao.diaryCountList(user);
    }


}
