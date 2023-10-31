package com.sirma.inventorymanagementsystem.models.services;

import com.sirma.inventorymanagementsystem.models.item.InventoryItem;
import com.sirma.inventorymanagementsystem.models.item.Order;

public class OrderService {

    private Order order;

    public Order addNewItemToOrder (Order order, InventoryItem inventoryItem) {
        order.getOrderItems().add(inventoryItem);
        return order;
    }
}
