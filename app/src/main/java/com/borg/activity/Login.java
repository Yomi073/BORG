package com.borg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borg.R;
import com.borg.activity.admin.AdminNavigation;
import com.borg.activity.user.UserNavigation;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.MaterialConsumption;
import com.borg.model.database.MaterialStock;
import com.borg.model.database.Role;
import com.borg.model.database.Task;
import com.borg.model.database.User;
import com.borg.model.database.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Login extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    Button btnLogin;
    Button btnSignUp;
    private static Integer loggedUser;

    public static Integer getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Integer loggedUser) {
        this.loggedUser = loggedUser;
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
                    setLoggedUser(db.UserDao().getUserIdByUsername(loginUsername));
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
        List<Role> rsr = db.RoleDao().getAllRoles();
        if(rsr.isEmpty()) {
            db.RoleDao().insertRole(1, "Admin");
            db.RoleDao().insertRole(2, "User");
        }
        List<User> rsu = db.UserDao().getAllUsers();
        if(rsu.isEmpty()) {
            db.UserDao().insertNewUser("admin","admin","admin","1","admin","admin","admin",1);
            db.UserDao().insertNewUser("a","a","a","1","a","a","a",1);
            db.UserDao().insertNewUser("q","q","q","2","q","q","q",2);
            db.UserDao().insertNewUser("s","s","s","2","s","s","s",2);
            db.UserDao().insertNewUser("w","w","w","2","w","w","w",2);
        }
        List<Client> rsc = db.ClientDao().getAllClients();
        if (rsc.isEmpty()) {
            db.ClientDao().insertNewClient("Marko", "Eres","Studenci", "063744744","markoeres@gmail.com");
            db.ClientDao().insertNewClient("Ivan", "Kozina", "Hrasljani", "71717171", "ivankozina@gmail.com");
            db.ClientDao().insertNewClient("Ante", "Boras", "Vitina", "63636363", "prodajemmed@gmail.com");
            db.ClientDao().insertNewClient("Matej", "Vasilj", "Crnopod", "7272727", "igramWOW@gmail.com");
        }
        List<Task> rst = db.TaskDao().readAllTask();

        if (rst.isEmpty()) {
            db.TaskDao().insertNewTask(1,1,new Date());
            db.TaskDao().insertNewTask(1,2,new Date());
            db.TaskDao().insertNewTask(1,3,new Date());
            db.TaskDao().insertNewTask(1,4,new Date());
            db.TaskDao().insertNewTask(2,1,new Date());
            db.TaskDao().insertNewTask(2,2,new Date());
            db.TaskDao().insertNewTask(2,3,new Date());
            db.TaskDao().insertNewTask(2,4,new Date());
            db.TaskDao().insertNewTask(3,1,new Date());
            db.TaskDao().insertNewTask(3,2,new Date());
            db.TaskDao().insertNewTask(3,3,new Date());
            db.TaskDao().insertNewTask(3,4,new Date());
            db.TaskDao().insertNewTask(4,1,new Date());
            db.TaskDao().insertNewTask(4,2,new Date());
            db.TaskDao().insertNewTask(4,3,new Date());
            db.TaskDao().insertNewTask(4,4,new Date());
            db.TaskDao().insertNewTask(5,1,new Date());
            db.TaskDao().insertNewTask(5,2,new Date());
            db.TaskDao().insertNewTask(5,3,new Date());
            db.TaskDao().insertNewTask(5,4,new Date());
        }
        List<MaterialStock> rsms =db.MaterialStockDao().getAllMaterials();

        if(rsms.isEmpty()){
            db.MaterialStockDao().insertNewMaterialStock("sijalica",600.0,2.0,4.0);
            db.MaterialStockDao().insertNewMaterialStock("kablo",10000.0,0.1,0.7);
            db.MaterialStockDao().insertNewMaterialStock("steker", 750.0,20.0,27.0);
        }
        List<MaterialConsumption> rsmc = db.MaterialConsumptionDao().readAllMaterialConsumption();

        if(rsmc.isEmpty()){
            db.MaterialConsumptionDao().insertNewMaterialConsumption(1.0,1,1);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(2.0,2, 2);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(3.0,3, 3);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(4.0,1,4);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(5.0,2,5);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(6.0,3,6);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(7.0,1,7);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(8.0,2,8);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(9.0,3,9);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(10.0,1,10);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(1.0,1,1);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(2.0,2, 2);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(3.0,3, 3);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(4.0,1,4);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(5.0,2,5);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(6.0,3,6);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(7.0,1,7);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(8.0,2,8);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(9.0,3,15);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(10.0,1,15);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(1.0,1,16);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(2.0,2, 16);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(3.0,3, 17);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(4.0,1,17);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(5.0,2,18);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(6.0,3,18);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(7.0,1,19);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(8.0,2,19);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(9.0,3,20);
            db.MaterialConsumptionDao().insertNewMaterialConsumption(10.0,1,20);


        }

    }
}