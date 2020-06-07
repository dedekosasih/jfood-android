package com.example.jfood_android;

public class History {

    private String event, tableNumber, date;

    public History() {
    }

    public History(String event, String tableNumber) {
        this.event = event;
        this.tableNumber = tableNumber;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "event='" + event + '\'' +
                ", date=" + date +
                ", tableNumber=" + tableNumber +
                '}';
    }
}
