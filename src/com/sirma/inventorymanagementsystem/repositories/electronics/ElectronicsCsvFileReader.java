package com.sirma.inventorymanagementsystem.repositories.electronics;

import com.sirma.inventorymanagementsystem.models.item.ElectronicsItem;
import com.sirma.inventorymanagementsystem.models.item.ItemCategory;
import com.sirma.inventorymanagementsystem.models.item.ItemDetails;
import com.sirma.inventorymanagementsystem.repositories.InventoryItemCsvFileReader;

import static com.sirma.inventorymanagementsystem.repositories.CsvFileConstants.ELECTRONICS_FILE;
import static com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileHeaderUtils.FILE_HEADER_LABELS;

public class ElectronicsCsvFileReader extends InventoryItemCsvFileReader<ElectronicsItem> {

    public ElectronicsCsvFileReader() {
        super(ELECTRONICS_FILE, FILE_HEADER_LABELS);
    }

    @Override
    protected ElectronicsItem parseEntity(String[] valuesAsString) {
        String itemName = valuesAsString[0];
        ItemCategory itemCategory = ItemCategory.fromStringToValue(valuesAsString[1].toLowerCase());
        double itemPrice = Double.parseDouble(valuesAsString[2]);
        String itemDescription = valuesAsString[3];
        String itemType = valuesAsString[4];
        int itemId = Integer.parseInt(valuesAsString[5]);
        int quantity = Integer.parseInt(valuesAsString[6]);

        ItemDetails itemDetails = new ItemDetails(itemName, itemCategory, itemPrice, itemDescription);

        return new ElectronicsItem(itemDetails,itemId,quantity);
    }
}
