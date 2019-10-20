package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import Beans.CommentDetail;
import Beans.Comments;
import DAO.CommentsDAO;
import Service.CommentService;
import androidx.annotation.Nullable;

public class addCommentActivity extends Activity {

    CommentsDAO commentsDAO;
    Button addCommentbtn;
    SimpleRatingBar ratingBar;
    EditText message;
    String storename;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        storename = getIntent().getExtras().getString("storename");
        commentsDAO = new CommentService();
        ratingBar = findViewById(R.id.ratingbar_addcomment);
        message = findViewById(R.id.comment_textarea);
        addCommentbtn = findViewById(R.id.add_comment_detail_btn);
        setOnClickListener();

    }

    public void setOnClickListener(){
        addCommentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = message.getText().toString();
                Integer start =Math.round(ratingBar.getRating());
                if(s.isEmpty()){
                    Toast.makeText(getApplicationContext()
                            ,"Please add your comment"
                            ,Toast.LENGTH_LONG).show();
                }
                else{
                    Comments comments = new Comments("test user"
                            ,storename,new CommentDetail(s,start,"test user"));
                    if(commentsDAO.addComment(comments)){
                        Toast.makeText(getApplicationContext()
                                ,"Your comment will be published"
                                ,Toast.LENGTH_LONG).show();
                    }
                    // pass restaurant and current user back
                    Intent intent = new Intent(addCommentActivity.this
                            ,CommentActivity.class);
                    intent.putExtra("storename",storename);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}
