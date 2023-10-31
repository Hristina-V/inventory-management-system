package com.sirma.inventorymanagementsystem.repositories;

import com.sirma.inventorymanagementsystem.repositories.groceries.GroceryCsvFileHeaderUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public abstract class CsvFileWriter<T> {

    private String fileName;
    private Map<Integer, String> fileHeaderLabels;

    public CsvFileWriter(String fileName, Map<Integer, String> fileHeaderLabels) {
        this.fileName = fileName;
        this.fileHeaderLabels = fileHeaderLabels;
    }


    public void overrideCsvFile(List<T> entities) {
        try(FileWriter writer = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(writer)) {

            printWriter.println(buildHeader());
            entities.forEach(item -> printWriter.println(convertItemToCsvRow(item)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void overrideCsvFile(T entity) {
        overrideCsvFile(List.of(entity));
    }

    public void appendToCsvFile(List<T> entities) {
        try (FileWriter fw = new FileWriter(fileName,true)){
            for (T entity:entities) {
                fw.append(convertItemToCsvRow(entity));
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void appendToCsvFile(T entity) {
        appendToCsvFile(List.of(entity));
    }

    protected String buildHeader() {
        return BaseCsvFileHeaderUtils.buildCsvHeaderRow(fileHeaderLabels);
    }

    protected abstract String convertItemToCsvRow(T item);

}
