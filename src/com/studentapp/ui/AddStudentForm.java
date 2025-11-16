package com.studentapp.ui;

import javax.swing.*;
import java.awt.*;
import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import com.studentapp.service.StudentServiceImpl;

public class AddStudentForm extends JFrame {

    private JTextField txtName, txtAge, txtDept, txtMarks;
    private StudentService service = new StudentServiceImpl();

    public AddStudentForm() {
        setTitle("Add Student");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Labels and fields
        add(new JLabel("Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Age:"));
        txtAge = new JTextField();
        add(txtAge);

        add(new JLabel("Department:"));
        txtDept = new JTextField();
        add(txtDept);

        add(new JLabel("Marks:"));
        txtMarks = new JTextField();
        add(txtMarks);

        JButton btnSave = new JButton("Save");
        add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        add(btnCancel);

        // Save button action
        btnSave.addActionListener(e -> saveStudent());

        // Close action
        btnCancel.addActionListener(e -> this.dispose());

        setVisible(true);
    }

    private void saveStudent() {
        try {
            String name = txtName.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String dept = txtDept.getText().trim();
            int marks = Integer.parseInt(txtMarks.getText().trim());

            Student s = new Student(0, name, age, dept, marks);

            if (service.addStudent(s)) {
                JOptionPane.showMessageDialog(this, "Student Added Successfully!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Data!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid details!");
        }
    }
}
