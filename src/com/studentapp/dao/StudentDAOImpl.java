package com.studentapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.studentapp.model.Student;
import com.studentapp.util.DatabaseConnection;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean addStudent(Student student) {
        String query = "INSERT INTO students(name, age, course, marks) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, student.getName());
            pst.setInt(2, student.getAge());
            pst.setString(3, student.getDepartment());
            pst.setInt(4, student.getMarks());

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setDepartment(rs.getString("course"));
                s.setMarks(rs.getInt("marks"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student getStudentById(int id) {
        String query = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setDepartment(rs.getString("course"));
                s.setMarks(rs.getInt("marks"));
                return s;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateStudent(Student student) {
    	String query = "UPDATE students SET name=?, course=?, marks=?, age=? WHERE id=?";
    	

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

        	pst.setString(1, student.getName());
            pst.setString(2, student.getDepartment());
            pst.setInt(3, student.getMarks());
            pst.setInt(4, student.getAge());
            pst.setInt(5, student.getId());
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Student> searchStudentsByName(String name) {
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM students WHERE LOWER(name) LIKE ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, "%" + name.toLowerCase() + "%"); // partial & case-insensitive
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setDepartment(rs.getString("course"));
                s.setMarks(rs.getInt("marks"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Student> searchStudentscourse(String course) {
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM students WHERE LOWER(course) LIKE ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, "%" + course.toLowerCase() + "%"); // partial & case-insensitive
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setDepartment(rs.getString("course"));
                s.setMarks(rs.getInt("marks"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

	@Override
	public List<Student> getAllStudents(String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
