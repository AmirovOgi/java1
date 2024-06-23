package View;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private final Console console = new Console();

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Главное Меню:");
            System.out.println("1. Создать новые документы");
            System.out.println("2. Показать документ по ID");
            System.out.println("3. Обновить существующие документы");
            System.out.println("4. Показать все документы");
            System.out.println("5. Выход");
            System.out.print("Пожалуйста, выберите действие: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // обработка новой строки после ввода числа

            switch (option) {
                case 1:
                    console.create();
                    break;
                case 2:
                    System.out.print("Введите ID документа для отображения: ");
                    int documentId = scanner.nextInt();
                    scanner.nextLine();  // обработка новой строки после ввода числа
                    console.displayDocument(documentId);
                    break;
                case 3:
                    console.updateDocuments();
                    break;
                case 4:
                    console.displayAllDocuments();
                    break;
                case 5:
                    System.out.println("Завершение работы программы.");
                    running = false;
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.showMenu();
    }
}
