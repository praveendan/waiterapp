package com.example.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.entities.Table;

import java.util.List;


/**
 * The Adaptor Class to load the grid Tiles for the table view with recycler view
 */
public class TableAdaptor extends RecyclerView.Adapter<TableAdaptor.ViewHolder> {
    private List<Table> data;
    private LayoutInflater layoutInflater;

    TableAdaptor(Context context, List<Table> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    // gets all the input fields and information
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textID; //CardView card;
        public ViewHolder(View itemView){
            super(itemView);
         //   card = itemView.findViewById(R.id.table_card);
            textID = itemView.findViewById(R.id.table_id);
        }
    }
}