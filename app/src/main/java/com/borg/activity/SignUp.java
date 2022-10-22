package com.borg.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borg.R;
import com.borg.model.DatabaseConnection;

public class SignUp extends AppCompatActivity {

    EditText signupName;
    EditText signupSurname;
    EditText signupAddress;
    EditText signupPhoneNumber;
    EditText signupEmail;
    EditText signupUsername;
    EditText signupPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupName = findViewById(R.id.signupName);
        signupSurname = findViewById(R.id.signupSurname);
        signupAddress = findViewById(R.id.signupAddress);
        signupPhoneNumber = findViewById(R.id.signupPhoneNumber);
        signupEmail = findViewById(R.id.signupEmail);
        signupUsername = findViewById(R.id.signupUsername);
        signupPassword = findViewById(R.id.signupPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(signupName.getText().toString(), signupSurname.getText().toString(), signupAddress.getText().toString(), signupPhoneNumber.getText().toString(), signupEmail.getText().toString(), signupUsername.getText().toString(), signupPassword.getText().toString());
            }
        });
    }

    private void signUp(String signupName, String signupSurname, String signupAddress, String signupPhoneNumber, String signupEmail, String signupUsername, String signupPassword){
        if (signupName.equals("")||signupSurname.equals("")||signupAddress.equals("")||signupPhoneNumber.equals("")||signupEmail.equals("")||signupUsername.equals("")||signupPassword.equals("")) {
            Toast.makeText(SignUp.this,"Please enter all fields!", Toast.LENGTH_LONG).show();
        } else {
            try {
                DatabaseConnection db = DatabaseConnection.getDbInstance(this.getApplicationContext());
                //find user by username
                String rs = db.UserDao().findUser(signupUsername);
                if (rs == null || rs.isEmpty()) {
                    db.UserDao().insertNewUser(signupName, signupSurname, signupAddress, signupPhoneNumber, signupEmail, signupUsername, signupPassword, 2);
                    Toast.makeText(SignUp.this,"You signed up successfully!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SignUp.this, Login.class);
                    startActivity(i);
                } else {
                    Toast.makeText(SignUp.this,"Username already exists", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(SignUp.this,e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }


}