package com.sirma.inventorymanagementsystem.models.model.item;

import java.time.LocalDate;

public class GroceryItem extends InventoryItem{

    private LocalDate expirationDate;

    public GroceryItem(ItemDetails itemDetails, int itemID, int quantity, LocalDate expirationDate) {
        super(itemDetails, itemID, quantity);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isPerishable() {
        return true;
    }

    @Override
    public void handleItemExpiration() {
        //TODO write implementation
        super.handleItemExpiration();
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "Name='" + getItemDetails().getItemName() +
                ", Category='" + getItemDetails().getItemCategory() +
                ", Price=" + getItemDetails().getItemPrice() +
                ", Description='" + getItemDetails().getItemDescription() +
                ", Type='" + getItemDetails().getItemType() +
                ", itemID=" + getItemID() +
                ", quantity=" + getQuantity() +
                ", expiration date=" + expirationDate +
                '}';
    }
}
