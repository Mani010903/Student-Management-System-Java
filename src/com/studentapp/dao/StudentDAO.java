package com.studentapp.dao;

import java.util.List;
import com.studentapp.model.Student;

public interface StudentDAO {

    boolean addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(int id);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);
    

	List<Student> searchStudentscourse(String course);

	List<Student> searchStudentsByName(String name);
	
	    List<Student> getAllStudents(String sortBy);


	}



