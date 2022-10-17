package com.borg.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borg.R;
import com.borg.model.DatabaseConnection;
import com.borg.model.Role;
import com.borg.model.User;

import java.util.List;

public class Login extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //On start insert data in database
        insert();

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                login(loginUsername.getText().toString(), loginPassword.getText().toString());
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });
    }


    private void login(String loginUsername, String loginPassword) {

        if (loginUsername.equals("") || loginPassword.equals("")){
            Toast.makeText(Login.this,"Please enter all fields!", Toast.LENGTH_LONG).show();
        } else {
            try {
                DatabaseConnection db = DatabaseConnection.getDbInstance(this.getApplicationContext());
                //find user by username and password
                List<User> rs = db.UserDao().findUser(loginUsername, loginPassword);
                if (rs.isEmpty()){
                    Toast.makeText(Login.this,"Wrong credentials!!", Toast.LENGTH_LONG).show();
                } else {
                    //if there is a match in database go to new activity
                    if (db.UserDao().findRoleNameByUserName(loginUsername).equals(getString(R.string.admin))){
                        Intent i = new Intent(this, AdminProfile.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(this, UserProfile.class);
                        startActivity(i);
                    }
                }
                db.close();
            } catch (Exception ex) {
                Toast.makeText(Login.this,"Something went wrong!", Toast.LENGTH_LONG).show();
                System.out.println("Error occurred: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void insert(){
        DatabaseConnection db = DatabaseConnection.getDbInstance(this.getApplicationContext());
        List<Role> rs = db.RoleDao().readAllRole();
        if(rs.isEmpty()) {
            db.RoleDao().insertRole(1, "Admin");
            db.RoleDao().insertRole(2, "User");
        }
        db.close();
    }
}