package com.example.waithera.homeapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Waithera on 26/10/2018.
 */

public class MyDobTextDatePicker implements View.OnClickListener,DatePickerDialog.OnDateSetListener{
    EditText age;
    private int day;
    private int monthofYear;
    private int birthYear;
    private Context context;

    public MyDobTextDatePicker(Context context, int editTextviewID) {
        Activity act=(Activity)context;
        this.age=(EditText)act.findViewById(editTextviewID);
        this.age.setOnClickListener(this);
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
birthYear=year;
monthofYear=month;
day=dayOfMonth;
updateDisplay();

    }

    @Override
    public void onClick(View v) {
        Calendar calendar=Calendar.getInstance(TimeZone.getDefault());

DatePickerDialog dialog=new DatePickerDialog(context,this,
calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
       calendar.get(Calendar.DAY_OF_MONTH) );
dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

dialog.show();
    }
    public void updateDisplay(){
        Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
        age.setText(new StringBuilder()
        .append(day).append("/").append(monthofYear+1).append("/").append(birthYear).append(" "));

    }

public String age(){
        String aGe;
    Calendar calendar=Calendar.getInstance();
    int year=calendar.get(Calendar.YEAR);
    int ageC=-(birthYear-year);
    aGe=Integer.toString(ageC);
    return aGe;
}

}
