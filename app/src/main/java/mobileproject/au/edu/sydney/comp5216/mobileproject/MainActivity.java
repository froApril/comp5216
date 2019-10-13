package mobileproject.au.edu.sydney.comp5216.mobileproject;

import Beans.CommentDetail;
import Beans.Comments;
import DAO.CommentsDAO;
import Service.CommentService;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommentsDAO commentsDAO = new CommentService();
        CommentDetail commentDetail = new CommentDetail("good",1);
        Comments comment = new Comments("xyf1","KFC",commentDetail);
        commentsDAO.addComment(comment);
//
//        commentDetail.setDetail("not good");
//
//        commentsDAO.updateComment(comment);

    }
}
