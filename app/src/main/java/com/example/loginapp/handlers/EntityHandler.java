package com.example.loginapp.handlers;

import com.example.loginapp.entities.Table;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler {
    private static EntityHandler single_instance = null;

    // static method to create instance of Singleton class
    public static EntityHandler getInstance()
    {
        if (single_instance == null)
            single_instance = new EntityHandler();

        return single_instance;
    }

    public List<String> getUsers() {
        List<String> users = new ArrayList<String>();
        users.add("tom");
        users.add("cruise");
        users.add("leo");
        users.add("deCaprio");
        return users;
    }
    public List<Table> getTables(){
        List<Table> data = new ArrayList<>();
        data.add(new Table(1));
        data.add(new Table(2));
        data.add(new Table(3));
        data.add(new Table(4));
        data.add(new Table(5));
        data.add(new Table(6));
        data.add(new Table(7));
        return data;
    }

}
