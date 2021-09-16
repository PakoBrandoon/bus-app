package com.power.bunkTech;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverSignUpActivity extends AppCompatActivity {
    EditText mName,mEmail,mLicence,mPassword;
    Button mSignUp,mLogIn;
    Spinner mSpinner;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_up);

        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mSignUp = findViewById(R.id.signup);
        mLogIn = findViewById(R.id.login);
        mLicence = findViewById(R.id.licence);
        mSpinner =findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(DriverSignUpActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.company));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(myAdapter);

        firebaseAuth =FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),SelectScreenActivity.class));
            finish();
        }


        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= mEmail.getText().toString().trim();
                String password= mEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mEmail.setError("Password is required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be 6 or more characters");
                    return;
                }

                //register user in firebase
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DriverSignUpActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),SelectScreenActivity.class));
                            String user_id = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("users").child("driver").child(user_id);
                            current_user_db.setValue(true);
                        } else {
                            Toast.makeText(DriverSignUpActivity.this,"Error!!! "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DriverLoginActivity.class));
            }
        });

    }
}
