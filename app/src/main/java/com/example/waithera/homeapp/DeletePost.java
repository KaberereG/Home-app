package com.example.waithera.homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeletePost extends AppCompatActivity {
    private String post_key = null;
    private EditText firstName,middleName,surName,gender,ageWorker,workerNumber, workerLocation,ageCurrent, citizenShip,idNumber,workExperience,referee, prevEmployerContact,duration, charges;
private Button delete;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_post);

        post_key = getIntent().getExtras().getString("Postid");
        mDatabase= FirebaseDatabase.getInstance().getReference().child("CarpenterDetails");

        firstName = (EditText) findViewById(R.id.firstName);
        middleName=(EditText)findViewById(R.id.middleName);
        surName=(EditText)findViewById(R.id.surName);
        gender=(EditText)findViewById(R.id.gender);
        ageWorker=(EditText)findViewById(R.id.age);
        workerNumber = (EditText) findViewById(R.id.workerNumber);
        workerLocation = (EditText) findViewById(R.id.workerLocation);
        workExperience = (EditText) findViewById(R.id.workExperience);
        prevEmployerContact = (EditText) findViewById(R.id.workerPrevEmployer);
        charges = (EditText) findViewById(R.id.charges);
        //  ageN=(EditText)findViewById(R.id.age);
        citizenShip=(EditText)findViewById(R.id.citizenship);
        idNumber=(EditText)findViewById(R.id.idNumber);
        referee=(EditText)findViewById(R.id.referee);
        duration=(EditText)findViewById(R.id.duration);
        mAuth=FirebaseAuth.getInstance();
        delete=(Button)findViewById(R.id.delete);

        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String full_name=dataSnapshot.child("firstname").getValue(String.class);
                String middle_name=dataSnapshot.child("middlename").getValue(String.class);
                String sur_name=dataSnapshot.child("surname").getValue(String.class);
                String gen_der=dataSnapshot.child("gender").getValue(String.class);
                String age=dataSnapshot.child("age").getValue(String.class);
                String id_number=dataSnapshot.child("idnumber").getValue(String.class);
                String citizenship=dataSnapshot.child("citizenship").getValue(String.class);
                String phone_number=dataSnapshot.child("workernumber").getValue(String.class);
                String location=dataSnapshot.child("location").getValue(String.class);
                String experience=dataSnapshot.child("experience").getValue(String.class);
                String employer=dataSnapshot.child("previousemployer").getValue(String.class);
                String refree=dataSnapshot.child("referee").getValue(String.class);
                String duratio=dataSnapshot.child("duration").getValue(String.class);
                String charge=dataSnapshot.child("charge").getValue(String.class);
                String post_uid=(String) dataSnapshot.child("uid").getValue();


                firstName.setText(full_name);
                middleName.setText(middle_name);
                surName.setText(sur_name);
                gender.setText(gen_der);
                ageWorker.setText(age);
                idNumber.setText(id_number);
                citizenShip.setText(citizenship);
                workerNumber.setText(phone_number);
                workerLocation.setText(location);
                workExperience.setText(experience);
                prevEmployerContact.setText(employer);
                referee.setText(refree);
                duration.setText(duratio);
                charges.setText(charge);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void deleteButtonClicked(View view){
        mDatabase.child(post_key).removeValue();

        Intent post=new Intent(DeletePost.this,Carpenter.class);
        startActivity(post);
    }
}
