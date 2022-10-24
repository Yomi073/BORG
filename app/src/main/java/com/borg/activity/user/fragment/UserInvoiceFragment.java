package com.borg.activity.user.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.borg.R;

public class UserInvoiceFragment extends Fragment {

    public UserInvoiceFragment() {
        // Required empty public constructor
    }

    public static UserInvoiceFragment newInstance(String param1, String param2) {
        UserInvoiceFragment fragment = new UserInvoiceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_user_invoice, container, false);
    }
}