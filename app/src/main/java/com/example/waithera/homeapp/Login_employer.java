package com.example.waithera.homeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_employer extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    private Button logout, nanny, plumber,carpenter,househelp,cleaner,other;
    private FirebaseAuth firebaseAuth;
    //Drawer
    private DrawerLayout mDrawerLayout;
private ActionBarDrawerToggle mToggle;
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
        carpenter=(Button)findViewById(R.id.carpenter);
        househelp=(Button)findViewById(R.id.househelp);
        cleaner=(Button)findViewById(R.id.cleaner);
        other=(Button)findViewById(R.id.otherWorkers);

        logout.setOnClickListener(this);
        nanny.setOnClickListener(this);
        plumber.setOnClickListener(this);
        carpenter.setOnClickListener(this);
househelp.setOnClickListener(this);
cleaner.setOnClickListener(this);
other.setOnClickListener(this);

//DrawerLayout
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
    mDrawerLayout.addDrawerListener(mToggle);
    mToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view == plumber) {
            Intent i = new Intent(this, Plumber_view.class);
            startActivity(i);

        }
        if(view==nanny){
            Intent i=new Intent(this,NannyView.class);
            startActivity(i);
        }
        if(view==carpenter){
            Intent c=new Intent(this,CarpenterView.class);
            startActivity(c);
        }
        if(view==househelp){
            Intent h=new Intent(this,HouseHelpView.class);
            startActivity(h);
        }
        if(view==cleaner){
            Intent c=new Intent(this,CleanerView.class);
            startActivity(c);
        }
        if(view==other){
            Intent o=new Intent(this,OthersView.class);
            startActivity(o);
        }
        if (view == logout) {
            firebaseAuth.signOut();
            finish();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case(R.id.home):
                Intent home=new  Intent(this,MainActivity.class);
                startActivity(home);
                break;
            case (R.id.help):
                Intent onlineHelp=new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/1_wHn8Hf40nfzw3__PpIBtorWx3o2pysXwPB8W10e4Ko/view"));
                startActivity(onlineHelp);

        }
        return true;
    }


}