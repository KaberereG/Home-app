package com.example.waithera.homeapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Waithera on 06/09/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> fullNameList;
    ArrayList<String> phoneNumberList;
    ArrayList<String> locationList;
    ArrayList<String> experienceList;
    ArrayList<String> employerList;
    ArrayList<String> chargesList;

    class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView fullName,phoneNumber,location,experience,employer,charges;
    public SearchViewHolder(View itemView){
        super(itemView);
        fullName=(TextView)itemView.findViewById(R.id.textName);
        phoneNumber=(TextView)itemView.findViewById(R.id.textNumber);
        location=(TextView)itemView.findViewById(R.id.textLocation);
        experience=(TextView)itemView.findViewById(R.id.textExperience);
        employer=(TextView)itemView.findViewById(R.id.textEmployer);
        charges=(TextView)itemView.findViewById(R.id.textCharges);

    }
    }
    public SearchAdapter(Context context, ArrayList<String> fullNameList, ArrayList<String> phoneNumberList, ArrayList<String> locationList, ArrayList<String> experienceList, ArrayList<String> employerList, ArrayList<String> chargesList){
      this.context=context;
      this.fullNameList=fullNameList;
      this.phoneNumberList=phoneNumberList;
      this.locationList=locationList;
      this.experienceList=experienceList;
      this.employerList=employerList;
      this.chargesList=chargesList;
    }
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.plumber_row,parent,false);

        return new SearchAdapter.SearchViewHolder(view);
    }
    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position){
        holder.fullName.setText(fullNameList.get(position));
        holder.phoneNumber.setText(phoneNumberList.get(position));
        holder.location.setText(locationList.get(position));
        holder.experience.setText(experienceList.get(position));
        holder.employer.setText(employerList.get(position));
        holder.charges.setText(chargesList.get(position));
    }
    @Override
    public int getItemCount() {
        return locationList.size();
    }
}
