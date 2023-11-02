package com.sirma.inventorymanagementsystem.models.model.item;

public class ItemDetails {

    private String itemName;
    private ItemCategory itemCategory;
    private double itemPrice;
    private String itemDescription;
    private final String itemType = "n.a."; //Not clear what the purpose of item type is.

    public ItemDetails(String itemName, ItemCategory itemCategory, double itemPrice, String itemDescription) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemType() {
        return itemType;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "Name='" + itemName + '\'' +
                ", Category='" + itemCategory + '\'' +
                ", Price=" + itemPrice + '\'' +
                ", Description='" + itemDescription + '\'' +
                ", Type='" + itemType +
                '}';
    }
}
