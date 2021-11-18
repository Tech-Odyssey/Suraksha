package com.example.shuraksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;




public class homeact extends AppCompatActivity {
    Button mlogout;
    ImageView mSos;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeact);
        mSos = findViewById(R.id.sos_img);
        mlogout = findViewById(R.id.logout);
        mSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Its working", Toast.LENGTH_SHORT).show();
            }
        });
        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*switch (v.getId()) {
                    // ...
                    case R.id.logout:
                        revokeAccess();
                        signOut();
                        break;
                    // ...
                }*/
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
        Intent intent2 = new Intent(homeact.this,login.class);
        startActivity(intent2);

    }

    /*private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent2 = new Intent(homeact.this,login.class);
                        startActivity(intent2);
                    }
                });
    }
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }*/


}