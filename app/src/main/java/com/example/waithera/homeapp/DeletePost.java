package com.example.waithera.homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeletePost extends AppCompatActivity {
    private String post_key = "";
    private TextView firstName,middleName,surName,gender,ageWorker,workerNumber, workerLocation,ageCurrent, citizenShip,idNumber,workExperience,referee, prevEmployerContact,duration, charges;
private Button delete;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_post);

        mAuth=FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        post_key = getIntent().getExtras().getString("Postid");

        mDatabase=FirebaseDatabase.getInstance().getReference().child("CarpenterDetails").child(post_key);


        Toast.makeText(this, post_key, Toast.LENGTH_SHORT).show();
//        mDatabase= FirebaseDatabase.getInstance().getReference();

//        mDatabase.child("CarpenterDetails");

        mCurrentUser = mAuth.getCurrentUser();


        firstName = (TextView) findViewById(R.id.firstName);
        middleName=(TextView) findViewById(R.id.middleName);
        surName=(TextView) findViewById(R.id.surName);
        gender=(TextView) findViewById(R.id.gender);
        ageWorker=(TextView) findViewById(R.id.age);
        workerNumber = (TextView) findViewById(R.id.workerNumber);
        workerLocation = (TextView) findViewById(R.id.workerLocation);
        workExperience = (TextView) findViewById(R.id.workExperience);
        prevEmployerContact = (TextView) findViewById(R.id.workerPrevEmployer);
        charges = (TextView) findViewById(R.id.charges);
        //  ageN=(EditText)findViewById(R.id.age);
        citizenShip=(TextView) findViewById(R.id.citizenship);
        idNumber=(TextView) findViewById(R.id.idNumber);
        referee=(TextView) findViewById(R.id.referee);
        duration=(TextView) findViewById(R.id.duration);
        mAuth=FirebaseAuth.getInstance();
        delete=(Button)findViewById(R.id.delete);




        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String full_name=(String)dataSnapshot.child("firstname").getValue();
                Log.i("Tag",full_name);
                String middle_name=(String)dataSnapshot.child("middlename").getValue();
                String sur_name=(String) dataSnapshot.child("surname").getValue();
                String gen_der=(String) dataSnapshot.child("gender").getValue();
                String age=(String) dataSnapshot.child("age").getValue();
                String id_number=(String)dataSnapshot.child("idnumber").getValue();
                String citizenship=(String)dataSnapshot.child("citizenship").getValue();
                String phone_number=(String)dataSnapshot.child("workernumber").getValue();
                String location=(String)dataSnapshot.child("location").getValue();
                String experience=(String)dataSnapshot.child("experience").getValue();
                String employer=(String)dataSnapshot.child("previousemployer").getValue();
                String refree=(String) dataSnapshot.child("referee").getValue();
                String duratio=(String) dataSnapshot.child("duration").getValue();
                String charge=(String) dataSnapshot.child("charge").getValue();
               // String post_uid=(String) dataSnapshot.child("uid").getValue();


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
