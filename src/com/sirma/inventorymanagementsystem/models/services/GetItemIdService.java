package com.sirma.inventorymanagementsystem.models.services;

import java.util.Scanner;

public class GetItemIdService {

    private static Scanner scanner = new Scanner(System.in);

    public static String getItemId() {
        String itemId;

        do {
            System.out.println("Please provide item ID: ");
            itemId = scanner.nextLine();
        } while(itemId.length() < 4 || itemId.length() > 6);

        return itemId;
    }
}
