package com.example.shuraksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
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





public class homeact extends AppCompatActivity {
    Button mlogout;
    Button mmessages;
    ImageView mSos;
    EditText mnumber;
    EditText mmessageforSOS;
    final int SEND_SMS_REQUEST_CODE =1;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeact);
        mSos = findViewById(R.id.sos_img);
        mlogout = findViewById(R.id.logout);
        mmessages = findViewById(R.id.MSSGS);
        mnumber = findViewById(R.id.editTextPhone);
        mmessageforSOS = findViewById(R.id.messagehome);

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
    public boolean checkPermission(String Permission){
        int check = ContextCompat.checkSelfPermission(this,Permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }



}