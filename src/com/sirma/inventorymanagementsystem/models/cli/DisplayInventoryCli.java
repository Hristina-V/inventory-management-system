package com.sirma.inventorymanagementsystem.models.cli;

import com.sirma.inventorymanagementsystem.models.item.*;
import com.sirma.inventorymanagementsystem.repositories.CsvFileConstants;
import com.sirma.inventorymanagementsystem.repositories.CsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.CsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileReader;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileWriter;

import java.util.List;
import java.util.Scanner;

public class DisplayInventoryCli {

    private ElectronicsCsvFileReader electronicsCsvFileReader = new ElectronicsCsvFileReader();

    private GroceryCsvFileReader groceryCsvFileReader = new GroceryCsvFileReader();

    private FragileCsvFileReader fragileCsvFileReader = new FragileCsvFileReader();

    private ElectronicsItem electronicsItem;

    private GroceryItem groceryItem;

    private FragileItem fragileItem;

    private Scanner scanner;

    public DisplayInventoryCli(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showDisplayInventoryMenu() {
        while (true) {
            displayUserPrompt();
            String userSelection = scanner.nextLine();

            if ("1".equals(userSelection)) {
                displayInventory(electronicsCsvFileReader, ItemCategory.ELECTRONICS);
                displayInventory(groceryCsvFileReader, ItemCategory.GROCERIES);
                displayInventory(fragileCsvFileReader, ItemCategory.FRAGILE);
            } else if ("2".equals(userSelection)) {
                displayInventory(electronicsCsvFileReader, ItemCategory.ELECTRONICS);
            } else if ("3".equals(userSelection)) {
                displayInventory(groceryCsvFileReader, ItemCategory.GROCERIES);
            } else if ("4".equals(userSelection)) {
                displayInventory(fragileCsvFileReader, ItemCategory.FRAGILE);
            } else if ("5".equals(userSelection)) {
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    private void displayUserPrompt() {
        System.out.println("Please select an option:");
        System.out.println("Select \"1\" to display entire inventory.");
        System.out.println("Select \"2\" to display electronics inventory.");
        System.out.println("Select \"3\" to display groceries inventory.");
        System.out.println("Select \"4\" to display fragile inventory.");
        System.out.println("Select \"5\" to go back to the previous menu.");

    }

    private void displayInventory(CsvFileReader csvFileReader, ItemCategory itemCategory) {
        List<InventoryItem> inventoryItems = csvFileReader.readListOfEntitiesFromFile();

        if(inventoryItems == null || inventoryItems.isEmpty()) {
            System.out.println("There are no items in category " + itemCategory);
        } else {
            for (InventoryItem item : inventoryItems) {
                System.out.println(item);
            }
        }
    }
}
