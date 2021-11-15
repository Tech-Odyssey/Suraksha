package com.example.shuraksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void gotosignin(View view) {
        Intent isignin = new Intent(this, MainActivity.class);
        startActivity(isignin);
    }


    public void gotohome(View view) {
        Intent ihome = new Intent(this, homeact.class);
        startActivity(ihome);
    }
}