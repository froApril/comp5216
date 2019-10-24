package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;


import Beans.OrderItem;
import adapter.OrderItemAdapter;

public class GetOrderActivity extends AppCompatActivity{

    private String storeName = "KFC";
    private ListView orderItemListView;
    private OrderItemAdapter orderItemAdapter;
    ArrayList<OrderItem> orderItemList;
    private DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();

    public final int VIEW_ORDER_REQUEST_CODE = 555;
    public final int ADD_ITEM_REQUEST_CODE = 666;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_get_order);
        orderItemListView = findViewById(R.id.order_list);
        loadMenu(storeName);
        setupListViewListener();
    }

    public void loadMenu(String storename){
        mDatabase.child("users").child("store").child(storename).child("order")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        orderItemList = new ArrayList<OrderItem>();
                        for (DataSnapshot alert:dataSnapshot.getChildren()) {

                            Gson gson = new Gson();
                            String s = gson.toJson(alert.getValue());
                            System.out.println(s);
                            orderItemList.add(gson.fromJson(s,OrderItem.class));
                        }
                        setListView(orderItemList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void setListView(final ArrayList<OrderItem> orderItemList){
        orderItemAdapter = new OrderItemAdapter(this,orderItemList);

        System.out.println(orderItemList.size());
        orderItemListView.setAdapter(orderItemAdapter);
    }

    private void setupListViewListener() {
        orderItemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int
                    position, long rowId)
            {
                Log.i("MainActivity", "Long Clicked item " + position);
                AlertDialog.Builder builder = new AlertDialog.Builder(GetOrderActivity.this);
                builder.setTitle(R.string.dialog_complete_title)
                        .setMessage(R.string.dialog_complete_msg)
                        .setPositiveButton(R.string.yes, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        OrderItem clickedItem = orderItemAdapter.getItem(position);
                                        clickedItem.setOrderTime("Completed");
                                        orderItemAdapter.notifyDataSetChanged(); // Notify listView adapter to update the list
                                        saveToDatabase(clickedItem);
                                        recreate();
                                    }
                                })
                        .setNegativeButton(R.string.no, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // User cancelled the dialog
                                        // Nothing happens
                                    }
                                });
                builder.create().show();
                return true;
            }
        });

        orderItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderItem clickedItem = orderItemAdapter.getItem(position);
                String tableID = clickedItem.getTableID();
                String orderTime = clickedItem.getOrderTime();
                String orderItem = clickedItem.getOrderItem();
                double totalPrice = clickedItem.getTotalPrice();
                Log.i("MainActivity", "Clicked item " + position + ": " + clickedItem);
                Intent intent = new Intent(GetOrderActivity.this, OrderDetailActivity.class);
                if (intent != null) {
                    // put "extras" into the bundle for access in the edit activity
                    intent.putExtra("tableID", tableID);
                    intent.putExtra("orderTime", orderTime);
                    intent.putExtra("orderItem", orderItem);
                    intent.putExtra("totalPrice", totalPrice);
                    intent.putExtra("position", position);
                    // brings up the second activity
                    startActivityForResult(intent, VIEW_ORDER_REQUEST_CODE);
                    orderItemAdapter.notifyDataSetChanged();
                    recreate();
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIEW_ORDER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                recreate();
            }
        }
    }

    private void saveToDatabase(final OrderItem item)
    {
        //Use asynchronous task to run query on the background to avoid locking UI
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mDatabase.child("users").child("store").child(storeName)
                        .child("order").child(item.getTableID()).setValue(item);
                return null;
            }
        }.execute();
    }
}
