package com.example.loginapp.handlers;

import com.example.loginapp.entities.Order;

public class OrderHandler {
    private static OrderHandler single_instance = null;

    // static method to create instance of Singleton class
    public static OrderHandler getInstance()
    {
        if (single_instance == null)
            single_instance = new OrderHandler();

        return single_instance;
    }

    public static Order createNewOrder(int i){
        return new Order(i);
    }
}
