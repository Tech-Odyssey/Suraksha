package com.example.shuraksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mRegisterBtn;
    Button mLoginBtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_login);


        mEmail = findViewById(R.id.email1);
        mPassword = findViewById(R.id.password1);
        mRegisterBtn = findViewById(R.id.register1);
        mLoginBtn = findViewById(R.id.loginbtn);


        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();


        if(user!=null){
            startActivity(new Intent(login.this, homeact.class));
            finish();
        }

        /*mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "working", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getApplicationContext(), register.class);
                startActivity(i1);}
        });*/


        mLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mEmail.setError("password is required");
                    return;
                }

                if(password.length()<6){
                    mPassword.setError("Password must be longer than 6 characters");
                    return;
                }

                // to authenticate the user in firebase
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), homeact.class));
                        }else{
                            Toast.makeText(login.this, "login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void gotoregister(View view){
        Intent i1 = new Intent(this, register.class);
        startActivity(i1);
    }


    public void gotohome(View view){
        Intent intent = new Intent(this, homeact.class);
        startActivity(intent);
    }


}
