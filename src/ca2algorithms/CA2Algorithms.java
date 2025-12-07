/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package ca2algorithms;

import controller.AppController;
import model.EmployeeRepository;
import model.EmployeeTree;
import view.ConsoleView;

public class CA2Algorithms {

    public static void main(String[] args) {

        // Create the console view so the user can see the menu and type input
        ConsoleView view = new ConsoleView();

        // Create the repository that will load and store all Employee objects
        EmployeeRepository repository = new EmployeeRepository();

        // Create the EmployeeTree that will be used to build the hierarchy
        EmployeeTree tree = new EmployeeTree();

        // Load the employee data from the CSV file before starting the app
        boolean loaded = repository.loadFromFile("Applicants_Form.txt");
        if (!loaded) {
            // If the data cannot be loaded, there is no point in running the system
            System.out.println("Could not load data. Exiting application.");
            return;
        }

        // Create the controller to connect view, repository and tree
        AppController controller = new AppController(view, repository, tree);

        // Start the main loop so the user can use the menu and trigger the algorithms
        controller.run();
    }
}
