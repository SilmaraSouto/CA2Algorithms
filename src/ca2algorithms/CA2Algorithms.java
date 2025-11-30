/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2algorithms;
import controller.AppController;
import model.EmployeeRepository;
import model.EmployeeTree;
import view.ConsoleView;

/**
 *
 * @author Acer
 */
public class CA2Algorithms {

  




    public static void main(String[] args) {

        ConsoleView view = new ConsoleView();
        EmployeeRepository repository = new EmployeeRepository();
        EmployeeTree employeeTree = new EmployeeTree();

        AppController controller = new AppController(view, repository, employeeTree);
        controller.run();
    }
}


