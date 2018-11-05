package com.example.skure.doortie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button registerButton;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }




    @Override
    protected void onStart(){
        super.onStart();

        Button logButton = (Button)findViewById(R.id.LoginButton);
        logButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                startActivity(new Intent(Login_Activity.this, MainActivity.class));

            }

        });
    }
}
