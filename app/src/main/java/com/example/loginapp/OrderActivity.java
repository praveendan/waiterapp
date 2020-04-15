package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.loginapp.dummy.DummyContent;
import com.example.loginapp.entities.Order;
import com.example.loginapp.handlers.OrderHandler;
import com.example.loginapp.viewModels.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements TablesFragment.OnListFragmentInteractionListener, MenuFragment.OnListFragmentInteractionListener {
    private String userId;
    private List<Order> orderList;
    private OrderHandler _orderHandler;

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
        OrderViewModel model = ViewModelProviders.of(this).get(OrderViewModel.class);
        model.setUserId(userId);
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
    public boolean onOptionsItemSelected(MenuItem item) {
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
        OrderViewModel model = ViewModelProviders.of(this).get(OrderViewModel.class);
        model.startOrder(item);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
