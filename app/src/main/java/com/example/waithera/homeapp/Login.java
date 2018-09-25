package com.example.waithera.homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button logout, nanny, plumber, carpenter, houseHelp, cleaner;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();

            startActivity(new Intent(this, MainActivity.class));

        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        logout = (Button) findViewById(R.id.logout);
        nanny = (Button) findViewById(R.id.nannys);
        plumber = (Button) findViewById(R.id.plumber);
        carpenter = (Button) findViewById(R.id.carpenter);
        houseHelp = (Button) findViewById(R.id.househelp);
        cleaner = (Button) findViewById(R.id.cleaner);

        logout.setOnClickListener(this);
        nanny.setOnClickListener(this);
        plumber.setOnClickListener(this);
        carpenter.setOnClickListener(this);
        houseHelp.setOnClickListener(this);
        cleaner.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == logout) {
            firebaseAuth.signOut();
            finish();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        if (view == nanny) {
            Intent f = new Intent(this, Worker_details.class);
            startActivity(f);
        }
        if (view == plumber) {
            Intent p = new Intent(this, Plumber.class);
            startActivity(p);
        }
        if (view == carpenter) {
            Intent c = new Intent(this, Carpenter.class);
            startActivity(c);
        }
        if (view == houseHelp) {
            Intent h = new Intent(this, HouseHelp.class);
            startActivity(h);
        }
        if (view == cleaner) {
            Intent c = new Intent(this, Cleaner.class);
            startActivity(c);
        }

    }
}
