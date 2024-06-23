package org.example.dataStorage;

import org.example.models.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class Manager {
    private final String path = "src/main/resources/documents/";
    private final String format = ".txt";

    public Manager() {}

    public void save(Document document) {
        String fileName = document.getDocumentType().toString().toLowerCase() + "_" + document.getId() + format;
        File file = new File(path + fileName);

        try (PrintWriter printWriter = new PrintWriter(file)) {
            Field[] fields = document.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(document);
                    printWriter.printf("%s=%s, ", field.getName(), value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("File written successfully for document type: " + document.getDocumentType() + ", id: " + document.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> getFileNames(int id) {
        File directory = new File(path);
        String[] filesList = directory.list();
        List<String> result = new ArrayList<>();

        if (filesList == null) {
            System.out.println("Ошибка с путем директории!");
            return null;
        }

        for (String fileName : filesList) {
            if (fileName.toLowerCase().endsWith(id+format)) {
                result.add(fileName);
            }
        }
        return result;
    }

    public Map<String, String[]> getFiles(List<String> fileNames){
        Map<String, String[]> result = new HashMap<>();

        for (String fileName : fileNames) {

            try (BufferedReader reader = new BufferedReader(new FileReader(path + fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.put(fileName, line.split(", "));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return result;
    }

    public List<Document> load(int id) {
        List<String> FileNames = getFileNames(1);
        Map<String, String[]> files = getFiles(FileNames);

        Invoice invoice = new Invoice();
        Order order = new Order();
        Payment payment = new Payment();
        Waybill waybill = new Waybill();

        for (Map.Entry<String, String[]> entry : files.entrySet())
            if (entry.getKey().toLowerCase().startsWith("invoice")) {
                invoice.setData(entry.getValue());
            } else if (entry.getKey().toLowerCase().startsWith("order")) {
                order.setData(entry.getValue());
            } else if (entry.getKey().toLowerCase().startsWith("payment")) {
                payment.setData(entry.getValue());
            } else if (entry.getKey().toLowerCase().startsWith("waybill")) {
                waybill.setData(entry.getValue());
            }

        return new ArrayList<>(Arrays.asList(invoice, order, payment, waybill));
    }
}
