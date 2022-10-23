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
import com.borg.activity.admin.adapter.AdminTaskAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.Task;

import java.util.ArrayList;
import java.util.List;

public class AdminTaskFragment extends Fragment {

    RecyclerView recyclerView;
    List<Task> tasksList_admin;

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
    }

    private List<Task> getList(){
        DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
        tasksList_admin = new ArrayList<>();
        tasksList_admin = db.TaskDao().getAllTasks();
        return tasksList_admin;
    }
}