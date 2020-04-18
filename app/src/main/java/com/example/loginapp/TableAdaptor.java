package com.example.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.entities.Table;

import java.util.ArrayList;
import java.util.List;


/**
 * The Adaptor Class to load the grid Tiles for the table view with recycler view
 */
public class TableAdaptor extends RecyclerView.Adapter<TableAdaptor.ViewHolder> {
    private List<Table> data;
    private LayoutInflater layoutInflater;
    private final TablesFragment.OnListFragmentInteractionListener mListener;

    TableAdaptor(Context context, TablesFragment.OnListFragmentInteractionListener listener){
        this.layoutInflater = LayoutInflater.from(context);
        data = new ArrayList<>();
        mListener = listener;
    }

    public void setTables(List<Table> data, TextView textView){
        this.data = data;
        if(data != null)
            textView.setText(data.size() + " Tables");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.table_tile, parent,false);

        return new ViewHolder(view);
    }
    //sets the data for all the input field and stuff here
    @Override
    public void onBindViewHolder(@NonNull TableAdaptor.ViewHolder holder, int position) {
        String tableId = String.valueOf(data.get(position).getTableId());

        holder.textID.setText(tableId);
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(tableId);
                    MenuFragment fragobj = new MenuFragment();
                    FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.order_layout, fragobj)
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(v.getContext(),"Problem Starting order. Please Try again later",Toast.LENGTH_LONG).show();
                }


            }

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    // gets all the input fields and information
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textID; //CardView card;
        View card;
        public ViewHolder(View itemView){
            super(itemView);
         //   card = itemView.findViewById(R.id.table_card);
            textID = itemView.findViewById(R.id.table_id);
            card = itemView;
        }
    }
}