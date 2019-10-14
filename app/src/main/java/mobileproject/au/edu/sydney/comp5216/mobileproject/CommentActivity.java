package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Beans.CommentDetail;
import Beans.Comments;
import Service.CommentService;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CommentActivity extends AppCompatActivity {

    private String storename = "KFC";
    private int state= 0;
    private ListView commentlist;
    private Map<String, CommentDetail>comments;
    private DatabaseReference mDatabase;
    private Button addCommentbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setAllCommentsList(storename);
        setAddButtonListener();

    }

    public void setAllCommentsList(String storename){
        mDatabase.child("restaurants").child(storename)
                .child("comments")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<CommentDetail>detaillist = new ArrayList<>();
                        for (DataSnapshot alert:dataSnapshot.getChildren()) {
                            Gson gson = new Gson();
                            String s = gson.toJson(alert.getValue());
                            detaillist.add(gson.fromJson(s,CommentDetail.class));
                        }
                        setListView(detaillist);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


    private void setListView(List<CommentDetail>detailList){
        commentlist = findViewById(R.id.comments_list);
        LayoutInflater inflater =getLayoutInflater();
        CommentListAdapter commentListAdapter =
                new CommentListAdapter(inflater,detailList);
        commentlist.setAdapter(commentListAdapter);

    }

    private void setAddButtonListener(){
        addCommentbtn = findViewById(R.id.add_comment_btn);
        addCommentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this,addCommentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



}
