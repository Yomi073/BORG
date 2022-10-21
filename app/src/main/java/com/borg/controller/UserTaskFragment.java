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
import com.borg.model.ViewUsersTasks;

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

        getList();

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        UserTaskAdapter userTaskAdapter = new UserTaskAdapter(getContext(),taskList);
        recyclerView.setAdapter(userTaskAdapter);
        //userTaskAdapter.notifyDataSetChanged();
    }

    private List<ViewUsersTasks> getList(){
        List<ViewUsersTasks> taskList = null; //db.TaskDao().getUsersTasks(getLoggedUser());
        taskList.add(new ViewUsersTasks(1,"ante", "soro", "11.11.2012"));
        taskList.add(new ViewUsersTasks(2,"ante", "soro", "11.11.2012"));

        taskList.add(new ViewUsersTasks(3,"ante", "soro", "11.11.2012"));
        return taskList;
    }
}