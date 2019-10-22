package Login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import mobileproject.au.edu.sydney.comp5216.mobileproject.R;

public class ForgotPassword extends BaseActivity {

    private FirebaseAuth mAuth;

    private Button mSendLinkButton;

    private EditText mEmailField;


    private String mPendingEmail;
    private String mEmailLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mSendLinkButton = findViewById(R.id.passwordlessSendEmailButton);

        mEmailField = findViewById(R.id.fieldEmail);
        mSendLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailField.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    mEmailField.setError("Email must not be empty.");
                    return;
                }else{
                    sendPasswordReset(email);
                }
            }
        });
    }

    public void sendPasswordReset(String email) {
        // [START send_password_reset]
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("ForgotPassword", "Email sent.");
                        }else{
                            Log.d("ForgotPassword", "Failed to send email");
                        }
                    }
                });
        // [END send_password_reset]
    }

}
