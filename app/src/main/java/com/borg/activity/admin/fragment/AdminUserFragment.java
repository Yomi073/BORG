package com.borg.activity.admin.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borg.R;
import com.borg.activity.admin.adapter.AdminUsersAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.ViewAdminUsers;

import java.util.ArrayList;
import java.util.List;

public class AdminUserFragment extends Fragment {

    RecyclerView recyclerView;
    List<ViewAdminUsers> usersList_admin;

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
    }

    private List<ViewAdminUsers> getList(){
        DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
        usersList_admin = new ArrayList<>();
        usersList_admin = db.UserDao().getAllUsers();
        return usersList_admin;
    }
}