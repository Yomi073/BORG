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
import android.widget.ImageButton;
import android.widget.Spinner;

import com.borg.R;
import com.borg.activity.admin.adapter.AdminTaskAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.Client;
import com.borg.model.database.User;
import com.borg.model.database.ViewUserTasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminInvoiceFragment extends Fragment {

    DatabaseConnection db;
    RecyclerView recyclerView;
    List<ViewUserTasks> tasksList_admin;

    public AdminInvoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_invoice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = DatabaseConnection.getDbInstance(getContext());

        recyclerView = view.findViewById(R.id.recycler_view_invoice);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdminTaskAdapter adminTaskAdapter = new AdminTaskAdapter(getActivity(),getList());
        recyclerView.setAdapter(adminTaskAdapter);

        ImageButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener( v -> {
            /* dodaj postojece materijale iz baze na ovaj racun
            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_add_invoice_material);

            Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
            dialogButton.setOnClickListener(v1 -> {
                String userSelected[] = String.valueOf(spinnerUser.getSelectedItem()).split(" ");
                String clientSelected[] = String.valueOf(spinnerClient.getSelectedItem()).split(" ");
                db.TaskDao().insertNewTask(Integer.parseInt(userSelected[0]),Integer.parseInt(clientSelected[0]),new Date());
                adminTaskAdapter.notifyItemAdd();
                dialog.dismiss();
            });
            dialog.show();
            */
        });

    }


    private List<ViewUserTasks> getList(){
        db = DatabaseConnection.getDbInstance(getContext());
        tasksList_admin = new ArrayList<>();
        tasksList_admin = db.TaskDao().getAllTasks();
        return tasksList_admin;
    }
}