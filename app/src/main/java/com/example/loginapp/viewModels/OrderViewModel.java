package com.example.loginapp.viewModels;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.entities.Order;
import com.example.loginapp.entities.Table;
import com.example.loginapp.handlers.EntityHandler;
import com.example.loginapp.handlers.OrderHandler;

import java.util.ArrayList;
import java.util.List;

/*
This  class will handle the order process
 */
public class OrderViewModel extends ViewModel {
    private String userId;
    private List<Order> orderList;
    private Order currentOrder;
    private OrderHandler _orderHandler = OrderHandler.getInstance();
    private EntityHandler _entityHandler= EntityHandler.getInstance();

    private MutableLiveData<List<Table>> tablesList;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void startOrder(String tableId) {
        currentOrder = _orderHandler.createNewOrder(Integer.parseInt(tableId));
    }

    public OrderViewModel(){
        orderList = new ArrayList<Order>();
        _orderHandler = OrderHandler.getInstance();
    }

    public LiveData<List<Table>> getTables() {
        if (tablesList == null) {
            tablesList = new MutableLiveData<>();
            loadTables();
        }
        return tablesList;
    }
    private void loadTables() {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            List<Table> tablesArrayList = new ArrayList<>();
            tablesArrayList =_entityHandler.getTables();

            tablesList.setValue(tablesArrayList);
        }, 1000);

    }
}
