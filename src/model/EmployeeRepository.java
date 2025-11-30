package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        // opcional: podemos já chamar loadFromFile aqui
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    /**
     * Lê o ficheiro CSV Applicants_Form.txt
     * e cria Employee a partir de First name, Last name, Position/Job title e Department.
     */
    public void loadFromFile(String filename) {
        employees.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // lê o cabeçalho e ignora

            while ((line = br.readLine()) != null) {
                // cada linha é: First name,Last name,Gender,Email,Salary,Department,Position,Job title,Company
                String[] parts = line.split(",");

                if (parts.length < 6) {
                    continue; // linha inválida
                }

                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String fullName = firstName + " " + lastName;

                String department = parts[5].trim(); // Department
                String managerType = "";

                // Se tiver coluna Position ou Job title, usamos como "managerType"
                if (parts.length > 6 && !parts[6].trim().isEmpty()) {
                    managerType = parts[6].trim();
                } else if (parts.length > 7 && !parts[7].trim().isEmpty()) {
                    managerType = parts[7].trim();
                } else {
                    managerType = "Staff";
                }

                Employee e = new Employee(fullName, managerType, department);
                employees.add(e);
            }

            System.out.println("File read successfully. Total records: " + employees.size());

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
