/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Acer
 */
package ie.cct.ca2.view;

import ie.cct.ca2.controller.MenuOption;
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

        return switch (choice) {
            case 1 -> MenuOption.SORT;
            case 2 -> MenuOption.SEARCH;
            case 3 -> MenuOption.ADD_RECORDS;
            case 4 -> MenuOption.BINARY_TREE;
            case 5 -> MenuOption.EXIT;
            default -> null;
        };
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
