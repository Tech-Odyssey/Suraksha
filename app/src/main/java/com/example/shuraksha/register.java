package com.example.shuraksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    String userID;
    EditText mEmail,mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    // progress bar if needed to tbd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.textView6);


        firestore = FirebaseFirestore.getInstance();

        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() !=null){
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty() && password.length()>6) {
                        fAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        //
                                        userID = fAuth.getCurrentUser().getUid();
                                        DocumentReference documentReference = firestore.collection("users").document(userID);
                                        Map<String,Object> user = new HashMap<>();
                                        user.put("email",email);
                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Log.d("TAG","onSuccess: user profile created for"+userID);
                                            }public void onFailure(@NonNull Exception e){
                                                Log.d("TAG","onFailure"+e.toString());
                                            }
                                        });
                                        startActivity(new Intent(register.this, login.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(register.this, "Registration Error", Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {
                        mPassword.setError("Password should greater than 6 characters");
                    }
                } else if (email.isEmpty()) {
                    mEmail.setError("Empty fields are not allowed");
                } else {
                    mEmail.setError("Please enter correct email");
                }
            }
        });

    }

    public void gotosignin(View view) {
        Intent isignin = new Intent(this, login.class);
        startActivity(isignin);
    }


    public void gotohome(View view){
        Intent ihome = new Intent(this, homeact.class);
        startActivity(ihome);
    }
}