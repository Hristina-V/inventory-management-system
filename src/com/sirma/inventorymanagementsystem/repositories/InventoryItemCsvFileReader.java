package com.sirma.inventorymanagementsystem.repositories;

import com.sirma.inventorymanagementsystem.models.model.item.InventoryItem;

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
