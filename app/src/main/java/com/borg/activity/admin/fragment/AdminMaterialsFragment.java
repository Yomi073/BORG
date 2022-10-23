package com.borg.activity.admin.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borg.R;
import com.borg.activity.admin.adapter.AdminMaterialsAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.MaterialStock;

import java.util.ArrayList;
import java.util.List;

public class AdminMaterialsFragment extends Fragment {

    RecyclerView recyclerView;
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

        recyclerView = view.findViewById(R.id.recycler_view_admin_materials);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        AdminMaterialsAdapter adminMaterialsAdapter = new AdminMaterialsAdapter(getContext(),getList());
        recyclerView.setAdapter(adminMaterialsAdapter);
    }

    private List<MaterialStock> getList(){
        DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
        materialsList = new ArrayList<>();
        materialsList = db.MaterialStockDao().getAllMaterials();
        return materialsList;
    }
}