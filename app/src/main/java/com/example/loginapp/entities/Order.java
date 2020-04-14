package com.example.loginapp.entities;

import java.util.List;

public class Order {
    private int tableId;
    private List<OrderItem> orderItemList;

    public Order(int tableId){
        this.tableId = tableId;
    }
}
