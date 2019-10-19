package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;


import Beans.MenuItem;
import adapter.MenuItemAdapter;

public class EditMenuActivity extends AppCompatActivity {

    private String storeName = "KFC";
    private ListView menuItemListView;
    private MenuItemAdapter menuItemAdapter;
    ArrayList<MenuItem> menuItemList;
    private DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_manage_menu);
        loadMenu(storeName);
    }

    public void loadMenu(String storename){
        mDatabase.child("restaurants").child(storename)
                .child("menu")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        menuItemList = new ArrayList<MenuItem>();
                        for (DataSnapshot alert:dataSnapshot.getChildren()) {

                            Gson gson = new Gson();
                            String s = gson.toJson(alert.getValue());
                            menuItemList.add(gson.fromJson(s,MenuItem.class));
                        }
                        setListView(menuItemList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void setListView(final ArrayList<MenuItem> menuItemList){
        menuItemListView = findViewById(R.id.menu_item_list);
        menuItemAdapter = new MenuItemAdapter(this,menuItemList);

        System.out.println(menuItemList.size());
        menuItemListView.setAdapter(menuItemAdapter);
    }
}
