package com.studentapp.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.studentapp.dao.StudentDAO;
import com.studentapp.dao.StudentDAOImpl;
import com.studentapp.model.Student;

public class SearchByNamePage extends JFrame {

    public SearchByNamePage() {

        setTitle("Search Student by Name");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel lbl = new JLabel("Enter Name:");
        JTextField txtName = new JTextField();
        JButton btnSearch = new JButton("Search");

        panel.add(lbl);
        panel.add(txtName);
        panel.add(btnSearch);

        add(panel, BorderLayout.NORTH);

        JTextArea result = new JTextArea();
        result.setEditable(false);
        add(new JScrollPane(result), BorderLayout.CENTER);

        // IMPORTANT FIX:
        StudentDAO dao = new StudentDAOImpl();  // ✔ Correct

        btnSearch.addActionListener(e -> {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a name.");
                return;
            }

            // IMPORTANT FIX:
            List<Student> list = dao.searchStudentsByName(name);  // ✔ Correct function name

            result.setText("");

            if (list.isEmpty()) {
                result.setText("No student found.");
            } else {
                for (Student s : list) {
                    result.append(
                        "ID: " + s.getId() +
                        ", Name: " + s.getName() +
                        ", Age: " + s.getAge() +
                        ", Dept: " + s.getDepartment() +
                        ", Marks: " + s.getMarks() + "\n"
                    );
                }
            }
        });

        setVisible(true);
    }
}
