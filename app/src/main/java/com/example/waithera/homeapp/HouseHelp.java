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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HouseHelp extends AppCompatActivity {
    private EditText workerName, workerNumber, workerLocation, workExperience, prevEmployerContact, charges;
    private Button submit;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference, mDatabaseUsers;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_help);


        workerName = (EditText) findViewById(R.id.workerName);
        workerNumber = (EditText) findViewById(R.id.workerNumber);
        workerLocation = (EditText) findViewById(R.id.workerLocation);
        workExperience = (EditText) findViewById(R.id.workExperience);
        prevEmployerContact = (EditText) findViewById(R.id.workerPrevEmployer);
        charges = (EditText) findViewById(R.id.charges);

//instantiating database reference and firebase auth
        databaseReference = database.getInstance().getReference().child("HousehelpDetails");
        mAuth=FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Workers").child(mCurrentUser.getUid());//reference to firebase database


        submit = (Button) findViewById(R.id.submit);
    }
    public void submitButtonClicked(View view) {
        final String workerN = workerName.getText().toString().trim();
        final String workerNo = workerNumber.getText().toString().trim();
        final String workerL = workerLocation.getText().toString().trim();
        final String workerE = workExperience.getText().toString().trim();
        final String prevEmp = prevEmployerContact.getText().toString().trim();
        final String charge = charges.getText().toString().trim();
        if (TextUtils.isEmpty(workerN)) {
            Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(workerNo)) {
            Toast.makeText(this, "Please enter your number", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(workerL)) {
            Toast.makeText(this, "Please enter your Location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(workerE)) {
            Toast.makeText(this, "Please enter your experience in detail or years of experience", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(prevEmp)) {
            Toast.makeText(this, "Please enter your previous employer contact", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(charge)) {
            Toast.makeText(this, "Please enter how much you charge", Toast.LENGTH_SHORT).show();
            return;
        }
        //when all fields are filled action to take is submit
        if(!TextUtils.isEmpty(workerN)&&!TextUtils.isEmpty(workerNo)&&!TextUtils.isEmpty(workerL)&&!TextUtils.isEmpty(workerE)&&!TextUtils.isEmpty(prevEmp)&&!TextUtils.isEmpty(charge)){
            Toast.makeText(this,"Submitting...",Toast.LENGTH_LONG).show();

            final DatabaseReference newPost=databaseReference.push();
            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                //how they shall ne
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newPost.child("fullname").setValue(workerN);
                    newPost.child("workernumber").setValue(workerNo);
                    newPost.child("location").setValue(workerL);
                    newPost.child("experience").setValue(workerE);
                    newPost.child("previousemployer").setValue(prevEmp);
                    newPost.child("charge").setValue(charge);
                    newPost.child("uid").setValue(mCurrentUser.getUid()); //to get current user uid
                    //aim to get the current user name of the user who has posted the above information
                    newPost.child("username").setValue(dataSnapshot.child("username").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Intent login=new Intent(HouseHelp.this,Login.class);
                                startActivity(login);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            Toast.makeText(HouseHelp.this,"Unable to upload",Toast.LENGTH_LONG).show();
        }


    }
}
