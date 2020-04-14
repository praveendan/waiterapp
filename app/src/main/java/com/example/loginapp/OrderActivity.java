package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.loginapp.entities.Order;
import com.example.loginapp.handlers.OrderHandler;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
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
    }

    public String getUserId(){
        return this.userId;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

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

    public void startOrder(View view) {
        TextView table = view.findViewById(R.id.table_id);
        int tableId = Integer.parseInt(table.getText().toString());
        orderList.add(_orderHandler.createNewOrder(tableId));


        //MenuFragment fragobj = new MenuFragment();
        //fragobj.setArguments(bundle);
       // FragmentManager fragmentManager = getSupportFragmentManager();
       // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //getSupportFragmentManager().beginTransaction()
        //        .replace(R.id.order_layout, fragobj)
        //        .addToBackStack(null)
        //        .commit();

        Toast toast = Toast.makeText(getApplicationContext(),
               "This is a message displayed in a Toast",
                Toast.LENGTH_SHORT);

        toast.show();
    }
}
