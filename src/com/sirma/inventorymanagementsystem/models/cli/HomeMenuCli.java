package com.sirma.inventorymanagementsystem.models.cli;

import com.sirma.inventorymanagementsystem.models.item.*;
import com.sirma.inventorymanagementsystem.repositories.CsvFileConstants;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import static com.sirma.inventorymanagementsystem.models.services.InventoryItemService.saveToFile;

public class HomeMenuCli {

    private Scanner scanner;

    private ElectronicsCsvFileWriter electronicsCsvFileWriter = new ElectronicsCsvFileWriter();

    private GroceryCsvFileWriter groceryCsvFileWriter = new GroceryCsvFileWriter();

    private FragileCsvFileWriter fragileCsvFileWriter = new FragileCsvFileWriter();

    private Random random = new Random();

    public HomeMenuCli(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu () {
        while (true) {
            displayHomeMenuUserPrompt();
            String userSelection = scanner.nextLine();

            if ("1".equals(userSelection)) {
                addNewItemToInventory();
            } else if ("2".equals(userSelection)) {
                DisplayInventoryCli displayInventoryCli = new DisplayInventoryCli(scanner);
                displayInventoryCli.showDisplayInventoryMenu();
            } else if ("3".equals(userSelection)) {
                ChangeCategoryCli changeCategoryCli = new ChangeCategoryCli();
                changeCategoryCli.showChangeCategoryMenu();
            } else if ("4".equals(userSelection)) {
                OrderCli orderCli = new OrderCli();
                orderCli.showOrderMenu();
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    private void addNewItemToInventory() {
        ItemDetails itemDetails = readItemDetails();

        System.out.println("Item quantity: ");
        int itemQuantity = Integer.parseInt(scanner.nextLine());

        if (itemDetails.getItemCategory() == ItemCategory.ELECTRONICS) {
            int itemId = random.nextInt((9999 - 1000) + 1) + 1000;
            ElectronicsItem electronicsItem = new ElectronicsItem(itemDetails, itemId, itemQuantity);
            saveToFile(CsvFileConstants.ELECTRONICS_FILE, electronicsCsvFileWriter, electronicsItem);
        } else if (itemDetails.getItemCategory() == ItemCategory.FRAGILE) {
            System.out.println("Item weight:");
            double itemWeight = Double.parseDouble(scanner.nextLine());
            int itemId = random.nextInt((99999 - 10000) + 1) + 10000;
            FragileItem fragileItem = new FragileItem(itemDetails, itemId, itemQuantity, itemWeight);
            saveToFile(CsvFileConstants.FRAGILE_FILE, fragileCsvFileWriter, fragileItem);
        } else if (itemDetails.getItemCategory() == ItemCategory.GROCERIES) {
            System.out.println("Expiration date:");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate expirationDate = LocalDate.parse(scanner.nextLine(), formatter);

            int itemId = random.nextInt((999999 - 100000) + 1) + 100000;
            GroceryItem groceryItem = new GroceryItem(itemDetails, itemId, itemQuantity, expirationDate);
            saveToFile(CsvFileConstants.GROCERIES_FILE, groceryCsvFileWriter, groceryItem);
        }
    }

    public ItemDetails readItemDetails() {
        System.out.println("Item name:");
        String itemName = scanner.nextLine();
        ItemCategory itemCategory = readItemCategory();
        System.out.println("Item price:");
        Double itemPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Item description:");
        String itemDescription = scanner.nextLine();

        ItemDetails itemDetails = new ItemDetails(itemName, itemCategory, itemPrice, itemDescription);

        return itemDetails;
    }

    private void displayHomeMenuUserPrompt() {
        System.out.println("Please choose what you want to do next:");
        System.out.println("Select \"1\" to add item to inventory.");
        System.out.println("Select \"2\" to display current inventory.");
        System.out.println("Select \"3\" to change item category.");
        System.out.println("Select \"4\" to create a new order.");
    }

    public ItemCategory readItemCategory () {
        while (true) {
            System.out.println("Item category");
            String itemCategoryString = scanner.nextLine();

            try {
                ItemCategory itemCategory = ItemCategory.fromStringToValue(itemCategoryString.toLowerCase());
                return itemCategory;
            } catch (NoSuchElementException e) {
                System.out.println("Invalid category");
            }
        }
    }
}
