
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * EmployeeRepository (Model)
 *
 * This class handles data loading, sorting, searching, and validation logic
 * for employees. It keeps all data-related operations out of the Controller
 * and View, which supports a clear MVC design.
 */
public class EmployeeRepository {

    // Main in-memory list with all employees loaded from the file and added by the user.
    private final List<Employee> employees = new ArrayList<>();

    // We keep track of employees added during this run so we can display them together.
    private final List<Employee> addedEmployees = new ArrayList<>();

    // These sets store all manager types and departments that exist in the data.
    // We use them later to validate user input instead of hard-coding allowed values.
    private final Set<String> managerTypes = new HashSet<>();
    private final Set<String> departments = new HashSet<>();

    public EmployeeRepository() {
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getAddedEmployees() {
        return addedEmployees;
    }

    public boolean isValidManagerType(String managerType) {
        return managerTypes.contains(managerType);
    }

    public boolean isValidDepartment(String department) {
        return departments.contains(department);
    }

    /**
     * This method is only used when the user manually adds a new record.
     * We keep these records in a separate list so we can show "all newly added records"
     * as requested in the assignment brief.
     */
    public void addUserEmployee(Employee e) {
        employees.add(e);
        addedEmployees.add(e);

        // If a new manager type or department appears, we register it as valid as well.
        managerTypes.add(e.getManagerType());
        departments.add(e.getDepartment());
    }

    /**
     * Reads the Applicants_Form file and builds the initial employee list.
     * While doing this, we also collect all valid manager types and departments
     * so we can later validate user input against real values from the dataset.
     */
    public boolean loadFromFile(String filename) {
        employees.clear();
        addedEmployees.clear();
        managerTypes.clear();
        departments.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // skip header line

            while ((line = br.readLine()) != null) {
                // Expected format:
                // First name,Last name,Gender,Email,Salary,Department,Position,Job title,Company
                String[] parts = line.split(",");

                if (parts.length < 6) {
                    // If the line does not have enough columns we simply ignore it.
                    continue;
                }

                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String fullName = firstName + " " + lastName;

                String department = parts[5].trim();
                String managerType;

                // We try to use Position or Job Title as the "manager type".
                if (parts.length > 6 && !parts[6].trim().isEmpty()) {
                    managerType = parts[6].trim();
                } else if (parts.length > 7 && !parts[7].trim().isEmpty()) {
                    managerType = parts[7].trim();
                } else {
                    managerType = "Staff";
                }

                Employee e = new Employee(fullName, managerType, department);
                employees.add(e);

                // We register every department and manager type we encounter as "valid".
                managerTypes.add(managerType);
                departments.add(department);
            }

            System.out.println("File read successfully. Total records: " + employees.size());
            return true;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }

    /**
     * We sort the list of employees by name using a recursive Merge Sort.
     * The main reason for this choice is performance: for large datasets
     * Merge Sort is much more scalable than simple algorithms like Bubble Sort.
     */
    public void sortEmployeesByNameRecursively() {
        if (employees.size() <= 1) {
            return;
        }

        List<Employee> sorted = mergeSort(new ArrayList<>(employees));
        employees.clear();
        employees.addAll(sorted);
    }

    private List<Employee> mergeSort(List<Employee> list) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<Employee> left = new ArrayList<>(list.subList(0, mid));
        List<Employee> right = new ArrayList<>(list.subList(mid, list.size()));

        List<Employee> sortedLeft = mergeSort(left);
        List<Employee> sortedRight = mergeSort(right);

        return merge(sortedLeft, sortedRight);
    }

    private List<Employee> merge(List<Employee> left, List<Employee> right) {
    List<Employee> result = new ArrayList<>();
    int i = 0, j = 0;

    while (i < left.size() && j < right.size()) {
        String leftName = left.get(i).getName();
        String rightName = right.get(j).getName();

        
        if (leftName.compareToIgnoreCase(rightName) <= 0) {
            result.add(left.get(i));
            i++;
        } else {
            result.add(right.get(j));
            j++;
        }
    }

    while (i < left.size()) {
        result.add(left.get(i));
        i++;
    }

    while (j < right.size()) {
        result.add(right.get(j));
        j++;
    }

    return result;
}


    public void printFirst20Employees() {
        int limit = Math.min(20, employees.size());

        System.out.printf("%-20s | %-20s | %-15s%n", "Name", "Manager Type", "Department");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < limit; i++) {
            Employee e = employees.get(i);
            System.out.printf("%-20s | %-20s | %-15s%n",
                    e.getName(), e.getManagerType(), e.getDepartment());
        }
    }

    /**
     * We use Binary Search on the sorted list of employees to locate a name quickly.
     * This is chosen over linear search because it scales much better with large datasets.
     */
    public Employee findByNameBinarySearch(String name) {
        if (employees.isEmpty()) {
            return null;
        }

        int low = 0;
        int high = employees.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Employee midEmp = employees.get(mid);
            int cmp = midEmp.getName().compareToIgnoreCase(name);

            if (cmp == 0) {
                return midEmp;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }
    /**
     * Prints only the employees added during the current session.
     * This directly supports the requirement to display "all newly added records".
     */
    public void printAddedEmployees() {
        if (addedEmployees.isEmpty()) {
            System.out.println("(No user-added records yet.)");
            return;
        }

        System.out.printf("%-20s | %-20s | %-15s%n", "Name", "Manager Type", "Department");
        System.out.println("----------------------------------------------------------");

        for (Employee e : addedEmployees) {
            System.out.printf("%-20s | %-20s | %-15s%n",
                    e.getName(), e.getManagerType(), e.getDepartment());
        }
    }

    /**
     * Returns all distinct manager types discovered in the dataset,
     * sorted alphabetically for clearer console output.
     */
    public Set<String> getAllManagerTypes() {
        return new TreeSet<>(managerTypes);
    }

    /**
     * Returns all distinct departments discovered in the dataset,
     * also sorted alphabetically.
     */
    public Set<String> getAllDepartments() {
        return new TreeSet<>(departments);
    }
}
