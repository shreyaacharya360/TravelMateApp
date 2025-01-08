package com.example.travelguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText loginUserName, loginPassword;
    TextView signup;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUserName = findViewById(R.id.user_name);
        loginPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        signup=findViewById(R.id.signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUsername() | !validatePassword()){

                }else{
                    checkUser();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername(){
        String val=loginUserName.getText().toString();
        if(val.isEmpty()){
            loginUserName.setError("User cannot be empty");
            return false;
        }else {
            loginUserName.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val=loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("Password cannot be empty");
            return false;
        }else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername=loginUserName.getText().toString().trim();
        String userPassword=loginPassword.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase=reference.orderByChild("userName").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    loginUserName.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)) {
                        loginUserName.setError(null);

                        String userNameFromDB=snapshot.child(userUsername).child("userName").getValue(String.class);
                        String emailFromDB=snapshot.child(userUsername).child("email").getValue(String.class);
                        String contactFromDB=snapshot.child(userUsername).child("contact").getValue(String.class);


                        Intent intent = new Intent(Login.this, MainActivity.class);

                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("password",passwordFromDB);
                        intent.putExtra("contact",contactFromDB);
                        intent.putExtra("userName",userNameFromDB);

                        startActivity(intent);
                    } else {
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                }else {
                        loginUserName.setError("User does not exits..!");
                        loginUserName.requestFocus();
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}