package com.example.loginapp.dummy;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int table;
    private int orderId;
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Order(int tableId, int orderId){
        this.table = tableId;
        this.orderId = orderId;
    }

    public void addNewOrderItem(int qty, String note, MenuContent.MenuItem menuItem){
        this.orderItems.add(new OrderItem(qty, note, menuItem));
    }

    public class OrderItem {
      //  private int orderItemId;
        private String note;
        private int qty;
        private MenuContent.MenuItem menuItem;

        public OrderItem(int qty, String note, MenuContent.MenuItem menuItem){
            this.qty = qty;
            this.note = note;
            this.menuItem = menuItem;
        }
    }
}
