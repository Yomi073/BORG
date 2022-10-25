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
import com.borg.model.database.MaterialStock;

import java.util.List;

public class AdminMaterialsAdapter extends RecyclerView.Adapter<AdminMaterialsAdapter.ViewHolder> {

    Context context;
    DatabaseConnection db;
    List<MaterialStock> materialsList;

    public AdminMaterialsAdapter(Context context, List<MaterialStock> materialsList) {
        this.context = context;
        this.materialsList = materialsList;
        this.db = DatabaseConnection.getDbInstance(context);
    }

    @NonNull
    @Override
    public AdminMaterialsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_admin_materials, parent,false);
        return new AdminMaterialsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminMaterialsAdapter.ViewHolder holder, int position) {
        if (materialsList != null && materialsList.size() > 0) {
            db = DatabaseConnection.getDbInstance(context);
            MaterialStock model = materialsList.get(position);
            //initialise columns
            holder.tab_admin_materials_col1.setText(String.valueOf(model.getId()));
            holder.tab_admin_materials_col2.setText(model.getName());
            holder.tab_admin_materials_col3.setText(String.valueOf(model.getQuantity()));
            holder.tab_admin_materials_col4.setText(String.valueOf(model.getPurchasePrice()));
            holder.tab_admin_materials_col5.setText(String.valueOf(model.getSellingPrice()));
            //delete materials button
            holder.deleteButton.setOnClickListener(v -> {
                db.MaterialStockDao().deleteMaterialId(model.getId());
                materialsList.remove(position);
                notifyItemRemoved(position);
            });
        }
    }

    @Override
    public int getItemCount() {return materialsList.size();
    }
    public void notifyItemAdd(){
        db = DatabaseConnection.getDbInstance(context);
        materialsList=db.MaterialStockDao().getAllMaterials();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tab_admin_materials_col1,tab_admin_materials_col2,tab_admin_materials_col3,tab_admin_materials_col4,tab_admin_materials_col5;
        ImageButton deleteButton;
        LinearLayout materialsList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            materialsList = itemView.findViewById(R.id.taskButton);
            tab_admin_materials_col1 = itemView.findViewById(R.id.tab_admin_materials_col1);
            tab_admin_materials_col2 = itemView.findViewById(R.id.tab_admin_materials_col2);
            tab_admin_materials_col3 = itemView.findViewById(R.id.tab_admin_materials_col3);
            tab_admin_materials_col4 = itemView.findViewById(R.id.tab_admin_materials_col4);
            tab_admin_materials_col5 = itemView.findViewById(R.id.tab_admin_materials_col5);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}