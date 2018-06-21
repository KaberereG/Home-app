package com.example.waithera.homeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CleanerView extends AppCompatActivity {
    private RecyclerView mCleanerList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_view);
        mCleanerList=(RecyclerView)findViewById(R.id.plumber_list);
        mCleanerList.setHasFixedSize(true);
        mCleanerList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase= FirebaseDatabase.getInstance().getReference().child("CleanerDetails");
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

    public static class PlumberViewHolder extends RecyclerView.ViewHolder{
        public PlumberViewHolder(View itemView){
            super(itemView);
            View mView=itemView;

        }

        public void setFullName(String fullname){
            TextView post_name=(TextView)itemView.findViewById(R.id.textName);
            post_name.setText(fullname);
        }
        public void setNumber(String workernumber ){
            TextView post_number=(TextView)itemView.findViewById(R.id.textNumber);
            post_number.setText(workernumber);
        }
        public void setLocation(String location){
            TextView post_location=(TextView)itemView.findViewById(R.id.textLocation);
            post_location.setText(location);
        }
        public void setExperience(String experience){
            TextView post_experience=(TextView)itemView.findViewById(R.id.textExperience);
            post_experience.setText(experience);
        }
        public void setEmployer(String employer){
            TextView post_employer=(TextView)itemView.findViewById(R.id.textEmployer);
            post_employer.setText(employer);
        }
        public void setCharges(String charge){
            TextView post_charges=(TextView)itemView.findViewById(R.id.textCharges);
            post_charges.setText(charge);
        }

    }
}
