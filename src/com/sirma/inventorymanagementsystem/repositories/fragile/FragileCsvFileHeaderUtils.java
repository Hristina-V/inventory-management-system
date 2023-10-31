package com.sirma.inventorymanagementsystem.repositories.fragile;

import java.util.HashMap;
import java.util.Map;

public class FragileCsvFileHeaderUtils {

    public static final Map<Integer, String> FILE_HEADER_LABELS;

    static {
        FILE_HEADER_LABELS = new HashMap<>();
        FILE_HEADER_LABELS.put(0, "item_name");
        FILE_HEADER_LABELS.put(1, "item_category");
        FILE_HEADER_LABELS.put(2, "item_price");
        FILE_HEADER_LABELS.put(3, "item_description");
        FILE_HEADER_LABELS.put(4, "item_type");
        FILE_HEADER_LABELS.put(5, "item_id");
        FILE_HEADER_LABELS.put(6, "quantity");
        FILE_HEADER_LABELS.put(7, "weight");
    }
}
