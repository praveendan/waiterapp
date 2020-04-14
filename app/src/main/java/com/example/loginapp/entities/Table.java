package com.example.loginapp.entities;

public class Table {
    private int tableId;
    private boolean isAvailable;
    private String assignedWaiter;
    private String specialNote;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAssignedWaiter() {
        return assignedWaiter;
    }

    public void setAssignedWaiter(String assignedWaiter) {
        this.assignedWaiter = assignedWaiter;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public Table(int tableId) {
        this.tableId = tableId;
    }
}
