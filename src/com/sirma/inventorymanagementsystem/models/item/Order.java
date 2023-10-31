package com.sirma.inventorymanagementsystem.models.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {

    private static Random random = new Random();

    private int orderId;

    private List<InventoryItem> orderItems;

    public Order() {
        this(new ArrayList<>());
    }

    public Order(List<InventoryItem> orderItems) {
        this(random.nextInt((999999 - 100000) + 1) + 100000, orderItems);
    }

    public Order(int orderId, List<InventoryItem> orderItems) {
        this.orderId = orderId;
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    private void setOrderId() {
        orderId = random.nextInt((999999 - 100000) + 1) + 100000;
    }

    public List<InventoryItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<InventoryItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderItems=" + orderItems +
                '}';
    }
}
