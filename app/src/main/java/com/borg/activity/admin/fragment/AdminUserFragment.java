package com.borg.activity.admin.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.borg.R;
import com.borg.activity.admin.adapter.AdminUsersAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.Client;
import com.borg.model.database.Role;
import com.borg.model.database.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminUserFragment extends Fragment {

    DatabaseConnection db;
    RecyclerView recyclerView;
    List<User> usersList_admin;

    public AdminUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_admin_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        AdminUsersAdapter adminUsersAdapter = new AdminUsersAdapter(getContext(),getList());
        recyclerView.setAdapter(adminUsersAdapter);

        ImageButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener( v -> {

            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_add_user);

            EditText firstName = (EditText) dialog.findViewById(R.id.add_user_txt_first_name);
            EditText lastName = (EditText) dialog.findViewById(R.id.add_user_txt_last_name);
            EditText address = (EditText) dialog.findViewById(R.id.add_user_txt_address);
            EditText phone = (EditText) dialog.findViewById(R.id.add_user_txt_phone);
            EditText email = (EditText) dialog.findViewById(R.id.add_user_txt_email);
            EditText username = (EditText) dialog.findViewById(R.id.add_user_txt_username);
            EditText password = (EditText) dialog.findViewById(R.id.add_user_txt_password);
            final Spinner spinnerRole = (Spinner) dialog.findViewById(R.id.spinner_user_role);

            List<Role> roles = db.RoleDao().getAllRoles();

            List<String> rolesItem = new ArrayList<>();

            for (Role role: roles) {
                rolesItem.add(role.getId()+" "+role.getName());
            }

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapterRole = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, rolesItem);
            // Drop down layout style - list view with radio button
            dataAdapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerRole.setAdapter(dataAdapterRole);

            Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
            dialogButton.setOnClickListener(v1 -> {
                String roleSelected[] = String.valueOf(spinnerRole.getSelectedItem()).split(" ");
                db.UserDao().insertNewUser(firstName.getText().toString(),lastName.getText().toString(),address.getText().toString(),phone.getText().toString(),email.getText().toString(),username.getText().toString(),password.getText().toString(),Integer.parseInt(roleSelected[0]));
                adminUsersAdapter.notifyItemAdd();
                dialog.dismiss();
            });
            dialog.show();
        });

    }

    private List<User> getList(){
        db = DatabaseConnection.getDbInstance(getContext());
        usersList_admin = new ArrayList<>();
        usersList_admin = db.UserDao().getAllUsers();
        return usersList_admin;
    }
}