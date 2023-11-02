package com.sirma.inventorymanagementsystem.models.model.item;

public class FragileItem extends InventoryItem {

    private double weight;

    public FragileItem(ItemDetails itemDetails, int itemID, int quantity, double weight) {
        super(itemDetails, itemID, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }

    @Override
    public void handleItemBreakage() {
        //TODO write implementation
        super.handleItemBreakage();
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
                ", Quantity=" + getQuantity() +
                ", Weight=" + weight +
                '}';
    }
}
