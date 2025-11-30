/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class EmployeeRepository {
  

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        // depois vamos carregar do ficheiro Applicants_Form.txt
        // e talvez adicionar alguns dados iniciais
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    // aqui depois vamos colocar:
    // - método para carregar do ficheiro
    // - método para ordenar recursivamente (merge sort)
    // - método para pesquisar (binary search)
}


