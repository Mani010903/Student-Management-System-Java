package com.studentapp.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.studentapp.dao.StudentDAO;

import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import com.studentapp.service.StudentServiceImpl;

public class ViewStudentsPage extends JFrame {

    private StudentService service = new StudentServiceImpl();
    private JTable table;
    private DefaultTableModel model;

    public ViewStudentsPage() {
        setTitle("Students List");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table Columns
        String[] columns = {"ID", "Name", "Age", "Department", "Marks"};

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        // Auto resize columns
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Refresh button
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> loadStudents());
        add(btnRefresh, BorderLayout.SOUTH);

        loadStudents();

        setVisible(true);
    }

    // Load students from service
    private void loadStudents() {
        model.setRowCount(0); // clear table

        List<Student> list = service.getAllStudents();
        for (Student s : list) {
            model.addRow(new Object[]{
                    s.getId(),
                    s.getName(),
                    s.getAge(),
                    s.getDepartment(),
                    s.getMarks()
            });
        }
    }
}
