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

public class DestinationActivity1 extends AppCompatActivity {

    Button back,next;
    private RadioButton Gaborone,Mochudi,Mahalapye,Palapye,Serowe,Letlhakane,Orapa,Selebe,Fransistown,Maun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        Gaborone = findViewById(R.id.Gaborone);
        Mochudi = findViewById(R.id.Mochudi);
        Mahalapye = findViewById(R.id.Mahalapye);
        Palapye = findViewById(R.id.Palapye);
        Serowe = findViewById(R.id.Serowe);
        Letlhakane = findViewById(R.id.Letlhakane);
        Orapa = findViewById(R.id.Orapa);
        Selebe = findViewById(R.id.Selebe);
        Fransistown = findViewById(R.id.Fransistown);
        Maun = findViewById(R.id.Maun);

        Gaborone.setChecked(Update("B_Gaborone"));
        Mochudi.setChecked(Update("B_Mochudi"));
        Mahalapye.setChecked(Update("B_Mahalapye"));
        Palapye.setChecked(Update("B_Palapye"));
        Serowe.setChecked(Update("B_Serowe"));
        Letlhakane.setChecked(Update("B_Letlhakane"));
        Orapa.setChecked(Update("B_Orapa"));
        Selebe.setChecked(Update("B_Selebe"));
        Fransistown.setChecked(Update("B_Fransistown"));
        Maun.setChecked(Update("B_Maun"));


        Gaborone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean gaborone_isChecked) {
                SaveIntoSharedPref("B_Gaborone", gaborone_isChecked);
            }
        });

        Mochudi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean mochudi_isChecked) {
                SaveIntoSharedPref("B_Mochudi", mochudi_isChecked);
            }
        });

        Mahalapye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean mahalapye_isChecked) {
                SaveIntoSharedPref("B_Mahalapye", mahalapye_isChecked);
            }
        });

        Palapye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean palapye_isChecked) {
                SaveIntoSharedPref("B_Palapye", palapye_isChecked);
            }
        });

        Serowe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean serowe_isChecked) {
                SaveIntoSharedPref("B_Serowe", serowe_isChecked);
            }
        });

        Letlhakane.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean letlhakane_isChecked) {
                SaveIntoSharedPref("B_Letlhakane", letlhakane_isChecked);
            }
        });

        Orapa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean orapa_isChecked) {
                SaveIntoSharedPref("B_Orapa", orapa_isChecked);
            }
        });

        Selebe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean selebe_isChecked) {
                SaveIntoSharedPref("B_Selebe", selebe_isChecked);
            }
        });

        Fransistown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean fransistown_isChecked) {
                SaveIntoSharedPref("B_Fransistown", fransistown_isChecked);
            }
        });

        Maun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean maun_isChecked) {
                SaveIntoSharedPref("B_Maun", maun_isChecked);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationActivity1.this, DepartureActivity1.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationActivity1.this, SelectCompanyActivity.class);
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