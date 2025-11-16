package com.studentapp.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Student Management System");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window

        setLayout(new GridLayout(7, 1, 10, 10));

        // Buttons
        JButton btnAdd = new JButton("Add Student");
        btnAdd.addActionListener(e -> new AddStudentForm());

        JButton btnView = new JButton("View All Students");
        btnView.addActionListener(e -> new ViewStudentsPage());

        JButton btnSearchId = new JButton("Search By ID");
        btnSearchId.addActionListener(e -> new SearchByIdPage());

        JButton btnSearchName = new JButton("Search By Name");
        btnSearchName.addActionListener(e -> new SearchByNamePage());

        JButton btnSearchDept = new JButton("Search By Department");
        btnSearchDept.addActionListener(e -> new SearchByDepartmentPage());

        JButton btnUpdate = new JButton("Update Student");
        btnUpdate.addActionListener(e -> new UpdateStudentPage());

        JButton btnDelete = new JButton("Delete Student");
        btnDelete.addActionListener(e -> new DeleteStudentPage());

        // Add buttons to frame
        add(btnAdd);
        add(btnView);
        add(btnSearchId);
        add(btnSearchName);
        add(btnSearchDept);
        add(btnUpdate);
        add(btnDelete);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
