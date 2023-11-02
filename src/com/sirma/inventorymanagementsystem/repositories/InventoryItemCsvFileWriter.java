package com.sirma.inventorymanagementsystem.repositories;

import com.sirma.inventorymanagementsystem.models.model.item.InventoryItem;

import java.util.List;
import java.util.Map;

public abstract class InventoryItemCsvFileWriter<T extends InventoryItem> extends CsvFileWriter<T> {

    private InventoryItemCsvFileReader<T> inventoryItemCsvFileReader;

    public InventoryItemCsvFileWriter(String fileName,
                                      Map<Integer, String> fileHeaderLabels,
                                      InventoryItemCsvFileReader<T> inventoryItemCsvFileReader) {
        super(fileName, fileHeaderLabels);
        this.inventoryItemCsvFileReader = inventoryItemCsvFileReader;
    }

    public T removeItemFromInventory(int itemId) {
        List<T> allItems = inventoryItemCsvFileReader.readListOfEntitiesFromFile();

        for (int i = 0; i < allItems.size(); i++) {
            T currentItem = allItems.get(i);
            if(currentItem.getItemID() == itemId) {
                allItems.remove(currentItem);
                overrideCsvFile(allItems);
                return  currentItem;
            }
        }
        System.out.println("No item found.");
        return null;
    }
}
