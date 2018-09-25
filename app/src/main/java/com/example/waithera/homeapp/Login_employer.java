package com.example.waithera.homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_employer extends AppCompatActivity implements View.OnClickListener {
    private Button logout, nanny, plumber, carpenter, househelp, cleaner;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_employer);

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
        househelp = (Button) findViewById(R.id.househelp);
        cleaner = (Button) findViewById(R.id.cleaner);

        logout.setOnClickListener(this);
        nanny.setOnClickListener(this);
        plumber.setOnClickListener(this);
        carpenter.setOnClickListener(this);
        househelp.setOnClickListener(this);
        cleaner.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == plumber) {
            Intent i = new Intent(this, Plumber_view.class);
            startActivity(i);

        }
        if (view == nanny) {
            Intent i = new Intent(this, NannyView.class);
            startActivity(i);
        }
        if (view == carpenter) {
            Intent c = new Intent(this, CarpenterView.class);
            startActivity(c);
        }
        if (view == househelp) {
            Intent h = new Intent(this, HouseHelpView.class);
            startActivity(h);
        }
        if (view == cleaner) {
            Intent c = new Intent(this, CleanerView.class);
            startActivity(c);
        }
        if (view == logout) {
            firebaseAuth.signOut();
            finish();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }
    }
}