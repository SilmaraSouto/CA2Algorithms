package controller;

import model.EmployeeRepository;
import model.EmployeeTree;
import view.ConsoleView;

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
                case SORT:
                    handleSort();
                    break;

                case SEARCH:
                    handleSearch();
                    break;

                case ADD_RECORDS:
                    handleAddRecords();
                    break;

                case BINARY_TREE:
                    handleBinaryTree();
                    break;

                case EXIT:
                    view.showMessage("Exiting application. Goodbye!");
                    running = false;
                    break;

                default:
                    view.showMessage("Unknown option.");
            }
        }
    }

    private void handleSort() {
        view.showMessage("SORT selected (to be implemented).");
        // Example (later):
        // repository.loadFromFile("Applicants_Form.txt");
        // repository.sortEmployeesByNameRecursively();
        // repository.printFirst20Employees();
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
