package com.studentapp.service;

import java.util.ArrayList;
import java.util.List;

import com.studentapp.dao.StudentDAO;
import com.studentapp.dao.StudentDAOImpl;
import com.studentapp.model.Student;

public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO = new StudentDAOImpl();
    private static int nextId = 1; // auto-increment ID tracker

    // Validate student details
    private boolean isValid(Student s) {
        if (s.getName() == null || s.getName().trim().length() < 2 || !s.getName().matches("[a-zA-Z ]+")) {
            return false;
        }
        if (s.getAge() < 5 || s.getAge() > 100) {
            return false;
        }
     // Department validation (same rule as InputUtil)
        if (s.getDepartment() == null || !s.getDepartment().matches("[a-zA-Z ]{2,30}")) {
            return false;
        }

        if (s.getMarks() < 0 || s.getMarks() > 100) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addStudent(Student student) {
        if (!isValid(student)) {
            System.out.println("✘ Invalid Data! Please enter correct details.");
            return false;
        }
        // Set auto-increment ID
        student.setId(nextId++);
        return studentDAO.addStudent(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    public boolean updateStudent(Student student) {
        if (!isValid(student)) {
            System.out.println("✘ Invalid Data! Update failed.");
            return false;
        }
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentDAO.deleteStudent(id);
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return studentDAO.searchStudentsByName(name);
    }

    @Override
    public List<Student> searchStudentscourse(String course) {
        return studentDAO.searchStudentscourse(course);
    }

    // Optional: reset ID tracker (useful for testing or reloading data)
    public static void resetIdCounter(List<Student> existingStudents) {
        int maxId = 0;
        for (Student s : existingStudents) {
            if (s.getId() > maxId) maxId = s.getId();
        }
        nextId = maxId + 1;
    }
}
