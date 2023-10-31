package com.sirma.inventorymanagementsystem.models.item;

public interface Perishable {
    //Include methods for checking if an item is perishable and for handling item expiration.
    boolean isPerishable();

    void handleItemExpiration();
}
