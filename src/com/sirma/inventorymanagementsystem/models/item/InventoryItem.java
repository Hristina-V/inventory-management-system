package com.sirma.inventorymanagementsystem.models.item;

public class InventoryItem extends AbstractItem {

    private int itemID;
    private int quantity;

    public InventoryItem(ItemDetails itemDetails, int itemID, int quantity) {
        super(itemDetails);
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double calculateValue() {
        return getItemDetails().getItemPrice() * quantity;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "Name='" + getItemDetails().getItemName() +
                ", Category='" + getItemDetails().getItemCategory() +
                ", Price=" + getItemDetails().getItemPrice() +
                ", Description='" + getItemDetails().getItemDescription() +
                ", Type='" + getItemDetails().getItemType() +
                ", itemID=" + itemID +
                ", quantity=" + quantity +
                '}';
    }
}
