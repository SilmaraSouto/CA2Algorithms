/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * EmployeeTree
 *
 * This class represents a simple binary tree used to model
 * an employee hierarchy. Employees are inserted in level-order
 * (breadth-first) so that each node has at most two children.
 */
public class EmployeeTree {

    private static class Node {
        private Employee employee;
        private Node left;
        private Node right;

        public Node(Employee employee) {
            this.employee = employee;
        }

        public Employee getEmployee() {
            return employee;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    private Node root;
    private int nodeCount;

    /**
     * Builds a binary tree using level-order insertion from the given list.
     * The first employee becomes the root, then children are filled left-to-right.
     */
    public void buildTreeFromList(List<Employee> employees) {
        root = null;
        nodeCount = 0;

        if (employees == null || employees.isEmpty()) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        root = new Node(employees.get(0));
        queue.offer(root);
        nodeCount = 1;

        int index = 1;
        while (index < employees.size()) {
            Node current = queue.peek();
            if (current.left == null) {
                current.left = new Node(employees.get(index++));
                queue.offer(current.left);
                nodeCount++;
            } else if (current.right == null) {
                current.right = new Node(employees.get(index++));
                queue.offer(current.right);
                nodeCount++;
                queue.poll(); // move to next parent once both children are filled
            }
        }
    }

    /**
     * Prints the tree in level-order (breadth-first),
     * showing each employee in aligned columns.
     */
    public void printLevelOrder() {
        if (root == null) {
            System.out.println("(Tree is empty)");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        System.out.printf("%-20s | %-20s | %-15s%n", "Name", "Manager Type", "Department");
        System.out.println("----------------------------------------------------------");

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Employee e = current.getEmployee();

            System.out.printf("%-20s | %-20s | %-15s%n",
                    e.getName(),
                    e.getManagerType(),
                    e.getDepartment());

            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
    }

    /**
     * In-order traversal, mainly for checking tree structure.
     */
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        Employee e = node.getEmployee();
        System.out.printf("%-20s | %-20s | %-15s%n",
                e.getName(),
                e.getManagerType(),
                e.getDepartment());
        printInOrder(node.right);
    }

    public int getHeight() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int getNodeCount() {
        return nodeCount;
    }
}
