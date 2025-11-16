package com.studentapp.ui;

import javax.swing.*;
import com.studentapp.dao.StudentDAO;
import com.studentapp.dao.StudentDAOImpl;


public class DeleteStudentPage extends JFrame {

    public DeleteStudentPage() {
        setTitle("Delete Student");
        setSize(300, 180);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("Enter Student ID:");
        lblId.setBounds(20, 20, 150, 30);
        add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(150, 20, 100, 30);
        add(txtId);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(90, 70, 100, 30);
        add(btnDelete);

        JLabel msg = new JLabel("");
        msg.setBounds(20, 110, 250, 20);
        add(msg);

        btnDelete.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            
            StudentDAO dao = new StudentDAOImpl();
            boolean deleted = dao.deleteStudent(id);

            msg.setText(deleted ? "Student deleted!" : "Student not found!");
        });

        setVisible(true);
    }
}
