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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.borg.R;
import com.borg.activity.Login;
import com.borg.activity.admin.adapter.AdminInvoiceAdapter;
import com.borg.activity.admin.adapter.AdminTaskAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.Client;
import com.borg.model.database.MaterialStock;
import com.borg.model.database.User;
import com.borg.model.database.ViewInvoice;
import com.borg.model.database.ViewUserTasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdminInvoiceFragment extends Fragment {

    DatabaseConnection db;
    RecyclerView recyclerView;
    List<ViewInvoice> viewInvoiceList;
    List<ViewUserTasks> tasksListAdmin;
    Integer selected_task;

    public AdminInvoiceFragment(Integer selected_task, List<ViewUserTasks> tasksListAdmin) {
        this.selected_task = selected_task;
        this.tasksListAdmin = tasksListAdmin;
    }

    public AdminInvoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_invoice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = DatabaseConnection.getDbInstance(getContext());
        viewInvoiceList = getList();

        TextView txtClientName= view.findViewById(R.id.txtClientName);
        TextView txtClientAddress= view.findViewById(R.id.txtClientAddress);
        TextView txtClientPhone= view.findViewById(R.id.txtClientPhone);
        TextView txtClientID= view.findViewById(R.id.txtClientID);
        TextView txtTaskID= view.findViewById(R.id.txtTaskID);
        TextView txtTaskDate= view.findViewById(R.id.txtTaskDate);
        TextView txtTaskTotalSum= view.findViewById(R.id.txtTaskTotalSum);

        try {
            ViewUserTasks clientModel = tasksListAdmin.stream().filter(e->e.getTask_id()==selected_task).collect(Collectors.toList()).get(0);
            txtClientName.setText(clientModel.getClient_firstName().toString());
            txtClientAddress.setText(clientModel.getClient_address().toString());
            txtClientPhone.setText(clientModel.getClient_phoneNumber().toString());
            txtClientID.setText(clientModel.getClient_id().toString());
            txtTaskID.setText(clientModel.getTask_id().toString());
            txtTaskDate.setText(clientModel.getTask_date().toString());
            txtTaskTotalSum.setText(getInvoiceTotal(viewInvoiceList).toString());
        }catch (Exception e) {
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        recyclerView = view.findViewById(R.id.recycler_view_invoice);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //mozda treba poslat poziciju
        AdminInvoiceAdapter adminInvoiceAdapter = new AdminInvoiceAdapter(getActivity(),viewInvoiceList,selected_task);
        recyclerView.setAdapter(adminInvoiceAdapter);



        ImageButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener( v -> {

            final Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_add_invoice_material);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.show();
            dialog.getWindow().setAttributes(lp);

            Spinner spinnerMaterial = (Spinner) dialog.findViewById(R.id.spinner_id_material);
            EditText quantityMaterial = (EditText) dialog.findViewById(R.id.add_fromdatabase_material_txt_material_quantity) ;

            List<MaterialStock> material = db.MaterialStockDao().getAllMaterials();

            List<String> materialItem = new ArrayList<>();

            for (MaterialStock materialStock: material) {
                materialItem.add(materialStock.getId()+" "+materialStock.getName());
            }

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapterMaterial = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, materialItem);
            // Drop down layout style - list view with radio button
            dataAdapterMaterial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerMaterial.setAdapter(dataAdapterMaterial);

            Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
            dialogButton.setOnClickListener(v1 -> {
                Double quantity = Double.parseDouble(quantityMaterial.getText().toString());
                String materialSelected[] = String.valueOf(spinnerMaterial.getSelectedItem()).split(" ");
                Double stock = db.MaterialStockDao().getMaterialQuantityByID(Integer.parseInt(materialSelected[0]));
                if(stock < quantity){
                    Toast.makeText(getActivity(),"There is not enough material on stock. Material quantity is :" + stock,  Toast.LENGTH_LONG).show();
                }else{
                    db.MaterialStockDao().updateMaterialOnStock(Integer.parseInt(materialSelected[0]),stock-quantity);
                    db.MaterialConsumptionDao().insertNewMaterialIntoTaskByTaskFK(selected_task,Integer.parseInt(materialSelected[0]), quantity);
                    adminInvoiceAdapter.notifyItemAdd();
                    dialog.dismiss();
                }
            });
            dialog.show();
        });

    }


    private List<ViewInvoice> getList(){
        db = DatabaseConnection.getDbInstance(getContext());
        viewInvoiceList = new ArrayList<>();
        viewInvoiceList = db.MaterialConsumptionDao().getInvoiceByTaskID(selected_task);
        return viewInvoiceList;
    }

    public Double getInvoiceTotal(List<ViewInvoice> viewInvoice){
        Double sum=0.0;
        for(int i = 0; i < viewInvoice.size(); i++){
            sum = sum + viewInvoice.get(i).getSum();
        }
        return sum;
    }

}