package StudentCourseRegistrationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Class to represent a Course
class Course {
    String code;
    String title;
    String description;
    int capacity;
    int registered;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.registered = 0;
        this.schedule = schedule;
    }

    // Check if there's available capacity to register
    public boolean canRegister() {
        return registered < capacity;
    }

    // Register a student to the course
    public void registerStudent() {
        registered++;
    }

    // Unregister a student from the course
    public void unregisterStudent() {
        if (registered > 0) registered--;
    }
}

// Class to represent a Student
class Student {
    String studentID;
    String name;
    List<Course> registeredCourses = new ArrayList<>();

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }
}

public class StudentCourseRegistrationSystem {
    private JFrame frame;
    private JComboBox<String> coursesDropDown;
    private JButton registerButton;
    private JButton unregisterButton;
    private JTextArea courseDetailsArea;
    private JLabel studentInfoLabel;

    private Map<String, Course> courseDatabase = new HashMap<>();
    private Map<String, Student> studentDatabase = new HashMap<>();
    private Student loggedInStudent; // Mocking a logged in student for this demo

    public StudentCourseRegistrationSystem() {
        frame = new JFrame("Student Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout());

        // Sample courses for the course database
        courseDatabase.put("CS101", new Course("CS101", "Intro to CS", "Basic concepts of CS", 30, "Mon-Wed 9am-11am"));
        courseDatabase.put("CS102", new Course("CS102", "Advanced CS", "Advanced concepts of CS", 25, "Tue-Thu 1pm-3pm"));

        // Sample student for the student database and mock login
        Student student = new Student("12345", "John Doe");
        studentDatabase.put(student.studentID, student);
        loggedInStudent = student; // Assuming John Doe is logged in

        coursesDropDown = new JComboBox<>(courseDatabase.keySet().toArray(new String[0]));
        registerButton = new JButton("Register");
        unregisterButton = new JButton("Unregister");
        courseDetailsArea = new JTextArea(10, 40);
        studentInfoLabel = new JLabel("Logged in as: " + loggedInStudent.name);

        coursesDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCourseDetails((String) coursesDropDown.getSelectedItem());
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerCourse((String) coursesDropDown.getSelectedItem());
            }
        });

        unregisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unregisterCourse((String) coursesDropDown.getSelectedItem());
            }
        });

        frame.add(studentInfoLabel);
        frame.add(coursesDropDown);
        frame.add(registerButton);
        frame.add(unregisterButton);
        frame.add(new JScrollPane(courseDetailsArea));

        frame.setVisible(true);
    }

    private void displayCourseDetails(String courseCode) {
        Course course = courseDatabase.get(courseCode);
        String details = "Title: " + course.title + "\nDescription: " + course.description
                + "\nSchedule: " + course.schedule + "\nAvailable Slots: " + (course.capacity - course.registered);
        courseDetailsArea.setText(details);
    }

    private void registerCourse(String courseCode) {
        Course course = courseDatabase.get(courseCode);
        if (course.canRegister()) {
            course.registerStudent();
            loggedInStudent.registeredCourses.add(course);
            displayCourseDetails(courseCode); // Update the display to show the new available slots
            JOptionPane.showMessageDialog(frame, "Successfully registered for " + course.title);
        } else {
            JOptionPane.showMessageDialog(frame, "Course is full!");
        }
    }

    private void unregisterCourse(String courseCode) {
        Course course = courseDatabase.get(courseCode);
        if (loggedInStudent.registeredCourses.contains(course)) {
            course.unregisterStudent();
            loggedInStudent.registeredCourses.remove(course);
            displayCourseDetails(courseCode); // Update the display to show the new available slots
            JOptionPane.showMessageDialog(frame, "Successfully unregistered from " + course.title);
        } else {
            JOptionPane.showMessageDialog(frame, "You are not registered for this course!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentCourseRegistrationSystem();
        });
    }
}
