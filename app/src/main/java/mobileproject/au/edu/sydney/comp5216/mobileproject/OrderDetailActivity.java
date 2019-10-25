package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends Activity
{
    public int position=0;
    TextView tableIdView, orderTimeView, orderItemView, totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //populate the screen using the layout
        setContentView(R.layout.activity_order_detail);

        //Get the data from the main screen
        String tableID = getIntent().getStringExtra("tableID");
        String orderTime = getIntent().getStringExtra("orderTime");
        String orderItem = getIntent().getStringExtra("orderItem");
        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0);
        position = getIntent().getIntExtra("position",-1);

        // show original content in the text field
        tableIdView = findViewById(R.id.tableID);
        tableIdView.setText("TABLE ID: " + tableID);
        orderTimeView = findViewById(R.id.orderTime);
        orderTimeView.setText(orderTime);
        orderItemView = findViewById(R.id.orderItems);
        orderItemView.setText(orderItem);
        totalPriceView = findViewById(R.id.totalPrice);
        totalPriceView.setText("Total Price: $" + String.format("%.2f",totalPrice));
    }
}
