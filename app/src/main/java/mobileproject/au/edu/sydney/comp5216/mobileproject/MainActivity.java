package mobileproject.au.edu.sydney.comp5216.mobileproject;

import Beans.Comments;
import DAO.CommentsDAO;
import Service.CommantService;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommentsDAO commentsDAO = new CommantService();
        Comments test = new Comments(1,"qwasa","1sasc",11,"@1321");
        commentsDAO.addComment(test);


    }
}
