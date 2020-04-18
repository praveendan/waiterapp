package com.example.loginapp.viewModels;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.dummy.MenuContent;
import com.example.loginapp.dummy.Order;
import com.example.loginapp.entities.Table;
import com.example.loginapp.handlers.EntityHandler;

import java.util.ArrayList;
import java.util.List;

/*
This  class will handle the order process
 */
public class OrderViewModel extends ViewModel {
    public String userId;
    public List<Order> orderList;
    public Order currentOrder;

    //private OrderHandler _orderHandler= OrderHandler.getInstance();
    private EntityHandler _entityHandler= EntityHandler.getInstance();

    private MutableLiveData<List<Table>> tablesList;
    private MutableLiveData<List<MenuContent.MenuItem>> menuItemsList;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void startOrder(String tableId) {
        int index = 0;
        if(orderList != null)
            index = orderList.size();
        this.currentOrder = new Order(Integer.parseInt(tableId), index);
    }

    public OrderViewModel(){
        orderList = new ArrayList<Order>();
      //  _orderHandler = OrderHandler.getInstance();
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

    public LiveData<List<MenuContent.MenuItem>> getMenuItems() {
        if (menuItemsList == null) {
            menuItemsList = new MutableLiveData<>();
            loadMenu();
        }
        return menuItemsList;
    }

    private void loadMenu() {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            List<MenuContent.MenuItem> menuArrayList = new ArrayList<>();
            menuArrayList = MenuContent.ITEMS;

            menuItemsList.setValue(menuArrayList);
        }, 1000);
    }

    public void addOrderItem(MenuContent.MenuItem item, String numberOfItems, String itemNote){
        this.currentOrder.addNewOrderItem(Integer.parseInt(numberOfItems), itemNote, item);
    }
}
