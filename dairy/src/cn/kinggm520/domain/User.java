package cn.kinggm520.domain;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-07 21:08
 */
public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String nickName;
    private String imageName;
    private String mood;
    private String joinDate;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(Integer userId, String userName, String password, String nickName, String imageName, String mood) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.imageName = imageName;
        this.mood = mood;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}