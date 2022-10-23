package com.borg.activity.user.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.ViewUserTasks;

import java.util.List;

public class UserTaskAdapter extends RecyclerView.Adapter<UserTaskAdapter.ViewHolder> {

    Context context;
    List<ViewUserTasks> taskList;

    public UserTaskAdapter(Context context, List<ViewUserTasks> taskList) {
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
            ViewUserTasks model = taskList.get(position);
            holder.taskDetails.setOnClickListener(v -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Write your message here.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        (dialog, id) -> dialog.cancel());

                builder1.setNegativeButton(
                        "No",
                        (dialog, id) -> dialog.cancel());

                AlertDialog alert11 = builder1.create();
                alert11.show();
            });
            holder.tab_user_task_col1.setText(String.valueOf(model.getId()));
            holder.tab_user_task_col2.setText(model.getFirstName());
            holder.tab_user_task_col3.setText(model.getAddress());
            holder.tab_user_task_col4.setText(model.getDate());
            holder.deleteButton.setOnClickListener( v -> {
                DatabaseConnection db = DatabaseConnection.getDbInstance(this.context);
                db.TaskDao().deleteTaskId(model.getId());
                taskList.remove(position);
                notifyItemRemoved(position);
            });

        }
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_user_task_col1,tab_user_task_col2,tab_user_task_col3,tab_user_task_col4;
        ImageButton deleteButton;
        LinearLayout taskDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDetails = itemView.findViewById(R.id.taskButton);
            tab_user_task_col1 = itemView.findViewById(R.id.tab_user_task_col1);
            tab_user_task_col2 = itemView.findViewById(R.id.tab_user_task_col2);
            tab_user_task_col3 = itemView.findViewById(R.id.tab_user_task_col3);
            tab_user_task_col4 = itemView.findViewById(R.id.tab_user_task_col4);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
