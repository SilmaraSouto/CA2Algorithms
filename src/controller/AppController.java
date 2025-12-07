
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




package controller;

import model.Employee;
import model.EmployeeRepository;
import model.EmployeeTree;
import view.ConsoleView;

/**
 * AppController (Controller layer in MVC)
 *
 * This class coordinates user interactions (via ConsoleView) with the domain logic
 * (EmployeeRepository and EmployeeTree). By concentrating orchestration here,
 * the Model stays focused on data/algorithms and the View on I/O, which demonstrates
 * a clean MVC separation as requested in the brief.
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
        view.showMessage("\nSORT selected (recursive merge sort by name).");

        repository.sortEmployeesByNameRecursively();

        view.showMessage("First 20 employees after sorting:\n");
        repository.printFirst20Employees();
    }

    
    private void handleSearch() {
        view.showMessage("\nSEARCH selected (binary search by name).");

        // We make sure the list is sorted before calling binary search.
        // This keeps the behaviour correct even if the user added new records.
        repository.sortEmployeesByNameRecursively();

       String name = view.readLine("Enter the employee full name to search: ").trim();

        Employee found = repository.findByNameBinarySearch(name);

        if (found != null) {
            view.showMessage("Employee found:");
            view.showMessage(found.toString());
        } else {
            view.showMessage("No employee found with that name.");
            
        }
    }


    /**
     * ADD RECORDS:
     * - Validates name (non-empty, minimum length).
     * - Shows existing manager types and departments from the dataset.
     * - Only accepts values that already exist in the data.
     */
    private void handleAddRecords() {
    view.showMessage("\nADD RECORDS selected.");

    // Validate the name (simple but avoids obvious mistakes)
    String firstName;
    String lastName;
    String name;

    while (true) {
        firstName = view.readLine("Enter first name: ").trim();
        lastName  = view.readLine("Enter last name: ").trim();
        name = (firstName + " " + lastName).trim();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            view.showMessage("First name and last name cannot be empty. Please enter valid names.");
            continue;
        }
        if (firstName.length() < 2 || lastName.length() < 2) {
            view.showMessage("Names are too short. Please enter at least 2 characters for each name.");
            continue;
        }
        if (name.length() < 3) {
            view.showMessage("Full name is too short. Please enter a longer full name.");
            continue;
        }
        break;
    }

    // Show existing manager types to guide the user
    view.showMessage("Existing manager types in the data:");
    for (String mt : repository.getAllManagerTypes()) {
        view.showMessage(" - " + mt);
    }

    String managerType;
    while (true) {
        managerType = view.readLine("Enter manager type / position (must match one of the above): ").trim();
        if (repository.isValidManagerType(managerType)) {
            break;
        }
        view.showMessage("Invalid manager type. Please choose one of the listed values above.");
    }

    // Show existing departments to guide the user
    view.showMessage("Existing departments in the data:");
    for (String dep : repository.getAllDepartments()) {
        view.showMessage(" - " + dep);
    }

    String department;
    while (true) {
        department = view.readLine("Enter department (must match one of the above): ").trim();
        if (repository.isValidDepartment(department)) {
            break;
        }
        view.showMessage("Invalid department. Please choose one of the listed values above.");
    }

    Employee e = new Employee(name, managerType, department);
    repository.addUserEmployee(e);

    view.showMessage("New employee added:");
    view.showMessage(e.toString());

    view.showMessage("\nAll newly added records so far:");
    repository.printAddedEmployees();

    repository.sortEmployeesByNameRecursively();
}

    private void handleBinaryTree() {
        view.showMessage("\nBINARY TREE selected.");

        if (repository.getEmployees().isEmpty()) {
            view.showMessage("No employees available to build the tree.");
            return;
        }

        employeeTree.buildTreeFromList(repository.getEmployees());

        view.showMessage("\nEMPLOYEE HIERARCHY (LEVEL ORDER):");
        employeeTree.printLevelOrder();

        view.showMessage("\nIN-ORDER TRAVERSAL (for structure inspection):");
        employeeTree.printInOrder();

        int height = employeeTree.getHeight();
        int count = employeeTree.getNodeCount();

        view.showMessage("\nTree height: " + height);
        view.showMessage("Total nodes in tree: " + count);
    }
}
