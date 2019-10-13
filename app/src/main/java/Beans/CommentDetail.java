package Beans;


import android.content.Intent;

public class CommentDetail {
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getSocre() {
        return socre;
    }

    public void setSocre(Integer socre) {
        this.socre = socre;
    }

    private Integer socre;

    public CommentDetail(){

    }
    public CommentDetail(String detail, Integer score){
        this.detail =detail;
        this.socre = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
