
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class Employee {

    // Store the full name of the employee (first name + last name)
    private String name;

    // Store the manager type / position (for example: junior, senior, manager)
    private String managerType;

    // Store the department where the employee works
    private String department;

    // Create a new Employee with the basic fields used in this assignment
    public Employee(String name, String managerType, String department) {
        this.name = name;
        this.managerType = managerType;
        this.department = department;
    }

    // Return the full name so it can be used in sorting and searching
    public String getName() {
        return name;
    }

    // Return the manager type so it can be displayed and validated
    public String getManagerType() {
        return managerType;
    }

    // Return the department so it can be displayed and validated
    public String getDepartment() {
        return department;
    }

    // Simple text representation used when printing employees to the console
    @Override
    public String toString() {
        return name + " - " + managerType + " - " + department;
    }
}
