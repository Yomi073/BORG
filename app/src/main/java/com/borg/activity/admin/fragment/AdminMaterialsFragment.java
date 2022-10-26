package com.borg.activity.admin.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.borg.R;
import com.borg.activity.admin.adapter.AdminMaterialsAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.MaterialStock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminMaterialsFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
    List<MaterialStock> materialsList;

    public AdminMaterialsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_materials, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag("TASK");
        if (fragment != null) activity.getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        recyclerView = view.findViewById(R.id.recycler_view_admin_materials);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdminMaterialsAdapter adminMaterialsAdapter = new AdminMaterialsAdapter(getContext(),getList());
        recyclerView.setAdapter(adminMaterialsAdapter);

        //Add new material button
        ImageButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener( v -> {

            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_add_material);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.show();
            dialog.getWindow().setAttributes(lp);

            EditText name = (EditText) dialog.findViewById(R.id.add_material_txt_material_name);
            EditText quantity = (EditText) dialog.findViewById(R.id.add_material_txt_material_quantity);
            EditText purchasePrice = (EditText) dialog.findViewById(R.id.add_material_txt_purchase_price);
            EditText sellingPrice = (EditText) dialog.findViewById(R.id.add_material_txt_selling_price);

            Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
            dialogButton.setOnClickListener(v1 -> {
                db.MaterialStockDao().insertNewMaterialStock(name.getText().toString(),Double.parseDouble(quantity.getText().toString()),Double.parseDouble(purchasePrice.getText().toString()),Double.parseDouble(sellingPrice.getText().toString()));
                adminMaterialsAdapter.notifyItemAdd();
                dialog.dismiss();
            });
            dialog.show();
        });

    }

    private List<MaterialStock> getList(){
        materialsList = new ArrayList<>();
        materialsList = db.MaterialStockDao().getAllMaterials();
        return materialsList;
    }
}