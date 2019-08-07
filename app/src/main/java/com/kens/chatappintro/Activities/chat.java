package com.kens.chatappintro.Activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kens.chatappintro.Model.Message;
import com.kens.chatappintro.R;
import com.kens.chatappintro.data.ChatAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class chat extends AppCompatActivity {

    private EditText messageEt;
    private Button sendBtn;
    public static final String USER_ID_KEY = "userId";
    private String currentUserId;
    private ListView listView;
    private List<com.kens.chatappintro.Model.Message> mMessages;
    private ChatAdapter mAdapter;
    private Handler handler = new Handler();    //for updating app periodically,but not a good way

    private static final int MAX_CHAT_MSG_SHOW = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getCurrentUser();

        handler.postDelayed(runnable, 100);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            refreshMessages();
            handler.postDelayed(this, 100);
        }
    };

    private void getCurrentUser(){
        currentUserId = ParseUser.getCurrentUser().getObjectId();
        messagePosting();
    }

    private void messagePosting(){
        messageEt = (EditText) findViewById(R.id.etMessage);
        sendBtn = (Button) findViewById(R.id.ButtonSend);
        listView = (ListView) findViewById(R.id.listView_chat);
        mMessages = new ArrayList<Message>();
        mAdapter = new ChatAdapter(chat.this, currentUserId,mMessages);
        listView.setAdapter(mAdapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!messageEt.getText().toString().equals("")){
                    Message msg = new Message();
                    msg.setUserId(currentUserId);
                    msg.setBody(messageEt.getText().toString());
                    msg.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            receiveMessage();
                        }
                    });
                    messageEt.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Empty message!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void receiveMessage(){
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        query.setLimit(MAX_CHAT_MSG_SHOW);
        query.orderByAscending("createAt");

        query.findInBackground(new FindCallback<Message>() {
            @Override
            public void done(List<Message> objects, ParseException e) {
                if(e == null) {
                    mMessages.clear();
                    mMessages.addAll(objects);
                    mAdapter.notifyDataSetChanged();

                    listView.invalidate();  //allows for the list view to be redraw
                } else {
                    Log.e("receiving message error: ", e.getMessage());
                }
            }
        });
    }

    //only receive message from other
    private void refreshMessages(){
        receiveMessage();
    }
}
