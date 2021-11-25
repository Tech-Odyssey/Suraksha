package com.example.shuraksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class helpline extends AppCompatActivity {

    Button mpolice;
    Button mambulance;
    Button mwomen_helpline;
    Button mmadhushimaam;
    private static final int Request_Call = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        mpolice = findViewById(R.id.police);
        mambulance = findViewById(R.id.Ambulance);
        mwomen_helpline = findViewById(R.id.Women_Helpline);
        mmadhushimaam = findViewById(R.id.madhushimaam);

        mpolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall1();
            }
        });
        mambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall2();
            }
        });
        mwomen_helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall3();
            }
        });

        mmadhushimaam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Request_Call) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall1();
                makePhoneCall2();
                makePhoneCall3();
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makePhoneCall1(){
        long no = 100;
        if(no>0){

            if(ContextCompat.checkSelfPermission(helpline.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE},Request_Call);

            }else{
                String dial = "tel:" + no;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }else{
            Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    private void makePhoneCall2(){
        long no = 102;
        if(no>0){

            if(ContextCompat.checkSelfPermission(helpline.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE},Request_Call);

            }else{
                String dial = "tel:" + no;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }else{
            Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    private void makePhoneCall3(){
        long no = 1091;
        if(no>0){

            if(ContextCompat.checkSelfPermission(helpline.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE},Request_Call);

            }else{
                String dial = "tel:" + no;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }else{
            Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    /*private void makePhoneCall4(){
        long no = 9829745279l;
        if(no>0){

            if(ContextCompat.checkSelfPermission(helpline.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE},Request_Call);

            }else{
                String dial = "tel:" + no;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }else{
            Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }*/
}