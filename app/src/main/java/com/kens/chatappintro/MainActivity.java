package com.kens.chatappintro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button SignUpBtn;
    private Button LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignUpBtn = (Button) findViewById(R.id.SignupBtn);
        LoginBtn = (Button) findViewById(R.id.LoginBtn);

        SignUpBtn.setOnClickListener(this);
        LoginBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.LoginBtn:
                //we do login
                break;
            case R.id.SignupBtn:
                //take to sign up activity
                startActivity(new Intent(MainActivity.this, create_account_activity.class));
                break;
        }
    }
}
