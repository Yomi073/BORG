package com.borg.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.adapter.UserTaskAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.ViewUsersTasks;

import java.util.ArrayList;
import java.util.List;


public class UserTaskFragment extends Fragment {

    RecyclerView recyclerView;
    List<ViewUsersTasks> taskList;

    public UserTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_task, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        UserTaskAdapter userTaskAdapter = new UserTaskAdapter(getContext(),getList());
        recyclerView.setAdapter(userTaskAdapter);
        //userTaskAdapter.notifyDataSetChanged();
    }

    private List<ViewUsersTasks> getList(){
        DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
        List<ViewUsersTasks> dasd = new ArrayList<>();
        List<ViewUsersTasks> taskList = db.TaskDao().getUsersTasks(1);
        return taskList;
    }
}