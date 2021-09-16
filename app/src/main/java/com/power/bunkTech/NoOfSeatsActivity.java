package com.power.bunkTech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class NoOfSeatsActivity extends AppCompatActivity {

   

    Button back,next,save,apply;
    TextView  seats;
    EditText editTextNumber;
    Switch switch1;
    
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String TEXT = "text";
    private static final String SWITCH1 = "switch1";

    private String text;
    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_of_seats);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        save = findViewById(R.id.save);
        apply= findViewById(R.id.apply);
        next = findViewById(R.id.next);
        editTextNumber = findViewById(R.id.editTextNumber);
        seats =findViewById(R.id.seats);
        switch1 = findViewById(R.id.switch1);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NoOfSeatsActivity.this, SelectScreenActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent intent = new Intent(NoOfSeatsActivity.this, DepartureActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                seats.setText(editTextNumber.getText().toString());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                saveData();
            }
        });

        loadData();
        updateViews();
    }


    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(TEXT,seats.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }


    public void loadData(){
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        switchOnOff= sharedPreferences.getBoolean(SWITCH1, false);

    }

    public void updateViews() {
        seats.setText(text);
        switch1.setChecked(switchOnOff);
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