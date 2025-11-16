package com.studentapp.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import com.studentapp.service.StudentServiceImpl;

public class SearchStudentsPage extends JFrame {

    private StudentService service = new StudentServiceImpl();
    private JTextField txtSearch;
    private JComboBox<String> searchType;
    private JTable table;
    private DefaultTableModel model;

    public SearchStudentsPage() {

        setTitle("Search Students");
        setSize(700, 430);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());

        searchType = new JComboBox<>(new String[]{"Search by ID", "Search by Name", "Search by Department"});
        txtSearch = new JTextField(15);

        JButton btnSearch = new JButton("Search");

        top.add(searchType);
        top.add(txtSearch);
        top.add(btnSearch);

        add(top, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Dept", "Marks"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Event
        btnSearch.addActionListener(e -> searchStudents());

        setVisible(true);
    }

    private void searchStudents() {
        model.setRowCount(0); // clear table

        String query = txtSearch.getText().trim();
        int type = searchType.getSelectedIndex();

        List<Student> result = null;

        try {
            switch (type) {
                case 0: // ID
                    int id = Integer.parseInt(query);
                    Student s = service.getStudentById(id);
                    if (s != null) model.addRow(new Object[]{s.getId(), s.getName(), s.getAge(), s.getDepartment(), s.getMarks()});
                    break;

                case 1: // Name
                    result = service.searchStudentsByName(query);
                    break;

                case 2: // Department
                    result = service.searchStudentscourse(query);
                    break;
            }

            if (result != null) {
                for (Student st : result) {
                    model.addRow(new Object[]{st.getId(), st.getName(), st.getAge(), st.getDepartment(), st.getMarks()});
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
