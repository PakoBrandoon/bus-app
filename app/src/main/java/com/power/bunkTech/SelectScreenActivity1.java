package com.power.bunkTech;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class SelectScreenActivity1 extends AppCompatActivity {

    private Button mSelectTime,mDestination,mDeparture,mSelectCompany,mSelectSeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_screen1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSelectTime= (Button) findViewById(R.id.SelectTime);
        mDestination= (Button) findViewById(R.id.destination);
        mDeparture= (Button) findViewById(R.id.departure);
        mSelectCompany= (Button) findViewById(R.id.SelectCompany);
        mSelectSeats= (Button) findViewById(R.id.SelectSeats);

        mSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity1.this, SelectTimeActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity1.this, DepartureActivity1.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        mDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity1.this, DestinationActivity1.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mSelectCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity1.this, SelectCompanyActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        mSelectSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity1.this, SelectSeatActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLougout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));

                break;
        }

        return true;
    }
    }