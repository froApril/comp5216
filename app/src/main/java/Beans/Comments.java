package Beans;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Comments {
    private String username;
    private String storename;
    private CommentDetail commentDetail;

    public Comments(){

    }

    public CommentDetail getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(CommentDetail commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Comments(String username, String storename, CommentDetail commentDetail){
        this.username= username;
        this.storename = storename;
        this.commentDetail = commentDetail;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }


}
