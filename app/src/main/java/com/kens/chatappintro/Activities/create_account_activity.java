package com.kens.chatappintro.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kens.chatappintro.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class create_account_activity extends AppCompatActivity {

    private EditText emailAddressET;
    private EditText passwordET;
    private EditText usernameET;
    private Button signupBTN;
    private ProgressBar progressBar;

    private AlertDialog.Builder alertDialogBulider;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_activity);

        emailAddressET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        usernameET = (EditText) findViewById(R.id.usernameET);
        signupBTN = (Button) findViewById(R.id.signupBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        alertDialogBulider = new AlertDialog.Builder(create_account_activity.this);

        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                createAccount();
            }
        });

        progressBar.setVisibility(View.INVISIBLE);

    }

    private void createAccount(){

        final String email = emailAddressET.getText().toString();
        final String username = usernameET.getText().toString();
        final String password = passwordET.getText().toString();

        if(email.equals("") || username.equals("") || password.equals("")) {

            alertDialogBulider.setTitle("Field required");
            alertDialogBulider.setMessage("Please complete the form.");


            alertDialogBulider.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

            alertDialog = alertDialogBulider.create();
            alertDialog.show();
        } else {
            //make the user on parer server
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            //disable the edit on field, but don't know why
            usernameET.setEnabled(false);
            emailAddressET.setEnabled(false);
            passwordET.setEnabled(false);

            //set custom property if you want
            //user.put("city", "HongKong");

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null) {
                        Toast.makeText(getApplicationContext(), "New user created", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);

                        //log them in right the way
                        logUserIn(username, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "User creation fail", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void logUserIn(String username, String password) {
        if(!username.equals("") && !password.equals("")){
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e == null) {
                       //things all good
                        Toast.makeText(getApplicationContext(), "User in", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(create_account_activity.this, chat.class));
                    } else {

                        Toast.makeText(getApplicationContext(), "User login fail", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
