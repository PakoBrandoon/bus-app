package com.power.bunkTech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class SelectTimeActivity extends AppCompatActivity {

    Button back,next;
    private RadioButton time1,time2,time3,time4,time5,time6,time7,time8,time9,time10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);
        time5= findViewById(R.id.time5);
        time6= findViewById(R.id.time6);
        time7= findViewById(R.id.time7);
        time8= findViewById(R.id.time8);
        time9= findViewById(R.id.time9);
        time10= findViewById(R.id.time10);


        time1.setChecked(Update("B_time1"));
        time2.setChecked(Update("B_time2"));
        time3.setChecked(Update("B_time3"));
        time4.setChecked(Update("B_time4"));
        time5.setChecked(Update("B_time5"));
        time6.setChecked(Update("B_time6"));
        time7.setChecked(Update("B_time7"));
        time8.setChecked(Update("B_time8"));
        time9.setChecked(Update("B_time9"));
        time10.setChecked(Update("B_time10"));





       time1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time1_isChecked) {
                SaveIntoSharedPref("B_time1", time1_isChecked);
            }
        });

        time2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time2_isChecked) {
                SaveIntoSharedPref("B_time2", time2_isChecked);
            }
        });time3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time3_isChecked) {
                SaveIntoSharedPref("B_time3", time3_isChecked);
            }
        });time4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time4_isChecked) {
                SaveIntoSharedPref("B_time4", time4_isChecked);
            }
        });time5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time5_isChecked) {
                SaveIntoSharedPref("B_time5", time5_isChecked);
            }
        });time6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time6_isChecked) {
                SaveIntoSharedPref("B_time6", time6_isChecked);
            }
        });time7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time7_isChecked) {
                SaveIntoSharedPref("B_time7", time7_isChecked);
            }
        });time8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time8_isChecked) {
                SaveIntoSharedPref("B_time8", time8_isChecked);
            }
        });time9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time9_isChecked) {
                SaveIntoSharedPref("B_time9", time9_isChecked);
            }
        });time10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean time10_isChecked) {
                SaveIntoSharedPref("B_time10", time10_isChecked);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTimeActivity.this, SelectScreenActivity1.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTimeActivity.this, DepartureActivity1.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }
    private  void SaveIntoSharedPref(String key, boolean value){

        SharedPreferences sp =  getSharedPreferences("B_TECH",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private boolean Update(String key) {

        SharedPreferences sp = getSharedPreferences("B_TECH",MODE_PRIVATE);
        return sp.getBoolean(key, false);
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