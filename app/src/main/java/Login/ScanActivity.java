package Login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import mobileproject.au.edu.sydney.comp5216.mobileproject.MainActivity;
import mobileproject.au.edu.sydney.comp5216.mobileproject.R;

public class ScanActivity extends AppCompatActivity {
    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private Button scanBtn;

    private static final int REQUEST_CODE_SCAN = 1007;



    private DatabaseReference mDatabase;
    // START declare_auth
    private FirebaseAuth mAuth;
    // END declare_auth
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailpassword);

        // Views
        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.detail);

        //Button
        scanBtn=findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator intentIntegrator = new IntentIntegrator(ScanActivity.this);
                // START SCAN
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: "  , Toast.LENGTH_LONG).show();
                String content = result.getContents();
                Intent intent = new Intent(ScanActivity.this, OrderActivity.class);
                intent.putExtra("uid" ,content);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {

        mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                        user.getEmail(), user.isEmailVerified()));
        mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
    }




}
