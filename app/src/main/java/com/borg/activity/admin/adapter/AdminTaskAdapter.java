package com.borg.activity.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.ViewAdminTasks;

import java.util.List;

public class AdminTaskAdapter extends RecyclerView.Adapter<AdminTaskAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<ViewAdminTasks> tasksListAdmin;

    public AdminTaskAdapter(Context context, List<ViewAdminTasks> tasksListAdmin) {
        this.context = context;
        this.tasksListAdmin = tasksListAdmin;
        this.db = DatabaseConnection.getDbInstance(context);
    }

    @NonNull
    @Override
    public AdminTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_admin_tasks, parent,false);
        return new AdminTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTaskAdapter.ViewHolder holder, int position) {
        if(tasksListAdmin != null && tasksListAdmin.size() > 0){
            ViewAdminTasks model = tasksListAdmin.get(position);
            holder.tab_admin_tasks_col1.setText(String.valueOf(model.getId()));
            holder.tab_admin_tasks_col2.setText(db.TaskDao().getUserNameByFK(model.getUser_FK()));
            holder.tab_admin_tasks_col3.setText(db.TaskDao().getClientNameByFK(model.getClient_FK()));
            holder.tab_admin_tasks_col4.setText("gpowjeglihsldighslidghlisdhglsdhgosdhgoshdgoshdgohsdgohsdog");

        }
    }

    @Override
    public int getItemCount() {
        return tasksListAdmin.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_admin_tasks_col1,tab_admin_tasks_col2,tab_admin_tasks_col3,tab_admin_tasks_col4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tab_admin_tasks_col1 = itemView.findViewById(R.id.tab_admin_tasks_col1);
            tab_admin_tasks_col2 = itemView.findViewById(R.id.tab_admin_tasks_col2);
            tab_admin_tasks_col3 = itemView.findViewById(R.id.tab_admin_tasks_col3);
            tab_admin_tasks_col4 = itemView.findViewById(R.id.tab_admin_tasks_col4);
        }
    }
}