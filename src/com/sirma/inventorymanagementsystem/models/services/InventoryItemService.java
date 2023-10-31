package com.sirma.inventorymanagementsystem.models.services;

import com.sirma.inventorymanagementsystem.models.item.ElectronicsItem;
import com.sirma.inventorymanagementsystem.models.item.FragileItem;
import com.sirma.inventorymanagementsystem.models.item.GroceryItem;
import com.sirma.inventorymanagementsystem.models.item.InventoryItem;
import com.sirma.inventorymanagementsystem.repositories.CsvFileConstants;
import com.sirma.inventorymanagementsystem.repositories.CsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileWriter;

import java.io.File;

public class InventoryItemService {

    private ElectronicsCsvFileWriter electronicsCsvFileWriter = new ElectronicsCsvFileWriter();

    private GroceryCsvFileWriter groceryCsvFileWriter = new GroceryCsvFileWriter();

    private FragileCsvFileWriter fragileCsvFileWriter = new FragileCsvFileWriter();

    private ElectronicsCsvFileReader electronicsCsvFileReader = new ElectronicsCsvFileReader();

    private GroceryCsvFileReader groceryCsvFileReader = new GroceryCsvFileReader();

    private FragileCsvFileReader fragileCsvFileReader = new FragileCsvFileReader();

    public <T extends InventoryItem> void saveInventoryItemToFile (T inventoryItem) {
        if (inventoryItem instanceof ElectronicsItem) {
            saveToFile(CsvFileConstants.ELECTRONICS_FILE, electronicsCsvFileWriter, (ElectronicsItem) inventoryItem);
        } else if (inventoryItem instanceof GroceryItem) {
            saveToFile(CsvFileConstants.GROCERIES_FILE, groceryCsvFileWriter, (GroceryItem) inventoryItem);
        } else if (inventoryItem instanceof FragileItem) {
            saveToFile(CsvFileConstants.FRAGILE_FILE, fragileCsvFileWriter, (FragileItem) inventoryItem);
        }
    }
    public static<T extends InventoryItem> void saveToFile(String file, CsvFileWriter<T> csvFileWriter, T inventoryItem) {
        if (new File(file).length() == 0){
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
}
