package com.sirma.inventorymanagementsystem.repositories.fragile;

import com.sirma.inventorymanagementsystem.models.model.item.FragileItem;
import com.sirma.inventorymanagementsystem.models.model.item.ItemCategory;
import com.sirma.inventorymanagementsystem.models.model.item.ItemDetails;
import com.sirma.inventorymanagementsystem.repositories.InventoryItemCsvFileReader;

import static com.sirma.inventorymanagementsystem.repositories.CsvFileConstants.FRAGILE_FILE;
import static com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileHeaderUtils.FILE_HEADER_LABELS;

public class FragileCsvFileReader extends InventoryItemCsvFileReader<FragileItem> {

    public FragileCsvFileReader() {
        super(FRAGILE_FILE, FILE_HEADER_LABELS);
    }

    @Override
    protected FragileItem parseEntity(String[] valuesAsString) {
        String itemName = valuesAsString[0];
        ItemCategory itemCategory = ItemCategory.fromStringToValue(valuesAsString[1].toLowerCase());
        double itemPrice = Double.parseDouble(valuesAsString[2]);
        String itemDescription = valuesAsString[3];
        String itemType = valuesAsString[4];
        int itemId = Integer.parseInt(valuesAsString[5]);
        int quantity = Integer.parseInt(valuesAsString[6]);
        double weight = Double.parseDouble(valuesAsString[7]);

        ItemDetails itemDetails = new ItemDetails(itemName, itemCategory, itemPrice, itemDescription);

        return new FragileItem(itemDetails, itemId, quantity, weight);
    }
}
