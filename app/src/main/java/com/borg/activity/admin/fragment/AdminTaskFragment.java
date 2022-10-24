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
import com.borg.activity.admin.adapter.AdminTaskAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.Task;
import com.borg.model.database.ViewUserTasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminTaskFragment extends Fragment {

    RecyclerView recyclerView;
    List<ViewUserTasks> tasksList_admin;

    public AdminTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_admin_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        AdminTaskAdapter adminTaskAdapter = new AdminTaskAdapter(getContext(),getList());
        recyclerView.setAdapter(adminTaskAdapter);

        ImageButton addButton = view.findViewById(R.id.addButton);

        addButton.setOnClickListener( v -> {
            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_add_task);

            //TO DO: najbolje ti je napraviti spinere na ovom layout, spinere punis iz baze sa posotjecim korisnicima
            //TO DO: ovdje mozes dodati ako stignes da odaberes vrijeme kroz datapicker
            //TO DO: i onda sve to kroz ovaj dole upit saljes, ovo je brzi primjer samo
            EditText et_user_id = (EditText) dialog.findViewById(R.id.plain_text_input_id_user);
            EditText et_client_id = (EditText) dialog.findViewById(R.id.plain_text_input_id_client);
            Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
            dialogButton.setOnClickListener(v1 -> {
                DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
                db.TaskDao().insertNewTask(Integer.parseInt(et_user_id.getText().toString()),Integer.parseInt(et_client_id.getText().toString()),new Date());
                adminTaskAdapter.notifyItemAdd();
                dialog.dismiss();
            });
            dialog.show();
        });
    }

    private List<ViewUserTasks> getList(){
        DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
        tasksList_admin = new ArrayList<>();
        tasksList_admin = db.TaskDao().getAllTasks();
        return tasksList_admin;
    }
}