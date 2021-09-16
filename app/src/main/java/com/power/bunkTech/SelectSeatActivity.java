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

public class SelectSeatActivity extends AppCompatActivity {

    Button back,exit;
    private RadioButton one,two,three,four,five;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = findViewById(R.id.back);
        exit = findViewById(R.id.exit);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);


        one.setChecked(Update("B_one"));
        two.setChecked(Update("B_two"));
        three.setChecked(Update("B_three"));
        four.setChecked(Update("B_four"));
        five.setChecked(Update("B_five"));



        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean one_isChecked) {
                SaveIntoSharedPref("B_one", one_isChecked);
            }
        });

        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean two_isChecked) {
                SaveIntoSharedPref("B_two", two_isChecked);
            }
        });

        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean three_isChecked) {
                SaveIntoSharedPref("B_three", three_isChecked);
            }
        });

        four.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean four_isChecked) {
                SaveIntoSharedPref("B_four", four_isChecked);
            }
        });

        five.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean five_isChecked) {
                SaveIntoSharedPref("B_five", five_isChecked);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSeatActivity.this, SelectCompanyActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSeatActivity.this, SelectScreenActivity1.class);
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