package com.example.waithera.homeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
 private EditText userName;
 private EditText editTextPassword;
 private Button logiN;
 private TextView worker;
 private TextView employer,reset;

 private ProgressDialog progressDialog;

 private FirebaseAuth mfirebaseAuth;
 private DatabaseReference mdatabaseReference;

 private DatabaseReference databaseReference;


    RelativeLayout rellay1,rellay2;
    Handler handler=new Handler();
    Runnable runnable=new Runnable(){
        @Override
        public void run(){
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellay1=(RelativeLayout)findViewById(R.id.rellay1);
        rellay2=(RelativeLayout)findViewById(R.id.rellay2);

        handler.postDelayed(runnable,2000);//2000 is the timeout for splash screen

    //initialize firebase auth and database reference
        mfirebaseAuth=FirebaseAuth.getInstance();
        mdatabaseReference=FirebaseDatabase.getInstance().getReference().child("Workers");
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Employers");

       userName=(EditText)findViewById(R.id.username);
       editTextPassword=(EditText)findViewById(R.id.password);
        worker=(TextView)findViewById(R.id.worker_signup);
employer=(TextView)findViewById(R.id.employer_signup);
reset=(TextView)findViewById(R.id.forgotPassword);

        progressDialog=new ProgressDialog(this);

        logiN=(Button)findViewById(R.id.login);

        logiN.setOnClickListener(this);
        worker.setOnClickListener(this);
        employer.setOnClickListener(this);
        reset.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        if(v==logiN){
            userLogin();
        }
        if(v==worker){

            finish();
            Intent i=new Intent(this,worker_signup.class);
            startActivity(i);
        }
        if(v==employer){
            Intent i=new Intent(this,Employer_signup.class);
            startActivity(i);
        }
        if(v==reset){
            Intent i=new Intent(this,ResetPassword.class);
            startActivity(i);
        }
    }
    public void userLogin(){
        String email=userName.getText().toString().trim();
        String passWord=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter valid email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passWord)){
            Toast.makeText(this,"Please enter valid password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Login in please wait...");
        progressDialog.show();

        mfirebaseAuth.signInWithEmailAndPassword(email,passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            checkUserExists();
                            /*
                            if(!checkuserexists()){
                            checkusersexists
                            }
                             */
                        }
                        else{
                            String message=task.getException().getMessage();
                            Toast.makeText(MainActivity.this,"Error occured "+message,Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }
    public void checkUserExists(){
        final String user_id=mfirebaseAuth.getCurrentUser().getUid();
        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user_id)){
                    Intent loginIntent=new Intent(MainActivity.this,Login.class);
                    //loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if(dataSnapshot.hasChild(user_id)){
                   Intent loginWorker=new Intent(MainActivity.this,Login_employer.class);
                   startActivity(loginWorker);
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

finish();
    }
}
