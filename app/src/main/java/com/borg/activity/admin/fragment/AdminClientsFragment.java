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
import com.borg.activity.admin.adapter.AdminClientsAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.Client;

import java.util.ArrayList;
import java.util.List;

public class AdminClientsFragment extends Fragment {

    RecyclerView recyclerView;
    List<Client> clientList;

    public AdminClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_clients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_admin_clients);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        AdminClientsAdapter adminClientsAdapter = new AdminClientsAdapter(getContext(),getList());
        recyclerView.setAdapter(adminClientsAdapter);
    }

    private List<Client> getList(){
        DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
        clientList = new ArrayList<>();
        clientList = db.ClientDao().getAllClients();
        return clientList;
    }
}