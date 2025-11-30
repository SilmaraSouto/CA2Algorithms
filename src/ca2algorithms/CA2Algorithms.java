/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2algorithms;
import controller.AppController;
import model.Employee;
import model.EmployeeRepository;
import model.EmployeeTree;
import view.ConsoleView;

/**
 *
 * @author Acer
 */
public class CA2Algorithms {

  




    public static void main(String[] args) {

        
        // dentro do main, depois de criar o repository:
EmployeeRepository repository = new EmployeeRepository();
repository.loadFromFile("Applicants_Form.txt");

for (Employee e : repository.getEmployees()) {
    System.out.println(e);
}

    }
}
