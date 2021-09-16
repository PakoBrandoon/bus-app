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

public class SelectCompanyActivity extends AppCompatActivity {

    Button back,next;
   private RadioButton BAMANGWATO,PANDA,NKKEXPRESS,SEABELO,MOKOKA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        BAMANGWATO = findViewById(R.id.BAMANGWATO);
        PANDA = findViewById(R.id.PANDA);
        NKKEXPRESS = findViewById(R.id.NKKEXPRESS);
        SEABELO = findViewById(R.id.SEABELO);
        MOKOKA = findViewById(R.id.MOKOKA);


        BAMANGWATO.setChecked(Update("B_BAMANGWATO"));
        PANDA.setChecked(Update("B_PANDA"));
        NKKEXPRESS.setChecked(Update("B_NKKEXPRESS"));
        SEABELO.setChecked(Update("B_SEABELO"));
        MOKOKA.setChecked(Update("B_MOKOKA"));



        BAMANGWATO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean BAMANGWATO_isChecked) {
                SaveIntoSharedPref("B_BAMANGWATO", BAMANGWATO_isChecked);
            }
        });

        PANDA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean PANDA_isChecked) {
                SaveIntoSharedPref("B_Mochudi", PANDA_isChecked);
            }
        });

        NKKEXPRESS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean NKKEXPRESS_isChecked) {
                SaveIntoSharedPref("B_Mahalapye", NKKEXPRESS_isChecked);
            }
        });

        SEABELO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean SEABELO_isChecked) {
                SaveIntoSharedPref("B_Palapye", SEABELO_isChecked);
            }
        });

        MOKOKA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean MOKOKA_isChecked) {
                SaveIntoSharedPref("B_MOKOKA", MOKOKA_isChecked);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCompanyActivity.this, DestinationActivity1.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCompanyActivity.this, SelectSeatActivity.class);
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