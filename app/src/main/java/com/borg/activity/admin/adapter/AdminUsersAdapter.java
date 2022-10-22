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
import com.borg.model.database.ViewAdminUsers;

import java.util.List;

public class AdminUsersAdapter extends RecyclerView.Adapter<AdminUsersAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<ViewAdminUsers> clientList;

    public AdminUsersAdapter(Context context, List<ViewAdminUsers> clientList) {
        this.context = context;
        this.clientList = clientList;
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
        if(clientList != null && clientList.size() > 0){
            ViewAdminUsers model = clientList.get(position);
            holder.tab_admin_users_col1.setText(String.valueOf(model.getId()));
            holder.tab_admin_users_col2.setText(model.getFirstName());
            holder.tab_admin_users_col3.setText(model.getLastName());
            holder.tab_admin_users_col4.setText(model.getAddress());
            holder.tab_admin_users_col5.setText(String.valueOf(model.getPhoneNumber()));
            holder.tab_admin_users_col6.setText(model.getEmail());
            holder.tab_admin_users_col7.setText(model.getUserName());
            holder.tab_admin_users_col8.setText(String.valueOf(model.getPassword()));
            holder.tab_admin_users_col9.setText(db.UserDao().getRoleByFK(model.getRole_FK()));

        }
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_admin_users_col1,tab_admin_users_col2,tab_admin_users_col3,tab_admin_users_col4,tab_admin_users_col5,tab_admin_users_col6,tab_admin_users_col7,tab_admin_users_col8,tab_admin_users_col9;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tab_admin_users_col1 = itemView.findViewById(R.id.tab_admin_users_col1);
            tab_admin_users_col2 = itemView.findViewById(R.id.tab_admin_users_col2);
            tab_admin_users_col3 = itemView.findViewById(R.id.tab_admin_users_col3);
            tab_admin_users_col4 = itemView.findViewById(R.id.tab_admin_users_col4);
            tab_admin_users_col5 = itemView.findViewById(R.id.tab_admin_users_col5);
            tab_admin_users_col6 = itemView.findViewById(R.id.tab_admin_users_col6);
            tab_admin_users_col7 = itemView.findViewById(R.id.tab_admin_users_col7);
            tab_admin_users_col8 = itemView.findViewById(R.id.tab_admin_users_col8);
            tab_admin_users_col9 = itemView.findViewById(R.id.tab_admin_users_col9);
        }
    }
}
