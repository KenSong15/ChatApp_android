package com.kens.chatappintro.Util;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class ChatApplication extends Application {

    public static final String APP_ID = "i2iF9hDMMqZ4dHW3Kr53WDvF7rRBJJZpIBgwVYnL";
    public static final String CLIENT_KEY = "HeaqLEtIOxECwWSV4hatDqZCc5G3MQWq8PMmo1lL";
    public static final String SERVER_URL = "https://parseapi.back4app.com";






    @Override
    public void onCreate() {
        super.onCreate();

        //initialized the Parse server
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APP_ID)
                .clientKey(CLIENT_KEY)
                .server(SERVER_URL)
                .build());






    }

    public void makeuser1(){
        ParseUser user = new ParseUser();
        user.setUsername("user1");
        user.setPassword("123456");
        user.setEmail("user1@test.com");

        user.put("phone", "123-456-7890");
        user.put("nationality", "china");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    //we are good
                    Toast.makeText(getApplicationContext(), "good!", Toast.LENGTH_SHORT).show();
                } else {
                    //something went wrong
                    Toast.makeText(getApplicationContext(), " NOT....", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void createObject() {


        ParseObject myNewObject = new ParseObject("Test");
        myNewObject.put("C1", "c1 value");
        myNewObject.put("C2", "c2 value");

        // Saves the new object.
        // Notice that the SaveCallback is totally optional!
        myNewObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                // Here you can handle errors, if thrown. Otherwise, "e" should be null
            }
        });
    }

    public void readObject() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MyCustomClassName");
        try {

            for(int i = 0; i < query.count(); i++){
                Log.d("Len", query.getClassName());
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // The query will search for a ParseObject, given its objectId.
        // When the query finishes running, it will invoke the GetCallback
        // with either the object, or the exception thrown
        query.getInBackground("EnQhzgLsIG", new GetCallback<ParseObject>() {
            public void done(ParseObject result, ParseException e) {
                if (e == null) {
                    Log.d("Ken result", result.toString());
                } else {
                    // something went wrong
                }
            }
        });
    }

}
