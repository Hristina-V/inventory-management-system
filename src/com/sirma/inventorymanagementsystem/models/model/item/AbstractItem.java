package com.sirma.inventorymanagementsystem.models.model.item;

public abstract class AbstractItem implements Item, Breakable, Categorizable, Perishable, Sellable {

    private ItemDetails itemDetails;

    public AbstractItem(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Override
    public ItemDetails getItemDetails() {
        // Implement item details.
        return itemDetails;
    }

    @Override
    public abstract double calculateValue();

    @Override
    public void displayItemDescription() {
        // Implement common functionality such as getting item details.
        System.out.println(itemDetails.getItemDescription());
    }

    //Provide default implementations for category, breakable, perishable, and sellable attributes.

    @Override
    public void setItemCategory(ItemCategory itemCategory) {
        itemDetails.setItemCategory(itemCategory);
    }

    @Override
    public ItemCategory getItemCategory() {
        return itemDetails.getItemCategory();
    }

    @Override
    public double getItemPrice() {
        return itemDetails.getItemPrice();
    }

    @Override
    public void setItemPrice(double itemPrice) {
        itemDetails.setItemPrice(itemPrice);
    }

    @Override
    public boolean isBreakable() {
        return getItemCategory().equals(ItemCategory.FRAGILE);
    }

    @Override
    public void handleItemBreakage() {
        //TODO write implementation
    }

    @Override
    public boolean isPerishable() {
        return getItemCategory().equals(ItemCategory.GROCERIES);
    }

    @Override
    public void handleItemExpiration() {
        //TODO write implementation
    }
}
