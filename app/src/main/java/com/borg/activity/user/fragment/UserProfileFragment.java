package com.borg.activity.user.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borg.R;
import com.borg.activity.Login;
import com.borg.model.DatabaseConnection;
import com.borg.model.database.User;

public class UserProfileFragment extends Fragment {

    EditText accName;
    EditText accSurname;
    EditText accAddress;
    EditText accPhoneNumber;
    EditText accEmail;
    EditText accUsername;
    EditText accPassword;
    Button btnUserProfileUpdate;
    DatabaseConnection db = DatabaseConnection.getDbInstance(getContext());
    User user = new User(db.UserDao().getUser(Login.getLoggedUser()));

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        accName = view.findViewById(R.id.userProfileName);
        accSurname = view.findViewById(R.id.userProfileSurname);
        accAddress = view.findViewById(R.id.userProfileAddress);
        accPhoneNumber = view.findViewById(R.id.userProfilePhoneNumber);
        accEmail = view.findViewById(R.id.userProfileEmail);
        accUsername = view.findViewById(R.id.userProfileUsername);
        accPassword = view.findViewById(R.id.userProfilePassword);

        accName.setText(user.getFirstName());
        accSurname.setText(user.getLastName());
        accAddress.setText(user.getAddress());
        accPhoneNumber.setText(user.getPhoneNumber());
        accEmail.setText(user.getEmail());
        accUsername.setText(user.getUserName());
        accPassword.setText(user.getPassword());

        Button btnUserProfileUpdate = (Button) view.findViewById(R.id.btnUserProfileUpdate);
        btnUserProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileUpdate(v);
            }
        });

    }

    private void UserProfileUpdate(View view){
        if(accName.getText().equals("") || accSurname.getText().equals("") || accAddress.getText().equals("") || accPhoneNumber.getText().equals("") || accEmail.getText().equals("") || accUsername.getText().equals("") || accPassword.getText().equals("") ){
            Toast.makeText(getContext(),"Please enter all fields!", Toast.LENGTH_LONG).show();
        }else{
            try {
                user.setFirstName(accName.getText().toString());
                user.setLastName(accSurname.getText().toString());
                user.setAddress(accAddress.getText().toString());
                user.setPhoneNumber(accPhoneNumber.getText().toString());
                user.setEmail(accEmail.getText().toString());
                user.setUserName(accUsername.getText().toString());
                user.setPassword(accPassword.getText().toString());
                db.UserDao().update(user);
                Toast.makeText(getContext(),"Update success!", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}

