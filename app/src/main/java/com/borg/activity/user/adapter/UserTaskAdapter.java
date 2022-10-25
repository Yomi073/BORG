package com.borg.activity.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.activity.Login;
import com.borg.activity.user.fragment.UserInvoiceFragment;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.ViewUserTasks;

import java.util.List;

public class UserTaskAdapter extends RecyclerView.Adapter<UserTaskAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<ViewUserTasks> taskList;

    public UserTaskAdapter(Context context, List<ViewUserTasks> taskList) {
        this.context = context;
        this.taskList = taskList;
        this.db = DatabaseConnection.getDbInstance(context);
    }

    @NonNull
    @Override
    public UserTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_user_task, parent,false);
        return new UserTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTaskAdapter.ViewHolder holder, int position) {
        if(taskList != null && taskList.size() > 0){
            db = DatabaseConnection.getDbInstance(context);
            ViewUserTasks model = taskList.get(position);

            //on row click
            holder.holderItemDetails.setOnClickListener(v -> {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new UserInvoiceFragment(position, taskList);//Proslijedi poziciju kliknutog redka u novi fragment
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.navHostFragmentUser, myFragment).addToBackStack(null).commit();
            });

            //initialise columns
            holder.tab_user_task_col1.setText(String.valueOf(model.getTask_id()));
            holder.tab_user_task_col2.setText(model.getClient_firstName());
            holder.tab_user_task_col3.setText(model.getClient_address());
            holder.tab_user_task_col4.setText(model.getTask_date());

        }
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void notifyItemAdd(){
        db = DatabaseConnection.getDbInstance(context);
        taskList=db.TaskDao().getUserTasks(Login.getLoggedUser());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_user_task_col1,tab_user_task_col2,tab_user_task_col3,tab_user_task_col4;
        LinearLayout holderItemDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holderItemDetails = itemView.findViewById(R.id.taskButton);
            tab_user_task_col1 = itemView.findViewById(R.id.tab_user_task_col1);
            tab_user_task_col2 = itemView.findViewById(R.id.tab_user_task_col2);
            tab_user_task_col3 = itemView.findViewById(R.id.tab_user_task_col3);
            tab_user_task_col4 = itemView.findViewById(R.id.tab_user_task_col4);
        }
    }
}
