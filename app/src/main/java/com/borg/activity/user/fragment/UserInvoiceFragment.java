package com.borg.activity.user.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borg.R;
import com.borg.activity.user.adapter.UserInvoiceAdapter;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.ViewInvoice;
import com.borg.model.database.ViewUserTasks;

import java.util.ArrayList;
import java.util.List;

public class UserInvoiceFragment extends Fragment {

    DatabaseConnection db;
    RecyclerView recyclerView;
    List<ViewInvoice> viewInvoiceList;
    List<ViewUserTasks> tasksListUser;
    Integer selected_task;

    public UserInvoiceFragment(Integer selected_task, List<ViewUserTasks> tasksListUser) {
        this.selected_task = selected_task;
        this.tasksListUser = tasksListUser;
    }

    public UserInvoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_invoice, container, false);
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

        //ako nisu svi podaci uneseni program puca
        try {
            ViewUserTasks clientModel = tasksListUser.get(selected_task);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//mozda treba poslat poziciju
        UserInvoiceAdapter userInvoiceAdapter = new UserInvoiceAdapter(getActivity(),viewInvoiceList,selected_task);
        recyclerView.setAdapter(userInvoiceAdapter);

    }


    private List<ViewInvoice> getList(){
        db = DatabaseConnection.getDbInstance(getContext());
        viewInvoiceList = new ArrayList<>();
        viewInvoiceList = db.MaterialConsumptionDao().getInvoiceByTaskID(tasksListUser.get(selected_task).getTask_id());
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