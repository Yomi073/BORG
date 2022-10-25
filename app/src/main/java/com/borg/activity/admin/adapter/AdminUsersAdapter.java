package com.borg.activity.admin.adapter;

import android.content.Context;
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
import com.borg.model.database.User;

import java.util.List;

public class AdminUsersAdapter extends RecyclerView.Adapter<AdminUsersAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<User> userList;

    public AdminUsersAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        this.db = DatabaseConnection.getDbInstance(context);
    }

    @NonNull
    @Override
    public AdminUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_admin_users, parent,false);
        return new AdminUsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminUsersAdapter.ViewHolder holder, int position) {
        if(userList != null && userList.size() > 0){
            db = DatabaseConnection.getDbInstance(context);
            User model = userList.get(position);
            //initialise columns
            holder.tab_admin_users_col1.setText(String.valueOf(model.getId()));
            holder.tab_admin_users_col2.setText(model.getFirstName());
            holder.tab_admin_users_col3.setText(model.getLastName());
            holder.tab_admin_users_col4.setText(model.getAddress());
            holder.tab_admin_users_col5.setText(String.valueOf(model.getPhoneNumber()));
            holder.tab_admin_users_col6.setText(model.getEmail());
            holder.tab_admin_users_col7.setText(model.getUserName());
            holder.tab_admin_users_col8.setText(String.valueOf(model.getPassword()));
            holder.tab_admin_users_col9.setText(db.UserDao().getRoleByFK(model.getRole_FK()));
            //delete materials button
            holder.deleteButton.setOnClickListener( v -> {
                db.UserDao().deleteUserId(model.getId());
                userList.remove(position);
                notifyItemRemoved(position);
            });

        }
    }

    @Override
    public int getItemCount() {return userList.size();}

    public void notifyItemAdd(){
        db = DatabaseConnection.getDbInstance(context);
        userList=db.UserDao().getAllUsers();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_admin_users_col1,tab_admin_users_col2,tab_admin_users_col3,tab_admin_users_col4,tab_admin_users_col5,tab_admin_users_col6,tab_admin_users_col7,tab_admin_users_col8,tab_admin_users_col9;
        ImageButton deleteButton;
        LinearLayout holderItemDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holderItemDetails = itemView.findViewById(R.id.taskButton);
            tab_admin_users_col1 = itemView.findViewById(R.id.tab_admin_users_col1);
            tab_admin_users_col2 = itemView.findViewById(R.id.tab_admin_users_col2);
            tab_admin_users_col3 = itemView.findViewById(R.id.tab_admin_users_col3);
            tab_admin_users_col4 = itemView.findViewById(R.id.tab_admin_users_col4);
            tab_admin_users_col5 = itemView.findViewById(R.id.tab_admin_users_col5);
            tab_admin_users_col6 = itemView.findViewById(R.id.tab_admin_users_col6);
            tab_admin_users_col7 = itemView.findViewById(R.id.tab_admin_users_col7);
            tab_admin_users_col8 = itemView.findViewById(R.id.tab_admin_users_col8);
            tab_admin_users_col9 = itemView.findViewById(R.id.tab_admin_users_col9);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
