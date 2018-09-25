package com.example.waithera.homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CleanerView extends AppCompatActivity {
    //implementing search
    EditText search_edit_text;
    // RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> fullNameList;
    ArrayList<String> phoneNumberList;
    ArrayList<String> locationList;
    ArrayList<String> experienceList;
    ArrayList<String> employerList;
    ArrayList<String> chargeList;
    SearchAdapter searchAdapter;
    private RecyclerView mCleanerList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_view);
        mCleanerList = (RecyclerView) findViewById(R.id.plumber_list);
        mCleanerList.setHasFixedSize(true);
        mCleanerList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CleanerDetails");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent registerIntent = new Intent(CleanerView.this, Employer_signup.class);
                    registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(registerIntent);
                }
            }
        };
        //implementing search
        search_edit_text = (EditText) findViewById(R.id.searchPlumber);
        //recyclerView=(RecyclerView)findViewById(R.id.nanny_list);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fullNameList = new ArrayList<>();
        phoneNumberList = new ArrayList<>();
        locationList = new ArrayList<>();
        experienceList = new ArrayList<>();
        employerList = new ArrayList<>();
        chargeList = new ArrayList<>();
        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    fullNameList.clear();
                    phoneNumberList.clear();
                    locationList.clear();
                    experienceList.clear();
                    employerList.clear();
                    chargeList.clear();
                    mCleanerList.removeAllViews();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<PlumberClass, Plumber_view.PlumberViewHolder> FBRA = new FirebaseRecyclerAdapter<PlumberClass, Plumber_view.PlumberViewHolder>(
                PlumberClass.class,
                R.layout.plumber_row,
                Plumber_view.PlumberViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(Plumber_view.PlumberViewHolder viewHolder, PlumberClass model, int position) {
                viewHolder.setFullName(model.getFullname());
                viewHolder.setNumber(model.getWorkernumber());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setExperience(model.getExperience());
                viewHolder.setEmployer(model.getPreviousemployer());
                viewHolder.setCharges(model.getCharge());

            }
        };

        mCleanerList.setAdapter(FBRA);
    }

    //search
    private void setAdapter(final String searchedString) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullNameList.clear();
                phoneNumberList.clear();
                locationList.clear();
                experienceList.clear();
                employerList.clear();
                chargeList.clear();
                mCleanerList.removeAllViews();

                int counter = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String full_name = snapshot.child("username").getValue(String.class);
                    String phone_number = snapshot.child("workernumber").getValue(String.class);
                    String location = snapshot.child("location").getValue(String.class);
                    String experience = snapshot.child("experience").getValue(String.class);
                    String employer = snapshot.child("previousemployer").getValue(String.class);
                    String charge = snapshot.child("charge").getValue(String.class);

                    if (location.contains(searchedString)) {
                        fullNameList.add(full_name);
                        phoneNumberList.add(phone_number);
                        locationList.add(location);
                        experienceList.add(experience);
                        employerList.add(employer);
                        chargeList.add(charge);
                        counter++;
                    }
                    if (counter == 15)
                        break;

                }
                searchAdapter = new SearchAdapter(CleanerView.this, fullNameList, phoneNumberList, locationList, experienceList, employerList, chargeList);
                mCleanerList.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static class PlumberViewHolder extends RecyclerView.ViewHolder {
        public PlumberViewHolder(View itemView) {
            super(itemView);
            View mView = itemView;

        }

        public void setFullName(String fullname) {
            TextView post_name = (TextView) itemView.findViewById(R.id.textName);
            post_name.setText(fullname);
        }

        public void setNumber(String workernumber) {
            TextView post_number = (TextView) itemView.findViewById(R.id.textNumber);
            post_number.setText(workernumber);
        }

        public void setLocation(String location) {
            TextView post_location = (TextView) itemView.findViewById(R.id.textLocation);
            post_location.setText(location);
        }

        public void setExperience(String experience) {
            TextView post_experience = (TextView) itemView.findViewById(R.id.textExperience);
            post_experience.setText(experience);
        }

        public void setEmployer(String employer) {
            TextView post_employer = (TextView) itemView.findViewById(R.id.textEmployer);
            post_employer.setText(employer);
        }

        public void setCharges(String charge) {
            TextView post_charges = (TextView) itemView.findViewById(R.id.textCharges);
            post_charges.setText(charge);
        }

    }
}
