package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Beans.Items;
import adapter.ItemsAdapter;

public class OrderActivity extends AppCompatActivity {
    private String storeName ;
    private String userName ;
    private String tableid = "table_";
    private String TAG = "Simon";
    private ListView itemsList;
    private ItemsAdapter itemsAdapter;
    ArrayList<Items> menuList;
    private DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        storeName = intent.getExtras().getString("storename");
        userName = intent.getExtras().getString("username");
        String table_id = intent.getExtras().getString("tableid");
        tableid = tableid+table_id;
        setAllCommentsList(storeName);
    }
    public void setAllCommentsList(String storename){
        mDatabase.child("restaurants").child(storename)
                .child("menu")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        menuList = new ArrayList<Items>();
                        for (DataSnapshot alert:dataSnapshot.getChildren()) {
                            Gson gson = new Gson();
                            String s = gson.toJson(alert.getValue());
                            menuList.add(gson.fromJson(s,Items.class));
                        }
                        setListView(menuList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
    Date date = new Date();
    String str = "dd-MM-yyyy hh:mm:ss";
    SimpleDateFormat format = new SimpleDateFormat(str);
    String time = format.format(date);

    private void setListView(final ArrayList<Items> menuList){
        itemsList = findViewById(R.id.list_item);
        itemsAdapter = new ItemsAdapter(this,menuList);
        itemsAdapter.setOnMyItemClickListener(new ItemsAdapter.OnMyItemClickListener() {
            @Override
            public void onBtnItemListener(TextView tv, int i, int num) {
                menuList.get(i).setNum(num);
            }
        });
        System.out.println(menuList.size());
        itemsList.setAdapter(itemsAdapter);
    }

    public void MakeOrder(View view){
        makeOrder(menuList,storeName);
        Toast.makeText(this, "Order Successful"  , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(OrderActivity.this,CommentActivity.class);
        intent.putExtra("storename",storeName);
        intent.putExtra("username",userName);
        startActivity(intent);
    }

    private void makeOrder(ArrayList<Items> order,String storeName){
        HashMap<String, Object> Makeorder = new HashMap<>();
        String s= "";
        Double price=0.0;
        for(int x = 0; x <order.size(); x++){

            if(order.get(x).getNum()>0){
                s = s + order.get(x).getTitle()+": "+order.get(x).getNum()+", ";
                price = price + order.get(x).getNum()*order.get(x).getPrice();
            }

        }
        Makeorder.put("orderItem", s);
        Makeorder.put("totalPrice", price);
        Makeorder.put("tableID", tableid);
        Makeorder.put("orderTime", time);

        mDatabase.child("users").child("store")
                .child(storeName).child("order").child(tableid)
                .setValue(Makeorder)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("ok!!","insert");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("problem", e + "");
                    }
                });
    }

}
