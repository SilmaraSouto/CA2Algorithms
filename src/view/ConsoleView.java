/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package view;

import controller.MenuOption;
import java.util.Scanner;

/**
 * ConsoleView (View layer in MVC)
 *
 * This class is responsible for all console input and output.
 * It reads the user's menu choice and basic text input, and prints
 * messages back to the user. By isolating these responsibilities here,
 * the Controller and Model remain independent of any specific UI.
 */
public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the main menu options to the user.
     * The numeric options correspond directly to MenuOption values.
     */
    public void showMenu() {
        System.out.println("\n===== EMPLOYEE MANAGEMENT MENU =====");
        System.out.println("1 - SORT employees (recursive) and show first 20");
        System.out.println("2 - SEARCH employee by name");
        System.out.println("3 - ADD new employee record");
        System.out.println("4 - BUILD and SHOW employee BINARY TREE");
        System.out.println("5 - EXIT");
        System.out.print("Your choice: ");
    }

    /**
     * Reads the user's menu choice from the console and maps it to a MenuOption enum.
     * If the input is invalid (non-integer or out of range), this method returns null,
     * allowing the Controller to handle the error gracefully.
     */
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

    /**
     * Prints a generic message to the console.
     * The Controller uses this method to communicate results and feedback.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompts the user with a message and returns the line entered.
     * Used for reading names, manager types, departments, etc.
     */
    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
