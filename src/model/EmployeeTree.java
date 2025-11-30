/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author Acer
 */

public class EmployeeTree {

    private static class Node {
        Employee employee;
        Node left;
        Node right;

        Node(Employee employee) {
            this.employee = employee;
        }
    }

    private Node root;

    public void buildTreeFromList(java.util.List<Employee> employees) {
        // depois vamos implementar level-order insertion aqui
    }

    public void printLevelOrder() {
        // depois implementamos: mostrar hierarquia
    }

    public int getHeight() {
        // depois implementamos: altura da árvore
        return 0;
    }

    public int getNodeCount() {
        // depois implementamos: contar nós
        return 0;
    }
}


