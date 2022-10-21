package com.borg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.model.ViewUsersTasks;

import java.util.List;

public class UserTaskAdapter extends RecyclerView.Adapter<UserTaskAdapter.ViewHolder> {

    Context context;
    List<ViewUsersTasks> taskList;

    public UserTaskAdapter(Context context, List<ViewUsersTasks> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_user_task, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTaskAdapter.ViewHolder holder, int position) {
        if(taskList != null && taskList.size() > 0){
            ViewUsersTasks model = taskList.get(position);
            holder.tab_user_task_col1.setText(model.getId());
            holder.tab_user_task_col2.setText(model.getFirstname());
            holder.tab_user_task_col3.setText(model.getAddress());
            holder.tab_user_task_col4.setText((CharSequence) model.getDate());

        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_user_task_col1,tab_user_task_col2,tab_user_task_col3,tab_user_task_col4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tab_user_task_col1 = itemView.findViewById(R.id.tab_user_task_col1);
            tab_user_task_col2 = itemView.findViewById(R.id.tab_user_task_col2);
            tab_user_task_col3 = itemView.findViewById(R.id.tab_user_task_col3);
            tab_user_task_col4 = itemView.findViewById(R.id.tab_user_task_col4);
        }
    }
}
