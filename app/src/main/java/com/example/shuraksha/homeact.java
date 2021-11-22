package com.example.shuraksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;



public class homeact extends AppCompatActivity {
    Button mlogout;
    Button mmessages;
    ImageView mSos;
    EditText mnumber;
    EditText mmessageforSOS;
    final int SEND_SMS_REQUEST_CODE =1;

    // for circle menu
    CircleMenu circleMenu;
    ConstraintLayout constraintLayout;

    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeact);
        mSos = findViewById(R.id.sos_img);
        mmessages = findViewById(R.id.MSSGS);
        mnumber = findViewById(R.id.editTextPhone);
//        mmessageforSOS = findViewById(R.id.messagehome);
        //circle menu
        circleMenu = findViewById(R.id.circle_menu);
        constraintLayout = findViewById(R.id.constraint_layout);

        // for circle menu
        circleMenu.setMainMenu(Color.parseColor("#FF8385"),R.mipmap.list,R.mipmap.multiply)
                .addSubMenu(Color.parseColor("#FF8385"),R.mipmap.home)
                .addSubMenu(Color.parseColor("#FF8385"),R.mipmap.add)
                .addSubMenu(Color.parseColor("#FF8385"),R.mipmap.account)
                .addSubMenu(Color.parseColor("#FF8385"),R.mipmap.gear)
                .addSubMenu(Color.parseColor("#FF8385"),R.mipmap.logout)
                .addSubMenu(Color.parseColor("#FF8385"),R.mipmap.help)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch (index){
                            case 0:
                                Toast.makeText(homeact.this, "Home", Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                Intent intent = new Intent(homeact.this, homeact.class);
                                startActivity(intent);
                                break;
                            case 1:
                                Toast.makeText(homeact.this, "Add recipe will be available soon", Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                break;
                            case 2:
                                Toast.makeText(homeact.this, "Your profile is being scanned by CIA", Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                break;
                            case 3:
                                Toast.makeText(homeact.this, "Settings is currently on vacation", Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                break;
                            case 4:
                                Toast.makeText(homeact.this, "You have been logged out bye bye", Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                FirebaseAuth.getInstance().signOut();
                                Intent intent2 = new Intent(homeact.this,login.class);
                                startActivity(intent2);
                                break;
                            case 5:
                                Toast.makeText(homeact.this, "Help is currently helpless", Toast.LENGTH_SHORT).show();
                                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                break;
                        }
                    }
                });





        mmessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(homeact.this,messages.class);
                startActivity(intent2);
            }
        });

        // for messages in home page via edit text|| trying to connect the SOS button so that it acts as the send button for sms
        if(checkPermission(Manifest.permission.SEND_SMS)){
            mSos.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},SEND_SMS_REQUEST_CODE);
        }
        mSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String no=mnumber.getText().toString();
                String msg=mmessageforSOS.getText().toString();

                msg = "This is a SOS message all available units please respond";

                //Getting intent and PendingIntent instance
                Intent intent=new Intent(getApplicationContext(),messages.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(no, null, msg, pi,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
            }
        });




    }





    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent2 = new Intent(homeact.this,login.class);
        startActivity(intent2);

    }
    public boolean checkPermission(String Permission){
        int check = ContextCompat.checkSelfPermission(this,Permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }



}