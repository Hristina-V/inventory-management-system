package com.sirma.inventorymanagementsystem.models.cli;

import com.sirma.inventorymanagementsystem.models.model.item.ElectronicsItem;
import com.sirma.inventorymanagementsystem.models.model.item.FragileItem;
import com.sirma.inventorymanagementsystem.models.model.item.GroceryItem;
import com.sirma.inventorymanagementsystem.models.model.item.InventoryItem;
import com.sirma.inventorymanagementsystem.models.model.order.Order;
import com.sirma.inventorymanagementsystem.models.services.OrderService;
import com.sirma.inventorymanagementsystem.models.services.InventoryItemService;
import com.sirma.inventorymanagementsystem.repositories.electronics.ElectronicsCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.fragile.FragileCsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileWriter;

import java.util.List;
import java.util.Scanner;

import static com.sirma.inventorymanagementsystem.models.services.GetItemIdService.getItemId;

public class OrderCli {

    private Scanner scanner = new Scanner(System.in);

    private ChangeCategoryCli changeCategoryCli = new ChangeCategoryCli();

    private HomeMenuCli homeMenuCli = new HomeMenuCli(scanner);

    private PaymentsCli paymentsCli = new PaymentsCli();

    private OrderService orderService = new OrderService();

    private InventoryItemService inventoryItemService = new InventoryItemService();

    private ElectronicsCsvFileWriter electronicsCsvFileWriter = new ElectronicsCsvFileWriter();

    private GroceryCsvFileWriter groceryCsvFileWriter = new GroceryCsvFileWriter();

    private FragileCsvFileWriter fragileCsvFileWriter = new FragileCsvFileWriter();

    public void showOrderMenu() {
        Order order = new Order();

        while (true) {
            displayUserPrompt();
            String userSelection = scanner.nextLine();

            if ("1".equals(userSelection)){
                String stringId = getItemId();
                InventoryItem inventoryItem = inventoryItemService.findItemById(stringId);
                int orderQuantity = 0;

                if (inventoryItem == null) {
                    System.out.println("There are no items with id " + stringId);
                    continue;
                } else {
                    System.out.println("Order quantity: ");
                    orderQuantity = Integer.parseInt(scanner.nextLine());
                }
                if (inventoryItem.getQuantity() > orderQuantity) {
                    //if quantity > 0 -> reduce quantity and override file;
                    addOrderItem(order, inventoryItem, orderQuantity);
                    inventoryItemService.changeItemQuantity(stringId, orderQuantity);
                    System.out.println("Item added to order.");
                } else if (inventoryItem.getQuantity() == orderQuantity) {
                    orderService.addNewItemToOrder(order, inventoryItem);
                    //if quantity == 0 -> remove item and override file;
                    if (inventoryItem instanceof ElectronicsItem) {
                        electronicsCsvFileWriter.removeItemFromInventory(Integer.parseInt(stringId));
                    } else if (inventoryItem instanceof GroceryItem) {
                        groceryCsvFileWriter.removeItemFromInventory(Integer.parseInt(stringId));
                    } else if (inventoryItem instanceof FragileItem) {
                        fragileCsvFileWriter.removeItemFromInventory(Integer.parseInt(stringId));
                    }
                } else {
                    System.out.println("Insufficient quantity. Current stock of item " + stringId +
                                        " is " + inventoryItem.getQuantity());
                }
                } else if ("2".equals(userSelection)) {
                    System.out.println(orderService.calculateTotalCost(order));
                } else if ("3".equals(userSelection)) {
                    paymentsCli.showPaymentMenu();
                } else if ("4".equals(userSelection)) {
                    break;
                } else {
                    System.out.println("Invalid selection.");
                }
            }
        }

    private void addOrderItem(Order order, InventoryItem inventoryItem, int orderQuantity) {
        List<InventoryItem> orderItems = order.getOrderItems();
        InventoryItem orderItem = inventoryItem;
        orderItem.setQuantity(orderQuantity);
        orderItems.add(orderItem);
    }


    private void displayUserPrompt() {
        System.out.println("Please select an option:");
        System.out.println("Select \"1\" to add item to order.");
        System.out.println("Select \"2\" to display total cost for current order.");
        System.out.println("Select \"3\" to proceed to checkout.");
        System.out.println("Select \"4\" to go back to the previous menu.");
    }
}
