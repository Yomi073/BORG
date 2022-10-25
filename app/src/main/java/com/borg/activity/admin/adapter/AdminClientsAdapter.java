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
import com.borg.model.database.Client;

import java.util.List;

public class AdminClientsAdapter extends RecyclerView.Adapter<AdminClientsAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<Client> clientList;

    public AdminClientsAdapter(Context context, List<Client> clientList) {
        this.context = context;
        this.clientList = clientList;
        this.db = DatabaseConnection.getDbInstance(context);
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
            db = DatabaseConnection.getDbInstance(context);
            Client model = clientList.get(position);
            //initialise columns
            holder.tab_admin_clients_col1.setText(String.valueOf(model.getId()));
            holder.tab_admin_clients_col2.setText(model.getFirstName());
            holder.tab_admin_clients_col3.setText(model.getLastName());
            holder.tab_admin_clients_col4.setText(model.getAddress());
            holder.tab_admin_clients_col5.setText(String.valueOf(model.getPhoneNumber()));
            holder.tab_admin_clients_col6.setText(model.getEmail());
            //delete materials button
            holder.deleteButton.setOnClickListener(v -> {
                db.MaterialStockDao().deleteMaterialId(model.getId());
                clientList.remove(position);
                notifyItemRemoved(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public void notifyItemAdd(){
        db = DatabaseConnection.getDbInstance(context);
        clientList=db.ClientDao().getAllClients();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_admin_clients_col1,tab_admin_clients_col2,tab_admin_clients_col3,tab_admin_clients_col4,tab_admin_clients_col5,tab_admin_clients_col6;
        ImageButton deleteButton;
        LinearLayout holderItemDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holderItemDetails = itemView.findViewById(R.id.taskButton);
            tab_admin_clients_col1 = itemView.findViewById(R.id.tab_admin_clients_col1);
            tab_admin_clients_col2 = itemView.findViewById(R.id.tab_admin_clients_col2);
            tab_admin_clients_col3 = itemView.findViewById(R.id.tab_admin_clients_col3);
            tab_admin_clients_col4 = itemView.findViewById(R.id.tab_admin_clients_col4);
            tab_admin_clients_col5 = itemView.findViewById(R.id.tab_admin_clients_col5);
            tab_admin_clients_col6 = itemView.findViewById(R.id.tab_admin_clients_col6);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
