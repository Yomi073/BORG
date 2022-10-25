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
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.activity.admin.fragment.AdminInvoiceFragment;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.MaterialStock;
import com.borg.model.database.MaterialConsumption;

import java.util.List;

public class AdminInvoiceAdapter extends RecyclerView.Adapter<AdminInvoiceAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<MaterialConsumption> materialConsumption;

    public AdminInvoiceAdapter(Context context, List<MaterialConsumption> materialConsumption) {
        this.context = context;
        this.materialConsumption = materialConsumption;
        this.db = DatabaseConnection.getDbInstance(context);
    }

    @NonNull
    @Override
    public AdminInvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_invoice, parent,false);
        return new AdminInvoiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminInvoiceAdapter.ViewHolder holder, int position) {
        if(materialConsumption != null && materialConsumption.size() > 0){
            db = DatabaseConnection.getDbInstance(context);
            MaterialConsumption model = materialConsumption.get(position);
            //on row click

                //TODO
                //postavit vrijenosti fragmenta: txtClientName  txtClientAddress  txtClientPhone  txtClientID  txtTaskID  txtTaskDate  txtTaskTotalSum;

            //initialise columns
            holder.tab_invoice_col1.setText(model.getId().toString());
            holder.tab_invoice_col2.setText(model.getMaterialName().toString());
            holder.tab_invoice_col3.setText(model.getMaterialQuantity().toString());
            holder.tab_invoice_col4.setText(model.getMaterialSellingPrice().toString());
            holder.tab_invoice_col5.setText(model.getMaterialPriceSum().toString());
            //delete materials button
            holder.deleteButton.setOnClickListener( v -> {
                db.MaterialConsumptionDao().deleteTaskId(model.getTask_id());
                materialConsumption.remove(position);
                notifyItemRemoved(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return materialConsumption.size();
    }

    public void notifyItemAdd(){
        db = DatabaseConnection.getDbInstance(context);
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        //treba popravit SQL UPIT DA VRACA LISTU OTISA SAM SPAVAT SUTRA CU
        materialConsumption=db.MaterialConsumptionDao().getInvoiceByTaskID();
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