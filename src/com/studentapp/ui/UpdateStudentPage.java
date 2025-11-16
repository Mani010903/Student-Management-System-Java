package com.studentapp.ui;

import javax.swing.*;
import com.studentapp.dao.StudentDAO;
import com.studentapp.model.Student;
import com.studentapp.dao.StudentDAOImpl;




public class UpdateStudentPage extends JFrame {

    public UpdateStudentPage() {
    	setTitle("Update Student");
    	setSize(400, 420);
    	setLayout(null);
    	setLocationRelativeTo(null);

    	JLabel lblId = new JLabel("Student ID:");
    	lblId.setBounds(20, 20, 120, 30);
    	add(lblId);

    	JTextField txtId = new JTextField();
    	txtId.setBounds(150, 20, 200, 30);
    	add(txtId);

    	JLabel lblName = new JLabel("New Name:");
    	lblName.setBounds(20, 70, 120, 30);
    	add(lblName);

    	JTextField txtName = new JTextField();
    	txtName.setBounds(150, 70, 200, 30);
    	add(txtName);

    	JLabel lblDept = new JLabel("New Dept:");
    	lblDept.setBounds(20, 120, 120, 30);
    	add(lblDept);

    	JTextField txtDept = new JTextField();
    	txtDept.setBounds(150, 120, 200, 30);
    	add(txtDept);

    	JLabel lblAge = new JLabel("New Age:");
    	lblAge.setBounds(20, 170, 120, 30);
    	add(lblAge);

    	JTextField txtAge = new JTextField();
    	txtAge.setBounds(150, 170, 200, 30);
    	add(txtAge);

    	JLabel lblMarks = new JLabel("New Marks:");
    	lblMarks.setBounds(20, 220, 120, 30);
    	add(lblMarks);

    	JTextField txtMarks = new JTextField();
    	txtMarks.setBounds(150, 220, 200, 30);
    	add(txtMarks);

    	JButton btnUpdate = new JButton("Update");
    	btnUpdate.setBounds(140, 270, 120, 35);
    	add(btnUpdate);

    	JLabel msg = new JLabel("");
    	msg.setBounds(20, 320, 350, 30);
    	add(msg);


        btnUpdate.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());

            StudentDAO dao = new StudentDAOImpl();
            Student old = dao.getStudentById(id);

            if (old == null) {
                msg.setText("Student not found.");
                return;
            }

            // If field is empty → use old value
            String newName = txtName.getText().trim().isEmpty() ? old.getName() : txtName.getText();
            String newDept = txtDept.getText().trim().isEmpty() ? old.getDepartment() : txtDept.getText();

            String ageText = txtAge.getText().trim();     // YOU MUST ADD AGE FIELD IN UI
            String marksText = txtMarks.getText().trim(); // YOU MUST ADD MARKS FIELD IN UI

            int newAge = ageText.isEmpty() ? old.getAge() : Integer.parseInt(ageText);
            int newMarks = marksText.isEmpty() ? old.getMarks() : Integer.parseInt(marksText);

            // Create updated student
            Student updatedStudent = new Student(id, newName, newAge, newDept, newMarks);

            boolean updated = dao.updateStudent(updatedStudent);

            if (updated) msg.setText("Student updated successfully!");
            else msg.setText("Update failed.");
        });

        setVisible(true);
    }
}
