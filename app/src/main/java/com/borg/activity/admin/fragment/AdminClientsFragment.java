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
import com.borg.activity.admin.adapter.AdminClientsAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.Client;
import com.borg.model.database.Role;

import java.util.ArrayList;
import java.util.List;

public class AdminClientsFragment extends Fragment {

    DatabaseConnection db;
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

        ImageButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener( v -> {

            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_add_client);

            EditText firstName = (EditText) dialog.findViewById(R.id.add_client_txt_first_name);
            EditText lastName = (EditText) dialog.findViewById(R.id.add_client_txt_last_name);
            EditText address = (EditText) dialog.findViewById(R.id.add_client_txt_address);
            EditText phone = (EditText) dialog.findViewById(R.id.add_client_txt_phone);
            EditText email = (EditText) dialog.findViewById(R.id.add_client_txt_email);

            Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
            dialogButton.setOnClickListener(v1 -> {
                db.ClientDao().insertNewClient(firstName.getText().toString(),lastName.getText().toString(),address.getText().toString(),phone.getText().toString(),email.getText().toString());
                adminClientsAdapter.notifyItemAdd();
                dialog.dismiss();
            });
            dialog.show();
        });

    }

    private List<Client> getList(){
        db = DatabaseConnection.getDbInstance(getContext());
        clientList = new ArrayList<>();
        clientList = db.ClientDao().getAllClients();
        return clientList;
    }
}