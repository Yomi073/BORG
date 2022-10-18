package com.borg.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.borg.R;

public class Navigation extends AppCompatActivity {

    TextView t;
    DrawerLayout drawerLayout;
    ImageView imageMenu;
    MenuItem navigationView;
    MenuItem menuMaterials;
    MenuItem menuClients;
    MenuItem menuUsers;
    MenuItem menuLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        t = findViewById(R.id.textTitle);
        t.setText(this.getClass().getSimpleName());

        drawerLayout = findViewById(R.id.drawerLayout);
        imageMenu = findViewById(R.id.imageMenu);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuTask:
                Toast.makeText(this,"menuTask", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuMaterials:
                Toast.makeText(this,"menuMaterials", Toast.LENGTH_LONG).show();
            case R.id.menuClients:
                Toast.makeText(this,"menuClients", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuUsers:
                Toast.makeText(this,"menuUsers", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuLogout:
                Toast.makeText(this,"menuLogout", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}