package com.example.loginapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TablesFragment extends Fragment {
    RecyclerView grid;
    private List<Integer> data = new ArrayList<Integer>();

    public TablesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_tables, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        data.add(7);


        grid = view.findViewById(R.id.table_grid);
        Context context = getContext();

        grid.setLayoutManager(new GridLayoutManager(context, 3));
        TableAdaptor adapter = new TableAdaptor(context, data);
        grid.setAdapter(adapter);

        TextView subTitle = view.findViewById(R.id.num_of_tables_title);
        subTitle.setText(data.size() + " Tables");
    }
}
