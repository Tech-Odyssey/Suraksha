package com.example.shuraksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class homeact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeact);
    }

    public void gotohelpline(View view) {
    }

    public void gotomap(View view) {
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent (getApplicationContext(), login.class));
        finish();
    }
}