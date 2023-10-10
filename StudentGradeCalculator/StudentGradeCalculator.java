package StudentGradeCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeCalculator {

    private JFrame frame;
    private List<JTextField> subjectFields = new ArrayList<>();
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averageLabel;
    private JLabel gradeLabel;

    public StudentGradeCalculator() {
        frame = new JFrame("Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(14, 2));

        String[] subjects = {
                "Programming",
                "Data Structures",
                "Databases",
                "Networks",
                "Operating Systems",
                "Software Engineering",
                "AI",
                "Computer Graphics",
                "Web Development",
                "Algorithms"
        };

        for (String subject : subjects) {
            frame.add(new JLabel(subject + " (out of 100):"));
            JTextField field = new JTextField();
            subjectFields.add(field);
            frame.add(field);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });

        totalMarksLabel = new JLabel("Total Marks: ");
        averageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        frame.add(calculateButton);
        frame.add(new JLabel(""));  // empty cell
        frame.add(totalMarksLabel);
        frame.add(averageLabel);
        frame.add(gradeLabel);

        frame.setVisible(true);
    }

    private void calculateGrade() {
        int totalMarks = 0;
        int totalSubjects = subjectFields.size();

        for (JTextField field : subjectFields) {
            try {
                int mark = Integer.parseInt(field.getText());
                if (mark < 0 || mark > 100) {
                    JOptionPane.showMessageDialog(frame, "Please enter marks between 0 and 100.");
                    return;
                }
                totalMarks += mark;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid marks for all subjects.");
                return;
            }
        }

        double average = (double) totalMarks / totalSubjects;

        String grade;
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averageLabel.setText("Average Percentage: " + String.format("%.2f", average) + "%");
        gradeLabel.setText("Grade: " + grade);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradeCalculator();
        });
    }
}
