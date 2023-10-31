package com.sirma.inventorymanagementsystem.repositories;

import com.sirma.inventorymanagementsystem.models.item.InventoryItem;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileReader;

import java.util.Map;

public abstract class InventoryItemCsvFileReader<T extends InventoryItem> extends CsvFileReader<T> {

    public InventoryItemCsvFileReader(String fileName, Map<Integer, String> fileHeaderLabels) {
        super(fileName, fileHeaderLabels);
    }

    public T readEntityByItemId(int itemId) {
        return readListOfEntitiesFromFile()
                .stream()
                .filter(x -> x.getItemID() == itemId)
                .findFirst()
                .orElse(null);
    }
}
