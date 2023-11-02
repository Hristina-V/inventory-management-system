package com.sirma.inventorymanagementsystem.repositories.electronics;

import com.sirma.inventorymanagementsystem.models.model.item.ElectronicsItem;
import com.sirma.inventorymanagementsystem.repositories.CsvFileConstants;
import com.sirma.inventorymanagementsystem.repositories.InventoryItemCsvFileWriter;

public class ElectronicsCsvFileWriter extends InventoryItemCsvFileWriter<ElectronicsItem> {

    public ElectronicsCsvFileWriter() {
        super(CsvFileConstants.ELECTRONICS_FILE, ElectronicsCsvFileHeaderUtils.FILE_HEADER_LABELS, new ElectronicsCsvFileReader());
    }

    @Override
    protected String convertItemToCsvRow(ElectronicsItem item) {
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
                .toString();
    }
}
