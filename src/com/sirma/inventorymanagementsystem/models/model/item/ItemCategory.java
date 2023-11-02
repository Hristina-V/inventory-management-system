package com.sirma.inventorymanagementsystem.models.model.item;

import java.util.Arrays;

public enum ItemCategory {
    ELECTRONICS("electronics"),
    GROCERIES("groceries"),
    FRAGILE("fragile");

    private String value;

    ItemCategory(String value) {
        this.value = value;
    }

    public static ItemCategory fromStringToValue(String value) {
        return Arrays.stream(ItemCategory.values()).filter(x -> x.value.equals(value)).findAny().get();
    }

    @Override
    public String toString() {
        return value;
    }
}
