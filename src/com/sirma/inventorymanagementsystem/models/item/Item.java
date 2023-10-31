package com.sirma.inventorymanagementsystem.models.item;

public interface Item {
    // Define methods for getting item details, calculating value, and displaying the item's description.
    ItemDetails getItemDetails();

    double calculateValue();

    void displayItemDescription();
}
