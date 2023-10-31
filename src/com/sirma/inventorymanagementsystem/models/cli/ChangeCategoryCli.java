package com.sirma.inventorymanagementsystem.models.cli;

import com.sirma.inventorymanagementsystem.models.item.*;
import com.sirma.inventorymanagementsystem.models.services.InventoryItemService;
import com.sirma.inventorymanagementsystem.models.services.OrderService;
import com.sirma.inventorymanagementsystem.repositories.CsvFileConstants;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

import static com.sirma.inventorymanagementsystem.models.services.GetItemIdService.getItemId;
import static com.sirma.inventorymanagementsystem.models.services.InventoryItemService.saveToFile;

public class ChangeCategoryCli {

    private Scanner scanner = new Scanner(System.in);

    private ElectronicsCsvFileWriter electronicsCsvFileWriter = new ElectronicsCsvFileWriter();

    private FragileCsvFileWriter fragileCsvFileWriter = new FragileCsvFileWriter();

    private GroceryCsvFileWriter groceryCsvFileWriter = new GroceryCsvFileWriter();

    private Random random = new Random();

    private InventoryItemService inventoryItemService = new InventoryItemService();

    private HomeMenuCli homeMenuCli = new HomeMenuCli(scanner);

    public void showChangeCategoryMenu() {
        InventoryItem inventoryItem;
        do {
            String stringId = getItemId();
            inventoryItem = inventoryItemService.deleteItemById(stringId);
        } while(inventoryItem == null);

        System.out.println("Please select new item category: ");
        ItemCategory newItemCategory = homeMenuCli.readItemCategory();
        inventoryItem.setItemCategory(newItemCategory);
        if (newItemCategory.equals(ItemCategory.ELECTRONICS)) {
            int newItemId = random.nextInt((9999 - 1000) + 1) + 1000;
            ElectronicsItem reclassifiedItem = new ElectronicsItem(inventoryItem.getItemDetails(),newItemId, inventoryItem.getQuantity());
            saveToFile(CsvFileConstants.ELECTRONICS_FILE, electronicsCsvFileWriter, reclassifiedItem);
        } else if (newItemCategory.equals(ItemCategory.FRAGILE)) {
            int newItemId = random.nextInt((99999 - 10000) + 1) + 10000;
            System.out.println("Please provide item weight: ");
            double itemWeight = Double.parseDouble(scanner.nextLine());
            FragileItem reclassifiedItem = new FragileItem(inventoryItem.getItemDetails(),newItemId, inventoryItem.getQuantity(), itemWeight);
            saveToFile(CsvFileConstants.FRAGILE_FILE, fragileCsvFileWriter, reclassifiedItem);
        } else {
            int newItemId = random.nextInt((999999 - 100000) + 1) + 100000;
            System.out.println("Please provide expiration date: ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate expirationDate = LocalDate.parse(scanner.nextLine(), formatter);
            GroceryItem reclassifiedItem = new GroceryItem(inventoryItem.getItemDetails(),newItemId, inventoryItem.getQuantity(), expirationDate);
            saveToFile(CsvFileConstants.GROCERIES_FILE, groceryCsvFileWriter, reclassifiedItem);
        }
    }
}
