package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.MenuFragment.OnListFragmentInteractionListener;
import com.example.loginapp.dummy.MenuContent;
import com.example.loginapp.dummy.MenuContent.MenuItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MenuContent.MenuItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final List<MenuContent.MenuItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MenuAdapter(List<MenuContent.MenuItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mCategoryView.setText(mValues.get(position).category);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, v);
                } else {
                    Toast.makeText(v.getContext(),"Problem loading information. Please try again later",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
    //    public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mCategoryView;
        public MenuItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        //    mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mCategoryView = (TextView) view.findViewById(R.id.category_title);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
