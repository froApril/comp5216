package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DAO.SignupDAO;

public class Signup extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        signUpSubmit();
    }

    private void signUpSubmit(){
        final EditText usernameEdit = findViewById(R.id.signup_username);
        final EditText passwordEdit = findViewById(R.id.signup_password);

        Button button = findViewById(R.id.signup_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(Signup.this,"Empty username"
                    ,Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(Signup.this,"Empty password"
                            ,Toast.LENGTH_SHORT).show();
                }
                else{
                    if(SignupDAO.checkSignup(username,password,Signup.this)){
                        Toast.makeText(Signup.this,"Sign up success"
                    ,Toast.LENGTH_SHORT).show();
                    }
                }
//                    Toast.makeText(Signup.this,"Sign up success"
//                    ,Toast.LENGTH_SHORT).show();

                    //jump to service activity
//                    Intent intent = new Intent(Signup.this,MainActivity.class);
//                    intent.putExtra("username",username.getText().toString());
//                    intent.putExtra("password", password.getText().toString());
//                    startActivity(intent);

            }
        });
    }
}
