package view;

import controller.MenuOption;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1 - SORT");
        System.out.println("2 - SEARCH");
        System.out.println("3 - ADD RECORDS");
        System.out.println("4 - BINARY TREE (Hierarchy)");
        System.out.println("5 - EXIT");
        System.out.print("Your choice: ");
    }

    public MenuOption readMenuOption() {
        String line = scanner.nextLine().trim();
        int choice;

        try {
            choice = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return null;
        }

        switch (choice) {
            case 1:
                return MenuOption.SORT;
            case 2:
                return MenuOption.SEARCH;
            case 3:
                return MenuOption.ADD_RECORDS;
            case 4:
                return MenuOption.BINARY_TREE;
            case 5:
                return MenuOption.EXIT;
            default:
                return null;
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
