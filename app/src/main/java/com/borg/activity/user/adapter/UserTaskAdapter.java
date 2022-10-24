package com.borg.activity.user.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.activity.Login;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.ViewUserTasks;

import java.util.List;

public class UserTaskAdapter extends RecyclerView.Adapter<UserTaskAdapter.ViewHolder> {

    Context context;
    List<ViewUserTasks> taskList;
    DatabaseConnection db = DatabaseConnection.getDbInstance(this.context);

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

                //opet popravit datum sa type converterom
                //txtTaskDate.setText(model.getTask_date().toString());
                //dodati sumu cijene svih itema racuna
                //txtTaskTotalSum.setText(model.get);


                dialog.show();
            });
            holder.tab_user_task_col1.setText(String.valueOf(model.getTask_id()));
            holder.tab_user_task_col2.setText(model.getClient_firstName());
            holder.tab_user_task_col3.setText(model.getClient_address());
            holder.tab_user_task_col4.setText(model.getTask_date().toString());
        }
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public int getColumnWidth(int column) {
        int pom=0,i;
        List<ViewUserTasks> pomList;

        for(i=0;i<taskList.size();i++){
            pomList = (List) taskList.get(i);

            if(pom < pomList.get(column).toString().length()) {
                pom = pomList.get(column).toString().length();
            }
        }
        return pom;
    }

    public void notifyItemAdd(){
        //potrebno da dodas i azuriras listu trenutno, bez da mijenjas ekran odma ti se pojavi novi task
        taskList=db.TaskDao().getUserTasks(Login.getLoggedUser());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_user_task_col1,tab_user_task_col2,tab_user_task_col3,tab_user_task_col4;
        LinearLayout taskDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDetails = itemView.findViewById(R.id.taskButton);
            tab_user_task_col1 = itemView.findViewById(R.id.tab_user_task_col1);
            tab_user_task_col2 = itemView.findViewById(R.id.tab_user_task_col2);
            tab_user_task_col3 = itemView.findViewById(R.id.tab_user_task_col3);
            tab_user_task_col4 = itemView.findViewById(R.id.tab_user_task_col4);
        }
    }
}
