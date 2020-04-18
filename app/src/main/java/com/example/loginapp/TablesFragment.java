package com.example.loginapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.viewModels.OrderViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TablesFragment extends Fragment {
    RecyclerView grid;

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

        //gets the PArent to load the tiles
        grid = view.findViewById(R.id.table_grid);
        Context context = getContext();
        //GridLayoutManager will set tiles for each row in the pArent RecyclerView
        grid.setLayoutManager(new GridLayoutManager(context, 3));
        TableAdaptor adapter = new TableAdaptor(context, mListener);


       // Populate Tables from the VIewModel
        OrderViewModel model = ViewModelProviders.of(getActivity()).get(OrderViewModel.class);
        model.getTables().observe(getViewLifecycleOwner(), item -> {
            TextView subTitle = view.findViewById(R.id.num_of_tables_title);
            adapter.setTables(item, subTitle);
            grid.setAdapter(adapter);
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }


    private OnListFragmentInteractionListener mListener;
    public interface OnListFragmentInteractionListener {

        void onListFragmentInteraction(String item);
    }
}
