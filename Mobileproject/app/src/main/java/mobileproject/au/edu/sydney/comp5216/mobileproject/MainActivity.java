package mobileproject.au.edu.sydney.comp5216.mobileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

import DAO.LoginDAO;
import databaseBeans.UserBean;

public class MainActivity extends AppCompatActivity {

    //the username and password typed in
    String username;
    String password;
    EditText usernameEdit;
    EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addClickListener();

    }

    private void addClickListener(){
        Button login = findViewById(R.id.login_button);
        Button singup = findViewById(R.id.signup_button);
        usernameEdit = findViewById(R.id.login_account);
        passwordEdit = findViewById(R.id.login_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEdit.getText().toString();
                password = passwordEdit.getText().toString();
                Log.e("username",username);
                Log.e("password",password);
                if(LoginDAO.loginCheck(username,password)) {
                    Toast.makeText(MainActivity.this
                            ,"login successs"
                            ,Toast.LENGTH_SHORT).show();
                    // todo
                }
                else{
                    Toast.makeText(MainActivity.this
                            ,"login fail"
                            ,Toast.LENGTH_SHORT).show();
                }

            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Signup.class);
                startActivity(intent);

            }
        });
    }


}
