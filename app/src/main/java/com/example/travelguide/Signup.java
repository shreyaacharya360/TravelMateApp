package com.example.travelguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText signupEmail, signupPassword, signupContact, signupUserName;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupEmail = findViewById(R.id.email);
        signupPassword = findViewById(R.id.password);
        signupContact = findViewById(R.id.contact);
        signupUserName = findViewById(R.id.user_name);
        signupButton = findViewById(R.id.create);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String email= signupEmail.getText().toString();
                String password = signupPassword.getText().toString();
                String contact = signupContact.getText().toString();
                String userName = signupUserName.getText().toString();

                HelperClass helperClass=new HelperClass(email,password,contact,userName);
                reference.child(userName).setValue(helperClass);

                Toast.makeText(Signup.this, "You have signup Successfully..!!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });


    }
}