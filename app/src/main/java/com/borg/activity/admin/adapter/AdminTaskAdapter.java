package com.borg.activity.admin.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.activity.Login;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.Task;
import com.borg.model.database.ViewUserTasks;

import java.util.List;

public class AdminTaskAdapter extends RecyclerView.Adapter<AdminTaskAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<ViewUserTasks> tasksListAdmin;

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
            ViewUserTasks model = tasksListAdmin.get(position);
            holder.taskDetails.setOnClickListener(v -> {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_user_invoice);

                TextView txtClientName = dialog.findViewById(R.id.txtClientName);
                TextView txtClientAddress = dialog.findViewById(R.id.txtClientAddress);
                TextView txtClientPhone = dialog.findViewById(R.id.txtClientPhone);
                TextView txtClientID = dialog.findViewById(R.id.txtClientID);
                TextView txtTaskID = dialog.findViewById(R.id.txtTaskID);
                TextView txtTaskDate = dialog.findViewById(R.id.txtTaskDate);
                TextView txtTaskTotalSum = dialog.findViewById(R.id.txtTaskTotalSum);

                txtClientName.setText(model.getClient_firstName().toString());
                txtClientAddress.setText(model.getClient_address().toString());
                //txtClientPhone.setText(model.getClient_phoneNumber().toString());
                txtClientID.setText(model.getClient_id().toString());
                txtTaskID.setText(model.getTask_id().toString());
                //txtTaskDate.setText(model.getTask_date().toString());
                //dodati sumu cijene svih itema racuna
                //txtTaskTotalSum.setText(model.get);


                dialog.show();
            });

            holder.tab_admin_tasks_col1.setText(String.valueOf(model.getTask_id()));
            holder.tab_admin_tasks_col2.setText(model.getClient_firstName());
            holder.tab_admin_tasks_col3.setText(model.getClient_address());
            holder.tab_admin_tasks_col4.setText(model.getTask_date().toString());

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
        tasksListAdmin=db.TaskDao().getUserTasks(Login.getLoggedUser());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_admin_tasks_col1,tab_admin_tasks_col2,tab_admin_tasks_col3,tab_admin_tasks_col4;
        ImageButton deleteButton;
        LinearLayout taskDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDetails = itemView.findViewById(R.id.taskButton);
            tab_admin_tasks_col1 = itemView.findViewById(R.id.tab_admin_tasks_col1);
            tab_admin_tasks_col2 = itemView.findViewById(R.id.tab_admin_tasks_col2);
            tab_admin_tasks_col3 = itemView.findViewById(R.id.tab_admin_tasks_col3);
            tab_admin_tasks_col4 = itemView.findViewById(R.id.tab_admin_tasks_col4);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}