package StudentManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

// Class to represent a Student
class Student {
    String name;
    String rollNumber;
    String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade;
    }
}

// Main GUI for the Student Management System
public class StudentManagementSystem {
    private JFrame frame;
    private JTextField nameField, rollField, gradeField, searchField;
    private JTextArea displayArea;
    private Map<String, Student> studentMap = new HashMap<>();

    public StudentManagementSystem() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new FlowLayout());

        // GUI components
        nameField = new JTextField(15);
        rollField = new JTextField(15);
        gradeField = new JTextField(15);
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All");
        searchField = new JTextField(15);
        displayArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Action listeners
        addButton.addActionListener(e -> addStudent());
        removeButton.addActionListener(e -> removeStudent());
        searchButton.addActionListener(e -> searchStudent());
        displayButton.addActionListener(e -> displayAllStudents());

        // Adding components to frame
        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Roll Number:"));
        frame.add(rollField);
        frame.add(new JLabel("Grade:"));
        frame.add(gradeField);
        frame.add(addButton);
        frame.add(removeButton);
        frame.add(new JLabel("Search by Roll Number:"));
        frame.add(searchField);
        frame.add(searchButton);
        frame.add(displayButton);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String grade = gradeField.getText().trim();

        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill out all fields!");
            return;
        }

        Student student = new Student(name, roll, grade);
        studentMap.put(roll, student);
        displayArea.append("Added: " + student + "\n");
    }

    private void removeStudent() {
        String roll = searchField.getText().trim();
        if (roll.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Enter a Roll Number to remove!");
            return;
        }

        Student removedStudent = studentMap.remove(roll);
        if (removedStudent != null) {
            displayArea.append("Removed: " + removedStudent + "\n");
        } else {
            JOptionPane.showMessageDialog(frame, "No student found with Roll Number: " + roll);
        }
    }

    private void searchStudent() {
        String roll = searchField.getText().trim();
        if (roll.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Enter a Roll Number to search!");
            return;
        }

        Student student = studentMap.get(roll);
        if (student != null) {
            displayArea.append("Found: " + student + "\n");
        } else {
            JOptionPane.showMessageDialog(frame, "No student found with Roll Number: " + roll);
        }
    }

    private void displayAllStudents() {
        displayArea.append("--- All Students ---\n");
        for (Student student : studentMap.values()) {
            displayArea.append(student + "\n");
        }
        displayArea.append("--------------------\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSystem();
        });
    }
}
