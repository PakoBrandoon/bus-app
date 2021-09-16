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

public class SelectScreenActivity extends AppCompatActivity {

    private Button mNoofSeats,mDestination,mDeparture,mNoofSeatsOccupied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNoofSeats= (Button) findViewById(R.id.noofseats);
        mDestination= (Button) findViewById(R.id.destination);
        mDeparture= (Button) findViewById(R.id.departure);
        mNoofSeatsOccupied= (Button) findViewById(R.id.noofseatsoccupied);

        mNoofSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity.this, NoOfSeatsActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity.this, DestinationActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        mDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity.this, DepartureActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mNoofSeatsOccupied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SelectScreenActivity.this, NoOfSeatsOccupiedActivity.class);
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