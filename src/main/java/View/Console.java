package View;

import org.example.Components.Product;
import org.example.dataStorage.Manager;
import org.example.models.*;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Manager manager = new Manager();

    public void create() {
        List<Document> documents = new ArrayList<>();
        System.out.println("Создание новых документов:\n");

        List<Document> documentTypes = Arrays.asList(new Invoice(), new Order(), new Payment(), new Waybill());

        for (Document document : documentTypes) {
            System.out.println("Создание документа [" + document.getDocumentType() + "]");

            try {
                Document filledDocument = fillDocumentData(document);
                documents.add(filledDocument);
                manager.save(filledDocument);
                displayDocument(filledDocument);
                System.out.println();
            } catch (Exception e) {
                System.out.println("Ошибка при заполнении данных документа: " + e.getMessage());
            }
        }

        System.out.println("Все документы успешно созданы.\n");
    }

    private Document fillDocumentData(Document document) throws IllegalAccessException, ParseException {
        Field[] fields = document.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("documentType")) continue;

            if (field.getType() == Date.class) {
                field.set(document, new Date());  // Установка текущей даты
            } else if (field.getType() == List.class && field.getName().equals("products")) {
                field.set(document, fillProductList());
            } else {
                System.out.print("Введите " + field.getName() + ": ");
                String input = scanner.nextLine();

                if (field.getType() == int.class) {
                    field.set(document, Integer.parseInt(input));
                } else if (field.getType() == double.class) {
                    field.set(document, Double.parseDouble(input));
                } else if (field.getType() == String.class) {
                    field.set(document, input);
                }
            }
        }

        return document;
    }

    private List<Product> fillProductList() {
        List<Product> products = new ArrayList<>();
        System.out.println("Ввод данных для продуктов (введите 'exit' для завершения):");

        while (true) {
            System.out.print("ID продукта: ");
            String idInput = scanner.nextLine();
            if (idInput.equalsIgnoreCase("exit")) break;

            System.out.print("Foreign ID продукта: ");
            String foreignIdInput = scanner.nextLine();
            if (foreignIdInput.equalsIgnoreCase("exit")) break;

            System.out.print("Имя продукта: ");
            String nameInput = scanner.nextLine();
            if (nameInput.equalsIgnoreCase("exit")) break;

            System.out.print("Цена продукта: ");
            String priceInput = scanner.nextLine();
            if (priceInput.equalsIgnoreCase("exit")) break;

            try {
                int id = Integer.parseInt(idInput);
                int foreignId = Integer.parseInt(foreignIdInput);
                String name = nameInput;
                double price = Double.parseDouble(priceInput);
                products.add(new Product(id, foreignId, name, price));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода данных: " + e.getMessage());
            }
        }
        return products;
    }

    public void displayDocument(int id) {
        List<Document> documents = manager.load(id);
        if (documents.isEmpty()) {
            System.out.println("Документы с данным ID не найдены.");
            return;
        }
        for (Document document : documents) {
            displayDocument(document);
            System.out.println();
        }
    }

    private void displayDocument(Document document) {
        Field[] fields = document.getClass().getDeclaredFields();

        System.out.println("Документ [" + document.getDocumentType() + "]");
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.print(field.getName() + ": " + field.get(document) + ", ");
            } catch (IllegalAccessException e) {
                System.out.println("Ошибка при отображении поля: " + e.getMessage());
            }
        }
        System.out.println();
    }

    public void updateDocuments() {
        while (true) {
            System.out.print("Введите ID документа для обновления или 'exit' для выхода: ");
            String idInput = scanner.nextLine();

            if (idInput.equalsIgnoreCase("exit")) {
                break;
            }

            int id;
            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ID. Попробуйте снова.");
                continue;
            }

            List<Document> documents = manager.load(id);

            if (documents.isEmpty()) {
                System.out.println("Документы с данным ID не найдены.");
                continue;
            }

            System.out.println("Доступные документы:");
            for (int i = 0; i < documents.size(); i++) {
                System.out.println((i + 1) + ". " + documents.get(i).getDocumentType());
            }

            System.out.print("Введите номер документа для обновления: ");
            int docIndex;
            try {
                docIndex = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный номер. Попробуйте снова.");
                continue;
            }

            if (docIndex < 0 || docIndex >= documents.size()) {
                System.out.println("Некорректный номер. Попробуйте снова.");
                continue;
            }

            try {
                Document documentToUpdate = documents.get(docIndex);
                Document updatedDocument = fillDocumentData(documentToUpdate);
                manager.save(updatedDocument);
                displayDocument(updatedDocument);
                System.out.println("Документ успешно обновлен.\n");
            } catch (Exception e) {
                System.out.println("Ошибка при обновлении документа: " + e.getMessage());
            }
        }
    }

    public void displayAllDocuments() {
        System.out.print("Введите ID документа для отображения: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // обработка новой строки после ввода числа
        displayDocument(id);
    }
}
