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
    ArrayList<String>durationList;
    ArrayList<String> chargesList;

    class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView fullName,middleName,surName,gender,age,idNumber,citizenship,phoneNumber,location,experience,employer,referee,duration,charges;
    public SearchViewHolder(View itemView){
        super(itemView);
        fullName=(TextView)itemView.findViewById(R.id.textFirstName);
        middleName=(TextView)itemView.findViewById(R.id.textMiddleName);
        surName=(TextView)itemView.findViewById(R.id.textSurName);
        gender=(TextView)itemView.findViewById(R.id.textGender);
        age=(TextView)itemView.findViewById(R.id.textAge);
        idNumber=(TextView)itemView.findViewById(R.id.textIdNumber);
        citizenship=(TextView)itemView.findViewById(R.id.textCitizenship);
        phoneNumber=(TextView)itemView.findViewById(R.id.textNumber);
        location=(TextView)itemView.findViewById(R.id.textLocation);
        experience=(TextView)itemView.findViewById(R.id.textExperience);
        employer=(TextView)itemView.findViewById(R.id.textEmployer);
        referee=(TextView)itemView.findViewById(R.id.textReferee);
        duration=(TextView)itemView.findViewById(R.id.textDuration);
        charges=(TextView)itemView.findViewById(R.id.textCharges);

    }
    }
    public SearchAdapter(Context context, ArrayList<String> fullNameList,ArrayList<String> middleNameList,ArrayList<String> surNameList,ArrayList<String>genderList,  ArrayList<String>ageList,  ArrayList<String>idNumberList,  ArrayList<String>citizenshipList, ArrayList<String> phoneNumberList, ArrayList<String> locationList, ArrayList<String> experienceList, ArrayList<String> employerList,ArrayList<String> refereeList,ArrayList<String> durationList, ArrayList<String> chargesList){
      this.context=context;
      this.fullNameList=fullNameList;
      this.middleNameList=middleNameList;
      this.surNameList=surNameList;
      this.genderList=genderList;
      this.ageList=ageList;
      this.idNumberList=idNumberList;
      this.citizenshipList=citizenshipList;
      this.phoneNumberList=phoneNumberList;
      this.locationList=locationList;
      this.experienceList=experienceList;
      this.employerList=employerList;
      this.refereeList=refereeList;
      this.durationList=durationList;
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
        holder.middleName.setText(middleNameList.get(position));
        holder.surName.setText(surNameList.get(position));
        holder.gender.setText(genderList.get(position));
        holder.age.setText(ageList.get(position));
        holder.idNumber.setText(idNumberList.get(position));
        holder.citizenship.setText(citizenshipList.get(position));
        holder.phoneNumber.setText(phoneNumberList.get(position));
        holder.location.setText(locationList.get(position));
        holder.experience.setText(experienceList.get(position));
        holder.employer.setText(employerList.get(position));
        holder.referee.setText(refereeList.get(position));
        holder.duration.setText(durationList.get(position));
        holder.charges.setText(chargesList.get(position));
    }
    @Override
    public int getItemCount() {
        return locationList.size();
    }
}
