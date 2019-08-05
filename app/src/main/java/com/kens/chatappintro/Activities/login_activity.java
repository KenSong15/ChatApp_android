package com.kens.chatappintro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kens.chatappintro.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;




public class login_activity extends AppCompatActivity {

    private EditText loginEdtL;
    private EditText pawEdtL;
    private Button regBtnL;
    private Button loginBtnL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        loginEdtL = (EditText) findViewById(R.id.loginUsernameET);
        pawEdtL = (EditText) findViewById(R.id.loginPassword);
        regBtnL = (Button) findViewById(R.id.loginRegBtn);
        loginBtnL = (Button) findViewById(R.id.loginBtn);

        regBtnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(login_activity.this, create_account_activity.class));
            }
        });

        loginBtnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserIn(loginEdtL.getText().toString(), pawEdtL.getText().toString());
            }
        });

    }

    public void logUserIn(final String username, String password) {
        if(!username.equals("") && !password.equals("")){
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e == null) {
                        //things all good
                        Toast.makeText(getApplicationContext(), "User: " + username + " in", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(login_activity.this, chat.class));
                    } else {

                        Toast.makeText(getApplicationContext(), "User login fail", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}

