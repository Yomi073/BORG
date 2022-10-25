package com.borg.activity.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.activity.admin.fragment.AdminInvoiceFragment;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.ViewUserTasks;

import java.util.List;

public class AdminTaskAdapter extends RecyclerView.Adapter<AdminTaskAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<ViewUserTasks> tasksListAdmin;
    Integer clickedTask_ID;

    public AdminTaskAdapter(Context context, List<ViewUserTasks> tasksListAdmin) {
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
            db = DatabaseConnection.getDbInstance(context);
            ViewUserTasks model = tasksListAdmin.get(position);

            //on row click
            holder.holderItemDetails.setOnClickListener(v -> {

                clickedTask_ID = model.getTask_id();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new AdminInvoiceFragment(clickedTask_ID, tasksListAdmin);//Proslijedi poziciju kliknutog redka u novi fragment
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.navHostFragmentAdmin, myFragment).addToBackStack(null).commit();
            });

            //initialise columns
            holder.tab_admin_tasks_col1.setText(String.valueOf(model.getTask_id()));
            holder.tab_admin_tasks_col2.setText(model.getClient_firstName());
            holder.tab_admin_tasks_col3.setText(model.getClient_address());
            holder.tab_admin_tasks_col4.setText(model.getTask_date().toString());

            //delete materials button
            holder.deleteButton.setOnClickListener( v -> {
                db.TaskDao().deleteTaskId(model.getTask_id());
                tasksListAdmin.remove(position);
                notifyItemRemoved(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return tasksListAdmin.size();
    }

    public void notifyItemAdd(){
        db = DatabaseConnection.getDbInstance(context);
        tasksListAdmin=db.TaskDao().getAllTasks();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_admin_tasks_col1,tab_admin_tasks_col2,tab_admin_tasks_col3,tab_admin_tasks_col4;
        ImageButton deleteButton;
        LinearLayout holderItemDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holderItemDetails = itemView.findViewById(R.id.taskButton);
            tab_admin_tasks_col1 = itemView.findViewById(R.id.tab_admin_tasks_col1);
            tab_admin_tasks_col2 = itemView.findViewById(R.id.tab_admin_tasks_col2);
            tab_admin_tasks_col3 = itemView.findViewById(R.id.tab_admin_tasks_col3);
            tab_admin_tasks_col4 = itemView.findViewById(R.id.tab_admin_tasks_col4);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}