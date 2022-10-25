package com.borg.activity.admin.adapter;

import android.content.Context;
import android.icu.text.Edits;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.activity.admin.fragment.AdminInvoiceFragment;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.MaterialStock;
import com.borg.model.database.ViewInvoice;
import com.borg.model.database.ViewUserTasks;

import java.util.Iterator;
import java.util.List;

public class AdminInvoiceAdapter extends RecyclerView.Adapter<AdminInvoiceAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<ViewInvoice> viewInvoice;
    Integer selected_task;

    public AdminInvoiceAdapter(Context context, List<ViewInvoice> viewInvoice, Integer selected_task) {
        this.context = context;
        this.viewInvoice = viewInvoice;
        this.db = DatabaseConnection.getDbInstance(context);
        this.selected_task = selected_task;
    }

    @NonNull
    @Override
    public AdminInvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_invoice, parent,false);
        return new AdminInvoiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminInvoiceAdapter.ViewHolder holder, int position) {
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

            //delete materials button
            holder.deleteButton.setOnClickListener( v -> {
                db.MaterialConsumptionDao().deleteMaterialConsumptionById(model.getIndeks());
                viewInvoice.remove(position);
                notifyItemRemoved(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return viewInvoice.size();
    }

    public void notifyItemAdd(){
        db = DatabaseConnection.getDbInstance(context);
        //treba popravit SQL UPIT DA VRACA LISTU OTISA SAM SPAVAT SUTRA CU
        viewInvoice=db.MaterialConsumptionDao().getInvoiceByTaskID(viewInvoice.get(selected_task).getId_task());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_invoice_col1,tab_invoice_col2,tab_invoice_col3,tab_invoice_col4,tab_invoice_col5;
        ImageButton deleteButton;
        LinearLayout holderItemDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holderItemDetails = itemView.findViewById(R.id.taskButton);
            tab_invoice_col1 = itemView.findViewById(R.id.tab_invoice_col1);
            tab_invoice_col2 = itemView.findViewById(R.id.tab_invoice_col2);
            tab_invoice_col3 = itemView.findViewById(R.id.tab_invoice_col3);
            tab_invoice_col4 = itemView.findViewById(R.id.tab_invoice_col4);
            tab_invoice_col5 = itemView.findViewById(R.id.tab_invoice_col5);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}