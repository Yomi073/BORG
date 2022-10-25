package com.borg.activity.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.ViewInvoice;

import java.util.List;

public class UserInvoiceAdapter extends RecyclerView.Adapter<UserInvoiceAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<ViewInvoice> viewInvoice;
    Integer selected_task;

    public UserInvoiceAdapter(Context context, List<ViewInvoice> viewInvoice, Integer selected_task) {
        this.context = context;
        this.viewInvoice = viewInvoice;
        this.db = DatabaseConnection.getDbInstance(context);
        this.selected_task = selected_task;
    }

    @NonNull
    @Override
    public UserInvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_invoice, parent,false);
        return new UserInvoiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInvoiceAdapter.ViewHolder holder, int position) {
        if(viewInvoice != null && viewInvoice.size() > 0){
            db = DatabaseConnection.getDbInstance(context);
            ViewInvoice model = viewInvoice.get(position);
            //on row click


            //initialise columns
            holder.tab_invoice_col1.setText(model.getIndeks().toString());
            holder.tab_invoice_col2.setText(model.getMaterial_name().toString());
            holder.tab_invoice_col3.setText(model.getQuantity_on_invoice().toString());
            holder.tab_invoice_col4.setText(model.getSellingPrice().toString());
            holder.tab_invoice_col5.setText(model.getSum().toString());

        }
    }

    @Override
    public int getItemCount() {
        return viewInvoice.size();
    }

    public void notifyItemAdd(){
        db = DatabaseConnection.getDbInstance(context);
        viewInvoice=db.MaterialConsumptionDao().getInvoiceByTaskID(viewInvoice.get(selected_task).getId_task());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_invoice_col1,tab_invoice_col2,tab_invoice_col3,tab_invoice_col4,tab_invoice_col5;
        LinearLayout holderItemDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holderItemDetails = itemView.findViewById(R.id.taskButton);
            tab_invoice_col1 = itemView.findViewById(R.id.tab_invoice_col1);
            tab_invoice_col2 = itemView.findViewById(R.id.tab_invoice_col2);
            tab_invoice_col3 = itemView.findViewById(R.id.tab_invoice_col3);
            tab_invoice_col4 = itemView.findViewById(R.id.tab_invoice_col4);
            tab_invoice_col5 = itemView.findViewById(R.id.tab_invoice_col5);
        }
    }
}