package com.example.waithera.homeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import  android.text.format.DateFormat;

public class Chat extends AppCompatActivity {
private static int SIGN_IN_REQUEST_CODE=1;
private FirebaseListAdapter<ChatMessage> adapter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private String post_key = null;
    RelativeLayout activity_chat;
    FloatingActionButton fab;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_sign_out){
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>(){
            @Override
                    public void onComplete(@NonNull Task<Void> task){
                Snackbar.make(activity_chat,"You have been signed out..",Snackbar.LENGTH_SHORT).show();
           finish();
            }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if(requestCode== SIGN_IN_REQUEST_CODE){
        if(resultCode==RESULT_OK){
            Snackbar.make(activity_chat,"Successfully signed in. Welcome!",Snackbar.LENGTH_SHORT).show();
        displayChatMessage();
        }
        else{
            Snackbar.make(activity_chat,"We couldn't sign you in, Please try again later",Snackbar.LENGTH_SHORT).show();
            finish();
        }
    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("NannyDetails");
        post_key = getIntent().getExtras().getString("Postid");

        activity_chat=(RelativeLayout)findViewById(R.id.activity_chat);
        fab=(FloatingActionButton)findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input=(EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().child("Chat").push().setValue(new ChatMessage(input.getText().toString(),
                       firebaseAuth.getCurrentUser().getEmail()));
                input.setText("");
            }
        });

        //check if nor sign in

        if (firebaseAuth.getCurrentUser() == null) {
            finish();

            startActivity(new Intent(this, MainActivity.class));

        }
        else
        {
            Snackbar.make(activity_chat,"Welcome" +firebaseAuth.getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
            //Load content
            displayChatMessage();
        }




    }
    private void displayChatMessage(){
final ListView listOfMessage=(ListView)findViewById(R.id.list_of_message);
adapter=new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference()) {
    @Override
    protected void populateView(View v, ChatMessage model, int position) {
//Get references to the views of list_items
        final TextView messageText,messageUser,messageTime;
        messageText=(TextView)v.findViewById(R.id.message_text);
        messageUser=(TextView)v.findViewById(R.id.message_user);
        messageTime=(TextView)v.findViewById(R.id.message_time);


        //get the user who sent the post
        String post_key = getIntent().getExtras().getString("Postid");


    messageText.setText(model.getMessageText());
    messageUser.setText(model.getMessgeUser());
    messageTime.setText(DateFormat.format("dd-mm-yyyy (HH:mm:ss)",model.getMessageTime()));
    }
};
listOfMessage.setAdapter(adapter);
    }
}
