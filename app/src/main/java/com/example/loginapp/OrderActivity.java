package com.example.loginapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.loginapp.dummy.MenuContent;
import com.example.loginapp.entities.Order;
import com.example.loginapp.handlers.OrderHandler;
import com.example.loginapp.viewModels.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements TablesFragment.OnListFragmentInteractionListener, MenuFragment.OnListFragmentInteractionListener {
    private String userId;
    private List<Order> orderList;
    private OrderHandler _orderHandler;
    OrderViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        orderList = new ArrayList<Order>();
        _orderHandler = OrderHandler.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        userId = getIntent().getStringExtra("USER_ID");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.order_layout, new TablesFragment());
        fragmentTransaction.commit();

        /*setting the viewmodel*/
        model = new ViewModelProvider(this).get(OrderViewModel.class);
    }

    public String getUserId(){
        return this.userId;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /* this is for the context Menu */
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.setting_menu_item:
                Intent intent=new Intent(this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //interation in the TablesFragment
    @Override
    public void onListFragmentInteraction(String item) {
        model = ViewModelProviders.of(this).get(OrderViewModel.class);
        model.setUserId(userId);
        model.startOrder(item);
      //  Toast.makeText(getApplicationContext(),userId + " "+item,Toast.LENGTH_LONG).show();
    }
    //interaction in the MenuFragment
    @Override
    public void onListFragmentInteraction(MenuContent.MenuItem item, View v) {
        model = ViewModelProviders.of(this).get(OrderViewModel.class);
        if(!item.content.isEmpty()){
            // get add menu item.xml view

            Context context = v.getContext();
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.add_menu_item_layout, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            Button addButton = promptsView.findViewById(R.id.add_menu_item_number_of_items_plus);
            Button minusButton = promptsView.findViewById(R.id.add_menu_item_number_of_items_minus);
            TextView numberOfItemsInput = promptsView.findViewById(R.id.add_menu_item_number_of_items);
            TextView notesInput = promptsView.findViewById(R.id.add_menu_item_notes);

            addButton.setOnClickListener((View v1) -> {
                int numberOfItems = Integer.parseInt(numberOfItemsInput.getText().toString()) + 1;
                numberOfItemsInput.setText(String.valueOf(numberOfItems));
            });

            minusButton.setOnClickListener((View v12) -> {
                int numberOfItems = Integer.parseInt(numberOfItemsInput.getText().toString()) - 1;
                if(-1 < numberOfItems)
                    numberOfItemsInput.setText(String.valueOf(numberOfItems));
            });

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    //result.setText(userInput.getText());
                                    model.addOrderItem(item,
                                            numberOfItemsInput.getText().toString(),
                                            notesInput.getText().toString());
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();


        }
    }
}
