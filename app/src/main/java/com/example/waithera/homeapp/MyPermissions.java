package com.example.waithera.homeapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Waithera on 28/09/2018.
 */

public class MyPermissions {


    public static boolean isCallAllowed(Activity activity){
        int result = ContextCompat.checkSelfPermission(activity,Manifest.permission.CALL_PHONE);

        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;

    }

   public static void requestCallPermission(Activity activity,int CALL_PERMISSION_CODE){
       if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)){

       }
       ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION_CODE);
   }
}
