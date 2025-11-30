/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.EmployeeRepository;
import model.EmployeeTree;
import view.ConsoleView;


/**
 *
 * @author Acer
 */



public class AppController {

    private final ConsoleView view;
    private final EmployeeRepository repository;
    private final EmployeeTree employeeTree;

    public AppController(ConsoleView view, EmployeeRepository repository, EmployeeTree employeeTree) {
        this.view = view;
        this.repository = repository;
        this.employeeTree = employeeTree;
    }

    public void run() {
        boolean running = true;

        while (running) {
            view.showMenu();
            MenuOption option = view.readMenuOption();

            if (option == null) {
                view.showMessage("Invalid option. Please try again.");
                continue;
            }

            switch (option) {
                case SORT -> handleSort();
                case SEARCH -> handleSearch();
                case ADD_RECORDS -> handleAddRecords();
                case BINARY_TREE -> handleBinaryTree();
                case EXIT -> {
                    view.showMessage("Exiting application. Goodbye!");
                    running = false;
                }
            }
        }
    }

    private void handleSort() {
        view.showMessage("SORT selected (to be implemented).");
        // aqui depois vamos chamar repository.sortEmployeesByName();
        // e mostrar os primeiros 20
    }

    private void handleSearch() {
        view.showMessage("SEARCH selected (to be implemented).");
    }

    private void handleAddRecords() {
        view.showMessage("ADD RECORDS selected (to be implemented).");
    }

    private void handleBinaryTree() {
        view.showMessage("BINARY TREE selected (to be implemented).");
    }
}
