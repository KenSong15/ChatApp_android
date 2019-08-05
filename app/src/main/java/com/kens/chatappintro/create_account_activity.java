package com.kens.chatappintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class create_account_activity extends AppCompatActivity {

    private EditText emailAddressET;
    private EditText passwordET;
    private EditText usernameET;
    private Button signupBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_activity);

        emailAddressET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        usernameET = (EditText) findViewById(R.id.usernameET);
        signupBTN = (Button) findViewById(R.id.signupBtn);
    }

    private void createAccount(){

    }
}
