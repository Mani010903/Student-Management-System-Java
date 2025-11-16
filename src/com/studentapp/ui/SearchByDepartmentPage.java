package com.studentapp.ui;

import javax.swing.*;
import java.util.List;
import com.studentapp.dao.StudentDAO;
import com.studentapp.model.Student;
import com.studentapp.dao.StudentDAOImpl;

public class SearchByDepartmentPage extends JFrame {

    public SearchByDepartmentPage() {
        setTitle("Search By Department");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblDept = new JLabel("Enter Department:");
        lblDept.setBounds(20, 20, 150, 30);
        add(lblDept);

        JTextField txtDept = new JTextField();
        txtDept.setBounds(150, 20, 150, 30);
        add(txtDept);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(100, 70, 120, 30);
        add(btnSearch);

        JTextArea result = new JTextArea();
        result.setBounds(20, 110, 300, 100);
        add(result);

        btnSearch.addActionListener(e -> {
            String dept = txtDept.getText();
            StudentDAO dao = new StudentDAOImpl();
            List<Student> list = dao.searchStudentscourse(dept);

            if (list.isEmpty()) {
                result.setText("No students found.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Student s : list) {
                    sb.append("ID: ").append(s.getId())
                      .append(", Name: ").append(s.getName())
                      .append(", Department: ").append(s.getDepartment())
                      .append(", marks: ").append(s.getMarks())
                      .append("\n");
                }
                result.setText(sb.toString());
            }
        }
        );
       


        setVisible(true);
    }
}
