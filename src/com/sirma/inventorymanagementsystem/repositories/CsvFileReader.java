package com.sirma.inventorymanagementsystem.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sirma.inventorymanagementsystem.repositories.CsvFileConstants.CSV_DELIMITER;

public abstract class CsvFileReader<T> {

    private String fileName;

    private Map<Integer, String> fileHeaderLabels;

    public CsvFileReader(String fileName, Map<Integer, String> fileHeaderLabels) {
        this.fileName = fileName;
        this.fileHeaderLabels = fileHeaderLabels;
    }

    /**
     * Reads list of entities from a CSV file.
     * File is provided through constructor.
     * Before parsing entities header row of CSV file is validated against the expected labels - provided again through the constructor.
     * @return List of Generic entities. Type of entity is provided by the concrete child class.
     */
    public List<T> readListOfEntitiesFromFile() {
        List<T> allItems = new ArrayList<T>();

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            //first line is the header line - so we would like to skip it.
            String headerRow = in.readLine();
            if (headerRow == null) {
                return new ArrayList<>();
            }

            String[] headerRowItems = headerRow.split(CSV_DELIMITER);
            validateHeaderRow(headerRowItems);

            String line = in.readLine();

            while (line != null) {
                String[] body = line.split(CSV_DELIMITER);

                allItems.add(parseEntity(body));
                line = in.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allItems;
    }

    protected void validateHeaderRow(String[] headerValues) {
        for (int i = 0; i < headerValues.length; i++) {
            String expectedValue = fileHeaderLabels.get(i);
            String actualValue = headerValues[i];
            if(!expectedValue.equals(actualValue)) {
                throw new RuntimeException(
                    "Invalid column #" + i + 1 + " header row for CSV file: "+ fileName
                    + " . Column name should be: " + expectedValue
                );
            }
        }
    }

    protected abstract T parseEntity(String[] valuesAsString);
}
