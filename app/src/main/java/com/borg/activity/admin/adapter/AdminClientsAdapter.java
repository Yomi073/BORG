package com.borg.activity.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.model.database.ViewAdminClients;

import java.util.List;

public class AdminClientsAdapter extends RecyclerView.Adapter<AdminClientsAdapter.ViewHolder> {

    Context context;
    List<ViewAdminClients> clientList;

    public AdminClientsAdapter(Context context, List<ViewAdminClients> clientList) {
        this.context = context;
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public AdminClientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_admin_clients, parent,false);
        return new AdminClientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminClientsAdapter.ViewHolder holder, int position) {
        if(clientList != null && clientList.size() > 0){
            ViewAdminClients model = clientList.get(position);
            holder.tab_admin_clients_col1.setText(String.valueOf(model.getId()));
            holder.tab_admin_clients_col2.setText(model.getFirstName());
            holder.tab_admin_clients_col3.setText(model.getLastName());
            holder.tab_admin_clients_col4.setText(model.getAddress());
            holder.tab_admin_clients_col5.setText(String.valueOf(model.getPhoneNumber()));
            holder.tab_admin_clients_col6.setText(model.getEmail());

        }
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_admin_clients_col1,tab_admin_clients_col2,tab_admin_clients_col3,tab_admin_clients_col4,tab_admin_clients_col5,tab_admin_clients_col6;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tab_admin_clients_col1 = itemView.findViewById(R.id.tab_admin_clients_col1);
            tab_admin_clients_col2 = itemView.findViewById(R.id.tab_admin_clients_col2);
            tab_admin_clients_col3 = itemView.findViewById(R.id.tab_admin_clients_col3);
            tab_admin_clients_col4 = itemView.findViewById(R.id.tab_admin_clients_col4);
            tab_admin_clients_col5 = itemView.findViewById(R.id.tab_admin_clients_col5);
            tab_admin_clients_col6 = itemView.findViewById(R.id.tab_admin_clients_col6);
        }
    }
}
