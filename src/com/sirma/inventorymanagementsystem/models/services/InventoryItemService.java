package com.sirma.inventorymanagementsystem.models.services;

import com.sirma.inventorymanagementsystem.models.model.item.ElectronicsItem;
import com.sirma.inventorymanagementsystem.models.model.item.FragileItem;
import com.sirma.inventorymanagementsystem.models.model.item.GroceryItem;
import com.sirma.inventorymanagementsystem.models.model.item.InventoryItem;
import com.sirma.inventorymanagementsystem.repositories.CsvFileConstants;
import com.sirma.inventorymanagementsystem.repositories.CsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.InventoryItemCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.InventoryItemCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileWriter;

import java.io.File;
import java.util.List;

public class InventoryItemService <T> {

    private InventoryItemCsvFileWriter electronicsCsvFileWriter = new ElectronicsCsvFileWriter();

    private InventoryItemCsvFileWriter groceryCsvFileWriter = new GroceryCsvFileWriter();

    private InventoryItemCsvFileWriter fragileCsvFileWriter = new FragileCsvFileWriter();

    private InventoryItemCsvFileReader electronicsCsvFileReader = new ElectronicsCsvFileReader();

    private InventoryItemCsvFileReader groceryCsvFileReader = new GroceryCsvFileReader();

    private InventoryItemCsvFileReader fragileCsvFileReader = new FragileCsvFileReader();

    public <T extends InventoryItem> void saveInventoryItemToFile(T inventoryItem) {
        if (inventoryItem instanceof ElectronicsItem) {
            saveToFile(CsvFileConstants.ELECTRONICS_FILE, electronicsCsvFileWriter, (ElectronicsItem) inventoryItem);
        } else if (inventoryItem instanceof GroceryItem) {
            saveToFile(CsvFileConstants.GROCERIES_FILE, groceryCsvFileWriter, (GroceryItem) inventoryItem);
        } else if (inventoryItem instanceof FragileItem) {
            saveToFile(CsvFileConstants.FRAGILE_FILE, fragileCsvFileWriter, (FragileItem) inventoryItem);
        }
    }

    public static <T extends InventoryItem> void saveToFile(String file, CsvFileWriter<T> csvFileWriter, T inventoryItem) {
        if (new File(file).length() == 0) {
            csvFileWriter.overrideCsvFile(inventoryItem);
        } else {
            csvFileWriter.appendToCsvFile(inventoryItem);
        }
    }

    public InventoryItem deleteItemById(String itemId) {
        int itemIdInt = Integer.parseInt(itemId);
        switch (itemId.length()) {
            case 4:
                return electronicsCsvFileWriter.removeItemFromInventory(itemIdInt);
            case 5:
                return fragileCsvFileWriter.removeItemFromInventory(itemIdInt);
            case 6:
                return groceryCsvFileWriter.removeItemFromInventory(itemIdInt);
            default:
                throw new RuntimeException("No items from this id range.");
        }
    }

    public InventoryItem findItemById(String itemId) {
        int itemIdInt = Integer.parseInt(itemId);
        switch (itemId.length()) {
            case 4:
                return electronicsCsvFileReader.readEntityByItemId(itemIdInt);
            case 5:
                return fragileCsvFileReader.readEntityByItemId(itemIdInt);
            case 6:
                return groceryCsvFileReader.readEntityByItemId(itemIdInt);
            default:
                throw new RuntimeException("No items from this id range.");
        }
    }

    public <Т extends InventoryItem> List<Т> findAllFromCategory(String itemId) {
        int itemIdInt = Integer.parseInt(itemId);

        switch (itemId.length()) {
            case 4:
                return electronicsCsvFileReader.readListOfEntitiesFromFile();
            case 5:
                return fragileCsvFileReader.readListOfEntitiesFromFile();
            case 6:
                return groceryCsvFileReader.readListOfEntitiesFromFile();
            default:
                throw new RuntimeException("No items from this id range.");
        }
    }

    public <T extends InventoryItem> void changeItemQuantity(String itemId, int quantity) {
        List<T> allItems = findAllFromCategory(itemId);

        for (T item : allItems) {
            if (item.getItemID() == Integer.parseInt(itemId)) {
                item.setQuantity(item.getQuantity() - quantity);
            }

            if (item instanceof ElectronicsItem) {
                electronicsCsvFileWriter.overrideCsvFile(allItems);
            } else if (item instanceof GroceryItem) {
                groceryCsvFileWriter.overrideCsvFile(allItems);
            } else if (item instanceof FragileItem) {
                fragileCsvFileWriter.overrideCsvFile(allItems);
            }
            break;
        }
    }
}
