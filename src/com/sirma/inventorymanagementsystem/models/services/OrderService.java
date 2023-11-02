package com.sirma.inventorymanagementsystem.models.services;

import com.sirma.inventorymanagementsystem.models.model.item.InventoryItem;
import com.sirma.inventorymanagementsystem.models.model.order.Order;

import java.util.List;

public class OrderService {

    private Order order;

    public Order addNewItemToOrder (Order order, InventoryItem inventoryItem) {
        order.getOrderItems().add(inventoryItem);
        return order;
    }

    public double calculateTotalCost (Order order) {
        List<InventoryItem> orderItems = order.getOrderItems();
//        double totalCost = 0;
//
//        for (InventoryItem item: orderItems) {
//            double itemCost = item.getItemPrice() * item.getQuantity();
//            totalCost += itemCost;
//        }
//
//        return totalCost;

        return orderItems
            .stream()
            .mapToDouble(item -> item.getQuantity() * item.getItemPrice())
            .sum();
    }
}
