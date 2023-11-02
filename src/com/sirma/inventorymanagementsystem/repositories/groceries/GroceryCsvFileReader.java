package com.sirma.inventorymanagementsystem.repositories.groceries;

import com.sirma.inventorymanagementsystem.models.model.item.GroceryItem;
import com.sirma.inventorymanagementsystem.models.model.item.ItemCategory;
import com.sirma.inventorymanagementsystem.models.model.item.ItemDetails;
import com.sirma.inventorymanagementsystem.repositories.InventoryItemCsvFileReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.sirma.inventorymanagementsystem.repositories.CsvFileConstants.GROCERIES_FILE;
import static com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileHeaderUtils.FILE_HEADER_LABELS;

public class GroceryCsvFileReader extends InventoryItemCsvFileReader<GroceryItem> {

    public GroceryCsvFileReader() {
        super(GROCERIES_FILE, FILE_HEADER_LABELS);
    }

    @Override
    protected GroceryItem parseEntity(String[] valuesAsString) {
        String itemName = valuesAsString[0];
        ItemCategory itemCategory = ItemCategory.fromStringToValue(valuesAsString[1].toLowerCase());
        double itemPrice = Double.parseDouble(valuesAsString[2]);
        String itemDescription = valuesAsString[3];
        String itemType = valuesAsString[4];
        int itemId = Integer.parseInt(valuesAsString[5]);
        int quantity = Integer.parseInt(valuesAsString[6]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expirationDate = LocalDate.parse(valuesAsString[7], formatter);

        ItemDetails itemDetails = new ItemDetails(itemName, itemCategory, itemPrice, itemDescription);

        return new GroceryItem(itemDetails,itemId,quantity,expirationDate);
    }
}
