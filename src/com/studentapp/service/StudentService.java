package com.studentapp.service;

import java.util.List;
import com.studentapp.model.Student;

public interface StudentService {

    boolean addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(int id);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

	List<Student> searchStudentsByName(String name);

	List<Student> searchStudentscourse(String course);

	
}
