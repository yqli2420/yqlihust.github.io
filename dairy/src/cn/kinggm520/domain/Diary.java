package cn.kinggm520.domain;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-25 17:37
 */
public class Diary {
    private Integer diaryId;
    private String title;
    private String content;
    private Integer typeId=-1;
    private String releaseDate;
    private String userName;
    private int diaryCount;
    private String typeName;

    public Diary() {
    }

    public Diary(String title, String content, Integer typeId, String userName) {
        this.title = title;
        this.content = content;
        this.typeId = typeId;
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

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
