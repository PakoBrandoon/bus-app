package com.power.bunkTech;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mSignUp, mLogIn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mSignUp = findViewById(R.id.signup);
        mLogIn = findViewById(R.id.login);

        firebaseAuth = FirebaseAuth.getInstance();

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mEmail.setError("Password is required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be 6 or more characters");
                    return;
                }

                //authenticate the user

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DriverLoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), SelectScreenActivity.class));
                        } else {
                            Toast.makeText(DriverLoginActivity.this, "Error!!! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
