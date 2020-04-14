package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.loginapp.handlers.EntityHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    List<String> userList = new ArrayList<String>();
    String address;
    private EntityHandler _entityHandler= EntityHandler.getInstance();

    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_login, container, false);
        address = getArguments().getString("serverName");
        Button loginButton = view.findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner userSelector = view.findViewById(R.id.waiter_spinner);
                String userId =  userSelector.getSelectedItem().toString();
                Intent intent=new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("USER_ID", userId);
                getActivity().finish();
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize your view here for use view.findViewById("your view id")
        Spinner spinner = (Spinner) view.findViewById(R.id.waiter_spinner);
        userList = _entityHandler.getUsers();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);


        TextView subTitle = view.findViewById(R.id.tv_subtitle);
        subTitle.setText("Connecting via : " + address);

    }
}
