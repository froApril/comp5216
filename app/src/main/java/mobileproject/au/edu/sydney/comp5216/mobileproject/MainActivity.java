package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        Button manageMenuBtn = (Button) findViewById(R.id.toManageMenuBtn);
        Button orderBtn = (Button) findViewById(R.id.toOrderBtn);

        // Capture button clicks
        manageMenuBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        EditMenuActivity.class);
                startActivity(myIntent);
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        OrderActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
