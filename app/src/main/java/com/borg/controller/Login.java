package com.borg.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borg.R;
import com.borg.model.Client;
import com.borg.model.DatabaseConnection;
import com.borg.model.Role;
import com.borg.model.Task;
import com.borg.model.User;

import java.util.List;

public class Login extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    Button btnLogin;
    Button btnSignUp;
    private static int loggedUser;

    public static int getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(int loggedUser) {
        Login.loggedUser = loggedUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //DUMMY
        //On start insert data in database
        insert();

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(v -> login(loginUsername.getText().toString(), loginPassword.getText().toString()));
        btnSignUp.setOnClickListener(v -> {
            Intent i = new Intent(Login.this, SignUp.class);
            startActivity(i);
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
                    loggedUser = db.UserDao().getLoggedUser(loginUsername);
                    //if there is a match in database go to new activity
                    if (db.UserDao().findRoleNameByUserName(loginUsername).equals(getString(R.string.admin))){
                        Intent i = new Intent(this, AdminNavigation.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(this, UserNavigation.class);
                        startActivity(i);
                    }
                }
            } catch (Exception ex) {
                Toast.makeText(Login.this,"Something went wrong!", Toast.LENGTH_LONG).show();
                System.out.println("Error occurred: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void insert(){
        DatabaseConnection db = DatabaseConnection.getDbInstance(this.getApplicationContext());
        List<Role> rsr = db.RoleDao().readAllRole();
        if(rsr.isEmpty()) {
            db.RoleDao().insertRole(1, "Admin");
            db.RoleDao().insertRole(2, "User");
        }
        List<User> rsu = db.UserDao().readAllUsers();
        if(rsu.isEmpty()) {
            db.UserDao().insertNewUser("admin","admin","admin","1","admin","admin","admin",1);
            db.UserDao().insertNewUser("a","a","a","1","a","a","a",1);
            db.UserDao().insertNewUser("q","q","q","2","q","q","q",2);
            db.UserDao().insertNewUser("s","s","s","2","s","s","s",2);
            db.UserDao().insertNewUser("w","w","w","2","w","w","w",2);
        }
        List<Client> rsc = db.ClientDao().readAllClient();
        if (rsc.isEmpty()) {
            db.ClientDao().insertNewClient("marko", "studenci");
            db.ClientDao().insertNewClient("ivan", "ligat");
            db.ClientDao().insertNewClient("ante", "vitina");
            db.ClientDao().insertNewClient("matej", "crnopod");
        }
        List<Task> rst = db.TaskDao().readAllTask();
        if (rst.isEmpty()) {
            db.TaskDao().insertNewTask(1,1);
            db.TaskDao().insertNewTask(1,2);
            db.TaskDao().insertNewTask(2,1);
            db.TaskDao().insertNewTask(2,2);
            db.TaskDao().insertNewTask(3,1);
            db.TaskDao().insertNewTask(3,2);
            db.TaskDao().insertNewTask(4,1);
            db.TaskDao().insertNewTask(4,2);
            //db.TaskDao().insertNewTask(5,1);
            //db.TaskDao().insertNewTask(5,2);
        }
    }
}