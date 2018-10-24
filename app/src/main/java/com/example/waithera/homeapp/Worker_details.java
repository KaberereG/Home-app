package com.example.waithera.homeapp;

import android.content.Intent;
import android.support.annotation.NonNull;import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class Worker_details extends AppCompatActivity{
    private EditText workerName, middleName,surName,ageN, citizenship,idNumber,workerNumber, workerLocation, workExperience, prevEmployerContact,referee, charges;
    private Button submit;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference, mDatabaseUsers;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);

        //references to edittexts

        workerName = (EditText) findViewById(R.id.firstName);
        middleName=(EditText)findViewById(R.id.middleName);
        surName=(EditText)findViewById(R.id.surName);
        workerNumber = (EditText) findViewById(R.id.workerNumber);
        workerLocation = (EditText) findViewById(R.id.workerLocation);
        workExperience = (EditText) findViewById(R.id.workExperience);
        prevEmployerContact = (EditText) findViewById(R.id.workerPrevEmployer);
        charges = (EditText) findViewById(R.id.charges);
        ageN=(EditText)findViewById(R.id.age);
        citizenship=(EditText)findViewById(R.id.citizenship);
        idNumber=(EditText)findViewById(R.id.idNumber);
        referee=(EditText)findViewById(R.id.referee);


        //spinner
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> myAdaper=new ArrayAdapter<String>(Worker_details.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));

        myAdaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdaper);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        switch (position){
//            case 0:
//             String male=String.valueOf(spinner.getSelectedItem());
//                break;
//            case 1:
//                String female=String.valueOf(spinner.getSelectedItem());
//                break;
//            case 2:
//                String other=String.valueOf(spinner.getSelectedItem());
//                break;
//        }
                String selected= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//instantiating database reference and firebase auth
        databaseReference = database.getInstance().getReference().child("NannyDetails");
      mAuth=FirebaseAuth.getInstance();
       mCurrentUser = mAuth.getCurrentUser();

       mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Workers").child(mCurrentUser.getUid());//reference to firebase database


        submit = (Button) findViewById(R.id.submit);


    }
//action when submit button is clicked

    public void submitButtonClicked(View view) {
            final String workerN = workerName.getText().toString().trim();
        final String middleN=middleName.getText().toString().trim();
        final String surN=surName.getText().toString().trim();
        final String gender=spinner.getSelectedItem().toString().trim();
        final String age=ageN.getText().toString().trim();
        final String idN=idNumber.getText().toString().trim();
        final String citizenS=citizenship.getText().toString().trim();
            final String workerNo = workerNumber.getText().toString().trim();
            final String workerL = workerLocation.getText().toString().trim();
            final String workerE = workExperience.getText().toString().trim();
            final String prevEmp = prevEmployerContact.getText().toString().trim();
        final String ref=referee.getText().toString().trim();
            final String charge = charges.getText().toString().trim();
            if (TextUtils.isEmpty(workerN)) {
                Toast.makeText(this, "Please enter your first name", Toast.LENGTH_SHORT).show();
                return;
            }
        if (TextUtils.isEmpty(middleN)) {
            Toast.makeText(this, "Please enter your middle name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(surN)) {
            Toast.makeText(this, "Please enter your surname", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Please enter your gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(idN)) {
            Toast.makeText(this, "Please enter your Id Number or Passport Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(citizenS)){
            Toast.makeText(this, "Please enter your citizenship", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "Please enter your referee", Toast.LENGTH_SHORT).show();
                return;
            }
        if (TextUtils.isEmpty(ref)){
            Toast.makeText(this, "Please enter your second referee", Toast.LENGTH_SHORT).show();
            return;
        }
            if (TextUtils.isEmpty(charge)) {
                Toast.makeText(this, "Please enter how much you charge", Toast.LENGTH_SHORT).show();
                return;
            }
            //when all fields are filled action to take is submit
        if(!TextUtils.isEmpty(workerN)&&!TextUtils.isEmpty(middleN)&&!TextUtils.isEmpty(surN)&&!TextUtils.isEmpty(gender)&&!TextUtils.isEmpty(workerNo)&&!TextUtils.isEmpty(workerL)&&!TextUtils.isEmpty(workerE)&&!TextUtils.isEmpty(prevEmp)&&!TextUtils.isEmpty(charge)&&!TextUtils.isEmpty(age)&&!TextUtils.isEmpty(citizenS)&&!TextUtils.isEmpty(idN)&&!TextUtils.isEmpty(ref)){
                Toast.makeText(this,"Submitting...",Toast.LENGTH_LONG).show();

                final DatabaseReference newPost=databaseReference.push();
                 mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                     //how they shall ne
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                          newPost.child("firstname").setValue(workerN);
                         newPost.child("middlename").setValue(middleN);
                         newPost.child("surname").setValue(surN);
                         newPost.child("gender").setValue(gender);
                         newPost.child("age").setValue(age);
                         newPost.child("idnumber").setValue(idN);
                         newPost.child("citizenship").setValue(citizenS);
                          newPost.child("workernumber").setValue(workerNo);
                          newPost.child("location").setValue(workerL);
                          newPost.child("experience").setValue(workerE);
                          newPost.child("previousemployer").setValue(prevEmp);
                         newPost.child("referee").setValue(ref);
                          newPost.child("charge").setValue(charge);
                          newPost.child("uid").setValue(mCurrentUser.getUid()); //to get current user uid
                         //aim to get the current user name of the user who has posted the above information
                          newPost.child("username").setValue(dataSnapshot.child("username").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {

                                  if(task.isSuccessful()){
                                      Intent login=new Intent(Worker_details.this,Chat.class);
                                      startActivity(login);
                                  }
                                  else{
                                      String message=task.getException().getMessage();
                                      Toast.makeText(Worker_details.this,"Error occured "+message,Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Worker_details.this,"Unable to upload",Toast.LENGTH_LONG).show();
            }


    }
}
