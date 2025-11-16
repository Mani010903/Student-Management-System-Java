package com.studentapp.ui;

import javax.swing.*;
import com.studentapp.dao.StudentDAO;
import com.studentapp.model.Student;
import com.studentapp.dao.StudentDAOImpl;



public class SearchByIdPage extends JFrame {

    public SearchByIdPage() {
        setTitle("Search Student By ID");
        setSize(350, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("Enter Student ID:");
        lblId.setBounds(20, 20, 120, 30);
        add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(150, 20, 150, 30);
        add(txtId);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(100, 70, 120, 30);
        add(btnSearch);

        JTextArea result = new JTextArea();
        result.setBounds(20, 110, 300, 40);
        result.setEditable(false);
        add(result);

        btnSearch.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            StudentDAO dao = new StudentDAOImpl();
            Student s = dao.getStudentById(id);

            if (s != null) {
                result.setText("Name: " + s.getName() + ", Dept: " + s.getDepartment()+ ", age: " + s.getAge()+ ", Marks: " + s.getMarks());
            } else {
                result.setText("No student found!");
            }
        });

        setVisible(true);
    }
}
