package com.example.shuraksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;




public class homeact extends AppCompatActivity {
    Button mlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeact);

        mlogout = findViewById(R.id.logout);

        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(homeact.this,login.class);
                startActivity(intent2);
            }
        });

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