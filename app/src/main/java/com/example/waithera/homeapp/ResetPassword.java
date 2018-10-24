package com.example.waithera.homeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
private Button resetPassword;
private EditText resetText;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth=FirebaseAuth.getInstance();


        resetPassword=(Button)findViewById(R.id.resetButton);
        resetText=(EditText)findViewById(R.id.resetEmail);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = resetText.getText().toString();
                if(TextUtils. isEmpty(userEmail)){
                    Toast.makeText(ResetPassword.this,"Please write your valid email address first...",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>(){
                         @Override
                        public void onComplete(@NonNull Task<Void> task)
{
    if(task.isSuccessful())
    {
        Toast.makeText(ResetPassword.this,"Please check your email account... If you want to reset your password",Toast.LENGTH_SHORT).show();
    startActivity(new Intent(ResetPassword.this,MainActivity.class));
    }
    else
    {
        String message=task.getException().getMessage();
        Toast.makeText(ResetPassword.this,"Error occured" +message,Toast.LENGTH_SHORT).show();
    }
}
                    });
                }
            }
        });
    }
}
