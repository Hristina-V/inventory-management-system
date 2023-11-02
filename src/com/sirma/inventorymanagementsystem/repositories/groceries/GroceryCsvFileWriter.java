package com.sirma.inventorymanagementsystem.repositories.groceries;

import com.sirma.inventorymanagementsystem.models.model.item.GroceryItem;
import com.sirma.inventorymanagementsystem.repositories.CsvFileConstants;
import com.sirma.inventorymanagementsystem.repositories.InventoryItemCsvFileWriter;

import java.time.format.DateTimeFormatter;

public class GroceryCsvFileWriter extends InventoryItemCsvFileWriter<GroceryItem> {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public GroceryCsvFileWriter() {
        super(CsvFileConstants.GROCERIES_FILE, GroceryCsvFileHeaderUtils.FILE_HEADER_LABELS, new GroceryCsvFileReader());
    }

    @Override
    protected String convertItemToCsvRow(GroceryItem item) {
        return new StringBuilder()
                .append(item.getItemDetails().getItemName())
                .append(CsvFileConstants.CSV_DELIMITER)
                .append(item.getItemCategory())
                .append(CsvFileConstants.CSV_DELIMITER)
                .append(item.getItemPrice())
                .append(CsvFileConstants.CSV_DELIMITER)
                .append(item.getItemDetails().getItemDescription())
                .append(CsvFileConstants.CSV_DELIMITER)
                .append(item.getItemDetails().getItemType())
                .append(CsvFileConstants.CSV_DELIMITER)
                .append(item.getItemID())
                .append(CsvFileConstants.CSV_DELIMITER)
                .append(item.getQuantity())
                .append(CsvFileConstants.CSV_DELIMITER)
                .append(item.getExpirationDate().format(formatter))
                .toString();
    }
}
