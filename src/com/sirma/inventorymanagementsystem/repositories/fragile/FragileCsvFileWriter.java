package com.sirma.inventorymanagementsystem.repositories.fragile;

import com.sirma.inventorymanagementsystem.models.item.ElectronicsItem;
import com.sirma.inventorymanagementsystem.models.item.FragileItem;
import com.sirma.inventorymanagementsystem.repositories.CsvFileConstants;
import com.sirma.inventorymanagementsystem.repositories.CsvFileWriter;
import com.sirma.inventorymanagementsystem.repositories.InventoryItemCsvFileWriter;

public class FragileCsvFileWriter extends InventoryItemCsvFileWriter<FragileItem> {

    public FragileCsvFileWriter() {
        super(CsvFileConstants.FRAGILE_FILE, FragileCsvFileHeaderUtils.FILE_HEADER_LABELS, new FragileCsvFileReader());
    }

    @Override
    protected String convertItemToCsvRow(FragileItem item) {
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
                .append(item.getWeight())
                .toString();
    }
}
