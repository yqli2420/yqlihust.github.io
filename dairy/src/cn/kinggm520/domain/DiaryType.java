package cn.kinggm520.domain;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-02 22:53
 */
public class DiaryType {
    private int diaryTypeId;
    private String userName;
    private String typeName;
    private int diaryCount;


    public int getDiaryTypeId() {
        return diaryTypeId;
    }

    public void setDiaryTypeId(int diaryTypeId) {
        this.diaryTypeId = diaryTypeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getDiaryCount() {
        return diaryCount;
    }

    public void setDiaryCount(int diaryCount) {
        this.diaryCount = diaryCount;
    }
}
