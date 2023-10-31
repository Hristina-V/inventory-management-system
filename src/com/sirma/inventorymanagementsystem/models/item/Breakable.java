package com.sirma.inventorymanagementsystem.models.item;

public interface Breakable {
    //Include methods for checking if an item is breakable and for handling item breakage.
    boolean isBreakable();

    void handleItemBreakage();
}
