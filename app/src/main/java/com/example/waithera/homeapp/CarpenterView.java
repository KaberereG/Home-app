package com.example.waithera.homeapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CarpenterView extends AppCompatActivity {
    private static final int CALL_PERMISSION_CODE = 23;
    private RecyclerView mCarpenterList;
    private DatabaseReference mDatabase;

    //implementing search
    EditText search_edit_text;
    // RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> fullNameList;
    ArrayList<String>middleNameList;
    ArrayList<String>surNameList;
    ArrayList<String>genderList;
    ArrayList<String>ageList;
    ArrayList<String>idNumberList;
    ArrayList<String>citizenshipList;
    ArrayList<String> phoneNumberList;
    ArrayList<String> locationList;
    ArrayList<String> experienceList;
    ArrayList<String> employerList;
    ArrayList<String> refereeList;
    ArrayList<String> chargeList;
    SearchAdapter searchAdapter;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpenter_view);

        mCarpenterList=(RecyclerView)findViewById(R.id.plumber_list);
        mCarpenterList.setHasFixedSize(true);
        mCarpenterList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase= FirebaseDatabase.getInstance().getReference().child("CarpenterDetails");
        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Intent registerIntent= new Intent(CarpenterView.this,Employer_signup.class);
                    registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(registerIntent);
                }
            }
        };
        //implementing search
        search_edit_text=(EditText) findViewById(R.id.searchPlumber);
        //recyclerView=(RecyclerView)findViewById(R.id.nanny_list);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fullNameList=new ArrayList<>();
        middleNameList=new ArrayList<>();
        surNameList=new ArrayList<>();
        genderList=new ArrayList<>();
        ageList=new ArrayList<>();
        idNumberList=new ArrayList<>();
        citizenshipList=new ArrayList<>();
        phoneNumberList=new ArrayList<>();
        locationList=new ArrayList<>();
        experienceList=new ArrayList<>();
        employerList=new ArrayList<>();
        refereeList=new ArrayList<>();
        chargeList=new ArrayList<>();
        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }else {
                    fullNameList.clear();
                    middleNameList.clear();
                    surNameList.clear();
                    genderList.clear();
                    ageList.clear();
                    idNumberList.clear();
                    citizenshipList.clear();
                    phoneNumberList.clear();
                    locationList.clear();
                    experienceList.clear();
                    employerList.clear();
                    refereeList.clear();
                    chargeList.clear();
                    mCarpenterList.removeAllViews();
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<PlumberClass,Plumber_view.PlumberViewHolder> FBRA =new FirebaseRecyclerAdapter<PlumberClass, Plumber_view.PlumberViewHolder>(
                PlumberClass.class,
                R.layout.plumber_row,
                Plumber_view.PlumberViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(Plumber_view.PlumberViewHolder viewHolder, final PlumberClass model, int position) {
                viewHolder.setFirstName(model.getFirstname());
                viewHolder.setMiddleName(model.getMiddlename());
              viewHolder.setSurName(model.getSurname());
              viewHolder.setGender(model.getGender());
              viewHolder.setAge(model.getAge());
              viewHolder.setIdNumber(model.getIdNumber());
              viewHolder.setCitizenship(model.getCitizenship());
                viewHolder.setNumber(model.getWorkernumber());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setExperience(model.getExperience());
                viewHolder.setEmployer(model.getPreviousemployer());
                viewHolder.setReferee(model.getReferee());
                viewHolder.setCharges(model.getCharge());
                final String address = model.getWorkernumber();
                viewHolder.clickSMS(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("address", address);
                        startActivity(smsIntent);
                    }
                });

                viewHolder.clickCall(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tel = "tel:" + model.getWorkernumber();
                        Intent i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse(tel));
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }else {
                            ActivityCompat.requestPermissions(CarpenterView.this,new String[]{android.Manifest.permission.CALL_PHONE},CALL_PERMISSION_CODE);
                        }
                        startActivity(i);
                    }
                });

            }
        };

        mCarpenterList.setAdapter(FBRA);
    }

//    public static class PlumberViewHolder extends RecyclerView.ViewHolder{
//        public PlumberViewHolder(View itemView){
//            super(itemView);
//            View mView=itemView;
//
//        }
//
//        public void setFullName(String fullname){
//            TextView post_name=(TextView)itemView.findViewById(R.id.textName);
//            post_name.setText(fullname);
//        }
//        public void setMiddleName(String middlename){
//            TextView post_name=(TextView)itemView.findViewById(R.id.textMiddleName);
//            post_name.setText(middlename);
//        }
//        public void setSurName(String surname){
//            TextView post_name=(TextView)itemView.findViewById(R.id.textSurName);
//            post_name.setText(surname);
//        }
//        public void setAge(String age){
//            TextView post_name=(TextView)itemView.findViewById(R.id.textAge);
//            post_name.setText(age);
//        }
//        public void setIdNumber(String idnumber){
//            TextView post_name=(TextView)itemView.findViewById(R.id.textId);
//            post_name.setText(idnumber);
//        }
//        public void setCitizenship(String citizenship){
//            TextView post_name=(TextView)itemView.findViewById(R.id.textCitizenship);
//            post_name.setText(citizenship);
//        }
//        public void setNumber(String workernumber ){
//            TextView post_number=(TextView)itemView.findViewById(R.id.textNumber);
//            post_number.setText(workernumber);
//        }
//        public void setLocation(String location){
//            TextView post_location=(TextView)itemView.findViewById(R.id.textLocation);
//            post_location.setText(location);
//        }
//        public void setExperience(String experience){
//            TextView post_experience=(TextView)itemView.findViewById(R.id.textExperience);
//            post_experience.setText(experience);
//        }
//        public void setEmployer(String employer){
//            TextView post_employer=(TextView)itemView.findViewById(R.id.textEmployer);
//            post_employer.setText(employer);
//        }
//        public void setReferee(String referee){
//            TextView post_name=(TextView)itemView.findViewById(R.id.textReferee);
//            post_name.setText(referee);
//        }
//        public void setCharges(String charge){
//            TextView post_charges=(TextView)itemView.findViewById(R.id.textCharges);
//            post_charges.setText(charge);
//        }

  //  }
    //search
    private void setAdapter(final String searchedString){
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullNameList.clear();
                middleNameList.clear();
                surNameList.clear();
                genderList.clear();
                ageList.clear();
                idNumberList.clear();
                citizenshipList.clear();
                phoneNumberList.clear();
                locationList.clear();
                experienceList.clear();
                employerList.clear();
                refereeList.clear();
                chargeList.clear();
                mCarpenterList.removeAllViews();

                int counter=0;

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String full_name=snapshot.child("firstname").getValue(String.class);
                    String middle_name=snapshot.child("middlename").getValue(String.class);
                    String sur_name=snapshot.child("surname").getValue(String.class);
                    String gen_der=snapshot.child("gender").getValue(String.class);
                    String age=snapshot.child("age").getValue(String.class);
                    String id_number=snapshot.child("idnumber").getValue(String.class);
                    String citizenship=snapshot.child("citizenship").getValue(String.class);
                    String phone_number=snapshot.child("workernumber").getValue(String.class);
                    String location=snapshot.child("location").getValue(String.class);
                    String experience=snapshot.child("experience").getValue(String.class);
                    String employer=snapshot.child("previousemployer").getValue(String.class);
                    String referee=snapshot.child("referee").getValue(String.class);
                    String charge=snapshot.child("charge").getValue(String.class);

                    if(location.contains(searchedString)){
                        fullNameList.add(full_name);
                        middleNameList.add(middle_name);
                        surNameList.add(sur_name);
                        genderList.add(gen_der);
                        ageList.add(age);
                        idNumberList.add(id_number);
                        citizenshipList.add(citizenship);
                        phoneNumberList.add(phone_number);
                        locationList.add(location);
                        experienceList.add(experience);
                        employerList.add(employer);
                        refereeList.add(referee);
                        chargeList.add(charge);
                        counter++;
                    }
                    if(counter==15)
                        break;

                }
                searchAdapter=new SearchAdapter(CarpenterView.this,fullNameList,middleNameList,surNameList,genderList,ageList,idNumberList,citizenshipList,phoneNumberList,locationList,experienceList,employerList,refereeList,chargeList);
                mCarpenterList.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
